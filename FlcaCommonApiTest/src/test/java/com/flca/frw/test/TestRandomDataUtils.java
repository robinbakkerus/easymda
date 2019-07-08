package com.flca.frw.test;

import junit.framework.Assert;

import org.junit.Test;

import com.flca.frw.test.type.TestEnum;


public class TestRandomDataUtils {

	@Test
	public void testRandomEnum()
	{
		TestEnum tstenum = (TestEnum) RandomDataUtils.randomEnum(TestEnum.class);
		Assert.assertTrue(tstenum != null);
		System.out.println(tstenum);
	}
}
