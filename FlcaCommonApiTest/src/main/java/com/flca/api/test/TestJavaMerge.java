package com.flca.api.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.flca.frw.util.FileUtils;
import com.flca.frw.util.ShellUtils;
import com.flca.mda.codegen.helpers.LogHelper;
import com.flca.mda.codegen.helpers.SimpleClibboardHelper;

/**
 * class to the JavaMerge TODO
 * @author nly36776
 *
 */
public class TestJavaMerge {

	private static final String rootdir = ShellUtils.getCurrentDir();
	
	@Test @Ignore
	public void testMerge01() {
		try {
			String r = merge(getNewCode(1), new FileInputStream(new File(getOldFilename(1))));
			FileUtils.saveFile(getMergedFilename(1), r);
			new SimpleClibboardHelper().save(r);
			System.out.println("saved to clipboard");
		} catch(Throwable t) {
			Assert.fail("error " + t);
		}
	}

	/**
	 * here we merge the 'generated' code (that is coming from file in the test-merge/new folder, with existing code.
	 * The existing code can be found under src-test/test/merge
	 * Note that the target is also the existing code! but we will overwrite it.
	 * @param newCode
	 * @param oldCode
	 * @return
	 */
	private String merge(String newCode, InputStream oldCode) {
		try {
			JMerger merger = getJMerger();
			merger.setSourceCompilationUnit(merger.createCompilationUnitForContents(newCode));
			merger.setTargetCompilationUnit(merger.createCompilationUnitForInputStream(oldCode));
			merger.merge();

			return merger.getTargetCompilationUnitContents();
		} catch (Throwable e) {
			LogHelper.error("error merging: ", e);
			throw new RuntimeException(e);
		}
	}
	
	
	protected JMerger getJMerger()
	{
		JControlModel jControlModel = new JControlModel();
		jControlModel.initialize(CodeGenUtil.instantiateFacadeHelper(JMerger.DEFAULT_FACADE_HELPER_CLASS), getMergeXml());
		JMerger jmerger = new JMerger(jControlModel);
		return jmerger;
	}
	
	private String getOldFilename(int nr)  {
		return rootdir + "/resources/test-merge/old/TestMerge" + nr + ".java";
	}

	private String getNewFilename(int nr)  {
		return rootdir + "/resources/test-merge/new/TestMerge" + nr + ".java";
	}

	private String getMergedFilename(int nr)  {
		return rootdir + "/resources/test-merge/merged/TestMerge" + nr + ".java";
	}

	private String getMergeXml() {
		return rootdir + "resources/test-merge/merge.xml" ;
	}
	
//	private String getOldCode(int nr) throws Exception {
//		return FileUtils.readFile(getOldFilename(nr));
//	}
//	
	private String getNewCode(int nr) throws Exception {
		return FileUtils.readFile(getNewFilename(nr));
	}

}
