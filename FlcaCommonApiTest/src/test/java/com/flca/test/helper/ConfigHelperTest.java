package com.flca.test.helper;

import java.util.List;

import com.flca.frw.config.ConfigBase;
import com.flca.test.TestConstants;

public class ConfigHelperTest extends ConfigBase implements TestConstants {

	public ConfigHelperTest(List<String> aConfigFileSystemDirs,
							List<String> aConfigClasspathDirs,
							List<String> aConfigScanJars) {
		
		super(aConfigFileSystemDirs, aConfigClasspathDirs, aConfigScanJars);
	}

}
