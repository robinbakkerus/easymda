package com.flca.api.test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import mda.type.IEntityType;

import org.junit.Assert;
import org.junit.Test;

import com.flca.test.types.TestA;

import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.data.Template;
import flca.mda.codegen.data.TemplateMergeStrategy;
import flca.mda.codegen.helpers.StrUtil;

public class TestSubsProperties {
	@Test
	public void test01() {
		TestA testobj = new TestA();
		Set<SubsValue> subsvals = new HashSet<SubsValue>();
		String filename = generateTargetFilename(testobj.getClass(), makeTemplate(), subsvals);
		System.out.println(filename);
		File f = new File(filename);
		try {
			f.mkdirs();
			f.createNewFile();
			System.out.println("succesfully created " + f.getAbsoluteFile());
			f.delete();
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	protected String generateTargetFilename(Class<?> aElement, ITemplate aTemplate, Set<SubsValue> aSubstitutes) {
		String result = aTemplate.getOutputFilename();
		result = StrUtil.subsProperties(result, aSubstitutes);
		Properties plusprops = new Properties();
		plusprops.put(CodegenConstants.CLASSNAME, aElement.getSimpleName());
		String pkg = aElement.getName().substring(0,
				aElement.getName().length() - (aElement.getSimpleName().length() + 1));
		plusprops.put(CodegenConstants.PACKAGE, pkg);
		result = StrUtil.subsProperties(result, plusprops);
		return result;
	}

	private ITemplate makeTemplate() {
		return new Template("Entity", "/backend/Entity.jet", "backend.Entity", "<%=target-dir%>/<%=src-gen%>",
				"<%=PACKAGE%>", "<%=CLASSNAME%>", ".java", null, new Class[] { IEntityType.class },
				TemplateMergeStrategy.MERGE, 5, null);

	}

}
