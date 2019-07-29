/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Adres;
import jrb.blazeds.klantbeeld.model.Geslacht;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.model.PensioenAanspraak;

import jrb.grpc.blazeds.*;

import protobuf.mapper.*;
  


public class KlantbeeldMapper {
	/**
	* returns the Pojo object from the given Protobuf object
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
	* returns the Protobuf object from the given Pojo object
	*/
	public static KlantbeeldMsg toProtobuf(final Klantbeeld source) {
		final KlantbeeldMsg.Builder builder = KlantbeeldMsg.newBuilder();
		if (source.getKlantId() != null) builder.setKlantId(source.getKlantId());
		if (source.getVoornaam() != null) builder.setVoornaam(source.getVoornaam());
		if (source.getAchternaam() != null) builder.setAchternaam(source.getAchternaam());
		if (source.getGeboorteDatum() != null) builder.setGeboorteDatum(ProtobufDateMapper.toProtobuf(source.getGeboorteDatum()));
		if (source.getGeslacht() != null) builder.setGeslacht(GeslachtMapper.toProtobuf(source.getGeslacht()));
		if (source.getSaldo() != null) builder.setSaldo(ProtobufBigDecimalMapper.toProtobuf(source.getSaldo()));
		if (source.getAdres() != null) builder.setAdres(AdresMapper.toProtobuf(source.getAdres()));
		if (source.getAanspraken() != null) builder.addAllAanspraken(PensioenAanspraakMapper.toProtobuf(source.getAanspraken()));		
		return builder.build();
	}
	
	public static List<Klantbeeld> fromProtobuf(final List<KlantbeeldMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromProtobuf(e)).collect(Collectors.toList());
	}

	public static List<KlantbeeldMsg> toProtobuf(final List<Klantbeeld> sourceList) {
		return sourceList.stream().map(e -> toProtobuf(e)).collect(Collectors.toList());
	}
}
