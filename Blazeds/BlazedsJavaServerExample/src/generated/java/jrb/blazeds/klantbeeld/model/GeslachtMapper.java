/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Geslacht;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class GeslachtMapper {
	/**
	* return the Pojo enum from the given Protobuf enum
	*/
	public static Geslacht fromProtobuf(final GeslachtMsg sourceMsg) {
		return Geslacht.valueOf(sourceMsg.name());
	}

	/**
	* return the Protobuf enum from the given Pojo enum
	*/
	public static GeslachtMsg toProtobuf(final Geslacht source) {
		return GeslachtMsg.valueOf(source.name());
	}
}
