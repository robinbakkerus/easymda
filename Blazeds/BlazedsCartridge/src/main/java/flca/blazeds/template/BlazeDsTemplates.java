package flca.blazeds.template;

import java.util.ArrayList;
import java.util.List;

import blazeds.BlazeDsCollector;
import blazeds.BlazeDsMapper;
import blazeds.BlazeDsMapperTest;
import blazeds.BlazeDsMockDataDart;
import blazeds.BlazeDsMockDataJava;
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

	private static final Template.Builder TB = Template.builder().withBranchName(BLAZEDS_BRANCH_NAME)
			.withCartridgeName(BLAZEDS_CARTRIDGE_NAME).withMergeStrategy(TemplateMergeStrategy.SKIP)
			.withTargetDir(SRCGEN).withPackage("<%=PACKAGE%>").withClassname("<%=CLASSNAME%>");

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
		result.add(this.makeMockDataJavaTemplate());
		result.add(this.makeMockDataDartTemplate());
		result.add(this.makeTestMapperTemplate());

		return result;
	}

	/*
	 * this is only template that is visible, it will use all the templates below.
	 */
	private ITemplate makeCollectorTemplate() {
		return TB.withName(TidBlazeDs.ALL_FILES.name()).withGenerator(BlazeDsCollector.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsCollector.jet").build();
	}

	private ITemplate makeProtobufTemplate() {
		return TB.withName(TidBlazeDs.PROTOBUF.name()).withGenerator(BlazeDsProtobuf.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsProtobuf.jet").build();
	}

	private ITemplate makeProtobufMsgTemplate() {
		return TB.withName(TidBlazeDs.PROTOBUF_MSG.name()).withGenerator(BlazeDsProtobufMsg.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsProtobufMsg.jet").build();
	}

	private ITemplate makeMapperTemplate() {
		return TB.withName(TidBlazeDs.MAPPER.name()).withGenerator(BlazeDsMapper.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsMapper.jet").build();
	}

	private ITemplate makeMockDataJavaTemplate() {
		return TB.withName(TidBlazeDs.MOCKDATA_LOADER.name()).withGenerator(BlazeDsMockDataJava.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsMockDataJava.jet")
				.withPackage(TSTGEN).build();
	}

	private ITemplate makeMockDataDartTemplate() {
		return TB.withName(TidBlazeDs.MOCKDATA_LOADER.name()).withGenerator(BlazeDsMockDataDart.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsMockDataDart.jet")
				.withPackage(TSTGEN).build();
	}
	
	private ITemplate makeTestMapperTemplate() {
		return TB.withName(TidBlazeDs.TEST_MAPPER.name()).withGenerator(BlazeDsMapperTest.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsTestMapper.jet")
				.withPackage(TSTGEN).build();
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
