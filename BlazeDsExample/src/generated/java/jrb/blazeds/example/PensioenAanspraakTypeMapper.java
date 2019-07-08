/* generated with EasyMda  */

package jrb.blazeds.example;
 
import java.util.HashSet;
import java.util.Set;

import jrb.blazeds.example.PensioenAanspraakType;

import jrb.grpc.blazeds.*;

import org.junit.Test;

import protobuf.mapper.*;
  


public class PensioenAanspraakTypeMapper {
	/**
	* return the Pojo enum from the given Protobuf enum
	*/
	public static PensioenAanspraakType fromProtobuf(final PensioenAanspraakTypeMsg sourceMsg) {
		return PensioenAanspraakType.valueOf(sourceMsg.name());
	}

	/**
	* return the Protobuf enum from the given Pojo enum
	*/
	public static PensioenAanspraakTypeMsg toProtobuf(final PensioenAanspraakType source) {
		return PensioenAanspraakTypeMsg.valueOf(source.name());
	}
}
