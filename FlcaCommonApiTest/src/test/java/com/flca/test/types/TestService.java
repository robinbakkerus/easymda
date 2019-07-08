package com.flca.test.types;

import java.util.List;

import mda.type.IServiceType;

public interface TestService extends IServiceType
{
	void doSomething();
	
	String helloWorl();
	
	String ping(String aMessage);
	
	List<TestA> searchTestA(String aName);
	
	TestA saveTestA(TestA aValue);

	void uploadTestA(List<TestA> aValues);
}
