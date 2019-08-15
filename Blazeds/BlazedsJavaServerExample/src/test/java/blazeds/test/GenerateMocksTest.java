package blazeds.test;

import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;

public class GenerateMocksTest extends GenerateBaseTest {

	@Test
	public void testGenerateKlantbeeldMock() {
		for (Class<?> clz : MSG_CLASSES) {
			if (!clz.isEnum()) {
				String s = this.generate(clz, BlazeDsTemplates.getTemplate(TidBlazeDs.MOCKDATA_LOADER));
				System.out.println(s);
			}
		}
	}
	
}
