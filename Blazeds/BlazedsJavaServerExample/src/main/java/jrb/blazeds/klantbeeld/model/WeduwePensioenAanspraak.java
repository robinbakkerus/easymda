package jrb.blazeds.klantbeeld.model;

import java.sql.Timestamp;

public class WeduwePensioenAanspraak extends PensioenAanspraak {

	private Timestamp partnerOverleden;

	public Timestamp getPartnerOverleden() {
		return partnerOverleden;
	}
 
	public void setPartnerOverleden(Timestamp partnerOverleden) {
		this.partnerOverleden = partnerOverleden;
	} 
}
