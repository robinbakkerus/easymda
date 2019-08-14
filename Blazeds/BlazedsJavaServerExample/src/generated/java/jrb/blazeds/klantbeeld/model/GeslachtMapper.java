/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Geslacht;
import jrb.blazeds.klantbeeld.model.GeslachtMapper;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.GeslachtMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class GeslachtMapper {
	/**
	* returns the Pojo enum from the given Protobuf enum
	*/
	public static Geslacht fromPb(final GeslachtMsg sourceMsg) {
		return Geslacht.valueOf(sourceMsg.name());
	}

	/**
	* returns the Protobuf enum from the given Pojo enum
	*/
	public static GeslachtMsg toPb(final Geslacht source) {
		return GeslachtMsg.valueOf(source.name());
	}
}

