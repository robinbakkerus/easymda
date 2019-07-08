package com.flca.frw.test;

import java.lang.reflect.Array;

import junit.framework.Assert;

import org.junit.Test;

public class TestMisc {

	@Test
	public void test01() {
		String s1 = "a";
		String s2 = "b";
		
		Object objs[] = new Object[] {s1, s2};
		Assert.assertTrue(objs.getClass().isArray());
		
		Object objs2[] = (Object[]) objs;
		Assert.assertTrue(objs2.length == objs.length);
		
		String s3 = (String) objs2[0];
		Assert.assertTrue(s3.equals("a"));
	}

	@Test
	public void testArrayArg() 	{
		testArrayArg(new Object[] {"test"});
	}

	public void testArrayArg(Object arg) {
		Object args[] = (Object[]) arg;
		System.out.println(args[0]);
		System.out.println(arg);
		if (arg instanceof Array) {
			
		}
	}
}
