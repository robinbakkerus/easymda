package protobuf.mapper;

import java.math.BigDecimal;

public class ProtobufBigDecimalMapper {

	public static String toProtobuf(final BigDecimal value) {
		return value.toString();
	}
	
	public static BigDecimal fromProtobuf(final String value) {
		return new BigDecimal(value);
	}
}
