package flca.blazeds.api;

import java.util.Set;

import org.junit.Test;

import jrb.blazeds.example.GetKlantbeeld;

public class ProtobufTypeUtilsTest {

	final ProtobufTypeUtils pbu = new ProtobufTypeUtils();
	
	@Test
	public void testFindAllMethodTypes() {
		Set<Class<?>> r = pbu.findAllMethodTypes(GetKlantbeeld.class); 
		r.forEach(c -> System.out.println(c));
	}

}
