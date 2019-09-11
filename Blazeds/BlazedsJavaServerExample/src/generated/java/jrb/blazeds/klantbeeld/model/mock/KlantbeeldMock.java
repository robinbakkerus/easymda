/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model.mock;
 
import java.math.BigDecimal;

import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Adres;
import jrb.blazeds.klantbeeld.model.Geslacht;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.model.PensioenAanspraak;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.KlantbeeldMsg.*;

import protobuf.mapper.*;
  


public class KlantbeeldMock {
	
	private static final Integer[] KLANTID = new Integer[] {-773267818,646430126,1444541997,}; 
	private static final String[] VOORNAAM = new String[] {"Aliomnc","Jvwssihcxs","Vjxqewrd",}; 
	private static final String[] ACHTERNAAM = new String[] {"Bfmnts","Bnciar","Hjihmemol",}; 
	private static final Date[] GEBOORTEDATUM = new Date[] {new Date(),new Date(),new Date(),}; 
	private static final Geslacht[] GESLACHT = new Geslacht[] {Geslacht.MAN,Geslacht.MAN,Geslacht.MAN,}; 
	private static final BigDecimal[] SALDO = new BigDecimal[] {new BigDecimal(0.8699127),new BigDecimal(0.58870053),new BigDecimal(0.7403748),}; 
		
	/**
	* Generate instance with mock data. 
	*/
	public static Klantbeeld build() {
		return build(0);
	} 
	
	/**
	* Generate List of instances with mock data. 
	*/
	public static List<Klantbeeld> buildList() {
		List<Klantbeeld> result = new ArrayList<>();
		for (int i=0; i<3; i++) {
			result.add(build());
		}
		return result;
	}
	
	private static Klantbeeld build(int nr) {
		Klantbeeld r = new Klantbeeld();
		r.setKlantId(KLANTID[nr]); 
		r.setVoornaam(VOORNAAM[nr]); 
		r.setAchternaam(ACHTERNAAM[nr]); 
		r.setGeboorteDatum(GEBOORTEDATUM[nr]); 
		r.setGeslacht(GESLACHT[nr]); 
		r.setSaldo(SALDO[nr]); 
		r.setAdres(AdresMock.build()); 
		r.setAanspraken(PensioenAanspraakMock.buildList()); 

		return r;
	} 
}

