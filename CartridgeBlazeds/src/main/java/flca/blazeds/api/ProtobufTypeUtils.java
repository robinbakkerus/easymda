package flca.blazeds.api;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import flca.mda.api.util.InterfaceUtils;
import flca.mda.api.util.TypeUtils;

public class ProtobufTypeUtils {

	private final InterfaceUtils iu = new InterfaceUtils();
	private final TypeUtils tu = new TypeUtils();
	
	/**
	 * return the equivalent name used for protobuf messages
	 * @param name
	 * @return
	 */
	public String protoMessageName(String name) {
		return name + "Msg";	
	}

	public String protoMessageName(Class<?> clazz) {
		return clazz.getSimpleName() + "Msg";	
	}
	
	/**
	 * return all, not primitive, types used in the given interface class, from all the methods. 
	 * This includes the method parameters, and return types.
	 */
	public Set<Class<?>> findAllMethodTypes(Class<?> clazz) {
		Set<Class<?>> r = new HashSet<>();
		for (Method m : iu.getMethods(clazz)) {
			findAllMethodReturnTypes(m, r);
			findAllMethodArgumentTypes(m, r);
		}
		return r;
	}

	private void findAllMethodReturnTypes(Method m, Set<Class<?>> r) {
		Class<?> rettyp = m.getReturnType();
		if (!tu.isSimpleField(rettyp)) {
			r.add(rettyp);
		}
	}

	private void findAllMethodArgumentTypes(Method m, Set<Class<?>> r) {
		Class<?> paramTypes[] = m.getParameterTypes();
		for (Class<?> clz : paramTypes) {
			if (!tu.isSimpleField(clz)) {
				r.add(clz);
			}			
		}
	}
}
