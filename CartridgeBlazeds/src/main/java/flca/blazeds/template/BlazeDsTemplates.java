package flca.blazeds.template;

import java.util.ArrayList;
import java.util.List;

import blazeds.BlazeDsCollector;
import blazeds.BlazeDsMapper;
import blazeds.BlazeDsMapperTest;
import blazeds.BlazeDsMockData;
import blazeds.BlazeDsProtobuf;
import blazeds.BlazeDsProtobufMsg;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.Template;
import flca.mda.codegen.data.TemplateMergeStrategy;

public class BlazeDsTemplates {

	public static final String BLAZEDS_CARTRIDGE_NAME = "cartridge-blazeds";
	public static final String BLAZEDS_BRANCH_NAME = "blazeds";

	public static final String BLAZEDS_BACKEND = "Backend";
	private static final String SRCGEN = "<%=Backend%>/src-gen";
	private static final String TSTGEN = "<%=Backend%>/src-gen-test";

	private static List<ITemplate> allTemplates;

	public BlazeDsTemplates() {
		super();
		if (allTemplates == null) {
			allTemplates = this.makeTemplates();
		}
	}

	public List<ITemplate> getAllTemplates() {
		return allTemplates;
	}

	public List<ITemplate> makeTemplates() {
		List<ITemplate> result = new ArrayList<ITemplate>();

		result.add(this.makeCollectorTemplate());
		result.add(this.makeProtobufTemplate());
		result.add(this.makeProtobufMsgTemplate());
		result.add(this.makeMapperTemplate());
		result.add(this.makeMockDataTemplate());
		result.add(this.makeTestMapperTemplate());

		return result;
	}

	/*
	 * this is only template that is visible, it will use all the templates below.
	 */
	private ITemplate makeCollectorTemplate() {
		Template r = new Template();
		r.setName(TidBlazeDs.ALL_FILES.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>");
		// r.setTargetDir(SRCGEN);
		// r.setFileExtension(".proto");
		r.setGeneratorFqn(BlazeDsCollector.class.getName());
		r.setJetPath("/BlazeDsCollector.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BLAZEDS_CARTRIDGE_NAME);
		r.setBranchName(BLAZEDS_BRANCH_NAME);
		r.setApplyToClasses(new Class<?>[] { IBlazeDsService.class });
		r.setRank(5);
		return r;
	}

	private ITemplate makeProtobufTemplate() {
		Template r = new Template();
		r.setName(TidBlazeDs.PROTOBUF.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>");
		r.setTargetDir(SRCGEN);
		r.setFileExtension(".proto");
		r.setGeneratorFqn(BlazeDsProtobuf.class.getName());
		r.setJetPath("/BlazeDsProtobuf.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BLAZEDS_CARTRIDGE_NAME);
		r.setBranchName(BLAZEDS_BRANCH_NAME);
		r.setApplyToClasses(new Class<?>[] {});
		r.setRank(5);
		return r;
	}

	private ITemplate makeProtobufMsgTemplate() {
		Template r = new Template();
		r.setName(TidBlazeDs.PROTOBUF_MSG.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>");
		r.setTargetDir(SRCGEN);
		r.setFileExtension(".proto");
		r.setGeneratorFqn(BlazeDsProtobufMsg.class.getName());
		r.setJetPath("/BlazeDsProtobufMsg.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BLAZEDS_CARTRIDGE_NAME);
		r.setBranchName(BLAZEDS_BRANCH_NAME);
		r.setApplyToClasses(new Class<?>[] {});
		r.setRank(5);
		return r;
	}

	private ITemplate makeMapperTemplate() {
		Template r = new Template();
		r.setName(TidBlazeDs.MAPPER.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>" + "Mapper");
		r.setTargetDir(SRCGEN);
		r.setFileExtension(".java");
		r.setGeneratorFqn(BlazeDsMapper.class.getName());
		r.setJetPath("/BlazeDsMapper.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BLAZEDS_CARTRIDGE_NAME);
		r.setBranchName(BLAZEDS_BRANCH_NAME);
		r.setApplyToClasses(new Class<?>[] {});
		r.setRank(5);
		return r;
	}

	private ITemplate makeMockDataTemplate() {
		Template r = new Template();
		r.setName(TidBlazeDs.MOCKDATA_LOADER.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("Test" + "<%=CLASSNAME%>" + "MockData");
		r.setTargetDir(TSTGEN);
		r.setFileExtension(".java");
		r.setGeneratorFqn(BlazeDsMockData.class.getName());
		r.setJetPath("/BlazeDsMockData.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BLAZEDS_CARTRIDGE_NAME);
		r.setBranchName(BLAZEDS_BRANCH_NAME);
		r.setApplyToClasses(new Class<?>[] {});
		r.setRank(5);
		return r;
	}

	private ITemplate makeTestMapperTemplate() {
		Template r = new Template();
		r.setName(TidBlazeDs.TEST_MAPPER.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>" + "MapperTest");
		r.setTargetDir(TSTGEN);
		r.setFileExtension(".java");
		r.setGeneratorFqn(BlazeDsMapperTest.class.getName());
		r.setJetPath("/BlazeDsTestMapper.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BLAZEDS_CARTRIDGE_NAME);
		r.setBranchName(BLAZEDS_BRANCH_NAME);
		r.setApplyToClasses(new Class<?>[] {});
		r.setRank(5);
		return r;
	}

	public static ITemplate getTemplate(TidBlazeDs aTid) {
		if (allTemplates == null) {
			new BlazeDsTemplates();
		}

		for (ITemplate t : allTemplates) {
			if (t.getName().equals(aTid.name())) {
				return t;
			}
		}
		return null;
	}
}
