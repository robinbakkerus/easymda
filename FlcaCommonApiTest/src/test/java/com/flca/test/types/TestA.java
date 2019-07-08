package com.flca.test.types;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.type.IEntityType;


public class TestA implements IEntityType
{
	String name;
	Date dob;
	int nrOfChildren;
	BigDecimal salaray;
	TestEnum testEnum;
	
	@ManyToOne
	TestB b;
	
	@OneToMany
	Set<TestC> cs;
}
