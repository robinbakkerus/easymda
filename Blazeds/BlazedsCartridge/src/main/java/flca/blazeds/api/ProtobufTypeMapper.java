package flca.blazeds.api;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

	//this contains the posfix method name from ProtobufMapper
	static {
		sMapperMap = new HashMap<>();
		sMapperMap.put(java.util.Date.class, "PbDate");
		sMapperMap.put(java.sql.Date.class, "PbSqlDate");
		sMapperMap.put(Timestamp.class, "PbTimestamp");
		sMapperMap.put(LocalDate.class, "PbLocalDate");
		sMapperMap.put(LocalDateTime.class, "PbLocalDateTime");
		sMapperMap.put(BigDecimal.class, "PbBigDecimal");
	}
	
	public static String getProtobufTypename(final Class<?> aClass) {
		if (sTypeMap.containsKey(aClass)) {
			return sTypeMap.get(aClass);
		} else {
			return "???";
		}
	}
	
	/**
	 * Returns true if for the given class a special mapper method is available in ProtobufMapper 
	 * @param aClass
	 * @return
	 */
	public static boolean hasProtobufTypeMapper(final Class<?> aClass) {
		return sMapperMap.get(aClass) != null;
	} 

	public static String getProtobufTypeGetter(final Class<?> aClass) {
		if (hasProtobufTypeMapper(aClass)) {
			return "ProtobufMapper.from" + sMapperMap.get(aClass);
		} else {
			return null;
		}
	}
	
	public static String getProtobufTypeSetter(final Class<?> aClass) {
		if (hasProtobufTypeMapper(aClass)) {
			return "ProtobufMapper.to" + sMapperMap.get(aClass);
		} else {
			return null;
		}
	}
}
