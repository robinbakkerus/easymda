package flca.demo.wizzard;

import mda.type.IWizzardPage;

public class Page2 implements IWizzardPage {

	String emailAddress;
	
	@Override
	public String getName() {
		return "page2";
	}

	@Override
	public String getTitle() {
		return "Wizzard page 2";
	}

}
