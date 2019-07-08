package com.flca.api.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.flca.frw.util.FileUtils;
import com.flca.frw.util.ShellUtils;

import flca.mda.api.util.SimpleMerge;

/**
 * The class <code>SimpleMergeTest</code> contains tests for the class {@link <code>SimpleMerge</code>}
 * 
 * @pattern JUnit Test Case
 * 
 * @generatedBy CodePro at 5/21/12 11:40 AM
 * 
 * @author nly36776
 * 
 * @version $Revision$
 */
public class TestSimpleMerge {

	private static final String YOURCODE = "manual-code";
	private static final String FREEBLOCK_START = YOURCODE + "-start:";
	private static final String FREEBLOCK_END = YOURCODE + "-end: ";
	private static final String YOUR_CODE_BELOW = "add your code below this line";

	private static final Pattern pFreeBlockStart = Pattern.compile("(^\\s?//.*)" + FREEBLOCK_START + "\\s*"
			+ "(\\w+)(.*)");
	private static final Pattern pFreeBlockEnd = Pattern.compile("(^\\s?//.*)" + FREEBLOCK_END + "\\s*" + "(\\w+)(.*)");
	private static final Pattern pYourCodeBelow = Pattern.compile("(^\\s?//.*)" + YOUR_CODE_BELOW + ".*");

	@Test
	public void findFreeblockStart() {
		String lines[] = new String[] { "//-- " + "manual-code-start:" + " ABC --",
				"//" + "manual-code-start:" + " ABC --", " //-- " + "manual-code-start:" + " ABC --",
				"//-- " + "manual-code-start: " + " ABC --", "//-- " + "manual-code-start: " + "ABC --",
				"//-- " + "manual-code-start: " + "ABC--", "//-- " + "manual-code-start: " + "ABC", };

		for (String line : lines) {
			System.out.println("testing " + line);
			Matcher m1 = pFreeBlockStart.matcher(line);
			Assert.assertTrue(m1.find());
			Assert.assertTrue(m1.group(2).equals("ABC"));
		}
	}

	@Test
	public void findFreeblockEnd() {
		String lines[] = new String[] { "//-- " + "manual-code-end:" + " ABC --",
				"//" + "manual-code-end:" + " ABC --", " //-- " + "manual-code-end:" + " ABC --",
				"//-- " + "manual-code-end: " + " ABC --", "//-- " + "manual-code-end: " + "ABC --",
				"//-- " + "manual-code-end: " + "ABC--", "//-- " + "manual-code-end: " + "ABC", };

		for (String line : lines) {
			System.out.println("testing " + line);
			Matcher m1 = pFreeBlockEnd.matcher(line);
			Assert.assertTrue(m1.find());
			Assert.assertTrue(m1.group(2).equals("ABC"));
		}
	}

	@Test
	public void findYourCodeBelow() {
		String lines[] = new String[] { "//-- " + "add your code below this line",
				"// " + "add your code below this line", " // " + "add your code below this line" + "  ", };

		for (String line : lines) {
			System.out.println("testing " + line);
			Matcher m1 = pYourCodeBelow.matcher(line);
			Assert.assertTrue(m1.find());
		}
	}

	@Test
	public void testSimpleMerge1() {
		System.out.println(ShellUtils.getUserDir());

		testSimpleMerge(1);
	}

	@Test
	public void testSimpleMerge2() {
		testSimpleMerge(2);
	}

	@Test
	public void testSimpleMerge3() {
		testSimpleMerge(3);
	}

	private void testSimpleMerge(int nr) {
		try {
			String oldfname = getOldFilename(nr);
			String newCode = FileUtils.readFile(getNewFilename(nr));
			String result = SimpleMerge.merge(newCode, new File(oldfname));
			FileUtils.saveFile(getMergedFilename(nr), result);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.toString());
		}
	}

	private String getNewFilename(int nr) {
		return getFilename("new", nr);
	}

	private String getOldFilename(int nr) {
		return getFilename("old", nr);
	}

	private String getMergedFilename(int nr) {
		return getFilename("merged", nr);
	}

	private String getFilename(String subdir, int nr) {
		return getFilesDir() + "/test-merge/" + subdir + "/TestMerge" + nr + ".java";
	}

	private String getFilesDir() {
		return ShellUtils.getUserDir() + ShellUtils.getSeperator() + "resources";
	}

}
