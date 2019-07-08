/* generated with EasyMda  */

package jrb.blazeds.example;
 
import java.util.HashSet;
import java.util.Set;

import jrb.blazeds.example.Geslacht;

import jrb.grpc.blazeds.*;

import org.junit.Test;

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
