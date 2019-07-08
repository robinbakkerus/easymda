package com.flca.frw.test;

import junit.framework.TestCase;

import com.flca.frw.util.FileUtils;
import com.flca.frw.util.JsonHelper;

public class TestTestParams extends TestCase {

	public void testMakeXStreamFile()
	{
		TestParameters tps = new TestParameters();
		
		TestParameter tp1 = new TestParameter();
		tp1.setName("Familyname");
		tp1.setType(TestParameterType.STRING);
		tp1.setValue("Proefpersoon");
		
		TestParameter tp2 = new TestParameter();
		tp2.setName("Partner");
		tp2.setType(TestParameterType.BOOLEAN);
		tp2.setValue("False");
		
		TestParameter tp3 = new TestParameter();
		tp3.setName("CreditCheck");
		tp3.setType(TestParameterType.CHOICE);
		tp3.addChoice("Ok");
		tp3.addChoice("Refer");
		tp3.addChoice("Technical error");
		tp3.setValue("Proefpersoon");
		
		tps.addParam(tp1);
		tps.addParam(tp2);
		tps.addParam(tp3);
		
		JsonHelper.saveObject(tps, FileUtils.getTempDir() + "testparameters.xml");
	}
}
