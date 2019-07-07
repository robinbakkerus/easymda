package jrb.blazeds.mapper;

import jrb.blazeds.example.Adres;
import jrb.grpc.GetKlantbeeldOuterClass.*;

public class AdresMapper {

	public static Adres fromMsg(final AdresMsg sourceMsg) {
		final Adres result = new Adres();
		result.setAdres(sourceMsg.getAdres());
		result.setPlaats(sourceMsg.getPlaats());
		result.setPostcode(sourceMsg.getPostcode());
		return result;
	}
	
	public static AdresMsg toMsg(final Adres source) {
		AdresMsg.Builder builder = AdresMsg.newBuilder();
		builder.setAdres(source.getAdres());
		builder.setPlaats(source.getPlaats());
		builder.setPostcode(source.getPostcode());
		return builder.build();
	}
}
