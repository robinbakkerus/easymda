package blazeds.test;

import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;

public class GenerateMappersTest extends GenerateBaseTest {

	@Test
	public void testGenerateMappers() {
		for (Class<?> clz : MSG_CLASSES) {
			String s = this.generate(clz, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
			System.out.println(s);
		}
	}
	
	@Test
	public void testGenerateMocks() {
		for (Class<?> clz : MSG_CLASSES) {
			String s = this.generate(clz, BlazeDsTemplates.getTemplate(TidBlazeDs.MOCKDATA_LOADER));
			System.out.println(s);
		}
	}
}
