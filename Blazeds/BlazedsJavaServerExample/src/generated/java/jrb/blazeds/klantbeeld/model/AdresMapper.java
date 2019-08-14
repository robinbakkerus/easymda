/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Adres;
import jrb.blazeds.klantbeeld.model.AdresMapper;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.AdresMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class AdresMapper {
	/**
	* Returns the Pojo object from the given Protobuf object.
	*/
	public static Adres fromPb(final AdresMsg sourceMsg) {
		final Adres result = new Adres();
		result.setAdres(sourceMsg.getAdres());
		result.setPostcode(sourceMsg.getPostcode());
		result.setPlaats(sourceMsg.getPlaats());
		return result;
	}	

	/**
	* Returns the Protobuf object from the given Pojo object
	*/
	public static AdresMsg toPb(final Adres source) {
		final AdresMsg.Builder builder = AdresMsg.newBuilder();
		if (source.getAdres() != null) builder.setAdres(source.getAdres());
		if (source.getPostcode() != null) builder.setPostcode(source.getPostcode());
		if (source.getPlaats() != null) builder.setPlaats(source.getPlaats());
		return builder.build();
	}
	
	public static List<Adres> fromPb(final List<AdresMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());
	}

	public static List<AdresMsg> toPb(final List<Adres> sourceList) {
		return sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());
	}
}

