package com.flca.test.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TestClass implements Serializable {

	private static final long serialVersionUID = -1662851494934116905L;

	private String s;
	private int n;
	private List<String> strList = new ArrayList<>();
	
	public TestClass() {
		super();
	}

	public TestClass(String s, int n) {
		super();
		this.s = s;
		this.n = n;
	}
	
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	@Override
	public boolean equals(Object arg) {
		TestClass other = (TestClass) arg;
		return this.s.equals(other.s) && this.n == other.n;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
