package com.flca.api.test;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

import com.flca.frw.util.ShellUtils;

import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.helpers.IniFileParser;
import flca.mda.codegen.helpers.IniFileSection;

public class TestIniFileParser {
	private static final String MULTI_LINE_START = "(\\S*)(\\s*=\\s*)(\\{\\{\\s*)$";
	private static final String MULTI_LINE_END = "^\\}}\\s*";
	private static final String INI_SECTION = "(^\\[\\s*)(\\S*)(\\s*\\]\\s*$)";
	private static final String INI_PROP = "(.*)(=)(.*$)";

	@Test
	public void testRegex() {
		String s = "test =  {{ ";
		Assert.assertTrue(s, s.matches(MULTI_LINE_START));
		s = "test =  {{";
		Assert.assertTrue(s, s.matches(MULTI_LINE_START));
		s = "* = {{";
		Assert.assertTrue(s, s.matches(MULTI_LINE_START));

		s = "}}";
		Assert.assertTrue(s, s.matches(MULTI_LINE_END));
		s = "}} ";
		Assert.assertTrue(s, s.matches(MULTI_LINE_END));
		s = "}}  ";
		Assert.assertTrue(s, s.matches(MULTI_LINE_END));

		s = "[test]";
		Assert.assertTrue(s, s.matches(INI_SECTION));
		s = "[ test ]";
		Assert.assertTrue(s, s.matches(INI_SECTION));
		s = "[ test.test ] ";
		Assert.assertTrue(s, s.matches(INI_SECTION));
	}

	@Test
	public void testRegex2() {
		Pattern p = Pattern.compile(INI_SECTION);

		String s = "[test]";
		Matcher m = p.matcher(s);
		Assert.assertTrue(s, m.find());
		Assert.assertTrue(s, m.group(2).equals("test"));

		s = "[ test ]";
		m = p.matcher(s);
		Assert.assertTrue(s, m.find());
		System.out.println(m.groupCount() + " ." + m.group(2) + ".");
		Assert.assertTrue(s, m.group(2).equals("test"));

		p = Pattern.compile(MULTI_LINE_START);
		s = "test =  {{ ";
		m = p.matcher(s);
		Assert.assertTrue(s, m.find());
		Assert.assertTrue(s, m.group(1).equals("test"));

		p = Pattern.compile(MULTI_LINE_END);
		s = "}}";
		m = p.matcher(s);
		Assert.assertTrue(s, m.find());

		s = "}} ";
		m = p.matcher(s);
		Assert.assertTrue(s, m.find());

		p = Pattern.compile(INI_PROP);
		s = "test=abc";
		m = p.matcher(s);
		Assert.assertTrue(s, m.find());
		Assert.assertTrue(s, m.group(3).equals("abc"));

		s = "test = abc ";
		m = p.matcher(s);
		Assert.assertTrue(s, m.find());
		Assert.assertTrue(s, m.group(3).trim().equals("abc"));
	}

	private static Collection<IniFileSection> sAllsections;

	@Test
	public void testIniParser() {
		try {
			File f = new File(ShellUtils.getCurrentDir() + "src/test/resources/test01.ini");
			sAllsections = new IniFileParser().parseFile(f);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testIniValues() {
		fillDataStore();
		Assert.assertTrue("test a1", DataStore.getInstance().getSectionValue("test", "a1").equals("this is a1"));
		Assert.assertTrue("test a2", DataStore.getInstance().getSectionValue("test", "a2").equals("this is a2"));
	}

	private void fillDataStore() {
		if (sAllsections == null) {
			testIniParser();
		}

		Map<String, IniFileSection> map = new HashMap<String, IniFileSection>();
		for (IniFileSection section : sAllsections) {
			map.put(section.getSectionName(), section);
		}
		DataStore.getInstance().setSections(map);
	}

}
