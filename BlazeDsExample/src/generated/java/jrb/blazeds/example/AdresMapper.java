/* generated with EasyMda  */

package jrb.blazeds.example;
 
import java.util.HashSet;
import java.util.Set;

import jrb.blazeds.example.Adres;

import jrb.grpc.blazeds.*;

import org.junit.Test;

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
}
