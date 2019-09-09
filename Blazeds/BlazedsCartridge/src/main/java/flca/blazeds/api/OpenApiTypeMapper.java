package flca.blazeds.api;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OpenApiTypeMapper {

	private static final Map<Class<?>, String> sTypeMap;
	private static final Map<Class<?>, String> sFormatMap;
		
	static {
		sTypeMap = new HashMap<>();
		sTypeMap.put(String.class, "string");
		sTypeMap.put(Integer.class, "integer");
		sTypeMap.put(int.class, "integer");
		sTypeMap.put(Long.class, "integer");
		sTypeMap.put(long.class, "integer");
		sTypeMap.put(Double.class, "number");
		sTypeMap.put(double.class, "number");
		sTypeMap.put(Float.class, "number");
		sTypeMap.put(float.class, "number");
		sTypeMap.put(Boolean.class, "boolean");
		sTypeMap.put(boolean.class, "boolean");
		sTypeMap.put(java.sql.Date.class, "string");
		sTypeMap.put(java.util.Date.class, "string");
		sTypeMap.put(BigDecimal.class, "number");
	}

	//this contains the posfix method name from OpenApiMapper
	static {
		sFormatMap = new HashMap<>();
		sFormatMap.put(java.util.Date.class, "date");
		sFormatMap.put(java.sql.Date.class, "date");
		sFormatMap.put(Timestamp.class, "date-time");
		sFormatMap.put(LocalDate.class, "date");
		sFormatMap.put(LocalDateTime.class, "date-time");
		sFormatMap.put(Double.class, "double");
		sFormatMap.put(double.class, "double");
		sFormatMap.put(Float.class, "float");
		sFormatMap.put(float.class, "float");
		sFormatMap.put(BigDecimal.class, "double");
	}
	
	public static String getOpenApiType(final Class<?> aClass) {
		if (sTypeMap.containsKey(aClass)) {
			return sTypeMap.get(aClass);
		} else {
			return "???";
		}
	}
	
	/**
	 * Returns true if for the given class a special mapper method is available in OpenApiMapper 
	 * @param aClass
	 * @return
	 */
	public static boolean hasOpenApiFormat(final Class<?> aClass) {
		return sFormatMap.get(aClass) != null;
	} 

	public static String getOpenApiFormat(final Class<?> aClass) {
		if (hasOpenApiFormat(aClass)) {
			return sFormatMap.get(aClass);
		} else {
			return null;
		}
	}

}
