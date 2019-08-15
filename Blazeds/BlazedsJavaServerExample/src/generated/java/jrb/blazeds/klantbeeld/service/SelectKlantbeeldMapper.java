/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.service;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeldMapper;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.SelectKlantbeeldMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class SelectKlantbeeldMapper {
	/**
	* Returns the Pojo object from the given Protobuf object.
	*/
	public static SelectKlantbeeld fromPb(final SelectKlantbeeldMsg sourceMsg) {
		final SelectKlantbeeld result = new SelectKlantbeeld();
		result.setKlantId(sourceMsg.getKlantId());
		result.setAchternaam(sourceMsg.getAchternaam());
		return result;
	}	

	/**
	* Returns the Protobuf object from the given Pojo object
	*/
	public static SelectKlantbeeldMsg toPb(final SelectKlantbeeld source) {
		final SelectKlantbeeldMsg.Builder builder = SelectKlantbeeldMsg.newBuilder();
		if (source.getKlantId() != null) builder.setKlantId(source.getKlantId());
		if (source.getAchternaam() != null) builder.setAchternaam(source.getAchternaam());
		return builder.build();
	}
	
	public static List<SelectKlantbeeld> fromPb(final List<SelectKlantbeeldMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());
	}

	public static List<SelectKlantbeeldMsg> toPb(final List<SelectKlantbeeld> sourceList) {
		return sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());
	}
}

