/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.WeduwePensioenAanspraak;
import jrb.blazeds.klantbeeld.model.WeduwePensioenAanspraakMapper;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.WeduwePensioenAanspraakMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class WeduwePensioenAanspraakMapper {
	/**
	* Returns the Pojo object from the given Protobuf object.
	*/
	public static WeduwePensioenAanspraak fromPb(final WeduwePensioenAanspraakMsg sourceMsg) {
		final WeduwePensioenAanspraak result = new WeduwePensioenAanspraak();
		result.setPartnerOverleden(ProtobufMapper.fromPbTimestamp(sourceMsg.getPartnerOverleden()));
		result.setType(PensioenAanspraakTypeMapper.fromPb(sourceMsg.getType()));
		result.setUitkering(ProtobufMapper.fromPbBigDecimal(sourceMsg.getUitkering()));
		result.setEinddatum(ProtobufMapper.fromPbDate(sourceMsg.getEinddatum()));
		return result;
	}	

	/**
	* Returns the Protobuf object from the given Pojo object
	*/
	public static WeduwePensioenAanspraakMsg toPb(final WeduwePensioenAanspraak source) {
		final WeduwePensioenAanspraakMsg.Builder builder = WeduwePensioenAanspraakMsg.newBuilder();
		if (source.getPartnerOverleden() != null) builder.setPartnerOverleden(ProtobufMapper.toPbTimestamp(source.getPartnerOverleden()));
		if (source.getType() != null) builder.setType(PensioenAanspraakTypeMapper.toPb(source.getType()));
		if (source.getUitkering() != null) builder.setUitkering(ProtobufMapper.toPbBigDecimal(source.getUitkering()));
		if (source.getEinddatum() != null) builder.setEinddatum(ProtobufMapper.toPbDate(source.getEinddatum()));
		return builder.build();
	}
	
	public static List<WeduwePensioenAanspraak> fromPb(final List<WeduwePensioenAanspraakMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());
	}

	public static List<WeduwePensioenAanspraakMsg> toPb(final List<WeduwePensioenAanspraak> sourceList) {
		return sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());
	}
}

