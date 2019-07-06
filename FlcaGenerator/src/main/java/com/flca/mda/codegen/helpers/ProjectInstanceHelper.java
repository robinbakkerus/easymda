package com.flca.mda.codegen.helpers;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flca.mda.api.util.TypeUtils;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.data.SubsValueType;
import flca.mda.codegen.helpers.FileHelper;
import flca.mda.codegen.helpers.ShellUtils;
import flca.mda.codegen.helpers.StrUtil;
import mda.type.IBaseType;

/**
 * This helper maintains all objects of the JavaProject that is
 * currentlyselected by the user. It has a static map of the corresponding
 * classloader and a map with all object instances plus source code. It also
 * maintains the current selected IJavaProject, if a new or other project is
 * selected, the maps will be reset. It should be initialized immediately after
 * a IJavaProject is selected via "refresh()" of simulated with
 * "refreshJunitTest()"
 * 
 * @author robin
 * 
 */
public class ProjectInstanceHelper {
	protected static Logger logger = LoggerFactory.getLogger(ProjectInstanceHelper.class);

	// this contains all fully qualified classname of the select model project.
	private Set<String> allInstances = new HashSet<String>();

	// this propert should be set inside the refresh method
	private IJavaProject currentProject;
	private List<String> selectedClassnames;

	private static ProjectInstanceHelper sInstance;

	protected TypeUtils tu;

	public ProjectInstanceHelper() {
	}

	public static ProjectInstanceHelper getInstance() {
		if (sInstance == null) {
			sInstance = new ProjectInstanceHelper();
		}
		return sInstance;
	}

	public void setModelProject(IJavaProject aModelProject, List<String> aSelectedClassnames) {
		if (this.currentProject == null || !this.key(aModelProject).equals(this.key(this.currentProject))) {
			this.currentProject = aModelProject;
		}
		this.selectedClassnames = aSelectedClassnames;
	}

	public void refresh() {
		String cp = this.getClasspath(this.currentProject);
		this.doRefresh(cp);
	}

	private void doRefresh(String aClasspath) {
		this.allInstances.clear();
		// String rootdir = cp.substring(0, cp.lastIndexOf("/"));

		List<File> classfnames = FileHelper.findFiles(aClasspath, new ClassFilter(), true);
		for (File file : classfnames) {
			String fqn = StrUtil.getFullyQualifiedClassname(aClasspath, file);

			try {
				this.addInstance(fqn);
			} catch (Exception e) {
				LogHelper.error("error creating instance " + fqn, e);
			}
		}

		this.addModelClassesToDatastore();
		this.saveBasePackage(); // TODO
	}

	private void addInstance(String aFqName) {
		if (!this.allInstances.contains(aFqName)) {
			this.allInstances.add(aFqName);
		}
	}

	private String getClasspath(IJavaProject project) {
		try {
			IProject p = project.getProject();
			String cp = p.getLocation().toPortableString();
			String builddir = project.getOutputLocation().toPortableString();
			cp += builddir.substring(builddir.lastIndexOf("/"));
			return cp;
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}

	public IJavaProject getCurrentProject() {
		return this.currentProject;
	}

	public List<String> getSelectedClassnames() {
		return this.selectedClassnames;
	}

	public void setCurrentProject(IJavaProject value) {
		this.currentProject = value;
	}

	public String getProjectName() {
		return this.currentProject.getElementName();
	}

	public String getProjectLocation(IJavaProject project) {
		IProject p = project.getProject();
		String result = p.getLocation().toPortableString();
		return result;
	}

	public String getCurrentProjectLocation() {
		return this.getProjectLocation(this.currentProject);
	}

	static class ClassFilter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			if (ShellUtils.isJunitTest()) {
				if (pathname.isDirectory()) {
					return true;
				} else {
					// ignore embedded classes
					return pathname.getName().endsWith(".class") && pathname.getName().indexOf("$") < 0;
				}
			} else {
				return pathname.isDirectory() || pathname.getName().endsWith(".class");
			}
		}
	}

	static class JavaSourceFilter implements FileFilter {
		private String fqn;

		public JavaSourceFilter(String fqn) {
			super();
			this.fqn = fqn;
		}

		@Override
		public boolean accept(File pathname) {
			if (pathname.isDirectory()) {
				return true;
			} else {
				if (pathname.getName().endsWith(".java")) {
					String s = StrUtil.replSlashsToDots(pathname.getPath());
					return (s.indexOf("." + this.fqn + ".") > 0);
				}
			}

			return false;
		}
	}

	private void addModelClassesToDatastore() {
		ClassLoader loader = ClassloaderHelper.getInstance().getClassLoader();

		for (String fqn : this.allInstances) {
			try {
				Class<?> clz = loader.loadClass(fqn);

				if (this.isModelClass(clz)) {
					DataStore.getInstance().addModelClass(clz);
				}
			} catch (Throwable t) {
				logger.error("error parsing model class " + fqn);
			}
		}
	}

	private void saveBasePackage() {
		String basepck = "todo"; // ((IApplicationType) o).getBasePackage();
		DataStore.getInstance().addSubsValue(new SubsValue(CodegenConstants.BASE_PACKAGE, basepck, SubsValueType.NONE));
	}

	private boolean isModelClass(Class<?> aClass) {
		if (this.tu == null) {
			this.tu = new TypeUtils();
		}
		return this.tu.hasType(aClass, IBaseType.class) || aClass.isEnum();
	}

	public boolean hasValidTargetDirs() {
		return true;
	}

	public String key(IJavaProject aProject) {
		return aProject.getElementName();
	}

}
