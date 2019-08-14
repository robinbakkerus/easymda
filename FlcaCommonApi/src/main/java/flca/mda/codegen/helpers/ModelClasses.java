package flca.mda.codegen.helpers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import flca.mda.api.util.TypeUtils;

public class ModelClasses {
	private static Map<Class<?>, List<Class<?>>> sSuperClassesMap;
	private static Map<Class<?>, List<Class<?>>> sSubClassesMap;
	private static Set<Class<?>> sAllClasses;

	public static void initialize(File modelDir) {
		List<File> modelDirs = new ArrayList<>();
		modelDirs.add(modelDir);
		fillClassMaps(modelDirs);
	}

	public static void initialize(List<File> modelDirs) {
		fillClassMaps(modelDirs);
	}

	public static List<Class<?>> getAllSuperClasses(Class<?> aFromClass) {
		if (sSuperClassesMap == null) {
			throw new RuntimeException("ModelClasses,initialize(modeldir) should be called first");
		}

		List<Class<?>> result = sSuperClassesMap.get(aFromClass);
		return result == null ? new ArrayList<>() : result;
	}

	public static List<Class<?>> getAllSubClasses(Class<?> aFromClass) {
		if (sSubClassesMap == null) {
			throw new RuntimeException("ModelClasses,initialize(modeldir) should be called first");
		}

		List<Class<?>> result = sSubClassesMap.get(aFromClass);
		return result == null ? new ArrayList<>() : result;
	}

	public static Set<Class<?>> getAllClasses() {
		if (sAllClasses == null) {
			throw new RuntimeException("ModelClasses,initialize(modeldir) should be called first");
		}

		return sAllClasses;
	}

	private static void fillClassMaps(List<File> modelDirs) {
		System.out.println(modelDirs);
		sAllClasses = new HashSet<>();
		sSubClassesMap = new HashMap<>();
		sSuperClassesMap = new HashMap<>();
		for (File dir : modelDirs) {
			fillTheClassMaps(dir);
		}
	}

	private static void fillTheClassMaps(File modelDir) {
		Helper helper = new Helper(modelDir);
		Collection<String> allResources = helper.getResourcesFromDirectory(modelDir);
		sAllClasses.addAll(helper.findAllModelClasses(allResources));
		sSuperClassesMap.putAll(helper.buildSuperClassesMap(sAllClasses));
		sSubClassesMap.putAll(helper.buildSubclassesMap(sSuperClassesMap));
	}
}

//---------------------------------------------------------------------------------------
class Helper {
	final File modelDir;
	final ClassLoader cl;
	final TypeUtils tu = new TypeUtils();

	public Helper(File modelDir) {
		super();
		this.modelDir = modelDir;
		this.cl = this.makeClassloader();
	}

	Map<Class<?>, List<Class<?>>> buildSuperClassesMap(final Set<Class<?>> allClasses) {
		Map<Class<?>, List<Class<?>>> r = new HashMap<>();
		for (Class<?> clz : allClasses) {
			List<Class<?>> superTypes = tu.getAllSuperTypes(clz);
			for (Class<?> supertyp : superTypes) {
				List<Class<?>> superClassList = r.containsKey(clz) ? r.get(clz) : new ArrayList<>();
				superClassList.add(supertyp);
				if (!r.containsKey(clz)) {
					r.put(clz, superClassList);
				}
			}
		}
		return r;
	}

	Map<Class<?>, List<Class<?>>> buildSubclassesMap(final Map<Class<?>, List<Class<?>>> superClassesMap) {
		Map<Class<?>, List<Class<?>>> r = new HashMap<>();
		int maxDepth = this.maxDepth(superClassesMap);
		for (Map.Entry<Class<?>, List<Class<?>>> entry : superClassesMap.entrySet()) {
			List<Class<?>> list = entry.getValue();
			for (int i = maxDepth; i >= 0; i--) {
				if (list.size() > i) {
					Class<?> clz = list.get(i);
					List<Class<?>> subtypes = r.containsKey(clz) ? r.get(clz) : new ArrayList<>();
					this.addSubtypes(list, entry.getKey(), subtypes, i-1);
					if (!r.containsKey(clz)) {
						r.put(clz, subtypes);
					}
				}
			}
		}

		return r;
	}
	
	private void addSubtypes(final List<Class<?>> list, final Class<?> mapType,  final List<Class<?>> result, final int fromIndex) {
		for (int i = 0; i < fromIndex; i++) {
			if (!result.contains(list.get(i))) {
				result.add(0, list.get(i));
			}
		}
		if (!result.contains(mapType)) {
			result.add(0, mapType);
		}
	}

	private int maxDepth(final Map<Class<?>, List<Class<?>>> superClassesMap) {
		int r = 0;
		for (Map.Entry<Class<?>, List<Class<?>>> list : superClassesMap.entrySet()) {
			if (list.getValue().size() > r) {
				r = list.getValue().size();
			}
		}
		return r;
	}

	Set<Class<?>> findAllModelClasses(final Collection<String> allResources) {
		Set<Class<?>> r = new HashSet<>();
		int at = modelDir.getAbsolutePath().length() + 1;
		for (String res : allResources) {
			String classname = res.substring(at, res.length() - 6);
			classname = classname.replace('\\', '.');
			classname = classname.replace('/', '.');
			loadAndAddClass(r, classname);
		}
		return r;
	}

	private void loadAndAddClass(Set<Class<?>> r, String classname) {
		try {
			r.add(cl.loadClass(classname));
		} catch (ClassNotFoundException | NoClassDefFoundError e) {
			System.out.println(e);
		} 
	}

	Collection<String> getResourcesFromDirectory(final File directory) {
		final ArrayList<String> result = new ArrayList<String>();
		final File[] fileList = directory.listFiles();
		if (fileList == null) {
			return result;
		}
		
		for (final File file : fileList) {
			if (file.isDirectory()) {
				result.addAll(getResourcesFromDirectory(file));
			} else {
				try {
					final String fileName = file.getCanonicalPath();
					if (fileName.endsWith(".class")) {
						result.add(fileName);
					}
				} catch (final IOException e) {
					throw new Error(e);
				}
			}
		}
		return result;
	}

	ClassLoader makeClassloader() {
		try {
			URL url;
			url = this.modelDir.toURI().toURL();
			URL urls[] = new URL[] { url };
			ClassLoader cl = new URLClassLoader(urls);
			return cl;
		} catch (MalformedURLException e) {
			throw new RuntimeException("Wrong modeldir " + e);
		}
	}
}
