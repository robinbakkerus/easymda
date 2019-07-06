package flca.blazeds.api;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import flca.mda.api.util.Fw;
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
			findRecursiveNestedTypes(rettyp, r);
		}
	}

	private void findAllMethodArgumentTypes(Method m, Set<Class<?>> r) {
		Class<?> paramTypes[] = m.getParameterTypes();
		for (Class<?> clz : paramTypes) {
			if (!tu.isSimpleField(clz)) {
				findRecursiveNestedTypes(clz, r);
			}			
		}
	}

	private void findRecursiveNestedTypes(Class<?> parent, Set<Class<?>> r) {
		if (!r.contains(parent)) {
			r.add(parent);
			List<Fw> fws = tu.getAllFields(parent);
			for (Fw fw : fws) {
				if (!fw.isSimple() && !fw.isId()) {
					findRecursiveNestedTypes(fw.genericType(), r);
				}
			}
		}
	}

	public String getProtobufTypename(final Fw fw) {
		if (fw.isEnum()) {
			return this.getProtobufTypenameEnum(fw);
		} else if (fw.isCollection()) {
			return this.getProtobufTypenameCollection(fw);
		} else if (fw.isSimple()) {
			return ProtobufTypeMapper.getProtobufTypename(fw.getField().getType());
		} else {
			return this.protoMessageName(fw.getField().getType());
		}
	}

	private String getProtobufTypenameCollection(final Fw fw) {
		Class<?> clz = fw.genericType();
		if (tu.isSimpleField(clz)) {
			String typename = ProtobufTypeMapper.getProtobufTypename(clz);
			return "repeated " + typename;
		} else {
			return "repeated " + this.protoMessageName(clz);
		}
	}
	
	private String getProtobufTypenameEnum(final Fw fw) {
		final StringBuffer sb = new StringBuffer();
		int index  = 0;
		Class<?> clz = fw.genericType();
		sb.append("enum " + this.protoMessageName(clz) + "{" + "\n");
		Object enumtypes[] = clz.getEnumConstants();
		for (Object obj : enumtypes) {
			sb.append("\t\t"+ obj.toString() + " = " + (index++) + ";\n");
		}
		sb.append("\t}" + "\n");
		return sb.toString();
	}

}
