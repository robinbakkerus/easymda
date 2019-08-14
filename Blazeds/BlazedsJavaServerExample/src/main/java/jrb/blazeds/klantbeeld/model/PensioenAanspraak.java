package jrb.blazeds.klantbeeld.model;

import java.math.BigDecimal;
import java.util.Date;

public abstract class PensioenAanspraak {

	private PensioenAanspraakType type;
	private BigDecimal uitkering;
	private Date einddatum;
	
	public PensioenAanspraakType getType() {
		return type;
	}
	public void setType(PensioenAanspraakType type) {
		this.type = type;
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
