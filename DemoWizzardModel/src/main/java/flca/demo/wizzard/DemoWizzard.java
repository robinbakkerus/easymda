package flca.demo.wizzard;

import java.util.ArrayList;
import java.util.List;

import mda.type.IWizzard;
import mda.type.IWizzardPage;

public class DemoWizzard implements IWizzard
{

	@Override
	public String getImage() {
		return "./icons/icon.jpg";
	}

	@Override
	public List<IWizzardPage> getPages() {
		List<IWizzardPage> result = new ArrayList<IWizzardPage>();
		result.add(new Page1());
		result.add(new Page2());
		return result;
	}

	@Override
	public String getTitle() {
		return "Demo Wizzard";
	}
	

}
