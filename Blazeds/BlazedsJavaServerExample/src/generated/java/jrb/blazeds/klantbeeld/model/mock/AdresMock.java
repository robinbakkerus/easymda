/* generated with EasyMda  */

package jrb.blazeds.klantbeeld.model.mock;
 
import java.util.*;

import java.util.stream.Collectors;

import jrb.blazeds.klantbeeld.model.Adres;

import jrb.grpc.blazeds.*;

import jrb.grpc.blazeds.AdresMsg.*;

import protobuf.mapper.*;
  


public class AdresMock {
	
	private static final String[] ADRES = new String[] {"Jivtwfeul","Vvxicnoug","Dlyjgv",}; 
	private static final String[] POSTCODE = new String[] {"Buelxbo","Lwtxvtpx","Svdjpknvc",}; 
	private static final String[] PLAATS = new String[] {"Qwppobv","Ductcexcq","Apjntric",}; 
		
	/**
	* Generate instance with mock data. 
	*/
	public static Adres build() {
		return build(0);
	} 
	
	/**
	* Generate List of instances with mock data. 
	*/
	public static List<Adres> buildList() {
		List<Adres> result = new ArrayList<>();
		for (int i=0; i<3; i++) {
			result.add(build());
		}
		return result;
	}
	
	private static Adres build(int nr) {
		Adres r = new Adres();
		r.setAdres(ADRES[nr]); 
		r.setPostcode(POSTCODE[nr]); 
		r.setPlaats(PLAATS[nr]); 

		return r;
	} 
}

