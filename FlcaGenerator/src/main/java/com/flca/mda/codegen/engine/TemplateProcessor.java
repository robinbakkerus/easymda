package com.flca.mda.codegen.engine;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.Assert;

import com.flca.mda.codegen.helpers.ClassloaderHelper;
import com.flca.mda.codegen.helpers.LogHelper;
import com.flca.mda.codegen.helpers.SaveGeneratedCodeHelper;

import flca.mda.api.util.JetArgument;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.CopyTemplate;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.ITemplateHooks;
import flca.mda.codegen.helpers.AnnotationsHelper;
import flca.mda.codegen.helpers.FilenameHelper;
import flca.mda.codegen.helpers.SourceCodeUtils;
import mda.annotation.DontGenerateCode;

public class TemplateProcessor {

	private static SourceCodeUtils su = new SourceCodeUtils();
	private static FilenameHelper fnh = new FilenameHelper();

	private Collection<String> alreadyCopied = new HashSet<String>();
	private SaveGeneratedCodeHelper saver = null;
	private IProgressMonitor monitor = null;
	private IJavaProject project;
	private ClassLoader loader;

	public TemplateProcessor(IJavaProject aProject, IProgressMonitor aMonitor) {
		this.monitor = aMonitor;
		this.project = aProject;
		this.saver = new SaveGeneratedCodeHelper(aProject, this.monitor);
		this.loader = ClassloaderHelper.getInstance().getClassLoader();
	}

	/**
	 * This may generate the source for the given class and template. It may also
	 * return null, if the template does not apply for the this class. The latter
	 * may very well be the case because this method is called via two loop's over
	 * all selected class-es znd all selected templates.
	 * 
	 * @param aModelClass
	 * @param aTemplate
	 * @return
	 */
	public String generate(Class<?> aModelClass, ITemplate aTemplate, Object[] aArguments, boolean aForceGenerate) {
		LogHelper.debug("> start generate: " + aTemplate.getName() + " " + aModelClass.getName());
		try {
			this.getHooks(aTemplate).doBeforeGenerate(aModelClass, aTemplate);
			GenerateResult result = this.generate(aTemplate, this.project, aModelClass, aArguments, aForceGenerate);
			if (result != null) {
				String gencode = this.getHooks(aTemplate).doAfterGenerate(result.generatedSource, aModelClass,
						aTemplate);
				this.saver.save(aModelClass, gencode, result.template);
				LogHelper.updateLinesGenerated(gencode);
				return gencode;
			} else {
				return null;
			}
		} catch (Throwable e) {
			return this.handleGenerateError(aModelClass, aTemplate, e);
		}
	}

	private GenerateResult generate(ITemplate aTemplate, IJavaProject aProject, Class<?> aModelClass,
			Object[] aArguments, boolean aForce) throws InstantiationException, IllegalAccessException {

		Object elemInstance = null;

		if (aModelClass != null) {
			if (!aModelClass.isInterface() && !aModelClass.isEnum() && !this.isAbstract(aModelClass)) {
				elemInstance = aModelClass.newInstance();
			}

			if (aForce || this.generateThisObject(aTemplate, aModelClass)) {
				return this.doGenerateElement(aTemplate, aModelClass, elemInstance, aArguments);
			} else if (this.copyThisObject(aTemplate, aModelClass)
					&& !this.alreadyCopied.contains(aModelClass.getName())) {
				return this.doCopyElement(aModelClass);
			}
		}
		return null;
	}

	private boolean isAbstract(Class<?> aClass) {
		return Modifier.isAbstract(aClass.getModifiers());
	}
	
	private GenerateResult doGenerateElement(ITemplate aTemplate, Class<?> aModelClass, Object elemInstance,
			Object... aExtraArgs) {
		try {
			ClassLoader loader = ClassloaderHelper.getInstance().getClassLoader();

			Class<?> generatorClass = loader.loadClass(aTemplate.getGeneratorFqn());
			Method method = generatorClass.getMethod("generate", new Class[] { Object.class });
			Object generator = generatorClass.newInstance();

//			Object jetarg = this.makeJetArg(aTemplate, aModelClass, elemInstance);
			JetArgument jetarg = new JetArgumentBuilder(this.loader).makeJetArg(aTemplate, aModelClass, elemInstance);
			Object gensrcObj = method.invoke(generator, new Object[] { this.makeArgs(jetarg, aExtraArgs) });
			String gensrc = (gensrcObj != null) ? gensrcObj.toString() : "";
			GenerateResult result = new GenerateResult(gensrc, aTemplate);
			return result;
		} catch (Exception e) {
			LogHelper.error("error while generating code ", e);
			throw new RuntimeException(e);
		}
	}

	private Object[] makeArgs(Object aJetArgument, Object... aExtraArgs) {
		if (aExtraArgs != null && aExtraArgs.length > 0) {
			Object result[] = new Object[aExtraArgs.length + 1];
			result[0] = aJetArgument;
			for (int i = 0; i < aExtraArgs.length; i++) {
				result[i + 1] = aExtraArgs[i];
			}
			return result;
		} else {
			return new Object[] { aJetArgument };
		}
	}

//	private Object makeJetArg(ITemplate aTemplate, Class<?> aModelClass, Object elemInstance) throws Exception {
//		Object jetarg = null;
//		if (elemInstance != null) {
//			jetarg = this.makeJetArgument(elemInstance, aTemplate, null, false);
//		} else {
//			jetarg = this.makeJetArgument(aModelClass, aTemplate, null, true);
//		}
//		return jetarg;
//	}

	private GenerateResult doCopyElement(Class<?> aModelClass) {
		String src = su.readSource(aModelClass.getName());
		ITemplate copyTemplate = new CopyTemplate();
		this.alreadyCopied.add(aModelClass.getName());
		return new GenerateResult(this.parsePackageStatement(src), copyTemplate);
	}

	private String parsePackageStatement(String aSource) {
		StringBuffer sb = new StringBuffer();
		String lines[] = aSource.split("\n");
		for (String line : lines) {
			if (su.isPackageStatement(line) && line.indexOf(DataStore.getInstance().getBasePackage()) > 0) {
				String s = "package " + fnh.formatPackage(su.getPackageClassname(line)) + ";";
				sb.append(s + "\n");
			} else {
				sb.append(line + "\n");
			}
		}
		return sb.toString();
	}

//	private Object makeJetArgument(Object aModel, ITemplate aTemplate, String sourcecode, boolean aInterface)
//			throws Exception {
//		Class<?> templateClz = this.loader.loadClass(Template.class.getName());
//		Class<?> itemplateClz = this.loader.loadClass(ITemplate.class.getName());
//
//		Class<?> paramTypes[] = new Class<?>[] { Object.class, itemplateClz, String.class };
//		if (aInterface) {
//			paramTypes = new Class<?>[] { Class.class, itemplateClz, String.class };
//		}
//
//		Object cloneTemplate = templateClz.newInstance();
//		BeanUtils.copyProperties(cloneTemplate, aTemplate);
//
//		Object values[] = new Object[] { aModel, cloneTemplate, sourcecode };
//
//		Class<?> clazz = this.loader.loadClass(JetArgument.class.getName());
//		Constructor<?> ctor = clazz.getConstructor(paramTypes);
//		Object result = ctor.newInstance(values);
//
//		return result;
//	}

	private boolean generateThisObject(ITemplate aTemplate, Class<?> aClass) {
		boolean r = aTemplate.appliesTo(aClass);

		if (r) {
			if (AnnotationsHelper.hasAnnotation(aClass, DontGenerateCode.class)) {
				r = false;
			}
		}

		return r;
	}

	private boolean copyThisObject(ITemplate aTemplate, Class<?> modelClass) {
		if (aTemplate.getHooks() != null) {
			return aTemplate.getHooks().copyModelClass(modelClass);
		} else {
			return false;
		}
	}

	private String handleGenerateError(Class<?> aModelClass, ITemplate aTemplate, Throwable e) {
		String fout = e.getMessage();
		if (e instanceof InvocationTargetException) {
			fout = ((InvocationTargetException) e).getMessage();
		} else if (e instanceof RuntimeException) {
			RuntimeException rte = (RuntimeException) e;
			if (rte.getCause() instanceof InvocationTargetException) {
				InvocationTargetException invtex = (InvocationTargetException) rte.getCause();
				fout = invtex.getTargetException().getMessage();
			}
		}
		LogHelper.error("error generating " + aTemplate.getName() + " " + aModelClass.getName() + " : " + fout, e);

		if (System.getProperty(CodegenConstants.JUNIT_TEST) != null) {
			Assert.fail(fout);
		}

		return null;
	}

	private ITemplateHooks getHooks(ITemplate aTemplate) {
		if (aTemplate.getHooks() == null) {
			return new DummyTemplateHook();
		} else {
			return aTemplate.getHooks();
		}
	}

	// -----------------------
	private static class GenerateResult {
		String generatedSource;
		ITemplate template;

		public GenerateResult(String aGeneratedSource, ITemplate aTemplate) {
			super();
			this.generatedSource = aGeneratedSource;
			this.template = aTemplate;
		}
	}

	private static class DummyTemplateHook implements ITemplateHooks {

		@Override
		public String doAfterGenerate(String newCode, Class<?> arg1, ITemplate arg2) {
			return newCode;
		}

		@Override
		public void doBeforeGenerate(Class<?> arg0, ITemplate arg1) {
		}

		@Override
		public String doMerge(String newCode, File oldFile, Class<?> arg2, ITemplate arg3) {
			return newCode;
		}

		@Override
		public String doGenerateFilename(String arg0, ITemplate arg1, Class<?> arg2) {
			return null;
		}

		@Override
		public boolean copyModelClass(Class<?> arg0) {
			return false;
		}
	}
}
