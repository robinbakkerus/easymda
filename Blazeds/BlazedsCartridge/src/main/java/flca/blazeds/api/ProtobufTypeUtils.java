package flca.blazeds.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import flca.mda.api.util.Fw;
import flca.mda.api.util.InterfaceUtils;
import flca.mda.api.util.NameUtils;
import flca.mda.api.util.TypeUtils;

public class ProtobufTypeUtils {

	private final InterfaceUtils iu = new InterfaceUtils();
	private final TypeUtils tu = new TypeUtils();
	private final NameUtils nu = new NameUtils();
	
	/**
	 * return the equivalent name used for protobuf messages
	 * 
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
	 * return all, not primitive, types used in the given interface class, from all
	 * the methods. This includes the method parameters, and return types.
	 */
	public Set<Class<?>> findAllMethodTypes(Class<?> clazz) {
		Set<Class<?>> r = new HashSet<>();
		for (Method m : this.iu.getMethods(clazz)) {
			this.findAllMethodReturnTypes(m, r);
			this.findAllMethodArgumentTypes(m, r);
		}
		return r;
	}

	private void findAllMethodReturnTypes(Method m, Set<Class<?>> r) {
		Class<?> rettyp = m.getReturnType();
		if (!this.tu.isSimpleField(rettyp)) {
			this.findRecursiveNestedTypes(rettyp, r);
		}
	}

	private void findAllMethodArgumentTypes(Method m, Set<Class<?>> r) {
		Class<?> paramTypes[] = m.getParameterTypes();
		for (Class<?> clz : paramTypes) {
			if (!this.tu.isSimpleField(clz)) {
				this.findRecursiveNestedTypes(clz, r);
			}
		}
	}

	private void findRecursiveNestedTypes(Class<?> parent, Set<Class<?>> r) {
		if (!r.contains(parent)) {
			r.add(parent);
			List<Fw> fws = this.tu.getAllFields(parent);
			for (Fw fw : fws) {
				if (fw.isEnum()) {
					r.add(fw.type());
				} else if (!fw.isSimple() && !fw.isId()) {
					this.findRecursiveNestedTypes(fw.genericType(), r);
				}
			}
		}
	}

	public String getProtobufTypename(final Fw fw) {
		if (fw.isCollection()) {
			return this.getProtobufTypenameCollection(fw);
		} else if (fw.isSimple()) {
			return ProtobufTypeMapper.getProtobufTypename(fw.getField().getType());
		} else {
			return this.protoMessageName(fw.getField().getType());
		}
	}

	private String getProtobufTypenameCollection(final Fw fw) {
		Class<?> clz = fw.genericType();
		if (this.tu.isSimpleField(clz)) {
			String typename = ProtobufTypeMapper.getProtobufTypename(clz);
			return "repeated " + typename;
		} else {
			return "repeated " + this.protoMessageName(clz);
		}
	}

	/**
	 * generates the proto snippet for the given enum class
	 * @param aClass
	 * @return
	 */
	public String generateProtobufEnumMsg(final Class<?> aClass) {
		final StringBuffer sb = new StringBuffer();
		int index = 0;
		sb.append("enum " + this.protoMessageName(aClass) + "{" + "\n");
		Object enumtypes[] = aClass.getEnumConstants();
		for (Object obj : enumtypes) {
			sb.append("\t" + obj.toString() + " = " + (index++) + ";\n");
		}
		sb.append("}" + "\n");
		return sb.toString();
	}

	public boolean hasPbMapper(final Fw fw) {
		if (ProtobufTypeMapper.hasProtobufTypeMapper(fw.type())) {
			return true;
		} else {
			return fw.isModelClass();
		}
	} 

	public String getPbGetter(final Fw fw) {
		if (this.hasPbMapper(fw)) {
			String getter = ProtobufTypeMapper.getProtobufTypeGetter(fw.type());
			if (getter != null) {
				return getter;
			} else {
				return String.format("%sMapper.fromPb", fw.genericTypeName());
			}
		} else {
			final String capName = nu.capName(fw.name());
			return "get" + capName;
		}
	}
	
	public String getPbSetter(final Fw fw) {
		if (this.hasPbMapper(fw)) {
			String getter = ProtobufTypeMapper.getProtobufTypeSetter(fw.type());
			if (getter != null) {
				return getter;
			} else {
				return String.format("%sMapper.toPb", fw.genericTypeName());
			}
		} else {
			final String capName = nu.capName(fw.name());
			return "set" + capName;
		}
	}

	/**
	 * return something like jrb.grpc.blazeds.* and jrb.grpc.blazeds.ExampleMsg.*
	 * TODO how to obtain the gprc service name from the given class.
	 * @return
	 */
	public List<String> getGrpcImports(Class<?> aClass) {
		List<String> r = new ArrayList<>();
		final String basePck = "jrb.grpc.blazeds"; //TODO
		r.add(basePck + ".*");
		r.add(String.format("%s.%sMsg.*", basePck, aClass.getSimpleName()));
		return r;
	}
}
