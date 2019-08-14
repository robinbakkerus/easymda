package flca.mda.api.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Car;
import com.example.model.Example;
import com.example.model.Ford;
import com.example.model.FordMax;

import flca.mda.codegen.helpers.ModelClasses;

public class FwTest {

	final TypeUtils tu = new TypeUtils();

	@BeforeClass
	public static void beforeOnce() {
		List<File> modelCpDirs = new ArrayList<>();
		modelCpDirs.add(new File(System.getProperty("user.dir") + "/bin/main"));
		modelCpDirs.add(new File(System.getProperty("user.dir") + "/bin/test"));
		ModelClasses.initialize(modelCpDirs);
	}

	@Test
	public void testGetType() {
		Fw fw = this.getFw(Example.class, "ford");
		assertEquals(Ford.class, fw.type());
		assertEquals(Ford.class, fw.genericType());

		fw = this.getFw(Example.class, "cars");
		assertEquals(List.class, fw.type());
		assertEquals(Car.class, fw.genericType());

		fw = this.getFw(Example.class, "opels");
		System.out.println("> " + fw.type());
		System.out.println("> " + fw.genericType());
//		assertEquals(List.class, fw.type());
//		assertEquals(Car.class, fw.genericType());

		fw = this.getFw(Example.class, "fordMaxColl");
		assertEquals(Collection.class, fw.type());
		assertEquals(FordMax.class, fw.genericType());
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
