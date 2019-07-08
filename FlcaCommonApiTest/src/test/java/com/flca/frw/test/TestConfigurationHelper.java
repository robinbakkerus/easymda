package com.flca.frw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.flca.frw.util.ShellUtils;
import com.flca.test.helper.ConfigHelperTest;

public class TestConfigurationHelper {

	private static List<String> noCp = new ArrayList<String>();
	private static List<String> cp1 = new ArrayList<String>();
	private static List<String> cp2 = new ArrayList<String>();
	private static List<String> cp12 = new ArrayList<String>();
	private static List<String> cp21 = new ArrayList<String>();
	private static List<String> noFs = new ArrayList<String>();
	private static List<String> fs1 = new ArrayList<String>();
	private static List<String> fs12 = new ArrayList<String>();
	private static List<String> fs21 = new ArrayList<String>();
	
	private static List<String> scanJar = new ArrayList<String>();
	private static List<String> scanAllJars = new ArrayList<String>();
	

	@BeforeClass
	public static void beforeOnce() {
		cp1.add("/config01");
		
		cp2.add("/config02");

		cp12.add("/config01");
		cp12.add("/config02");

		cp21.add("/config02");
		cp21.add("/config01");

		fs1.add(getFileSystemDir() + "/test-dir");
		
		fs12.add(getFileSystemDir() + "/test-dir");
		fs12.add(getFileSystemDir() + "/test-dir2");

		fs21.add(getFileSystemDir() + "/test-dir2");
		fs21.add(getFileSystemDir() + "/test-dir");

		scanAllJars.add("*.jar");
		scanJar.add("test5.jar");
	}
	
	private static String getFileSystemDir() {
		try {
			String result = new File(".").getCanonicalPath();
			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * test 1 classpath dir only and dont scan jars
	 */
	@Test
	public void test01() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, cp1, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK1"));
	}

	/**
	 * test 2 classpath dir only and dont scan jars
	 */
	@Test
	public void test02() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, cp12, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK1"));
	}

	/**
	 * test 1 classpath dir only and dont scan jars , with different search order
	 */
	@Test
	public void test03() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, cp21, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK2"));
	}

	/**
	 * test 1 classpath dir only and dont scan jars , get value from ini file
	 */
	@Test
	public void test04() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, cp2, null);
		String s = cfghlp.getString("section1.value1");
		assertNotNull(s);
		assertTrue(s.equals("section1 value"));
	}

	/**
	 * test 1 classpath dir only and dont scan jars , get int value from ini file
	 */
	@Test
	public void test05() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, cp2, null);
		int r = cfghlp.getInt("section1.intvalue", 0);
		assertTrue(r == 10);
	}
	
	/**
	 * test 1 filessystem dir only and get value from properties file
	 */
	@Test
	public void test06() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(fs1, noCp, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK3"));
	}

	/**
	 * test 2 filessystem dirs only and get value from properties file
	 */
	@Test
	public void test07() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(fs12, noCp, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK3"));
	}

	/**
	 * test 2 filessystem dirs only and get value from properties file, with different search order
	 */
	@Test
	public void test08() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(fs21, noCp, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK4"));
	}

	/**
	 * test 2 filessystem dirs and 2 classpath dirs, get the most specific property
	 */
	@Test
	public void test09() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(fs21, cp21, null);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK4"));
	}

	/**
	 * test for resources only inside classpath jar-files
	 */
	@Test
	public void test10() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, noCp, scanJar);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK5"));
	}

	/**
	 * test for files resources and embedded resources
	 */
	@Test
	public void test11() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(fs12, cp12, scanJar);

		String s = cfghlp.getString("test.property");
		assertNotNull(s);
		assertTrue(s.equals("OK3"));
	}

	/**
	 * test classpath dir only and dont scan jars , get value from xml file
	 */
	@Test
	public void test12() {
		ConfigHelperTest cfghlp = new ConfigHelperTest(noFs, cp12, null);
		String backColor = cfghlp.getString("colors.background");
		String textColor = cfghlp.getString("colors.text");
		String linkNormal = cfghlp.getString("colors.link[@normal]");
		String defColor = cfghlp.getString("colors.default");
		int rowsPerPage = cfghlp.getInt("rowsPerPage");
		List<Object> buttons = cfghlp.getList("buttons.name");
		assertTrue(backColor != null && textColor != null && linkNormal != null && defColor != null && rowsPerPage > 0);
		assertTrue(buttons != null && buttons.size() > 0);
	}
	
	
	 @Test
	public void testOs() {
		String os = ShellUtils.getOs();
		System.out.println("os = " + os);

		System.out.println("os.name = " + System.getProperty("os.name"));
		System.out.println("user.home = " + System.getProperty("user.home"));
		System.out.println("user.dir = " + System.getProperty("user.dir"));
	}
}
