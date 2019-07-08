package com.flca.api.test;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.flca.test.types.TestService;

import flca.mda.api.util.InterfaceUtils;
import flca.mda.api.util.JetArgument;
import flca.mda.api.util.TypeUtils;

public class TestInterfaceHelper {
	private InterfaceUtils ih = new InterfaceUtils();
	private TypeUtils tu = new TypeUtils();

	private static Method sMethods[] = null;

	@BeforeClass
	public static void beforeEach() {
		// System.setProperty(CodegenConstants.JUNIT_TEST, "true");
		// PreferencesHelper.retrievePreferences();
		// TemplatesHelper.getInstance().refresh();
		// ProjectInstancesHelper.refreshJunitTest("FlcaMdaCodegen",
		// getProjectCp());
		@SuppressWarnings("unused")
		JetArgument jetarg = new JetArgument(TestService.class, null, makeSourceCode());
	}

	// private static String getProjectCp()
	// {
	// if (PropertyHelper.isWindows()) {
	// return
	// "C:/mydocs/robin/Project/flca/projects/com.flca.mda/flca.mda.templates.webapp/flca.mda.templates.webapp";
	// } else {
	// return
	// "/media/c/mydocs/robin/Project/flca/projects/com.flca.mda/flca.mda.templates.webapp/bin";
	// }
	// }

	@Test
	public void getArguments01() {
		try {
			Method m = getMethod("doSomething");
			List<String> r = ih.getArguments(m);
			Assert.assertTrue(r.size() == 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getArguments02() {
		try {
			Method m = getMethod("ping");
			List<String> r = ih.getArguments(m);
			Assert.assertTrue("aMessage".equals(r.get(0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getType01() {
		try {
			Method m = getMethod("uploadTestA");
			Class<?> clz = ih.getParameterType(m, 0);
			System.out.println(clz);
			Assert.assertTrue(tu.isCollection(clz));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getGenericType01() {
		try {
			Method m = getMethod("uploadTestA");
			Class<?> clz = ih.getGenericParameterType(m, 0);
			System.out.println(clz);
			Assert.assertFalse(tu.isCollection(clz));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Method getMethod(String aMethodName) {
		if (sMethods == null) {
			sMethods = TestService.class.getMethods();
		}

		for (Method m : sMethods) {
			if (m.getName().equals(aMethodName)) {
				return m;
			}
		}
		return null;
	}

	private static String makeSourceCode() {
		String r = "";
		r += "package test.types\n";
		r += "public interface TestService extends ServiceType\n";
		r += "{\n";
		r += "	void doSomething();\n";
		r += "	\n";
		r += "	String helloWorl();\n";
		r += "	\n";
		r += "	String ping(String aMessage);\n";
		r += "	\n";
		r += "	List<TestA> searchTestA(String aName);\n";
		r += "	\n";
		r += "	TestA saveTestA(TestA aValue);\n";
		r += "}\n";
		return r;
	}
}
