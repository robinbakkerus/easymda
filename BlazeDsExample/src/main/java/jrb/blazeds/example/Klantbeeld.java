package jrb.blazeds.example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Klantbeeld {

	private Integer klantId;
	private String voornaam;
	private String achternaam;
	private Date geboorteDatum;
	private Geslacht geslacht;
	private BigDecimal saldo;
	private Adres adres;
	private List<PensioenAanspraak> aanspraken;
	
	public Integer getKlantId() {
		return klantId;
	}
	public void setKlantId(Integer klantId) {
		this.klantId = klantId;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public Date getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(Date geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Geslacht getGeslacht() {
		return geslacht;
	}
	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}
	public List<PensioenAanspraak> getAanspraken() {
		return aanspraken;
	}
	public void setAanspraken(List<PensioenAanspraak> aanspraken) {
		this.aanspraken = aanspraken;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
}
