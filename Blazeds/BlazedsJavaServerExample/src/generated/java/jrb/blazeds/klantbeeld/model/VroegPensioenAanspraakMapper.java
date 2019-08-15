/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.math.BigDecimal;

import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.VroegPensioenAanspraak;
import jrb.blazeds.klantbeeld.model.VroegPensioenAanspraakMapper;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.VroegPensioenAanspraakMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class VroegPensioenAanspraakMapper {
	/**
	* Returns the Pojo object from the given Protobuf object.
	*/
	public static VroegPensioenAanspraak fromPb(final VroegPensioenAanspraakMsg sourceMsg) {
		final VroegPensioenAanspraak result = new VroegPensioenAanspraak();
		result.setLooptijd(sourceMsg.getLooptijd());
		return result;
	}	

	/**
	* Returns the Protobuf object from the given Pojo object
	*/
	public static VroegPensioenAanspraakMsg toPb(final VroegPensioenAanspraak source) {
		final VroegPensioenAanspraakMsg.Builder builder = VroegPensioenAanspraakMsg.newBuilder();
		builder.setLooptijd(source.getLooptijd());
		return builder.build();
	}
	
	public static List<VroegPensioenAanspraak> fromPb(final List<VroegPensioenAanspraakMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());
	}

	public static List<VroegPensioenAanspraakMsg> toPb(final List<VroegPensioenAanspraak> sourceList) {
		return sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());
	}
}

