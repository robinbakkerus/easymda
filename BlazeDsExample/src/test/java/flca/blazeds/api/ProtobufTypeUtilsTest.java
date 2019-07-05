package flca.blazeds.api;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import flca.mda.api.util.Fw;
import jrb.blazeds.example.GetKlantbeeld;
import jrb.blazeds.example.Klantbeeld;

public class ProtobufTypeUtilsTest {

	final ProtobufTypeUtils pbu = new ProtobufTypeUtils();
	
	@Test
	public void testFindAllMethodTypes() {
		Set<Class<?>> r = pbu.findAllMethodTypes(GetKlantbeeld.class); 
		r.forEach(c -> System.out.println(c));
		Assert.assertEquals(2, r.size());
	}
	
	@Test
	public void testGetProtobufTypenameEnum() {
		Field fields[] = Klantbeeld.class.getDeclaredFields();
		for (Field fld : fields) {
			if (fld.getName().toLowerCase().contains("geslacht")) {
				Fw fw = new Fw(fld, Klantbeeld.class);
				String s = pbu.getProtobufTypename(fw);
				System.out.println(s);
			}
		}
	}

}
