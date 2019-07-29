/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class PensioenAanspraakTypeMapper {
	/**
	* return the Pojo enum from the given Protobuf enum
	*/
	public static PensioenAanspraakType fromProtobuf(final PensioenAanspraakTypeMsg sourceMsg) {
		return PensioenAanspraakType.valueOf(sourceMsg.name());
	}

	/**
	* return the Protobuf enum from the given Pojo enum
	*/
	public static PensioenAanspraakTypeMsg toProtobuf(final PensioenAanspraakType source) {
		return PensioenAanspraakTypeMsg.valueOf(source.name());
	}
}
