package jrb.blazeds.klantbeeld.model.mock;

import org.junit.Test;

import jrb.blazeds.klantbeeld.model.Klantbeeld;

public class KlantbeeldMockTest {

	@Test
	public void test() {
		Klantbeeld klantbeeld = KlantbeeldMock.build();
		System.out.println(klantbeeld);
	}

}
