package mda.template;

import java.util.ArrayList;
import java.util.List;

import mapper.Mappers;
import mapper.TOMapper;
import mapper.TestTOMapper;
import mda.template.type.ITOMapper;
import mda.template.type.IWSOMapper;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.Template;
import flca.mda.codegen.data.TemplateMergeStrategy;


public class BeanMapperConstants {

	public static final String BEANMAPPER_CARTRIDGE_NAME = "cartridge-beanmapper";
	public static final String BEANMAPPER_BRANCH_NAME = "beanmappers";

	public static final String BEANMAPPER_BACKEND = "Backend"; 
	private static final String SRCGEN = "<%=Backend%>/src-gen";
	private static final String TSTGEN = "<%=Backend%>/src-gen-test";

	private static List<ITemplate> allTemplates;

	public BeanMapperConstants() {
		super();
		if (allTemplates == null) {
			allTemplates = makeTemplates();
		}
	}

	public List<ITemplate> getAllTemplates() {
		return allTemplates;
	}
	
	public List<ITemplate> makeTemplates() {
		List<ITemplate> result = new ArrayList<ITemplate>();
		
		result.add(makeTemplateMappers());
		result.add(makeTemplateTOMapper());
		result.add(makeTemplateTestTOMapper());
		
		return result;
	}



	private ITemplate makeTemplateMappers() {
		Template r = new Template();
		r.setName(TidBeanMapper.BEANMAPPERS.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>");
		r.setTargetDir(SRCGEN);
		r.setFileExtension(".java");
		r.setGeneratorFqn(Mappers.class.getName());
		r.setJetPath("/Mappers.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BEANMAPPER_CARTRIDGE_NAME);
		r.setBranchName(BEANMAPPER_BRANCH_NAME);
		r.setApplyToClasses(new Class<?> [] {ITOMapper.class,IWSOMapper.class});
		r.setRank(5);
		return r;
	}

	private ITemplate makeTemplateTOMapper() {
		Template r = new Template();
		r.setName(TidBeanMapper.BEANMAPPER_TO.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("<%=CLASSNAME%>" + "Mapper");
		r.setTargetDir(SRCGEN);
		r.setFileExtension(".java");
		r.setGeneratorFqn(TOMapper.class.getName());
		r.setJetPath("/mapper/TOMapper.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BEANMAPPER_CARTRIDGE_NAME);
		r.setBranchName(BEANMAPPER_BRANCH_NAME);
		r.setApplyToClasses(new Class<?> [] {});
		r.setRank(5);
		return r;
	}
	
	private ITemplate makeTemplateTestTOMapper() {
		Template r = new Template();
		r.setName(TidBeanMapper.TEST_BEANMAPPER_TO.name());
		r.setPackage("<%=PACKAGE%>");
		r.setClassname("Test" + "<%=CLASSNAME%>" + "Mapper");
		r.setTargetDir(TSTGEN);
		r.setFileExtension(".java");
		r.setGeneratorFqn(TestTOMapper.class.getName());
		r.setJetPath("/mapper/TestTOMapper.jet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(BEANMAPPER_CARTRIDGE_NAME);
		r.setBranchName(BEANMAPPER_BRANCH_NAME);
		r.setApplyToClasses(new Class<?> [] {});
		r.setRank(5);
		return r;
	}
	

	public static ITemplate getTemplate(TidBeanMapper aTid) {
		if (allTemplates == null) {
			new BeanMapperConstants(); 
		}

		for (ITemplate t : allTemplates) {
			if (t.getName().equals(aTid.name())) {
				return t;
			}
		}
		return null;
	}
}
