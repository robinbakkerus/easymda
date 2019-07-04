package jrb.blazeds;

import flca.blazeds.template.IBlazeDsService;
import jrb.blazeds.example.GetKlantbeeld;

public class KlantbeeldSelector implements IBlazeDsService {

	@Override
	public Class<?> getBlazeDsService() {
		return GetKlantbeeld.class;
	}

}
