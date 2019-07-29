package blazeds.test;

import org.junit.BeforeClass;
import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;
import flca.test.base.AbstractTestTemplates;
import jrb.blazeds.klantbeeld.model.Adres;
import jrb.blazeds.klantbeeld.model.Geslacht;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.model.PensioenAanspraak;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;

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
	
//	@Test
	public void testGenerateSelectKlantbeeldMapper() {
		String s = this.generate(SelectKlantbeeld.class, BlazeDsTemplates.getTemplate(TidBlazeDs.MAPPER));
		System.out.println(s);
	}
	
}
