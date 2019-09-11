/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model.mock;
 
import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.WeduwePensioenAanspraak;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.WeduwePensioenAanspraakMsg.*;

import protobuf.mapper.*;
  


public class WeduwePensioenAanspraakMock {
	
	private static final Timestamp[] PARTNEROVERLEDEN = new Timestamp[] {new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),}; 
	private static final PensioenAanspraakType[] AANSPRAAKTYPE = new PensioenAanspraakType[] {PensioenAanspraakType.TYPE_A,PensioenAanspraakType.TYPE_A,PensioenAanspraakType.TYPE_A,}; 
	private static final BigDecimal[] UITKERING = new BigDecimal[] {new BigDecimal(0.1624803),new BigDecimal(0.5851679),new BigDecimal(0.49743092),}; 
	private static final Date[] EINDDATUM = new Date[] {new Date(),new Date(),new Date(),}; 
		
	/**
	* Generate instance with mock data. 
	*/
	public static WeduwePensioenAanspraak build() {
		return build(0);
	} 
	
	/**
	* Generate List of instances with mock data. 
	*/
	public static List<WeduwePensioenAanspraak> buildList() {
		List<WeduwePensioenAanspraak> result = new ArrayList<>();
		for (int i=0; i<3; i++) {
			result.add(build());
		}
		return result;
	}
	
	private static WeduwePensioenAanspraak build(int nr) {
		WeduwePensioenAanspraak r = new WeduwePensioenAanspraak();
		r.setPartnerOverleden(PARTNEROVERLEDEN[nr]); 
		r.setAanspraakType(AANSPRAAKTYPE[nr]); 
		r.setUitkering(UITKERING[nr]); 
		r.setEinddatum(EINDDATUM[nr]); 

		return r;
	} 
}

