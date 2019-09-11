/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model.mock;
 
import java.math.BigDecimal;

import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.VroegPensioenAanspraak;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.VroegPensioenAanspraakMsg.*;

import protobuf.mapper.*;
  


public class VroegPensioenAanspraakMock {
	
	private static final int[] LOOPTIJD = new int[] {1603698689,-1589826406,2020378606,}; 
	private static final PensioenAanspraakType[] AANSPRAAKTYPE = new PensioenAanspraakType[] {PensioenAanspraakType.TYPE_A,PensioenAanspraakType.TYPE_A,PensioenAanspraakType.TYPE_A,}; 
	private static final BigDecimal[] UITKERING = new BigDecimal[] {new BigDecimal(0.62663496),new BigDecimal(0.32826722),new BigDecimal(0.027767062),}; 
	private static final Date[] EINDDATUM = new Date[] {new Date(),new Date(),new Date(),}; 
		
	/**
	* Generate instance with mock data. 
	*/
	public static VroegPensioenAanspraak build() {
		return build(0);
	} 
	
	/**
	* Generate List of instances with mock data. 
	*/
	public static List<VroegPensioenAanspraak> buildList() {
		List<VroegPensioenAanspraak> result = new ArrayList<>();
		for (int i=0; i<3; i++) {
			result.add(build());
		}
		return result;
	}
	
	private static VroegPensioenAanspraak build(int nr) {
		VroegPensioenAanspraak r = new VroegPensioenAanspraak();
		r.setLooptijd(LOOPTIJD[nr]); 
		r.setAanspraakType(AANSPRAAKTYPE[nr]); 
		r.setUitkering(UITKERING[nr]); 
		r.setEinddatum(EINDDATUM[nr]); 

		return r;
	} 
}

