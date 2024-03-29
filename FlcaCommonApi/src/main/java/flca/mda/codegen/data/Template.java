package flca.mda.codegen.data;

import java.util.List;

import flca.mda.api.util.TypeUtils;
import flca.mda.codegen.helpers.StrUtil;
import mda.type.IAnyClass;
import mda.type.IAnyEnum;
import mda.type.IAnyInterface;

public class Template implements ITemplate {
	private String name;
	private String jetPath;
	private String targetDir;
	private String pack;
	private String classname;
	private String extension;
	private String generatorFqn;
	private String insertionTag;
	private Class<?> applyToClasses[];
	private TemplateMergeStrategy mergeStrategy;
	private int rank = 5;
	private String cartridgeName;
	private String branchName;
	private ITemplateHooks hooks;
	
	private static TypeUtils tu = new TypeUtils();

	public Template() {
	}

	public Template(String aName, String aJetPath, String aGeneratorFqn, String aTargetDir, String aPackage,
			String aClassName, String aFileExt, String aInsertionTag, Class<?> aApplyToClasses[],
			TemplateMergeStrategy aMergeStrategy, int aRank, String aCartridgeName) {
	
		name = aName;
		jetPath = aJetPath;
		generatorFqn = aGeneratorFqn;
		targetDir = aTargetDir;
		pack = aPackage;
		classname = aClassName;
		extension = aFileExt;
		insertionTag = aInsertionTag;
		applyToClasses = aApplyToClasses;
		mergeStrategy = aMergeStrategy;
		rank = aRank;
		cartridgeName = aCartridgeName;
		
	}
	

	public static Template.Builder builder() {
		return new Template.Builder();
	}

	public static Template.Builder builder(String name, Class<?> generator) {
		return new Template.Builder(name, generator);
	}

	public static Template.Builder builder(String name, Class<?> generator, Builder builder) {
		return new Template.Builder(name, generator, builder);
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getJetPath() {
		return jetPath;
	}

	public void setJetPath(String jetPath) {
		this.jetPath = jetPath;
	}

	@Override
	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	@Override
	public String getPackage() {
		return pack;
	}

	public void setPackage(String pack) {
		this.pack = pack;
	}

	@Override
	public String getClassname() {
		if (classname != null) {
			int at = classname.indexOf("#");
			if (at > 0) {
				return classname.substring(0, at);
			} else {
				return classname;
			}
		}
		return null;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String getFileExtension() {
		return extension;
	}

	public void setFileExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public Class<?>[] getApplyToClasses() {
		return applyToClasses;
	}

	public void setApplyToClasses(Class<?>[] applyToClasses) {
		this.applyToClasses = applyToClasses;
	}

	public TemplateMergeStrategy getMergeStrategy() {
		return mergeStrategy;
	}

	public void setMergeStrategy(TemplateMergeStrategy mergeStrategy) {
		this.mergeStrategy = mergeStrategy;
	}

	@Override
	public String getGeneratorFqn() {
		return generatorFqn;
	}

	public void setGeneratorFqn(String generatorFqn) {
		this.generatorFqn = generatorFqn;
	}

	@Override
	public String getOutputFilename() {
		String d = "/";
		String usepck = StrUtil.replace(pack, ".", d);
		String ext = extension;
		if (ext != null && ext.startsWith(".")) {
			ext = ext.substring(1);
		} else {
			ext = ".java"; // TODO
		}
		return getTargetDir() + d + usepck + d + getClassname() + "." + ext;
	}
	

	@Override
	public boolean appliesTo(Class<?> aTestClass) {
		if (applyToClasses != null && aTestClass != null) {
			List<Class<?>> superTypes = tu.getAllSuperTypes(aTestClass);
			for (Class<?> applyToClz : applyToClasses) {
				if (aTestClass.equals(applyToClz)) {
					return true;
				} else {
					for (Class<?> clz : superTypes) {
						if (applyToClz.getName().equals(clz.getName())) {
							return true;
						}
					}
				}
			}

			// if we here, the 'normal' rules dont apply, maybe we have an
			// IAnyXxx type rule
			for (Class<?> applyToClz : applyToClasses) {
				if (aTestClass.isInterface()) {
					return (applyToClz.getName().equals(IAnyInterface.class.getName()));
				} else if (aTestClass.isEnum()) {
					return (applyToClz.getName().equals(IAnyEnum.class.getName()));
				} else {
					return (applyToClz.getName().equals(IAnyClass.class.getName()));
				}
			}
		}
		return false;
	}

	@Override
	public int getRank() {
		return rank;
	}

	
	@Override
	public String getCartridgeName() {
		return cartridgeName;
	}

	public void setCartridgeName(String aCartridgeName) {
		cartridgeName = aCartridgeName;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(ITemplate o) {
		return Integer.valueOf(getRank()).compareTo(Integer.valueOf(o.getRank()));
	}

	@Override
	public String getInsertionTag() {
		return insertionTag;
	}

	public void setInsertionTag(String insertionTag) {
		this.insertionTag = insertionTag;
	}
	
	@Override
	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String aValue) {
		this.branchName = aValue;
	}

	@Override
	public ITemplateHooks getHooks() {
		return hooks;
	}

	public void setHooks(ITemplateHooks aHooks) {
		this.hooks = aHooks;
	}
	
	
	//------------- help method to clone myself
	

	@Override
	public String toString() {
		return "Template [name=" + name + ", generatorFqn=" + generatorFqn + ", cartridgeName=" + cartridgeName + ", branchName="
				+ branchName + "]";
	}

	public ITemplate cloneMe() {
		Template result = new Template();
		
		result.setName(this.getName());
		result.setJetPath(this.getJetPath());
		result.setTargetDir(this.getTargetDir());
		result.setPackage(this.getPackage());
		result.setClassname(this.getClassname());
		result.setFileExtension(this.getFileExtension());
		result.setGeneratorFqn(this.getGeneratorFqn());
		result.setInsertionTag(this.getInsertionTag());
		result.setApplyToClasses(this.getApplyToClasses());
		result.setMergeStrategy(this.getMergeStrategy());
		result.setRank(this.getRank());
		result.setCartridgeName(this.getCartridgeName());
		result.setBranchName(this.getBranchName());
		result.setHooks(this.getHooks());
		return result;
	}
	
	//--------------- Builder 

	public static class Builder {
		private String name;
		private String jetPath;
		private String targetDir;
		private String pack;
		private String classname;
		private String extension;
		private String generatorFqn;
		private String insertionTag;
		private Class<?> applyToClasses[];
		private TemplateMergeStrategy mergeStrategy;
		private int rank = 5;
		private String cartridgeName;
		private String branchName;
		private ITemplateHooks hooks;
		
		public Builder withName(String value) {
			this.name = value;
			return this;
		}

		public Builder withJetPath(String value) {
			this.jetPath = value;
			return this;
		}

		public Builder withTargetDir(String value) {
			this.targetDir = value;
			return this;
		}

		public Builder withPackage(String value) {
			this.pack = value;
			return this;
		}

		public Builder withClassname(String value) {
			this.classname = value;
			return this;
		}

		public Builder withExtension(String value) {
			this.extension = value;
			return this;
		}

		public Builder withGeneratorFqn(String value) {
			this.generatorFqn = value;
			return this;
		}

		public Builder withGenerator(Class<?> value) {
			this.generatorFqn = value.getName();
			return this;
		}
		
		public Builder withInsertionTag(String value) {
			this.insertionTag = value;
			return this;
		}

		public Builder withApplyTo(Class<?>[] value) {
			this.applyToClasses = value;
			return this;
		}

		public Builder withMergeStrategy(TemplateMergeStrategy value) {
			this.mergeStrategy = value;
			return this;
		}

		public Builder withRank(int value) {
			this.rank = value;
			return this;
		}

		public Builder withCartridgeName(String value) {
			this.cartridgeName = value;
			return this;
		}

		public Builder withBranchName(String value) {
			this.branchName = value;
			return this;
		}
		
		public Builder() {
		}
	
		public Builder(String name, String generatorFqn) {
			super();
			this.name = name;
			this.generatorFqn = generatorFqn;
		}
		
		public Builder(String name, Class<?> generator) {
			super();
			this.name = name;
			this.generatorFqn = generator.getName();
		}
		
		public Builder(String name, Class<?> generator, Builder builder) {
			super();
			this.name = name;
			this.generatorFqn = generator.getName();
			this.jetPath = builder.jetPath;
			this.targetDir = builder.targetDir;
			this.pack = builder.pack;
			this.classname = builder.classname;
			this.extension = builder.extension;
			this.insertionTag = builder.insertionTag;
			this.applyToClasses = builder.applyToClasses;
			this.mergeStrategy = builder.mergeStrategy;
			this.rank = builder.rank;
			this.cartridgeName = builder.cartridgeName;
			this.branchName = builder.branchName;
			this.hooks = builder.hooks;
		}
		
		public Template build() {
			Template r = new Template();
			r.name = this.name;
			r.jetPath = this.jetPath;
			r.targetDir = this.targetDir;
			r.pack = this.pack;
			r.classname = this.classname;
			r.extension = this.extension;
			r.generatorFqn = this.generatorFqn;
			r.insertionTag = this.insertionTag;
			r.applyToClasses = this.applyToClasses;
			r.mergeStrategy = this.mergeStrategy;
			r.rank = this.rank;
			r.cartridgeName = this.cartridgeName;
			r.branchName = this.branchName;
			r.hooks = this.hooks;
			return r;
		}
		
	}
	
}


