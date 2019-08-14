/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Adres;
import jrb.blazeds.klantbeeld.model.Geslacht;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.model.KlantbeeldMapper;
import jrb.blazeds.klantbeeld.model.PensioenAanspraak;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.KlantbeeldMsg.*;

import protobuf.mapper.ProtobufMapper;
  


public class KlantbeeldMapper {
	/**
	* Returns the Pojo object from the given Protobuf object.
	*/
	public static Klantbeeld fromPb(final KlantbeeldMsg sourceMsg) {
		final Klantbeeld result = new Klantbeeld();
		result.setKlantId(sourceMsg.getKlantId());
		result.setVoornaam(sourceMsg.getVoornaam());
		result.setAchternaam(sourceMsg.getAchternaam());
		result.setGeboorteDatum(ProtobufMapper.fromPbDate(sourceMsg.getGeboorteDatum()));
		result.setGeslacht(GeslachtMapper.fromPb(sourceMsg.getGeslacht()));
		result.setSaldo(ProtobufMapper.fromPbBigDecimal(sourceMsg.getSaldo()));
		result.setAdres(AdresMapper.fromPb(sourceMsg.getAdres()));
		result.setAanspraken(PensioenAanspraakMapper.fromPb(sourceMsg.getAansprakenList()));
		return result;
	}	

	/**
	* Returns the Protobuf object from the given Pojo object
	*/
	public static KlantbeeldMsg toPb(final Klantbeeld source) {
		final KlantbeeldMsg.Builder builder = KlantbeeldMsg.newBuilder();
		if (source.getKlantId() != null) builder.setKlantId(source.getKlantId());
		if (source.getVoornaam() != null) builder.setVoornaam(source.getVoornaam());
		if (source.getAchternaam() != null) builder.setAchternaam(source.getAchternaam());
		if (source.getGeboorteDatum() != null) builder.setGeboorteDatum(ProtobufMapper.toPbDate(source.getGeboorteDatum()));
		if (source.getGeslacht() != null) builder.setGeslacht(GeslachtMapper.toPb(source.getGeslacht()));
		if (source.getSaldo() != null) builder.setSaldo(ProtobufMapper.toPbBigDecimal(source.getSaldo()));
		if (source.getAdres() != null) builder.setAdres(AdresMapper.toPb(source.getAdres()));
		if (source.getAanspraken() != null) builder.addAllAanspraken(PensioenAanspraakMapper.toPb(source.getAanspraken()));
		return builder.build();
	}
	
	public static List<Klantbeeld> fromPb(final List<KlantbeeldMsg> sourceMsgList) {
		return sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());
	}

	public static List<KlantbeeldMsg> toPb(final List<Klantbeeld> sourceList) {
		return sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());
	}
}

