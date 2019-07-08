package com.flca.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.flca.api.test.TestIniFileParser;
import com.flca.api.test.TestJavaMerge;
import com.flca.api.test.TestSimpleMerge;
import com.flca.api.test.TestSourceCodeUtils;
import com.flca.frw.test.TestConfigurationHelper;
import com.flca.frw.test.TestDatabases;
import com.flca.frw.test.TestFileUtils;
import com.flca.frw.test.TestFlcaContextJsonMapper;
import com.flca.frw.test.TestJsonHelper;
import com.flca.frw.test.TestMisc;
import com.flca.frw.test.TestRandomDataUtils;
import com.flca.frw.test.TestTestParams;
import com.flca.frw.test.TestTransformUtils;
import com.flca.frw.test.TestValidator;

@RunWith(Suite.class)
@SuiteClasses({ 
	//frw junit test
	TestConfigurationHelper.class,
	TestDatabases.class,
	TestFileUtils.class,
	TestFlcaContextJsonMapper.class,
	TestMisc.class,
	TestRandomDataUtils.class,
	TestTestParams.class,
	TestTransformUtils.class,
	TestValidator.class,
	TestJsonHelper.class,

	// api junit testss
	TestIniFileParser.class,
	TestJavaMerge.class,
	TestSimpleMerge.class,
	TestSourceCodeUtils.class,
})
public class AllTestsSuite {
}
