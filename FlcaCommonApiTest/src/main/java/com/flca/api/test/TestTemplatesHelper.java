package com.flca.api.test;


import org.junit.Assert;
import org.junit.Test;

import com.flca.test.types.TestA;
import com.flca.test.types.TestService;



import flca.mda.api.util.TemplateUtils;


public class TestTemplatesHelper
{
	private TemplateUtils tu = new TemplateUtils();
	
	@Test
	public void getClassName01()
	{
		String r = tu.getClassName(new TestA().getClass(), TemplatesBuilder.makeEntityTemplate());
		System.out.println(r);
		Assert.assertTrue("TestA".equals(r));
	}

	@Test
	public void getClassName02()
	{
		String r = tu.getClassName(TestService.class, TemplatesBuilder.makeServiceTemplate());
		System.out.println(r);
		Assert.assertTrue("TestService".equals(r));
	}

	@Test
	public void getClassName03()
	{
		String r = tu.getClassName(TestService.class, TemplatesBuilder.makeServiceIntfTemplate());
		System.out.println(r);
		Assert.assertTrue("TestServiceIntf".equals(r));
	}

	@Test
	public void getPackage01()
	{
		String r = tu.getPackage(new TestA().getClass(), TemplatesBuilder.makeEntityTemplate());
		System.out.println(r);
		Assert.assertTrue("test.types".equals(r));
	}

	@Test
	public void getPackage02()
	{
		String r = tu.getPackage(TestService.class, TemplatesBuilder.makeServiceTemplate());
		System.out.println(r);
		Assert.assertTrue("test.types".equals(r));
	}

	@Test
	public void getPackage03()
	{
		String r = tu.getPackage(TestService.class, TemplatesBuilder.makeServiceIntfTemplate());
		System.out.println(r);
		Assert.assertTrue("test.types.intf".equals(r));
	}
}
