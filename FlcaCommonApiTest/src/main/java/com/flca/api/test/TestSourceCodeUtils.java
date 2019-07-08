package com.flca.api.test;

import java.util.Collection;

import org.junit.Test;

import com.flca.frw.util.ShellUtils;
import com.flca.test.helper.ReadFileHelper;

import flca.mda.codegen.helpers.SourceCodeUtils;

public class TestSourceCodeUtils {

	private static final String FNAME1 = "TestSourceCodeUtils.java";
	private static final String FNAME2 = "ClassWithJpaAnnotations.java";

	private static SourceCodeUtils su = new SourceCodeUtils();

	@Test
	public void getFormattedSourcecodeLines() {
		System.out.println("----- getFormattedSourcecodeLines() ----------");
		System.out.println(ShellUtils.getUserDir());
		String source = ReadFileHelper.readSourceCode(FNAME1);

		Collection<String> lines = su.getFormattedSourcecodeLines(source);
		for (String line : lines) {
			System.out.println(line);
		}
	}

	@Test
	public void getSourcecodeLines() {
		System.out.println("----- getAllImports() ----------");
		String source = ReadFileHelper.readSourceCode(FNAME1);
		Collection<String> importStmts = su.getAllImports(source);
		for (String line : importStmts) {
			System.out.println(line);
		}
	}

	@Test
	public void getImports() {
		System.out.println("----- getImports() ----------");
		String source = ReadFileHelper.readSourceCode(FNAME1);
		Collection<Class<?>> importStmts = su.getImports(source);
		for (Class<?> clazz : importStmts) {
			System.out.println(clazz);
		}
		System.out.println("");
	}

	@Test
	public void getClassAnnotations() {
		System.out.println("----- getClassAnnotations() ----------");
		String source = ReadFileHelper.readSourceCode(FNAME2);
		Collection<String> annots = su.getClassAnnotations(source);
		for (String a : annots) {
			System.out.println(a);
		}
		System.out.println("");
	}

}
