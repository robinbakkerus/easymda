package blazeds.test;

import org.junit.BeforeClass;
import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;
import flca.test.base.AbstractTestTemplates;
import jrb.blazeds.example.Adres;
import jrb.blazeds.example.Geslacht;
import jrb.blazeds.example.GetKlantbeeld;
import jrb.blazeds.example.Klantbeeld;
import jrb.blazeds.example.PensioenAanspraak;
import jrb.blazeds.example.PensioenAanspraakType;

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

//	@Test
	public void testGenerateEnumMapper() {
		String s = this.generate(Geslacht.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
		s = this.generate(PensioenAanspraakType.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
	}

//	@Test
	public void testGenerateAdresMapper() {
		String s = this.generate(Adres.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
	}

	@Test
	public void testGenerateKlantbeeldMapper() {
		String s = this.generate(Klantbeeld.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
	}

//	@Test
	public void testGenerateAanspraakMapper() {
		String s = this.generate(PensioenAanspraak.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
	}
}
