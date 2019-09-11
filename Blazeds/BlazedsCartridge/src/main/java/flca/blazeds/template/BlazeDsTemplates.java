package flca.blazeds.template;

import java.util.ArrayList;
import java.util.List;

import blazeds.BlazeDsCollector;
import blazeds.BlazeDsMapper;
import blazeds.BlazeDsMapperTest;
import blazeds.BlazeDsMockDataDart;
import blazeds.BlazeDsMockDataJavaGrpc;
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
		result.add(this.makeMockDataJavaTemplate());
		result.add(this.makeMockDataJavaGrpcTemplate());
		result.add(this.makeMockDataDartTemplate());
		result.add(this.makeTestMapperTemplate());
		result.add(this.makeOpenApiTemplate());
		result.add(this.makeSwaggerTemplate());

		return result;
	}

	// the default template builder.
	private Template.Builder tb() {
		return Template.builder().withBranchName(BLAZEDS_BRANCH_NAME).withCartridgeName(BLAZEDS_CARTRIDGE_NAME)
				.withMergeStrategy(TemplateMergeStrategy.OVERWRITE).withTargetDir(SRCGEN).withPackage("<%=PACKAGE%>")
				.withClassname("<%=CLASSNAME%>").withExtension(".java").withApplyTo(new Class[] {});
	}

	/*
	 * this is the only template that is visible, it will use all the templates
	 * below.
	 */
	private ITemplate makeCollectorTemplate() {
		return this.tb().withName(TidBlazeDs.ALL_FILES.name()).withGenerator(BlazeDsCollector.class)
				.withApplyTo(new Class<?>[] { IBlazeDsService.class }).withJetPath("/BlazeDsCollector.jet").build();
	}

	private ITemplate makeProtobufTemplate() {
		return this.tb().withName(TidBlazeDs.PROTOBUF.name()).withGenerator(BlazeDsProtobuf.class)
				.withExtension(".proto").withJetPath("/BlazeDsProtobuf.jet").build();
	}

	private ITemplate makeProtobufMsgTemplate() {
		return this.tb().withName(TidBlazeDs.PROTOBUF_MSG.name()).withGenerator(BlazeDsProtobufMsg.class)
				.withJetPath("/BlazeDsProtobufMsg.jet").build();
	}

	private ITemplate makeMapperTemplate() {
		return this.tb().withName(TidBlazeDs.MAPPER.name()).withGenerator(BlazeDsMapper.class)
				.withClassname("<%=CLASSNAME%>" + "Mapper").withJetPath("/BlazeDsMapper.jet").build();
	}

	private ITemplate makeMockDataJavaGrpcTemplate() {
		return this.tb().withName(TidBlazeDs.MOCK_JAVA_GRPC.name()).withGenerator(BlazeDsMockDataJavaGrpc.class)
				.withClassname("<%=CLASSNAME%>" + "Mock").withJetPath("/BlazeDsMockDataJavaGrpc.jet").withTargetDir(TSTGEN)
				.build();
	}

	private ITemplate makeMockDataJavaTemplate() {
		return this.tb().withName(TidBlazeDs.MOCK_JAVA.name()).withGeneratorFqn("blazeds.BlazeDsMockDataJava")
				.withClassname("<%=CLASSNAME%>" + "Mock").withJetPath("/BlazeDsMockDataJava.jet").withTargetDir(TSTGEN)
				.withPackage("<%=PACKAGE%>.mock").build();
	}

	private ITemplate makeMockDataDartTemplate() {
		return this.tb().withName(TidBlazeDs.MOCK_DART.name()).withGenerator(BlazeDsMockDataDart.class)
				.withExtension(".dart").withJetPath("/BlazeDsMockDataDart.jet").build();
	}

	private ITemplate makeTestMapperTemplate() {
		return this.tb().withName(TidBlazeDs.TEST_MAPPER.name()).withGenerator(BlazeDsMapperTest.class)
				.withClassname("<%=CLASSNAME%>" + "MapperTest").withJetPath("/BlazeDsTestMapper.jet")
				.withTargetDir(TSTGEN).build();
	}

	private ITemplate makeOpenApiTemplate() {
		return this.tb().withName(TidBlazeDs.OPEN_API_YAML.name())
				.withGeneratorFqn("blazeds.BlazeDsOpenApi").withExtension(".yaml")
				.withJetPath("/BlazeDsOpenApi.jet").build();
	}

	private ITemplate makeSwaggerTemplate() {
		return this.tb().withName(TidBlazeDs.SWAGGER.name())
				.withGeneratorFqn("blazeds.BlazeDsSwagger").withExtension(".yaml")
				.withJetPath("/BlazeDsOpenApi.jet").build();
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
