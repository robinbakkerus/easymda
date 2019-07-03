package mda.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mda.type.IRegisterTemplates;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.data.SubsValueType;
import flca.mda.codegen.data.TemplatesBranch;
import flca.mda.common.api.helpers.RegisterTemplatesHelper;

public class RegisterBeanMapperTemplates implements IRegisterTemplates {
	
	private final static SubsValueType FOLDER = SubsValueType.FOLDER;
	
	@Override
	public String getName() {
		return BeanMapperConstants.BEANMAPPER_CARTRIDGE_NAME;
	}

	@Override
	public String getDescription() {
		return "cartrdige to generate to-from mappers";
	}

	@Override
	public List<TemplatesBranch> getTemplateBranches() {
		List<TemplatesBranch> result = new ArrayList<TemplatesBranch>();

		result.add(makeBranch(BeanMapperConstants.BEANMAPPER_BRANCH_NAME, getDescription()));

		return result;
	}
	
	private TemplatesBranch makeBranch(String aName, String aDescr) {
		TemplatesBranch result = new TemplatesBranch();
		result.setName(aName);
		result.setDescription(aDescr);

		for (ITemplate template : new BeanMapperConstants().getAllTemplates()) {
			result.addTemplate(template);
		}

		return result;
	}


	@Override
	public Collection<SubsValue> getSubstituteValues() {
		Collection<SubsValue> result = new ArrayList<SubsValue>();
		result.add(makeSubsValue(BeanMapperConstants.BEANMAPPER_BACKEND, FOLDER, "target directory ", 3, null ));
		return result;
	}
	
	private SubsValue makeSubsValue(String name, SubsValueType type, String help, 
			int rank, String aDefaultValue) {
		return RegisterTemplatesHelper.makeSubsValue(name, type, name, help, rank, aDefaultValue, 
				new String[] {BeanMapperConstants.BEANMAPPER_CARTRIDGE_NAME});
	}


	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public void doBefore() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doAfter() {
		// TODO Auto-generated method stub
	}

	
}
