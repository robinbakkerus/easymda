package flca.blazeds.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProtobufTypeMapper {

	private static final Map<Class<?>, String> sTypeMap;
	private static final Map<Class<?>, String> sMapperMap;
		
	static {
		sTypeMap = new HashMap<>();
		sTypeMap.put(String.class, "string");
		sTypeMap.put(Integer.class, "int32");
		sTypeMap.put(int.class, "int32");
		sTypeMap.put(Long.class, "int64");
		sTypeMap.put(long.class, "int64");
		sTypeMap.put(Double.class, "double");
		sTypeMap.put(double.class, "double");
		sTypeMap.put(Float.class, "float");
		sTypeMap.put(float.class, "float");
		sTypeMap.put(Boolean.class, "bool");
		sTypeMap.put(boolean.class, "bool");
		sTypeMap.put(java.sql.Date.class, "int64");
		sTypeMap.put(java.util.Date.class, "int64");
		sTypeMap.put(BigDecimal.class, "string");
	}

	static {
		sMapperMap = new HashMap<>();
		sMapperMap.put(java.util.Date.class, "ProtobufDateMapper");
		sMapperMap.put(java.sql.Date.class, "ProtobufDateMapper");
		sMapperMap.put(BigDecimal.class, "ProtobufBigDecimalMapper");
	}
	
	public static String getProtobufTypename(final Class<?> aClass) {
		if (sTypeMap.containsKey(aClass)) {
			return sTypeMap.get(aClass);
		} else {
			return "???";
		}
	}
	
	public static String getProtobufMapper(final Class<?> aClass) {
		return sMapperMap.get(aClass);
	}
	
}
