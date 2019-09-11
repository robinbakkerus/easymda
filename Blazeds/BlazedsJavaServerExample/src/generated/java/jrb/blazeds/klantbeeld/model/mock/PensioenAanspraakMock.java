/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model.mock;
 
import java.math.BigDecimal;

import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.PensioenAanspraak;
import jrb.blazeds.klantbeeld.model.PensioenAanspraakType;
import jrb.blazeds.klantbeeld.model.VroegPensioenAanspraak;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.PensioenAanspraakMsg.*;

import protobuf.mapper.*;
  


public class PensioenAanspraakMock {
	
	private static final int[] LOOPTIJD = new int[] {-1044029654,-546772724,156263726,}; 
	private static final PensioenAanspraakType[] AANSPRAAKTYPE = new PensioenAanspraakType[] {PensioenAanspraakType.TYPE_A,PensioenAanspraakType.TYPE_A,PensioenAanspraakType.TYPE_A,}; 
	private static final BigDecimal[] UITKERING = new BigDecimal[] {new BigDecimal(0.34338582),new BigDecimal(0.15727258),new BigDecimal(0.7774321),}; 
	private static final Date[] EINDDATUM = new Date[] {new Date(),new Date(),new Date(),}; 
		
	/**
	* Generate instance with mock data. 
	*/
	public static PensioenAanspraak build() {
		return build(0);
	} 
	
	/**
	* Generate List of instances with mock data. 
	*/
	public static List<PensioenAanspraak> buildList() {
		List<PensioenAanspraak> result = new ArrayList<>();
		for (int i=0; i<3; i++) {
			result.add(build());
		}
		return result;
	}
	
	private static PensioenAanspraak build(int nr) {
		VroegPensioenAanspraak r = new VroegPensioenAanspraak();
		r.setLooptijd(LOOPTIJD[nr]); 
		r.setAanspraakType(AANSPRAAKTYPE[nr]); 
		r.setUitkering(UITKERING[nr]); 
		r.setEinddatum(EINDDATUM[nr]); 

		return r;
	} 
}

