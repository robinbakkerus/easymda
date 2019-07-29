package jrb.blazeds.klantbeeld.service;

import jrb.blazeds.klantbeeld.model.Klantbeeld;

public class GetKlantbeeldImpl implements GetKlantbeeld {

	@Override
	public Klantbeeld getKlantbeeld(SelectKlantbeeld selectKlantbeeld) {
		return this.makeKlantbeeld();
	}

	Klantbeeld makeKlantbeeld() {
		Klantbeeld r = new Klantbeeld();
		
		r.setAchternaam("Bakkerus");
		
		return r;
	}
}
