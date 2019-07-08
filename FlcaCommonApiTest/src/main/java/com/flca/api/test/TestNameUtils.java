package com.flca.api.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import flca.mda.api.util.NameUtils;

public class TestNameUtils {

	private static final NameUtils nu = new NameUtils();
	
	@Test
	public void testJoin1() {
		List<String> names = new ArrayList<>();
		names.add("aaa");
		names.add("bbb");
		String r = nu.join(names, "", ", ", "");
		assertTrue(r.equals("aaa, bbb"));
	}
	
	@Test
	public void testJoin2() {
		List<String> names = new ArrayList<>();
		names.add("aaa");
		names.add("bbb");
		String r = nu.join(names, "", "\n\t", "\n");
		assertTrue(r.equals("aaa\n\tbbb\n"));
	}
}
