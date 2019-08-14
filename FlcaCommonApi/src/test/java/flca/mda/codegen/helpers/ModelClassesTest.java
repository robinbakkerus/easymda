package flca.mda.codegen.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.FordMax;
import com.example.model.AbstractExample;
import com.example.model.Vehicle;

public class ModelClassesTest {

	@BeforeClass
	public static void beforeOnce() {
		List<File> modelCpDirs = new ArrayList<>();
		modelCpDirs.add(new File(System.getProperty("user.dir") + "/bin/test"));
		ModelClasses.initialize(modelCpDirs);
	}
	
	@Test
	public void test_getAllSuperClasses() {
		List<Class<?>> superClasses = ModelClasses.getAllSuperClasses(Vehicle.class);
		Assert.assertEquals(0, superClasses.size());
		superClasses = ModelClasses.getAllSuperClasses(FordMax.class);
		System.out.println("superClasses for FordMax : " + superClasses);
		Assert.assertEquals(3, superClasses.size());
		Assert.assertEquals(Vehicle.class, superClasses.get(2));
	}

	@Test
	public void test_getAllSubClasses() {
		List<Class<?>> subClasses = ModelClasses.getAllSubClasses(Vehicle.class);
		System.out.println("subClasses for Vehicle : " + subClasses);
		Assert.assertEquals(6, subClasses.size());
		//Assert.assertEquals(FordMax.class, subClasses.get(0));
		subClasses = ModelClasses.getAllSubClasses(FordMax.class);
		Assert.assertEquals(0, subClasses.size());
	}
	
	@Test
	public void test_getAllSubClassesForPensioen() {
		List<Class<?>> subClasses = ModelClasses.getAllSubClasses(AbstractExample.class);
		System.out.println("subClasses for AbstractExample : " + subClasses);
		Assert.assertEquals(2, subClasses.size());
	}	
}
