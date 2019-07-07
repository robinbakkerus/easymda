package blazeds.test;

import org.junit.BeforeClass;
import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;
import flca.test.base.AbstractTestTemplates;
import jrb.blazeds.example.GetKlantbeeld;
import jrb.blazeds.example.Klantbeeld;

public class GenerateFilesTest extends AbstractTestTemplates {

	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new BlazedsTestData());
	}
	
//	@Test
	public void testGenerateProtobuf() {
		String s = this.generate(GetKlantbeeld.class, BlazeDsTemplates.getTemplate(TidBlazeDs.PROTOBUF));
		System.out.println(s);
	}

	@Test
	public void testGenerateMapper() {
		String s = this.generate(Klantbeeld.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
	}
}
