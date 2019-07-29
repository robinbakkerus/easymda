/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.service;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class SelectKlantbeeldMapper {
	/**
	* return the Pojo object from the given Protobuf object
	*/
	public static SelectKlantbeeld fromProtobuf(final SelectKlantbeeldMsg sourceMsg) {
		final SelectKlantbeeld result = new SelectKlantbeeld();
		result.setKlantId(sourceMsg.getKlantId());
		result.setAchternaam(sourceMsg.getAchternaam());		
		return result;
	}	

	/**
	* return the Protobuf object from the given Pojo object
	*/
	public static SelectKlantbeeldMsg toProtobuf(final SelectKlantbeeld source) {
		final SelectKlantbeeldMsg.Builder builder = SelectKlantbeeldMsg.newBuilder();
		builder.setKlantId(source.getKlantId());
		builder.setAchternaam(source.getAchternaam());		
		return builder.build();
	}
	
	public static List<SelectKlantbeeld> fromProtobuf(final List<SelectKlantbeeldMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromProtobuf(e)).collect(Collectors.toList());
	}

	public static List<SelectKlantbeeldMsg> toProtobuf(final List<SelectKlantbeeld> sourceList) {
		return sourceList.stream().map(e -> toProtobuf(e)).collect(Collectors.toList());
	}
}
