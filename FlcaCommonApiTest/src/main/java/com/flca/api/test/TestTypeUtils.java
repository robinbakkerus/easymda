package com.flca.api.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mda.annotation.jpa.Id;
import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.type.IEntityType;

import org.junit.Test;

import com.flca.test.types.Impl1;
import com.flca.test.types.Impl2;

import flca.mda.api.util.Fw;
import flca.mda.api.util.FwSelectType;
import flca.mda.api.util.TypeUtils;

public class TestTypeUtils {

	private TypeUtils tu = new TypeUtils();

	@Test
	public void testIsXxxMethods() {
		TypeUtils tu = new TypeUtils();
		List<Fw> fields = tu.getAllFields(TestClass.class);

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("name", Boolean.FALSE);
		map.put("dob", Boolean.FALSE);
		map.put("primitiveInt", Boolean.TRUE);
		map.put("simpleInt", Boolean.FALSE);
		map.put("salaray", Boolean.FALSE);
		map.put("testEnum", Boolean.FALSE);
		map.put("primitiveBool", Boolean.TRUE);
		map.put("simpleBool", Boolean.FALSE);
		map.put("many2one", Boolean.FALSE);
		map.put("one2many", Boolean.FALSE);

		// System.out.println("--- isPrimitive --------------------------------");
		for (String name : map.keySet()) {
			Fw fw = getFieldByName(fields, name);
			Boolean b = fw.isPrimitive();
			assertTrue(name, b == map.get(name).booleanValue());
		}

		map.put("name", Boolean.TRUE);
		map.put("dob", Boolean.TRUE);
		map.put("primitiveInt", Boolean.TRUE);
		map.put("simpleInt", Boolean.TRUE);
		map.put("salaray", Boolean.TRUE);
		map.put("testEnum", Boolean.TRUE);
		map.put("primitiveBool", Boolean.TRUE);
		map.put("simpleBool", Boolean.TRUE);

		// System.out.println("--- isSimpleField --------------------------------");
		for (String name : map.keySet()) {
			Fw fw = getFieldByName(fields, name);
			Boolean b = fw.isSimple();
			assertTrue(name, b == map.get(name).booleanValue());
		}

		for (String name : map.keySet()) {
			map.put(name, Boolean.FALSE);
		}
		map.put("testEnum", Boolean.TRUE);

		// System.out.println("--- isEnum --------------------------------");
		for (String name : map.keySet()) {
			Fw fw = getFieldByName(fields, name);
			Boolean b = fw.isEnum();
			assertTrue(name, b == map.get(name).booleanValue());
		}

		for (String name : map.keySet()) {
			map.put(name, Boolean.FALSE);
		}
		map.put("many2one", Boolean.TRUE);

		// System.out.println("--- isOneToManyField --------------------------------");
		for (String name : map.keySet()) {
			Fw fw = getFieldByName(fields, name);
			Boolean b = fw.isManyToOneField();
			assertTrue(name, b == map.get(name).booleanValue());
		}

		// System.out.println("--- isManyToOneField --------------------------------");
		for (String name : map.keySet()) {
			map.put(name, Boolean.FALSE);
		}
		map.put("one2many", Boolean.TRUE);

		// System.out.println("--- isOneToManyField --------------------------------");
		for (String name : map.keySet()) {
			Fw fw = getFieldByName(fields, name);
			Boolean b = fw.isOneToManyField();
			assertTrue(name, b == map.get(name).booleanValue());
		}
	}

	@Test
	public void testSuperClasses1() {
		Class<?> supertype = tu.getSuperClass(Impl1.class);
		assertTrue(supertype == null);
	}

	@Test
	public void testSubClasses1() {
		Class<?> subtypes[] = tu.getAllSuperTypes(Impl1.class);
		assertTrue(subtypes.length == 1);
	}

	@Test
	public void testSuperClasses2() {
		Class<?> supertype = tu.getSuperClass(Impl2.class);
		System.out.println(supertype);
	}

	@Test
	public void testSubClasses2() {
		List<Class<?>> subtypes = tu.getSubClasses(Impl2.class);
		System.out.println("n2 = " + subtypes.size());
	}


	@Test
	public void getFields01() {
		List<Fw> result = tu.getAllFields(TestClass.class);
		int n = result.size();
		result = tu.getFieldsInc(TestClass.class, FwSelectType.MANYTOONE);
		assertTrue(result.size() == 1);
		result = tu.getFieldsExc(TestClass.class, FwSelectType.MANYTOONE);
		assertTrue(result.size() == n - 1);
		result = tu.getFieldsInc(TestClass.class, FwSelectType.ID);
		assertTrue(result.size() == 1 && result.get(0).isId());
		result = tu.getFieldsInc(TestClass2.class, FwSelectType.ID);
		assertTrue(result.size() == 1 && result.get(0).isId() && !result.get(0).isSpecial());
	}

	@Test
	public void getFields02() {
		List<FwSelectType> includes = new ArrayList<>();
		List<FwSelectType> excludes = new ArrayList<>();
		includes.add(FwSelectType.RELATIONS);
		excludes.add(FwSelectType.MANYTOONE);
		
		List<Fw> result = tu.getFields(TestClass.class, includes, excludes);
		assertTrue(result.size() == 1 );
		
		excludes.clear();
		excludes.add(FwSelectType.MANYTOMANY);
		
		result = tu.getFields(TestClass.class, includes, excludes);
		assertTrue(result.size() == 2 );
	}

	private Fw getFieldByName(List<Fw> aFields, String aName) {
		for (Fw field : aFields) {
			if (field.name().equals(aName)) {
				return field;
			}
		}
		throw new NullPointerException("no field found for " + aName);
	}

	// -------------
	class TestClass implements IEntityType {
		String name;
		Date dob;
		int primitiveInt;
		Integer simpleInt;
		BigDecimal salaray;
		TstEnum testEnum;
		boolean primitiveBool;
		Boolean simpleBool;

		@ManyToOne()
		TestMany2One many2one;

		@OneToMany
		Set<TestOne2Many> one2many;
	}

	class TestMany2One {
		String bname;
	}

	class TestOne2Many {
		String cname;
	}

	class TestOne2One {
		String name;
	}

	enum TstEnum {
		AAA;
	}

	class TestClass2 implements IEntityType {
		@Id
		Long pk;
		String name;
	}
}
