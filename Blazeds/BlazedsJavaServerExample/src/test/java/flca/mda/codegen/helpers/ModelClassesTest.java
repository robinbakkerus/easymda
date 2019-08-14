package flca.mda.codegen.helpers;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jrb.blazeds.klantbeeld.model.PensioenAanspraak;

public class ModelClassesTest {

	@BeforeClass
	public static void beforeOnce() {
		File modelCpDir = new File(System.getProperty("user.dir") + "/bin/main");
		ModelClasses.initialize(modelCpDir);
	}
	
	@Test
	public void test_getAllSubClasses() {
		List<Class<?>> subClasses = ModelClasses.getAllSubClasses(PensioenAanspraak.class);
		System.out.println("subClasses for PensioenAanspraak : " + subClasses);
//		Assert.assertEquals(4, subClasses.size());
	}
}
