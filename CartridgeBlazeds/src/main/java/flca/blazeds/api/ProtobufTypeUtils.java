package flca.blazeds.api;

public class ProtobufTypeUtils {

	/**
	 * return the equivalent name used for protobuf messages
	 * @param name
	 * @return
	 */
	public String protoMessageName(String name) {
		return name + "Msg";	
	}
}
