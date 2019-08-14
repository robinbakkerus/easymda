package blazeds.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import flca.mda.codegen.data.SubsValue;
import flca.test.base.AbstractTestTemplatesData;

public class BlazedsTestData extends AbstractTestTemplatesData {
	String MODEL_DIR = "BlazedsJavaServerExample";
	String CARTRIDGE_DIR = "BlazedsCartridge";
	String PLUGIN_DIR = "FlcaGenerator";

	@Override
	public File getPluginDir() {
		return new File(getProjectDir() + "/" + this.PLUGIN_DIR);
	}

	@Override
	public File getModelDir() {
		return new File(getProjectDir() + "/" + this.MODEL_DIR + "/bin/main");
	}

	@Override
	public File getTemplateDir() {
		return new File(getProjectDir() + "/" + this.CARTRIDGE_DIR);
	}

	private final static String BACKEND_SRC_GEN = "C:/tmp";
//			FileUtils.getTempDir() + "/src-wizard-generated";

	@Override
	public List<SubsValue> getSubsvalues() {
		List<SubsValue> r = new ArrayList<SubsValue>();
		r.add(new SubsValue("Backend", BACKEND_SRC_GEN));
		return r;
	}
}
