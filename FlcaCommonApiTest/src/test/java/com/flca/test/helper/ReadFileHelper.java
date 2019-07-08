package com.flca.test.helper;

import java.io.FilenameFilter;
import java.util.List;

import flca.mda.codegen.helpers.FileHelper;
import flca.mda.codegen.helpers.ShellUtils;

public class ReadFileHelper {

	public static String readSourceCode(String aFname) {
		try {
			FilenameFilter fpf = new FileNameFilter(aFname);
			List<String> files = FileUtils.findFiles(ShellUtils.getUserDir(), fpf, true);
			if (files != null && files.size() > 0) {
				return FileHelper.readFile(files.get(0));
			} else {
				throw new Exception("file " + aFname + " not found under " + ShellUtils.getUserDir());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}