package com.flca.api.test;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import com.flca.test.types.TestA;


import flca.mda.api.util.TypeUtils;


public class TestTypeHelper
{
	private static TypeUtils th = new TypeUtils();
	
	@Test
	public void getAllSimpleFields()
	{
		TestA testobj = new TestA();
		
		List<Field> fields = th.getAllTheFields(testobj.getClass());
		System.out.println(fields);
	}
	
	@Test
	public void getImportedType01()
	{
		TestA testobj = new TestA();
		List<Field> fields = th.getAllTheFields(testobj.getClass());
		
		for (Field field : fields) {
			//TODO System.out.println(th.getTypeName(field));
		}
	}
}
