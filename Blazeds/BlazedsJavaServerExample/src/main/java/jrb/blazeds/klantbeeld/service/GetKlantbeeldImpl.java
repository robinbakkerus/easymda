package jrb.blazeds.klantbeeld.service;

import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.model.mock.KlantbeeldMock;

public class GetKlantbeeldImpl implements GetKlantbeeld {

	@Override
	public Klantbeeld getKlantbeeld(SelectKlantbeeld selectKlantbeeld) {
		return this.makeKlantbeeld();
	}

	Klantbeeld makeKlantbeeld() { 
		Klantbeeld r = KlantbeeldMock.build();
		return r;
	}
}
