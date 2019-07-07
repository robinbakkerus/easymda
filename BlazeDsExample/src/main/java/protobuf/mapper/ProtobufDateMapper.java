package protobuf.mapper;

import java.util.Date;

public class ProtobufDateMapper {

	public static long toProtobuf(final Date date) {
		return date.getTime();
	}
	
	public static Date fromProtobuf(final long protoDate) {
		return new Date(protoDate);
	}
}
