package flca.mda.api.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Example;

import flca.mda.codegen.helpers.ModelClasses;

import static org.junit.Assert.*;

public class TypeUtilsTest {

	TypeUtils tu = new TypeUtils();
	
	@BeforeClass
	public static void beforeOnce() {
		List<File> modelCpDirs = new ArrayList<>();
		modelCpDirs.add(new File(System.getProperty("user.dir") + "/bin/test"));
		ModelClasses.initialize(modelCpDirs);
	}

	@Test
	public void testGetFields() {
		List<Fw> r = tu.getAllFields(Example.class);
		r.forEach(fw -> System.out.println(fw.name() + " " + fw.typeName()));
		assertEquals(8, r.size());
	}

	@Test
	public void testGetSelectedFields() {
		List<Fw> r = tu.getFields(Example.class, FwSelect.newBuilder().build());
		r.forEach(fw -> System.out.println(fw.name() + " " + fw.typeName()));
		assertEquals(7, r.size());

		r = tu.getFields(Example.class, FwSelect.emptyBuilder().build());
		assertEquals(0, r.size());

		r = tu.getFields(Example.class, FwSelect.emptyBuilder().withEnum(true).build());
		r.forEach(fw -> System.out.println(fw.name() + " " + fw.typeName()));
		assertEquals(1, r.size());
	
		r = tu.getFields(Example.class, FwSelect.emptyBuilder().withSuper(true).build());
		r.forEach(fw -> System.out.println(fw.name() + " " + fw.typeName()));
		assertEquals(7, r.size());

		r = tu.getFields(Example.class, FwSelect.emptyBuilder().withModelClass(true).build());
		r.forEach(fw -> System.out.println(fw.name() + " " + fw.typeName()));
		assertEquals(5, r.size());
	}
}
