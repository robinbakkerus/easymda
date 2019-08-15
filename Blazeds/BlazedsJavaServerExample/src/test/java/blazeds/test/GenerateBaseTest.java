package blazeds.test;

import org.junit.BeforeClass;

import flca.test.base.AbstractTestTemplates;
import jrb.blazeds.klantbeeld.model.Adres;
import jrb.blazeds.klantbeeld.model.Geslacht;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.model.PensioenAanspraak;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.VroegPensioenAanspraak;
import jrb.blazeds.klantbeeld.model.WeduwePensioenAanspraak;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;

public class GenerateBaseTest extends AbstractTestTemplates {

	static final Class<?>[] MSG_CLASSES = new Class<?>[] {
		SelectKlantbeeld.class,
		Geslacht.class,
		PensioenAanspraakType.class,
		Adres.class,
		Klantbeeld.class,
		PensioenAanspraak.class,
		VroegPensioenAanspraak.class,
		WeduwePensioenAanspraak.class,
	};

	static final Class<?>[] SRV_CLASSES = new Class<?>[] {
		GetKlantbeeld.class, 
	};
	
	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new BlazedsTestData());
	}
	
}
