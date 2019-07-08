package com.flca.frw.test;

import org.junit.Assert;
import org.junit.Test;

import com.flca.frw.util.JsonHelper;
import com.flca.frw.util.ShellUtils;
import com.flca.test.model.TestClass;

public class TestJsonHelper {

	@Test
	public void testDump() {
		String fname = ShellUtils.getTempDir() +  "dump-testclass.json";
		TtClass tstcls = new TtClass("ok", 1);
		JsonHelper.saveObject(tstcls, fname);
		
		Object o = JsonHelper.readObject(fname, TtClass.class);
		Assert.assertTrue(tstcls.equals(o));
	}


}
