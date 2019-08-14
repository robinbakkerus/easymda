package test.api;

import java.util.Date;

import org.junit.Test;

import flca.mda.api.util.MockDataUtil;

public class MockDataUtilTest {

	private final MockDataUtil mu = new MockDataUtil();

	
	@Test
	public void mockName() {
		String s = mu.randomString();
		System.out.println(s);
	}

	@Test
	public void mockDate() throws Exception {
		Date r = mu.randomDate();
		System.out.println(r);
	}
}
