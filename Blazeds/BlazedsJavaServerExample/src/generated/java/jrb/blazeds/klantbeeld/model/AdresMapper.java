/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Adres;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class AdresMapper {
	/**
	* return the Pojo object from the given Protobuf object
	*/
	public static Adres fromProtobuf(final AdresMsg sourceMsg) {
		final Adres result = new Adres();
		result.setAdres(sourceMsg.getAdres());
		result.setPostcode(sourceMsg.getPostcode());
		result.setPlaats(sourceMsg.getPlaats());		
		return result;
	}	

	/**
	* return the Protobuf object from the given Pojo object
	*/
	public static AdresMsg toProtobuf(final Adres source) {
		final AdresMsg.Builder builder = AdresMsg.newBuilder();
		builder.setAdres(source.getAdres());
		builder.setPostcode(source.getPostcode());
		builder.setPlaats(source.getPlaats());		
		return builder.build();
	}
	
	public static List<Adres> fromProtobuf(final List<AdresMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromProtobuf(e)).collect(Collectors.toList());
	}

	public static List<AdresMsg> toProtobuf(final List<Adres> sourceList) {
		return sourceList.stream().map(e -> toProtobuf(e)).collect(Collectors.toList());
	}
}
