/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakTypeMapper;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.PensioenAanspraakTypeMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class PensioenAanspraakTypeMapper {
	/**
	* returns the Pojo enum from the given Protobuf enum
	*/
	public static PensioenAanspraakType fromPb(final PensioenAanspraakTypeMsg sourceMsg) {
		return PensioenAanspraakType.valueOf(sourceMsg.name());
	}

	/**
	* returns the Protobuf enum from the given Pojo enum
	*/
	public static PensioenAanspraakTypeMsg toPb(final PensioenAanspraakType source) {
		return PensioenAanspraakTypeMsg.valueOf(source.name());
	}
}

