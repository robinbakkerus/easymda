package com.flca.mda.codegen.engine;

import java.lang.reflect.Constructor;

import org.apache.commons.beanutils.BeanUtils;

import flca.mda.api.util.JetArgument;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.Template;

public class JetArgumentBuilder {

	private final ClassLoader classLoader;
	
	public JetArgumentBuilder(ClassLoader classLoader) {
		super();
		this.classLoader = classLoader;
	}

	public JetArgument makeJetArg(ITemplate aTemplate, Class<?> aModelClass, Object elemInstance) throws Exception {
		JetArgument jetarg = null;
		if (elemInstance != null) {
			jetarg = this.makeJetArgument(elemInstance, aTemplate, null, false);
		} else {
			jetarg = this.makeJetArgument(aModelClass, aTemplate, null, true);
		}
		return jetarg;
	}
	
	private JetArgument makeJetArgument(Object aModel, ITemplate aTemplate, String sourcecode, boolean aInterface)
			throws Exception {
		Class<?> templateClz = this.classLoader.loadClass(Template.class.getName());
		Class<?> itemplateClz = this.classLoader.loadClass(ITemplate.class.getName());

		Class<?> paramTypes[] = new Class<?>[] { Object.class, itemplateClz, String.class };
		if (aInterface) {
			paramTypes = new Class<?>[] { Class.class, itemplateClz, String.class };
		}

		Object cloneTemplate = templateClz.newInstance();
		BeanUtils.copyProperties(cloneTemplate, aTemplate);

//		Object values[] = new Object[] { aModel, cloneTemplate, sourcecode };
//
//		Class<?> clazz = this.classLoader.loadClass(JetArgument.class.getName());
//		Constructor<?> ctor = clazz.getConstructor(paramTypes);
//		Object result = ctor.newInstance(values);

		return new JetArgument(aModel, aTemplate, sourcecode);
	}

}
