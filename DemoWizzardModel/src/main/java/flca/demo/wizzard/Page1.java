package flca.demo.wizzard;

import java.util.Date;

import mda.type.IWizzardPage;

public class Page1 implements IWizzardPage {

	String firstName;
	String lastName;
	@mda.annotation.wizzard.PageField(label = "date of birth")
	Date dob;
	@mda.annotation.wizzard.PageField(label = "experience")
	int yearsOfJavaExperience;
	
	@Override
	public String getName() {
		return "page1";
	}

	@Override
	public String getTitle() {
		return "Wizzard page 1";
	}

}
