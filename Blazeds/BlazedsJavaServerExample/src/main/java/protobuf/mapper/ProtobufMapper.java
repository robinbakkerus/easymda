package protobuf.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ProtobufMapper {

	public static long toPbDate(final Date date) {
		return date.getTime();
	}
	
	public static Date fromPbDate(final long value) {
		return new Date(value);
	}
	
	public static long toPbSqlDate(final java.sql.Date date) {
		return date.getTime();
	}
	
	public static java.sql.Date fromPbSqlDate(final long value) {
		return new java.sql.Date(value);
	}

	public static Timestamp fromPbTimestamp(final long value) {
		return new Timestamp(value);
	}

	public static long toPbTimestamp(final Timestamp value) {
		return value.getTime();
	}
	
	public static String toPbBigDecimal(final BigDecimal value) {
		return value.toString();
	}
	
	public static BigDecimal fromPbBigDecimal(final String value) {
		return new BigDecimal(value);
	}
	
	public static long toPbLocalDate(final LocalDate value) {
		return java.util.Date.from(value.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant()).getTime();
	}
	
	public static LocalDate fromPbLocalDate(final long value) {
		return Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static long toPbLocalDateTime(final LocalDateTime value) {
		return java.util.Date.from(value
			      .atZone(ZoneId.systemDefault())
			      .toInstant()).getTime();
	}
	
	public static LocalDateTime fromPbLocalDateTime(final long value) {
		return Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
}
