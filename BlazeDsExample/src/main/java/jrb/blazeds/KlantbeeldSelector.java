package jrb.blazeds;

import jrb.blazeds.example.GetKlantbeeld;
import mda.template.type.IBlazeDsService;

public class KlantbeeldSelector implements IBlazeDsService {

	@Override
	public Class<?> getBlazeDsService() {
		return GetKlantbeeld.class;
	}

}
