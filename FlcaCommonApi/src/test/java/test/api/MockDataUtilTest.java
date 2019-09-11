package test.api;

import java.util.Date;

import org.junit.Test;

import com.example.model.LaatPensioen;

import flca.mda.api.util.Fw;
import flca.mda.api.util.MockDataUtil;

public class MockDataUtilTest {

	private final MockDataUtil mdu = new MockDataUtil();

	
	@Test
	public void mockName() {
		String s = mdu.randomString();
		System.out.println(s);
	}

	@Test
	public void mockDate() throws Exception {
		Date r = mdu.randomDate();
		System.out.println(r);
	}
	
	@Test
	public void mockFwRandomValue() throws Exception {
		Fw fw = Fw.makeFw(LaatPensioen.class, "name");
		String r = mdu.randomValue(fw);
		System.out.println(r);
	}
	
}
