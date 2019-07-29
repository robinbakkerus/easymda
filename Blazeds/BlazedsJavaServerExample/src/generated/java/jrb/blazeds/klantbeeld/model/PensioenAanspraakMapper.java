/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraak;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class PensioenAanspraakMapper {
	/**
	* return the Pojo object from the given Protobuf object
	*/
	public static PensioenAanspraak fromProtobuf(final PensioenAanspraakMsg sourceMsg) {
		final PensioenAanspraak result = new PensioenAanspraak();
		result.setType(PensioenAanspraakTypeMapper.fromProtobuf(sourceMsg.getType()));
		result.setUitkering(ProtobufBigDecimalMapper.fromProtobuf(sourceMsg.getUitkering()));
		result.setEinddatum(ProtobufDateMapper.fromProtobuf(sourceMsg.getEinddatum()));		
		return result;
	}	

	/**
	* return the Protobuf object from the given Pojo object
	*/
	public static PensioenAanspraakMsg toProtobuf(final PensioenAanspraak source) {
		final PensioenAanspraakMsg.Builder builder = PensioenAanspraakMsg.newBuilder();
		builder.setType(PensioenAanspraakTypeMapper.toProtobuf(source.getType()));
		builder.setUitkering(ProtobufBigDecimalMapper.toProtobuf(source.getUitkering()));
		builder.setEinddatum(ProtobufDateMapper.toProtobuf(source.getEinddatum()));		
		return builder.build();
	}
	
	public static List<PensioenAanspraak> fromProtobuf(final List<PensioenAanspraakMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromProtobuf(e)).collect(Collectors.toList());
	}

	public static List<PensioenAanspraakMsg> toProtobuf(final List<PensioenAanspraak> sourceList) {
		return sourceList.stream().map(e -> toProtobuf(e)).collect(Collectors.toList());
	}
}
