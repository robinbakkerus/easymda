package com.flca.frw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

import com.flca.frw.util.FileUtils;
import com.flca.frw.util.ShellUtils;

public class TestFileUtils  {

	private static String sFilename;

	private static String sTestData;

	@Test
	public void testSaveFile() {
		sFilename = ShellUtils.getTempDir() + new Date().getTime() + ".txt";
		sTestData = "this is a test";
		try {
			FileUtils.saveFile(sFilename, sTestData);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testReadFile() {
		testSaveFile();
		try {
			String r = FileUtils.readFile(sFilename);
			assertTrue(r.equals(sTestData));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private static final String RESNAME = "/com/flca/frw/test/TestFileUtils.class";
	
	@Test
	public void testReadResource() {
		try {
			String s1 = FileUtils.readResource(this, RESNAME);
			assertTrue(s1 != null && s1.length() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testReadUrl() {
		try {
			URL url = FileUtils.getExistingUrl(RESNAME);
			assertNotNull(url);
			String r = FileUtils.readFile(url);
			assertNotNull(r);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private static int sCount1 = 0;
	private static int sCount2 = 0;
	private static int sCount3 = 0;

	@Test
	public void testFindFiles01() {
		java.util.List<String> result = FileUtils.findFiles(ShellUtils.getTempDir(), false);
		sCount1 = result.size();
		assertTrue(sCount1 > 0);
	}

	@Test 
	public void testFindFiles02() {
		java.util.List<String> result = FileUtils.findFiles(ShellUtils.getTempDir(), true);
		sCount2 = result.size();
		assertTrue(sCount2 > 0);
		assertTrue(sCount2 > sCount1);
	}

	@Test
	public void testFindFiles03() {
		java.util.List<String> result = FileUtils.findFiles(ShellUtils.getTempDir(), new MyFilter(), true);
		sCount3 = result.size();
		assertTrue(sCount3 > 0);
		assertTrue(sCount3 < sCount2);
	}

	private static String FNAME = "/src/test/resources/test.properties";

	@Test
	public void testGetExistingUrl01() {
		URL url = FileUtils.getExistingUrl("/test.properties");
		assertNotNull(url);

		URL url2 = FileUtils.getExistingUrl("/not-there-test.properties");
		assertNull(url2);

		URL url3 = FileUtils.getExistingUrl(ShellUtils.getCurrentDir() + FNAME);
		assertNotNull(url3);

		try {
			String s = FileUtils.readFile(url3.getFile());
			assertNotNull(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class MyFilter implements FileFilter {

		public boolean accept(File arg0) {
			return arg0.getName().toLowerCase().endsWith(".txt");
		}
	}

	
}
