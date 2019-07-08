package com.flca.api.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.flca.api.test.TestTypeUtils.TestMany2One;
import com.flca.api.test.TestTypeUtils.TestOne2Many;
import com.flca.api.test.TestTypeUtils.TestOne2One;
import com.flca.api.test.TestTypeUtils.TstEnum;

import flca.mda.api.util.Fw;
import flca.mda.api.util.TypeUtils;
import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.type.IEntityType;

public class TestFw {

	final TypeUtils tu = new TypeUtils();
	
	@Test
	public void testIsPrimitive() {
		Fw fw = tu.getFieldByName(TtClass.class, "name");
		assertFalse(fw.isPrimitive());
		fw = tu.getFieldByName(TtClass.class, "primitiveInt");
		assertTrue(fw.isPrimitive());
		fw = tu.getFieldByName(TtClass.class, "simpleInt");
		assertFalse(fw.isPrimitive());
	}

	@Test
	public void testIsSimple() {
		Fw fw = tu.getFieldByName(TtClass.class, "name");
		assertTrue(fw.isSimple());
		fw = tu.getFieldByName(TtClass.class, "primitiveInt");
		assertTrue(fw.isSimple());
		fw = tu.getFieldByName(TtClass.class, "simpleInt");
		assertTrue(fw.isSimple());
		fw = tu.getFieldByName(TtClass.class, "dob");
		assertTrue(fw.isSimple());
		fw = tu.getFieldByName(TtClass.class, "many2one");
		assertFalse(fw.isSimple());
		fw = tu.getFieldByName(TtClass.class, "one2many");
		assertFalse(fw.isSimple());
	}

	@Test
	public void testIsCollection() {
		Fw fw = tu.getFieldByName(TtClass.class, "strList");
		assertTrue(fw.isCollection());
		fw = tu.getFieldByName(TtClass.class, "strSet");
		assertTrue(fw.isCollection());
		fw = tu.getFieldByName(TtClass.class, "strArray");
		assertTrue(fw.isArray());
	}
	
	@Test
	public void testGetType() {
		Fw fw = tu.getFieldByName(TtClass.class, "name");
		test("name", fw.type(), String.class);
		fw = tu.getFieldByName(TtClass.class, "primitiveInt");
		test("primitiveInt", fw.type(), int.class);
		fw = tu.getFieldByName(TtClass.class, "simpleInt");
		test("simpleInt", fw.type(), Integer.class);
		fw = tu.getFieldByName(TtClass.class, "many2one");
		test("many2one", fw.type(), TestMany2One.class);
		fw = tu.getFieldByName(TtClass.class, "one2many");
		test("one2many", fw.type(), Set.class);
		fw = tu.getFieldByName(TtClass.class, "one2one");
		test("one2one", fw.type(), TestOne2One.class);
	}

	@Test
	public void testGetGenericType() {
		Fw fw = tu.getFieldByName(TtClass.class, "name");
		test("name", fw.genericType(), String.class);
		fw = tu.getFieldByName(TtClass.class, "primitiveInt");
		test("primitiveInt", fw.genericType(), int.class);
		fw = tu.getFieldByName(TtClass.class, "simpleInt");
		test("simpleInt", fw.genericType(), Integer.class);
		fw = tu.getFieldByName(TtClass.class, "many2one");
		test("many2one", fw.genericType(), TestMany2One.class);
		fw = tu.getFieldByName(TtClass.class, "one2many");
		Class<?> gentyp = fw.genericType();
		System.out.println(gentyp);
		test("one2many", fw.genericType(), TestOne2Many.class);
		fw = tu.getFieldByName(TtClass.class, "one2one");
		test("one2one", fw.genericType(), TestOne2One.class);
	}
	
	@Test
	public void testGetTypeName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "String");
		map.put("dob", "Date");
		map.put("primitiveInt", "int");
		map.put("simpleInt", "Integer");
		map.put("salaray", "BigDecimal");
		map.put("testEnum", "TstEnum");
		map.put("primitiveBool", "boolean");
		map.put("simpleBool", "Boolean");
		map.put("many2one", "TestMany2One");
		map.put("one2many", "Set<TestOne2Many>");
		map.put("one2one", "TestOne2One");

		for (String name : map.keySet()) {
			Fw fw = tu.getFieldByName(TtClass.class, name);
				String typename = fw.typeName();
				String expectedTypename = map.get(name);
				test(name, typename, expectedTypename);
		}
	}

	@Test
	public void testGetGenericTypeName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "String");
		map.put("dob", "Date");
		map.put("primitiveInt", "int");
		map.put("simpleInt", "Integer");
		map.put("salaray", "BigDecimal");
		map.put("testEnum", "TstEnum");
		map.put("primitiveBool", "boolean");
		map.put("simpleBool", "Boolean");
		map.put("many2one", "TestMany2One");
		map.put("one2many", "TestOne2Many");
		map.put("one2one", "TestOne2One");

		for (String name : map.keySet()) {
			Fw fw = tu.getFieldByName(TtClass.class, name);
				String typename = fw.genericTypeName();
				String expectedTypename = map.get(name);
				test(name, typename, expectedTypename);
		}
	}

	@Test
	public void testActualTypeName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "String");
		map.put("dob", "Date");
		map.put("primitiveInt", "int");
		map.put("simpleInt", "Integer");
		map.put("salaray", "BigDecimal");
		map.put("testEnum", "TstEnum");
		map.put("primitiveBool", "boolean");
		map.put("simpleBool", "Boolean");
		map.put("many2one", "TestMany2One");
		map.put("one2many", "Set");
		map.put("one2one", "TestOne2One");

		for (String name : map.keySet()) {
			Fw fw = tu.getFieldByName(TtClass.class, name);
				String typename = fw.actualTypeName();
				String expectedTypename = map.get(name);
				test(name, typename, expectedTypename);
		}
	}
	

	@Test
	public void testGetId() {
		String idname = tu.getIdName(TtClass.class);
		assertTrue(idname.equals("id"));
		String idtype = tu.getIdTypeName(TtClass.class);
		assertTrue(idtype.equals("Long"));
	}
	
	private void test(String name, Object value, Object expected) {
		assertTrue(name + " should be " + expected + " instead of " + value, value.equals(expected)); 
	}
	
	//========
	class TtClass implements IEntityType {
		String name;
		Date dob;
		int primitiveInt;
		Integer simpleInt;
		BigDecimal salaray;
		TstEnum testEnum;
		boolean primitiveBool;
		Boolean simpleBool;
		List<String> strList = new ArrayList<>();
		Set<String> strSet = new HashSet<>();
		String strArray[] = new String[10];

		@OneToOne()
		TestOne2One one2one;

		@ManyToOne()
		TestMany2One many2one;

		@OneToMany
		Set<TestOne2Many> one2many;
	}

}
