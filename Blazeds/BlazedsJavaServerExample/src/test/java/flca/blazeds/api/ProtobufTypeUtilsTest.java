package flca.blazeds.api;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import flca.mda.api.util.Fw;
import flca.mda.api.util.TypeUtils;
import flca.mda.codegen.helpers.ModelClasses;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;

public class ProtobufTypeUtilsTest {

	final ProtobufTypeUtils pbu = new ProtobufTypeUtils();
	final TypeUtils tu = new TypeUtils();
	
	@BeforeClass
	public static void beforeOnce() {
		File modelCpDir = new File(System.getProperty("user.dir") + "/bin/main");
		ModelClasses.initialize(modelCpDir);
	}
	
	@Test
	public void testFindAllMethodTypes() {
		Set<Class<?>> r = pbu.findAllMethodTypes(GetKlantbeeld.class); 
		r.forEach(c -> System.out.println(c));
		Assert.assertEquals(6, r.size());
	}
	 
	@Test
	public void testGetProtobufTypename() {
		Fw fw = this.getFw(Klantbeeld.class, "geslacht");
		assertEquals("???", pbu.getProtobufTypename(fw));
	
		fw = this.getFw(Klantbeeld.class, "voornaam");
		assertEquals("string", pbu.getProtobufTypename(fw));
		
		fw = this.getFw(Klantbeeld.class, "geboorteDatum");
		assertEquals("int64", pbu.getProtobufTypename(fw));
	}

	@Test
	public void testGetPbGetter() {
		Fw fw = this.getFw(Klantbeeld.class, "voornaam");
		assertEquals("getVoornaam", pbu.getPbGetter(fw));

		fw = this.getFw(Klantbeeld.class, "geboorteDatum");
		System.out.println(pbu.getPbGetter(fw));
		assertEquals("ProtobufMapper.fromPbDate", pbu.getPbGetter(fw));

		fw = this.getFw(Klantbeeld.class, "adres");
		System.out.println(pbu.getPbGetter(fw));
		assertEquals("AdresMapper.fromPb", pbu.getPbGetter(fw));

		fw = this.getFw(Klantbeeld.class, "geslacht");
		System.out.println(pbu.getPbGetter(fw));
		assertEquals("GeslachtMapper.fromPb", pbu.getPbGetter(fw));

		fw = this.getFw(Klantbeeld.class, "aanspraken");
		System.out.println(pbu.getPbGetter(fw));
		assertEquals("GeslachtMapper.fromPb", pbu.getPbGetter(fw));
	}
	
	private Fw getFw(Class<?> clz, String substr) {
		List<Fw> fws = tu.getAllFields(clz);
		for (Fw fw : fws) {
			if (fw.name().contains(substr)) {
				return fw;
			}
		}
		System.err.println("No matching field for " + substr + " found");
		return null;
	}
}
