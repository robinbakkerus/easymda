package jrb.blazeds.klantbeeld.model;

import java.math.BigDecimal;
import java.util.Date;

public abstract class PensioenAanspraak {

	private PensioenAanspraakType aanspraakType;
	private BigDecimal uitkering;
	private Date einddatum;
	

	public PensioenAanspraakType getAanspraakType() {
		return aanspraakType;
	}
	public void setAanspraakType(PensioenAanspraakType aanspraakType) {
		this.aanspraakType = aanspraakType;
	}
	public BigDecimal getUitkering() {
		return uitkering;
	}
	public void setUitkering(BigDecimal uitkering) {
		this.uitkering = uitkering;
	}
	public Date getEinddatum() {
		return einddatum;
	}
	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}
	
	
}
