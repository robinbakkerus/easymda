package blazeds.test;

import org.junit.BeforeClass;
import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;
import flca.test.base.AbstractTestTemplates;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;

public class GenerateOpenApiYamlTest extends GenerateBaseTest {

	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new BlazedsTestData());
	}
	
	@Test
	public void testGenerateProtobuf() {
		String s = this.generate(GetKlantbeeld.class, BlazeDsTemplates.getTemplate(TidBlazeDs.OPEN_API_YAML));
		System.out.println(s);
	}
}
