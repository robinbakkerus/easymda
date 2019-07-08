package com.flca.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.mock.MockJavaProject;
import test.mock.MockMonitor;
import test.mock.MockPath;
import test.mock.MockProject;

import com.flca.mda.codegen.engine.TemplateProcessor;
import com.flca.mda.codegen.helpers.CartridgeHelper;
import com.flca.mda.codegen.helpers.ClassloaderHelper;
import com.flca.mda.codegen.helpers.LogHelper;
import com.flca.mda.codegen.helpers.PluginHelper;
import com.flca.mda.codegen.helpers.ProjectInstanceHelper;

import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.data.TemplatesStore;
import flca.mda.codegen.helpers.IniFileHelper;
import flca.mda.codegen.helpers.SubClassesHelper;
import flca.test.base.TestTemplatesBase;
import flca.test.base.TestTemplatesData;

/**
 * Extend from this class if your jUnit test also requires the "generator setup" like DataStore TemplateStore etc
 * @author robin
 *
 */
public abstract class TestBase {

	protected static Logger logger = LoggerFactory.getLogger(TestTemplatesBase.class);

	protected static List<String> selectedClassnames = new ArrayList<String>();

	private static ClassLoader sLoader;
	protected static int startLogbackErrorCount;

	protected static void beforeOnceBase(TestTemplatesData aProvidedData) {

		try {
			System.setProperty(CodegenConstants.JUNIT_TEST, "true");
//			System.setProperty(CodegenConstants.OVERWRITE_WITHOUT_MERGING, "true");
			IJavaProject modelProject = makeJavaProject(aProvidedData.getModelDir().getPath());
			PluginHelper.getInstance().setJunitTestPluginRootdir(aProvidedData.getPluginDir());

			ProjectInstanceHelper.getInstance().setModelProject(modelProject, selectedClassnames);
			CartridgeHelper.getInstance().initialize(CartridgeHelper.getInstance().getAllCartridgeClassPaths());
			ProjectInstanceHelper.getInstance().refresh();
			sLoader = ClassloaderHelper.getInstance().getClassLoader();

			DataStore.getInstance().readSavedSubsValues(new File(ProjectInstanceHelper.getInstance().getCurrentProjectLocation()));
			
			SubClassesHelper.initialize();

			IniFileHelper.initialize(aProvidedData.getPluginDir());

//			mergeIniFilesFromTemplateAndModel();

			setupSubsValues(aProvidedData);

			DataStore.getInstance().cloneForApi(sLoader);
			TemplatesStore.getInstance().cloneForApi(sLoader);

			IJavaProject modelproj = makeJavaProject(aProvidedData.getModelDir().getPath());
			
		} catch (Throwable e) {
			LogHelper.error("error in beforeOnceBase", e);
			Assert.fail("error during startup " + e.getMessage());
		}
	}
	

	private static void setupSubsValues(TestTemplatesData aProvidedData) {
		// local overwrites (values are set in super class
		for (SubsValue subsval : aProvidedData.getSubsvalues()) {
			DataStore.getInstance().mergeSubsValue(subsval, true);
		}

		Collection<SubsValue> subsvalues = CartridgeHelper.getInstance().getSubsValues();
		DataStore.getInstance().mergeSubsValues(subsvalues, false);
		LogHelper.info(DataStore.getInstance().getSubsvalues().toString());
	}

//	private static void mergeIniFilesFromTemplateAndModel() {
//		for (CartridgeClasspathData data : CartridgeHelper.getInstance().getAllCartridgeClassPaths()) {
//			IniFileHelper.merge(data.getFile());
//		}
//		String modelDir = ProjectInstanceHelper.getInstance().getCurrentProjectLocation();
//		IniFileHelper.merge(new File(modelDir));
//	}

	protected static IJavaProject makeJavaProject(String aRootdir) {
		IPath path = new MockPath(aRootdir);
		IProject project = new MockProject(path);
		IJavaProject result = new MockJavaProject(project);
		return result;
	}

	
	
}

