/* generated with EasyMda  */

package jrb.blazeds.example;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.example.Adres;
import jrb.blazeds.example.Geslacht;
import jrb.blazeds.example.Klantbeeld;
import jrb.blazeds.example.PensioenAanspraak;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class KlantbeeldMapper {
	/**
	* return the Pojo object from the given Protobuf object
	*/
	public static Klantbeeld fromProtobuf(final KlantbeeldMsg sourceMsg) {
		final Klantbeeld result = new Klantbeeld();
		result.setKlantId(sourceMsg.getKlantId());
		result.setVoornaam(sourceMsg.getVoornaam());
		result.setAchternaam(sourceMsg.getAchternaam());
		result.setGeboorteDatum(ProtobufDateMapper.fromProtobuf(sourceMsg.getGeboorteDatum()));
		result.setGeslacht(GeslachtMapper.fromProtobuf(sourceMsg.getGeslacht()));
		result.setSaldo(ProtobufBigDecimalMapper.fromProtobuf(sourceMsg.getSaldo()));
		result.setAdres(AdresMapper.fromProtobuf(sourceMsg.getAdres()));
		result.setAanspraken(PensioenAanspraakMapper.fromProtobuf(sourceMsg.getAansprakenList()));		
		return result;
	}	

	/**
	* return the Protobuf object from the given Pojo object
	*/
	public static KlantbeeldMsg toProtobuf(final Klantbeeld source) {
		final KlantbeeldMsg.Builder builder = KlantbeeldMsg.newBuilder();
		builder.setKlantId(source.getKlantId());
		builder.setVoornaam(source.getVoornaam());
		builder.setAchternaam(source.getAchternaam());
		builder.setGeboorteDatum(ProtobufDateMapper.toProtobuf(source.getGeboorteDatum()));
		builder.setGeslacht(GeslachtMapper.toProtobuf(source.getGeslacht()));
		builder.setSaldo(ProtobufBigDecimalMapper.toProtobuf(source.getSaldo()));
		builder.setAdres(AdresMapper.toProtobuf(source.getAdres()));
		builder.addAllAanspraken(PensioenAanspraakMapper.toProtobuf(source.getAanspraken()));		
		return builder.build();
	}
	
	public static List<Klantbeeld> fromProtobuf(final List<KlantbeeldMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromProtobuf(e)).collect(Collectors.toList());
	}

	public static List<KlantbeeldMsg> toProtobuf(final List<Klantbeeld> sourceList) {
		return sourceList.stream().map(e -> toProtobuf(e)).collect(Collectors.toList());
	}
}
