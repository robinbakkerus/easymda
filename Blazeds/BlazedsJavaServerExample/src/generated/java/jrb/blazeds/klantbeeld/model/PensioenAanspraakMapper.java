/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraak;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakMapper;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.PensioenAanspraakMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class PensioenAanspraakMapper {
	/**
	* returns the Pojo object from the given Protobuf object
	*/
	public static PensioenAanspraak fromPb(final PensioenAanspraakMsg sourceMsg) {
		PensioenAanspraak result = null;
		if (sourceMsg.getPensioenAanspraakCase().equals(PensioenAanspraakCase.VROEGPENSIOENAANSPRAAK)) {
			result = VroegPensioenAanspraakMapper.fromPb(sourceMsg.getVroegPensioenAanspraak());
		}
		if (sourceMsg.getPensioenAanspraakCase().equals(PensioenAanspraakCase.WEDUWEPENSIOENAANSPRAAK)) {
			result = WeduwePensioenAanspraakMapper.fromPb(sourceMsg.getWeduwePensioenAanspraak());
		}

		result.setType(PensioenAanspraakTypeMapper.fromPb(sourceMsg.getType()));
		result.setUitkering(ProtobufMapper.fromPbBigDecimal(sourceMsg.getUitkering()));
		result.setEinddatum(ProtobufMapper.fromPbDate(sourceMsg.getEinddatum()));
		return result;
	}	

	/**
	* returns the Protobuf object from the given Pojo object
	*/
	public static PensioenAanspraakMsg toPb(final PensioenAanspraak source) {
		final PensioenAanspraakMsg.Builder builder = PensioenAanspraakMsg.newBuilder();
		if (source.getType() != null) builder.setType(PensioenAanspraakTypeMapper.toPb(source.getType()));
		if (source.getUitkering() != null) builder.setUitkering(ProtobufMapper.toPbBigDecimal(source.getUitkering()));
		if (source.getEinddatum() != null) builder.setEinddatum(ProtobufMapper.toPbDate(source.getEinddatum()));
		return builder.build();
	}
	
	public static List<PensioenAanspraak> fromPb(final List<PensioenAanspraakMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());
	}

	public static List<PensioenAanspraakMsg> toPb(final List<PensioenAanspraak> sourceList) {
		return sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());
	}
}

