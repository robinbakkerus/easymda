package flca.mda.api.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.helpers.AnnotationsHelper;
import flca.mda.codegen.helpers.FilenameHelper;
import flca.mda.codegen.helpers.ModelClasses;
import flca.mda.common.api.helpers.ImportHelper;
import mda.annotation.jpa.Inheritance;
import mda.annotation.jpa.JoinTable;
import mda.annotation.jpa.ManyToMany;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.Table;
import mda.type.IApplicationType;
import mda.type.IDtoType;
import mda.type.IEntityType;
import mda.type.IServiceType;

public class TypeUtils implements TypeConstants {

	public static final Logger logger = LoggerFactory.getLogger(TypeUtils.class);

	protected static NameUtils nu = new NameUtils();
	protected static ValidatorUtils vu = new ValidatorUtils();
	private static FilenameHelper fnh = new FilenameHelper();

	public static Map<Class<?>, Class<?>> CONCRETE_TYPES = null;
	public static Set<String> COLLECTION_TYPES = null;

	/**
	 * General utility class used in de jet files.
	 */
	public TypeUtils() {
	}

	/**
	 * This will convert the given object to an instance of the given class. We need
	 * this method because the model class was loaded a different classloader.
	 * 
	 * @param aFromObject
	 * @param toClass
	 * @return
	 */
	public Object cast(Object aFromObject, Class<?> toClass) {
		try {
			Class<?> targetClz = Class.forName(toClass.getName());
			Object result = targetClz.newInstance();
			BeanUtils.copyProperties(result, aFromObject);
			return result;
		} catch (Exception e) {
			logger.warn("error casting " + aFromObject.getClass().getName() + " : " + e);
			return null;
		}
	}

	/**
	 * Returns the primitive or class name for the given Type. Class names will be
	 * added as imports to the GenModel's ImportManager, and the imported form will
	 * be returned.
	 */
	public String getImportedConcreteType(Class<?> aClass, Class<?> aGenericType) {
		Class<?> concreteClass = this.getConcreteClass(aClass);

		if (!concreteClass.isPrimitive()) {
			ImportHelper.addImport(concreteClass);
		}
		if (aGenericType != null && !aGenericType.isPrimitive()) {
			ImportHelper.addImport(aGenericType);
		}

		if (this.isCollection(aClass)) {
			if (aGenericType != null) {
				return concreteClass.getSimpleName() + "<" + aGenericType.getSimpleName() + ">";
			} else {
				return concreteClass.getSimpleName() + "<?>";
			}
		} else {
			return aClass.getSimpleName();
		}
	}

	/**
	 * return the generic type of the given field or the actual type if this is not
	 * a generic type
	 * 
	 * @param aField
	 * @return
	 */
	protected Class<?> getGenericOrActualType(Field aField) {
		if (aField == null) {
			return null;
		}

		Type gentyp = aField.getGenericType();
		if (gentyp instanceof ParameterizedType) {
			ParameterizedType partyp = (ParameterizedType) gentyp;
			Type[] fieldArgTypes = partyp.getActualTypeArguments();
			if (fieldArgTypes.length == 1) {
				Class<?> fieldArgClass = (Class<?>) fieldArgTypes[0];
				ImportHelper.addImport(fieldArgClass);
				return fieldArgClass;
			} else {
				return null;// should not be possible
			}
		} else {
			ImportHelper.addImport(this.getTypeName(aField.getType()));
			return aField.getType();
		}
	}

	/**
	 * return all the supertypes except Object for the given class, this loops
	 * recursively up the hiarchy
	 * 
	 * @param aClass
	 * @return
	 */
	public List<Class<?>> getAllSuperTypes(Class<?> aClass) {
		List<Class<?>> result = new ArrayList<Class<?>>();

		this.getAllSuperTypes(aClass, result);
		this.getAllInterfaces(aClass, result);

		return result;
	}

	/**
	 * get all the interfaces for the given class, this loops recursively up the
	 * hiarchy
	 * 
	 * @param aClass
	 * @return
	 */
	public Class<?>[] getAllInterfaces(Class<?> aClass) {
		List<Class<?>> result = new ArrayList<Class<?>>();
		this.getAllInterfaces(aClass, result);
		Class<?> types[] = new Class[0];
		return result.toArray(types);
	}

	private void getAllInterfaces(Class<?> aClass, List<Class<?>> aResult) {
		Class<?> interfaces[] = aClass.getInterfaces();
		for (Class<?> intf : interfaces) {
			aResult.add(intf);
			this.getAllInterfaces(intf, aResult);
		}
	}

	private void getAllSuperTypes(Class<?> aClass, List<Class<?>> aResult) {
		Class<?> clz = aClass.getSuperclass();
		while (clz != null && !clz.equals(Object.class)) {
			aResult.add(clz);
			clz = clz.getSuperclass();
		}
	}

	/**
	 * returns boolean to indicate if given class implemenst or extends the
	 * aTestForClass
	 * 
	 * @param aSourceClass
	 * @param aTestForClass
	 * @return
	 */
	public boolean hasType(Class<?> aSourceClass, Class<?> aTestForClass) {
		List<Class<?>> clazzes = this.getAllSuperTypes(aSourceClass);
		for (Class<?> clz : clazzes) {
			if (clz.getName().equals(aTestForClass.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns boolean to indicate if given class implements the aTestForClass
	 * 
	 * @param aSourceClass
	 * @param aTestForClass
	 * @return
	 */
	public boolean hasInterface(Class<?> aSourceClass, Class<?> aTestForClass) {
		Class<?> clazzes[] = this.getAllInterfaces(aSourceClass);
		for (Class<?> clz : clazzes) {
			if (clz.getName().equals(aTestForClass.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * return all Field(s) (properties) of the given class
	 * 
	 * @param aSourceClass
	 * @return
	 */
	public List<Field> getAllTheFields(Class<?> aSourceClass) {
		List<Field> result = new ArrayList<Field>();
		this.getAllFields(aSourceClass, result);
		return result;
	}

	private void getAllFields(Class<?> aSourceClass, List<Field> result) {
		Field fields[] = aSourceClass.getDeclaredFields();
		for (Field field : fields) {
			result.add(field);
		}

		Class<?> superClz = this.getSuperClass(aSourceClass);
		while (superClz != null && !superClz.equals(Object.class)) {
			this.getAllFields(superClz, result);
			superClz = this.getSuperClass(superClz);
		}
	}

	protected Fw makeIdFieldWrapper(Class<?> ownerClass) {
		return new IdFieldWrapper(this, ownerClass);
	}

//	protected List<Fw> getSpecialFields(Class<?> aClass, FwSelectType... selectOpts) {
//		return new ArrayList<Fw>();
//	}

	public void importIfNeeded(Fw aField) {
		this.importIfNeeded(aField.type(), aField.genericType());
	}

	public void importIfNeeded(Class<?> aClass, Type aGenericType) {
		if (!aClass.isPrimitive()) {
			ImportHelper.addImport(aClass);
		}
		if (aGenericType != null && !aGenericType.equals(aClass)) {
			if (aGenericType instanceof ParameterizedType) {
				ParameterizedType typeimpl = (ParameterizedType) aGenericType;
				for (Type type : typeimpl.getActualTypeArguments()) {
					ImportHelper.addImport(type.getClass());
				}
			}
		}
	}

	/**
	 * returns boolean to indicatie that this field needs to be surounded with an
	 * Option[]
	 * 
	 * @param fw
	 * @return
	 */
	public boolean needsOption(Fw fw) {
		return false;
	}

	public List<Class<?>> getAllImportedClasses() {
		return this.getAllImportedClasses(this.currentClass());
	}

	/**
	 * given the source class, return all the non-simplme classes that are being
	 * used by this class.
	 * 
	 * @param aSourceClass
	 * @return
	 */
	public List<Class<?>> getAllImportedClasses(Class<?> aSourceClass) {
		List<Class<?>> result = new ArrayList<Class<?>>();
		for (Fw fld : this.getAllFields(aSourceClass)) {
			if (fld.isEnum() || !fld.isSimple()) {
				result.add(fld.type());
			}
		}
		return result;
	}

	/**
	 * return the given field annotation of the given field, or null if this field
	 * is not annotated
	 * 
	 * @param aField
	 * @param aAnnotation
	 * @return
	 */
	public Annotation getAnnotation(Fw aField, Class<?> aAnnotation) {
		return AnnotationsHelper.getAnnotation(aField, aAnnotation);
	}

	/**
	 * return the given Class annotation of the given class, or null if this field
	 * is not annotated
	 * 
	 * @param aClass      Class
	 * @param aAnnotation Class
	 * @return
	 */
	public Annotation getAnnotation(Class<?> aClass, Class<?> aAnnotation) {
		return AnnotationsHelper.getAnnotation(aClass, aAnnotation);
	}

	/**
	 * return the given Class annotation of the given class, or null if this field
	 * is not annotated
	 * 
	 * @param aClass      Class
	 * @param aAnnotation Annotation
	 * @return
	 */
	public Annotation getAnnotation(Class<?> aClass, Annotation aAnnotation) {
		return AnnotationsHelper.getAnnotation(aClass, aAnnotation);
	}

	/**
	 * return the given method annotation of the given method, or null if this field
	 * is not annotated
	 * 
	 * @param aMethod
	 * @param aAnnotation
	 * @return
	 */
	public Annotation getAnnotation(Method aMethod, Class<?> aAnnotation) {
		return AnnotationsHelper.getAnnotation(aMethod, aAnnotation);
	}

	/**
	 * return true of the given field annotation is defined on the given field
	 * 
	 * @param aField
	 * @param aAnnotation Class
	 * @return
	 */
	public boolean hasAnnotation(Fw aField, Class<?> aAnnotation) {
		return this.getAnnotation(aField, aAnnotation) != null;
	}

	/**
	 * return true of the given field annotation is defined on the given field
	 * 
	 * @param aField
	 * @param aFqNameToLookfor fully qualified classname String
	 * @return
	 */
	public boolean hasAnnotation(Fw aField, String aFqNameToLookfor) {
		return AnnotationsHelper.getAnnotation(aField, aFqNameToLookfor) != null;
	}

	/**
	 * return true of the given Class annotation is defined on the given class
	 * 
	 * @param aTargetClass
	 * @param aAnnotation
	 * @return
	 */
	public boolean hasAnnotation(Class<?> aTargetClass, Class<?> aAnnotation) {
		return AnnotationsHelper.getAnnotation(aTargetClass, aAnnotation) != null;
	}

	/**
	 * return true of the given Class annotation is defined on the given fully
	 * qualified classname
	 * 
	 * @param aTargetClass
	 * @param aFqNameToLookfor
	 * @return
	 */
	public boolean hasAnnotation(Class<?> aTargetClass, String aFqNameToLookfor) {
		return AnnotationsHelper.getAnnotation(aTargetClass, aFqNameToLookfor) != null;
	}

	/**
	 * return true of the given method annotation is defined on the given method
	 * 
	 * @param aMethod
	 * @param aAnnotation
	 * @return
	 */
	public boolean hasAnnotation(Method aMethod, Class<?> aAnnotation) {
		return AnnotationsHelper.getAnnotation(aMethod, aAnnotation) != null;
	}

	/**
	 * return true of the given method annotation is defined on the given fully
	 * qualified classname
	 * 
	 * @param aMethod
	 * @param aFqNameToLookfor
	 * @return
	 */
	public boolean hasAnnotation(Method aMethod, String aFqNameToLookfor) {
		return AnnotationsHelper.getAnnotation(aMethod, aFqNameToLookfor) != null;
	}

	/**
	 * return true if the given class is an Enum
	 * 
	 * @param aClass
	 * @return
	 */
	public boolean isEnum(Class<?> aClass) {
		return aClass.isEnum();
	}

	/**
	 * return true if the given class is a collection (ie Set, List, etc)
	 * 
	 * @param aClass
	 * @return
	 */
	public boolean isCollection(Class<?> aClass) {
		return (this.getCollectionTypes().contains(aClass.getName()));
	}

	/**
	 * this returns the concrete class of given class, this may be overwritten in
	 * for example ScalaTypeUtils etc for example the
	 * 
	 * <pre>
	 * Set -> HashSet
	 * List -> ArrayList
	 * </pre>
	 * 
	 * @param aCollectionIntf
	 * @return
	 */
	public Class<?> getConcreteClass(Class<?> aClass) {
		if (this.getConcreteTypes().containsKey(aClass)) {
			return this.getConcreteTypes().get(aClass);
		} else {
			return aClass;
		}
	}

	public String getAnnotations(Class<?> aClass) {
		logger.error("getAnnotations() methods should be overwritten");
		return "getAnnotations() should be overwritten";
	}

	public String getAnnotations(Fw fw) {
		logger.error("getAnnotations() methods should be overwritten");
		return "getAnnotations() should be overwritten";
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String getDocumentation() {
		String result = null;

		result = ""; // TODO

		return result;
	}

	/**
	 * This return the class that belong the primary key of the given class
	 * 
	 * @param aClass
	 * @return
	 */
	public Class<?> getIdType(Class<?> aClass) {
		Fw pkfld = this.getExplicitIdField(aClass);
		if (pkfld != null) {
			Class<?> result = pkfld.type();
			ImportHelper.addImport(result);
			return result;
		}

		return Long.class;
	}

	public Class<?> getIdType() {
		return this.getIdType(this.currentClass());
	}

	/**
	 * returns the name of the fieldtype that defines the primary key for the given
	 * class
	 * 
	 * @return
	 */
	public String getIdTypeName(Class<?> aClass) {
		return this.getIdType(aClass).getSimpleName();
	}

	/**
	 * returns the name of the fieldtype that defines the primary key for the
	 * current active entity
	 * 
	 * @return
	 */
	public String getIdTypeName() {
		return this.getIdType(this.currentClass()).getSimpleName();
	}

	/**
	 * returns the field that defines the primary key for the given class
	 * 
	 * @return
	 */
	public Fw getExplicitIdField(Class<?> aClass) {
		FwSelect fwSelect = FwSelect.emptyBuilder().withId(true).build();
		List<Fw> fws = this.getFields(aClass, fwSelect);
		if (fws.size() > 0) {
			return fws.get(0);
		} else {
			return null;
		}
	}

	/**
	 * returns the field that defines the primary key for the given class
	 * 
	 * @return
	 */
	public Fw getExplicitIdField(List<Fw> fields) {
		for (Fw fw : fields) {
			if (!fw.isSpecial() && AnnotationsHelper.hasAnnotation(fw, mda.annotation.jpa.Id.class)) {
				return fw;
			}
		}

		return null;
	}

	/**
	 * return the fieldname of the field that defines the primary
	 * 
	 * @param aClass
	 * @return
	 */
	public String getIdName(Class<?> aClass) {
		Fw pkfld = this.getExplicitIdField(aClass);
		if (pkfld != null) {
			return pkfld.name();
		}

		return "id";
	}

	public boolean hasExplicitId(Class<?> aClass) {
		return this.getExplicitIdField(aClass) != null;
	}

	public boolean isEntity(Class<?> aClass) {
		return this.hasType(aClass, IEntityType.class);
	}

	public boolean isDto(Class<?> aClass) {
		return this.hasType(aClass, IDtoType.class);
	}

	public boolean isService(Class<?> aClass) {
		return this.hasType(aClass, IServiceType.class);
	}

	/**
	 * use this method if no explicit @Id field exists
	 * 
	 * @return
	 */
	public String generateIdField() {
		ImportHelper.addImport("javax.persistence.Id");
		ImportHelper.addImport("javax.persistence.GeneratedValue");
		ImportHelper.addImport("javax.persistence.GenerationType");
		String result = "\t@Id\n";
		result += "\t@GeneratedValue(strategy = GenerationType.AUTO) \n";
		result += "\tprivate Long id;\n";
		return result;
	}

	public String generateIdGetterSetter() {
		if (this.hasExplicitId(this.currentClass())) {
			return this.generateExplicitIdGetterSetter();
		} else {
			return this.generateStdIdGetterSetter();
		}
	}

	/**
	 * use this method if no an explicit @Id is provided.
	 * 
	 * @return
	 */
	private String generateExplicitIdGetterSetter() {
		Class<?> clz = this.currentClass();
		String explicitIdName = this.getIdName(clz);
		String explicitIdType = this.getIdTypeName(clz);
		StringBuffer sb = new StringBuffer();
		sb.append("\tpublic " + explicitIdType + " " + this.explicitIdGetter() + " {\n");
		sb.append("\t\treturn this." + explicitIdName + ";\n");
		sb.append("\t}\n");
		sb.append("\tpublic void set" + nu.capName(explicitIdName) + "(" + explicitIdType + " aValue) {\n");
		sb.append("\t\t this." + explicitIdName + " = aValue;\n");
		sb.append("\t}\n");

		return sb.toString();
	}

	/**
	 * use this method if no explicit @Id field exists. TODO make more 'plugable'
	 * (using ini file?)
	 * 
	 * @return
	 */
	private String generateStdIdGetterSetter() {
		StringBuffer sb = new StringBuffer();
		sb.append("\tpublic Long getId() {\n");
		sb.append("\t\t" + "return id;\n");
		sb.append("\t}\n");
		sb.append("\tpublic void setId(Long aValue) {\n");
		sb.append("\t\t" + "id = aValue;\n");
		sb.append("\t}\n\n");
		return sb.toString();
	}

	public String idGetter() {
		if (this.hasExplicitId(this.currentClass())) {
			return this.explicitIdGetter();
		} else {
			return "getId()";
		}
	}

	private String explicitIdGetter() {
		Class<?> clz = this.currentClass();
		String explicitIdName = this.getIdName(clz);
		return "get" + nu.capName(explicitIdName) + "()";
	}

	/**
	 * return all superclasses of the given class.
	 * 
	 * @see SubClassHelper
	 * @return
	 */
	public List<Class<?>> getSuperClasses(Class<?> aClass) {
		return ModelClasses.getAllSuperClasses(aClass);
	}

	/**
	 * return all subclasses of the given class. This is a List (and not a Set)
	 * because the last entry is the least significant (but not Object)
	 * 
	 * @see SubClassHelper
	 * @return
	 */
	public List<Class<?>> getSubClasses(Class<?> aClass) {
		return ModelClasses.getAllSubClasses(aClass);
	}

	/**
	 * return the superclass of the given class. Note it will NOT return the obvious
	 * Object.class, if the superclass is Object.class then null is returned
	 * 
	 * @return
	 */
	public Class<?> getSuperClass(Class<?> aClass) {
		Class<?> result = aClass.getSuperclass();
		if (result != Object.class) {
			return result;
		} else {
			return null;
		}
	}

	/**
	 * Does this class has a SubClass ?
	 * 
	 * @param aClass
	 * @return
	 */
	public boolean hasSubClasses(Class<?> aClass) {
		return this.getSubClasses(aClass) != null && this.getSubClasses(aClass).size() > 0;
	}

	/**
	 * Does this class has a SubClass ?
	 * 
	 * @param aClass
	 * @return
	 */
	public boolean hasSuperClasses(Class<?> aClass) {
		return this.getSuperClasses(aClass) != null && this.getSuperClasses(aClass).size() > 0;
	}

	/**
	 * returns true if the given model class has is the base class from which other
	 * classes may extend note this is an alias for hasInheritance
	 * 
	 * @return
	 */
	public boolean isBaseClass(Class<?> aClass) {
		return this.hasInheritance(aClass);
	}

	/**
	 * Returns true if given class is abstract
	 * 
	 * @param aClass
	 * @return
	 */
	public boolean isAbstract(Class<?> aClass) {
		boolean b = Modifier.isAbstract(aClass.getModifiers());
		return b;
	}

	/**
	 * returns true if the given model extends some base class. to retrieve the
	 * class it extends from use getSuperClass(aClass)
	 * 
	 * @return
	 */
	public boolean isSubClass(Class<?> aClass) {
		return this.getSuperClass(aClass) != null;
	}

	/**
	 * returns true if the given model is not a base- and not a subclass
	 * 
	 * @return
	 */
	public boolean isNormalClass(Class<?> aClass) {
		return !this.isBaseClass(aClass) && !this.isSubClass(aClass);
	}

	private static final String IMPLEMENTS = " implements ";

	public String getExtendsAndImplements(Class<?> aClass, Class<?> aDefaultImplements[]) {
		if (aDefaultImplements != null) {
			String impls[] = new String[aDefaultImplements.length];
			for (int i = 0; i < aDefaultImplements.length; i++) {
				impls[i] = aDefaultImplements[i].getName();
			}
			return this.getExtendsAndImplements(aClass, impls);
		} else {
			return this.getExtendsAndImplements(aClass, new String[] {});
		}
	}

	public String getExtendsAndImplements(Class<?> aClass, String aDefaultImplements[]) {
		String result = "";

		Class<?> supercls = aClass.getSuperclass();
		if (supercls != null && !supercls.equals(Object.class)) {
			result = "extends " + supercls.getSimpleName();
			ImportHelper.addImport(supercls);
		}

		Class<?> intfs[] = aClass.getInterfaces();
		if (intfs.length > 0) {
			for (Class<?> intf : intfs) {
				if (!this.isCodegenInterface(intf)) {
					if (result.indexOf(IMPLEMENTS) < 0) {
						result += IMPLEMENTS;
					}
					ImportHelper.addImport(intf);
					if (!result.endsWith(IMPLEMENTS)) {
						result += ", ";
					}
					result += intf.getSimpleName();
				}
			}
		}

		if (aDefaultImplements != null && aDefaultImplements.length > 0) {
			if (result.indexOf(IMPLEMENTS) < 0) {
				result += IMPLEMENTS;
			}
			for (int i = 0; i < aDefaultImplements.length; i++) {
				String impl = aDefaultImplements[i];
				ImportHelper.addImport(impl);
				result += new NameUtils().getSimplename(impl);
				if (i < aDefaultImplements.length - 1) {
					result += ",";
				}
			}
		}
		return result;
	}

	/**
	 * This returns the Fw with the given fieldname in the given class, or null if
	 * not found
	 * 
	 * @param aClass
	 * @return
	 */
	public Fw getFieldByName(Class<?> aClass, String aFieldname) {
		List<Field> classFields = Arrays.asList(aClass.getDeclaredFields());
		for (Field fld : this.getAllTheFields(aClass)) {
			if (fld.getName().equals(aFieldname))
				return this.makeFw(fld, aClass, !classFields.contains(fld));
		}
		return null;
	}

	/**
	 * This returns the Field with the given type in the given class, or null if not
	 * found
	 * 
	 * @param aClass
	 * @param aFieldType
	 * @return
	 */
	public Fw getFieldByType(Class<?> aClass, Class<?> aFieldType) {
		List<Field> classFields = Arrays.asList(aClass.getDeclaredFields());
		for (Field fld : this.getAllTheFields(aClass)) {
			if (fld.getType().equals(aFieldType))
				return this.makeFw(fld, aClass, !classFields.contains(fld));
		}
		return null;
	}

	private boolean isCodegenInterface(Class<?> aIntf) {
		return aIntf.getName().startsWith("mda.");
	}

	/**
	 * Return true if this property is annotated with @JoinTable
	 * 
	 * @param aField
	 * @return
	 */
	public boolean isJoinTable(Fw fw) {
		if (this.hasAnnotation(fw, JoinTable.class)) {
			return true;
		} else {
			if (this.hasAnnotation(fw, OneToMany.class)) {
				OneToMany anno = (OneToMany) this.getAnnotation(fw, OneToMany.class);
				// TODO other scenario
				return (anno.mappedBy() != null && anno.mappedBy().trim().length() > 0);
			} else if (this.hasAnnotation(fw, ManyToMany.class)) {
				return true;
			}
			return false;
		}
	}

	public String getOneToManyType(Fw fw, Class<?> aParent) {
		return "todo";
	}

	public String[] getEnumItems(Class<?> aClass) {
		if (aClass != null && aClass.isEnum()) {
			try {
				Method m = aClass.getMethod("values", new Class<?>[] {});
				Object values[] = (Object[]) m.invoke(null, new Object[] {});
				if (values != null && values.length > 0) {
					String result[] = new String[values.length];
					for (int i = 0; i < values.length; i++) {
						result[i] = values[i].toString();
					}
					return result;
				}
			} catch (Exception e) {
				logger.error("error in getEnumItems()", e);
			}
		}
		return new String[] { "???" };
	}

	public boolean hasAbstractAnnotation() {
		Class<?> clz = this.currentClass();

		return (this.hasAnnotation(clz, mda.annotation.Abstract.class));
	}

	public String getSerialVersionUID() {
		return "" + new Date().getTime() + "L";
	}

	/**
	 * Use this method if you want to include generated code from a jet template
	 * indicated with the snippet class. The second param is the argument that is
	 * passed to this jet template. The major difference with the <b>generate</b>
	 * method is that this is not called via an ITemplate but with class of the jet
	 * file. Example: <%= tu.include(Snippet.class, cc) %>
	 * 
	 * 
	 * @param aFqClassname
	 * @param aArgument
	 * @return
	 */
	public String include(Class<?> aSnippetClass, Object... aArguments) {
		try {
			Method method = aSnippetClass.getMethod("generate", new Class[] { Object.class });
			Object generator = aSnippetClass.newInstance();
			Object gensrcObj = method.invoke(generator, new Object[] { aArguments });
			String result = (gensrcObj != null) ? gensrcObj.toString() : "";
			return result;
		} catch (InvocationTargetException ex) {
			logger.error("error while include() code for " + aSnippetClass.getName() + " " + ex.getTargetException());
			return "**error**";
		} catch (Throwable t) {
			logger.error("error while include() code for " + aSnippetClass.getName() + " " + t);
			return "**error**";
		}
	}

	public String include(Class<?> aSnippetClass, JetArgument arg) {
		try {
			Method method = aSnippetClass.getMethod("generate", new Class[] { Object.class });
			Object generator = aSnippetClass.newInstance();
			Object gensrcObj = method.invoke(generator, new Object[] { arg });
			String result = (gensrcObj != null) ? gensrcObj.toString() : "";
			return result;
		} catch (InvocationTargetException ex) {
			logger.error("error while include() code for " + aSnippetClass.getName() + " " + ex.getTargetException());
			return "**error**";
		} catch (Throwable t) {
			logger.error("error while include() code for " + aSnippetClass.getName() + " " + t);
			return "**error**";
		}
	}

	/**
	 * return a valid classname given a Class This method also takes into account an
	 * Array class. Such a class would normally return something like
	 * "[Lcom.xxx.Aclasname;"
	 * 
	 * @param aClass
	 * @return
	 */
	public String getClassname(Class<?> aClass) {
		String result = aClass.getCanonicalName();
		if (result.endsWith("[]")) {
			result = result.substring(0, result.length() - 2);
		}
		return result;
	}

	public String getSimpleClassname(Class<?> aClass) {
		String result = this.getClassname(aClass);
		if (result.indexOf(".") > 0) {
			result = result.substring(result.lastIndexOf(".") + 1);
		}
		return result;
	}

	/**
	 * shortcut to get the base-package string
	 * 
	 * @see DataStore.getBasePackage()
	 * @return
	 */
	public String getBasePackage() {
		return DataStore.getInstance().getBasePackage();
	}

	/**
	 * shortcut to get the (user) supplied App-package string
	 * 
	 * @see DataStore.getBasePackage()
	 * @return
	 */
	public String getAppPackage() {
		return fnh.getOutputAppPackage();
	}

	/**
	 * replace the base-package with app-package in the given input string
	 * 
	 * @param aPackage
	 * @return
	 */
	public String substitutePackage(String aString) {
		return fnh.formatPackage(aString);
	}

	/**
	 * return the one class that implements the ApplicationBaseType interface
	 * 
	 * @return
	 */
	public IApplicationType getApplicationBaseType() {
		return ModelClasses.findApplicationType();
	}

	/**
	 * returns the Class that is currenly active
	 * 
	 * @return
	 */
	public Class<?> currentClass() {
		return JetArgument.getCurrent().getElementClass();
	}

	/**
	 * returns the Table name that belong to the current active class. If the class
	 * is annotated with @Table and the name is provided, this will be returned else
	 * the classname is returned
	 * 
	 * @return
	 */
	public String getTableName() {
		return this.getTableName(this.currentClass());
	}

	/**
	 * returns the Table name that belong to given class.
	 * 
	 * @see getTableName()
	 * @param aClass
	 * @return
	 */
	public String getTableName(Class<?> aClass) {
		String result = aClass.getSimpleName();
		if (this.hasAnnotation(aClass, Table.class)) {
			Table anno = (Table) this.getAnnotation(aClass, Table.class);
			if (anno.name() != null && !anno.name().isEmpty())
				result = anno.name();
		}
		return result;
	}

	/**
	 * This methods validates the given class, to check if alle annotations are set
	 * correctly
	 * 
	 * @param aClass
	 * @return
	 */
	public boolean validate(Class<?> aClass, List<String> aMessages) {

		boolean status = AnnotationsHelper.validate(aClass, aMessages);

		for (Fw fw : this.getAllFields(aClass)) {
			status = status && AnnotationsHelper.validate(fw, aMessages);
		}
		return status;
	}

	/**
	 * return all model entity classes
	 * 
	 * @return
	 */
	public List<Class<?>> getAllModelEntities() {
		return ModelClasses.findModelClassesWithInterface(IEntityType.class);
	}

	/**
	 * return all model service classes
	 * 
	 * @return
	 */
	public List<Class<?>> getAllModelServices() {
		return ModelClasses.findModelClassesWithInterface(IServiceType.class);
	}

	/**
	 * return all model service classes
	 * 
	 * @return
	 */
	public List<Class<?>> getAllModelEnums() {
		return ModelClasses.findModelEnums();
	}

	/**
	 * This a NestedObjects object that contains a list of all nested objects
	 * including the class-paths and corresponding field names.
	 * 
	 * @param aClass
	 * @return NestedObjects
	 */
	public NestedObjects getNestedObjects(Class<?> aClass) {
		return new NestedObjects(aClass);
	}

	/**
	 * This returns true if the first class, is nested (somewhere) in the second
	 * class. for example: In Tsta you have: Set<Tstc> cs; In TstC you have: Tsta a;
	 * Within Tstc you can check field a if it Tstc is nested with Tsta like this:
	 * boolean nested = tu.isNestedIn(cc, fw.getType());
	 * 
	 * @param testclass the class to be tested if it is inside
	 * @param fromclass the root class that is scanned
	 * @return true if nested
	 */
	public boolean isNestedIn(Class<?> testclass, Class<?> rootclass) {
		boolean result = false;
		NestedObjects nestedobjs = this.getNestedObjects(rootclass);
		for (NestedObject nested : nestedobjs.getNestedObjects()) {
			if (nested.getNestedType().equals(testclass)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public Map<Class<?>, Class<?>> getConcreteTypes() {
		return CONCRETE_TYPES;
	}

	public Set<String> getCollectionTypes() {
		return COLLECTION_TYPES;
	}

	/**
	 * retrieve the filled-in value from SubsValue from the DataStore
	 * 
	 * @param aName
	 * @return
	 */
	public String getSubsvalueFromDataStore(String aName) {
		SubsValue subsval = DataStore.getInstance().getSubsValue(aName);
		if (subsval != null) {
			return subsval.getValue();
		} else {
			logger.error("No SubsValue found for : " + aName);
			return "?" + aName + "?";
		}
	}

	/**
	 * return the current cartrdiges folder. While running the plugin this is
	 * <eclipse-dir>/flca.easymda.generator_xxx/cartridges While junit testing this
	 * is the value returned by the TestTemplatesData interface method:
	 * getPluginDir(), eg <easymda>/flca.mda.generator/cartridges
	 * 
	 * @return
	 */
	public File getCartridgeDir() {
		return new File(DataStore.getInstance().getValue(CodegenConstants.DATASTORE_CARTRIDGE_DIR));
	}

	/**
	 * return the current cartrdiges folder. While running the plugin this is root
	 * dir of the selected class or package While junit testing this is the value
	 * returned by the TestTemplatesData interface method: getModelDir(), eg
	 * <easymda>/flca.mda.test.model.webapp
	 * 
	 * @return
	 */
	public File getModelDir() {
		return new File(DataStore.getInstance().getValue(CodegenConstants.DATASTORE_MODEL_DIR));
	}

	/**
	 * returns true if the given model class has the @Inheritance annotation
	 * 
	 * @return
	 */
	public boolean hasInheritance(Class<?> aClass) {
		return this.hasAnnotation(aClass, Inheritance.class);
	}

	/**
	 * 
	 * @param aClass
	 * @param FwSelect
	 * @return
	 */
	public List<Fw> getFields(final Class<?> aClass, final FwSelect fwSelect) {
		List<Fw> r = new ArrayList<>();
		for (Fw fw : this.getAllFields(aClass)) {
			if (this.includeField(aClass, fw, fwSelect)) {
				r.add(fw);
			}
		}
		return r;
	}

	public List<Fw> getAllFields(final Class<?> aClass) {
		List<Fw> result = new ArrayList<>();
		result.addAll(this.getSpecialFields(aClass));
		List<Field> classFields = Arrays.asList(aClass.getDeclaredFields());
		for (Field fld : this.getAllTheFields(aClass)) {
			boolean isSuperField = !classFields.contains(fld);
			result.add(this.makeFw(fld, aClass, isSuperField));
		}

		this.removeDoubleIdField(result);
		return this.sortAllFields(result);
	}

	private void removeDoubleIdField(List<Fw> result) {
		// remove the double id field if ness.
		Fw explicitId = this.getExplicitIdField(result);
		if (explicitId != null) {
			Fw specialId = this.getSpecialIdField(result);
			if (specialId != null) {
				result.remove(specialId);
			}
		}
	}

	private List<Fw> sortAllFields(List<Fw> fields) {
		Collections.sort(fields, new FwComparator());
		return fields;
	}

	private Fw getSpecialIdField(List<Fw> fields) {
		Fw result = null;
		for (Fw fw : fields) {
			if (fw.isSpecial() && fw.isId()) {
				result = fw;
				break;
			}
		}
		return result;
	}

	protected Fw makeFw(final Field fld, Class<?> ownerClass, boolean superField) {
		return new Fw(this, fld, ownerClass, superField);
	}

	protected Fw makeFw(final SpecialField fld, Class<?> ownerClass) {
		return new Fw(this, fld, ownerClass);
	}

	protected List<Fw> getSpecialFields(Class<?> aClass) {
		List<Fw> result = new ArrayList<Fw>();
		result.add(this.makeIdFieldWrapper(aClass));
		return result;
	}

	private boolean includeField(final Class<?> aClass, final Fw fw, final FwSelect fwSelect) {
		boolean result = false;

		if (fw.isOneToOneField()) {
			if (fwSelect.isIncOneToOne() != null) {
				result = fwSelect.isIncOneToOne();
			} else if (fwSelect.isIncRelations() != null) {
				result = fwSelect.isIncRelations();
			}
		}
		
		if (fw.isOneToManyField()) {
			if (fwSelect.isIncOneToMany() != null) {
				result = fwSelect.isIncOneToMany();
			} else if (fwSelect.isIncRelations() != null) {
				result = fwSelect.isIncRelations();
			}
		}
		
		if (fw.isManyToOneField()) {
			if (fwSelect.isIncManyToOne() != null) {
				result = fwSelect.isIncManyToOne();
			} else if (fwSelect.isIncRelations() != null) {
				result = fwSelect.isIncRelations();
			}
		}
		
		if (fw.isManyToManyField()) {
			if (fwSelect.isIncManyToMany() != null) {
				result = fwSelect.isIncManyToMany();
			} else if (fwSelect.isIncRelations() != null) {
				result = fwSelect.isIncRelations();
			}
		}
		
		if (fw.isSimple()) {
			if (fwSelect.isIncSimple() != null) result = fwSelect.isIncSimple();
		}
		
		if (fw.isModelClass()) {
			if (fwSelect.isIncModelClass() != null) result = fwSelect.isIncModelClass();
		}
		
		if (fw.isCollection()) {
			if (fwSelect.isIncCollection() != null) result = fwSelect.isIncCollection();
		}
		
		if (fw.isEnum()) {
			if (fwSelect.isIncEnum() != null) result = fwSelect.isIncEnum();
		}
		
		if (fw.isSpecial()) {
			if (fwSelect.isIncSpecials() != null) result = fwSelect.isIncSpecials();
		}
		
		if (fw.isSpecial() && fw.getSpecialfield().getSpecialFieldType() == SpecialField.SpecialType.DISCRIMINATOR) {
			if (fwSelect.isIncDiscriminator() != null) {
				result = fwSelect.isIncDiscriminator();
			} else if (fwSelect.isIncSpecials() != null) {
				result = fwSelect.isIncSpecials();
			}
		}
		
		if (fw.isSpecial() && fw.getSpecialfield().getSpecialFieldType() == SpecialField.SpecialType.ID) {
			if (fwSelect.isIncId() != null) {
				result = fwSelect.isIncId();
			} else if (fwSelect.isIncSpecials() != null) {
				result = fwSelect.isIncSpecials();
			}
		}
		
		if (fw.isSuper()) {
			if (fwSelect.isIncSuper() != null) result = fwSelect.isIncSuper();
		}


		return result;
	}

	
	/**
	 * Returns the primitive or class name for the given Type. Class names will be
	 * added as imports to the GenModel's ImportManager, and the imported form will
	 * be returned.
	 */
	public String getTypeName(Class<?> aClass) {
		if (!aClass.isPrimitive()) {
			ImportHelper.addImport(aClass);
		}

		if (this.isCollection(aClass)) {
			return aClass.getSimpleName() + "<?" + ">";
		} else {
			return aClass.getSimpleName();
		}
	}

	/**
	 * Returns the primitive or class name for the given Type. Class names will be
	 * added as imports to the GenModel's ImportManager, and the imported form will
	 * be returned.
	 */
	public String getTypeName(Class<?> aClass, Class<?> aGenericType) {
		if (!aClass.isPrimitive()) {
			ImportHelper.addImport(aClass);
		}
		if (aGenericType != null && !aGenericType.isPrimitive()) {
			ImportHelper.addImport(aGenericType);
		}

		if (this.isCollection(aClass)) {
			if (aGenericType != null) {
				return aClass.getSimpleName() + "<" + aGenericType.getSimpleName() + ">";
			} else {
				return aClass.getSimpleName() + "<?>";
			}
		} else {
			String result = aClass.getSimpleName();
			return result;
		}
	}

	public boolean isSimpleField(Class<?> aClass) {
		if (aClass == null) {
			return true;
		} else if (this.isCollection(aClass)) {
			return false;
		} else {
			return aClass.isPrimitive() || aClass.getName().startsWith("java") || aClass.isEnum();
		}
	}

	/**
	 * Return true if this field us marked with @Val
	 * 
	 * @param fld
	 * @return boolean
	 */
	public boolean isValType(Fw flw) {
		return false;
	}

	/**
	 * Return true if this field us marked with @Var
	 * 
	 * @param fld
	 * @return boolean
	 */
	public boolean isVarType(Fw flw) {
		return false;
	}

	public boolean isBooleanType(Class<?> type) {
		return "boolean".equals(type.getSimpleName().toLowerCase());
	}

	public boolean isNumericType(Class<?> aClass) {
		if (this.isSimpleField(aClass)) {
			String typename = aClass.getSimpleName();
			return NUMERIC_FIELDS.indexOf(typename.toLowerCase()) >= 0;
		} else {
			return false;
		}
	}

	public boolean isStringType(Class<?> type) {
		return "String".equals(type.getSimpleName());
	}

	public boolean isBigDecimalType(Class<?> type) {
		return "BigDecimal".equals(type.getSimpleName());
	}

	/**
	 * return true if the given class is int Integer long Long short or Short
	 * 
	 * @param type
	 * @return
	 */
	public boolean isIntType(Class<?> type) {
		if (this.isSimpleField(type)) {
			String typename = type.getName().toLowerCase();
			if (typename.startsWith("java.lang")) {
				typename = typename.substring(10);
			}
			return (INT_FIELDS.indexOf(typename) >= 0);
		} else {
			return false;
		}
	}

	/**
	 * return true if the given class is double Double float Floao or BigDecimal
	 * 
	 * @param type
	 * @return
	 */
	public boolean isDecimalType(Class<?> type) {
		if (this.isSimpleField(type)) {
			String typename = type.getName().toLowerCase();
			if (typename.startsWith("java.lang") || typename.startsWith("java.math")) {
				typename = typename.substring(10);
			}
			return (DEC_FIELDS.indexOf(typename) >= 0);
		} else {
			return false;
		}
	}

	/**
	 * return true if the given class is double Double float Floao or BigDecimal
	 * 
	 * @param type
	 * @return
	 */
	public boolean isDateType(Class<?> type) {
		if (this.isSimpleField(type)) {
			String typename = type.getName();
			return (DATE_FIELDS.indexOf(typename) >= 0);
		} else {
			return false;
		}
	}

	public boolean isTimestampType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "Timestamp".equals(type.getSimpleName());
		}
	}

	public boolean isTimeType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "Time".equals(type.getSimpleName());
		}
	}

	public boolean isIntegerType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "Integer".equals(type.getSimpleName());
		}
	}

	public boolean is_intType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "int".equals(type.getSimpleName());
		}
	}

	public boolean isDoubleType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "Double".equals(type.getSimpleName());
		}
	}

	public boolean is_doubleType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "double".equals(type.getSimpleName());
		}
	}

	public boolean isLongType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "Long".equals(type.getSimpleName());
		}
	}

	public boolean is_longType(Class<?> type) {
		if (type == null) {
			return false;
		} else {
			return "long".equals(type.getSimpleName());
		}
	}

	// public boolean isBlob(Type type) {
	// return "Byte[]".equals(type.getName());
	// }

	// public boolean isCurrency(Property property) {
	// return "Currency".equals(property.getType().getName());
	// }
	//
	// public boolean isPercentage(Property property) {
	// return "Percentage".equals(property.getType().getName());
	// }
	//
	// public boolean isText(Property property) {
	// return "Text".equals(property.getType().getName());
	// }
	//
	// public boolean isImage(Property property) {
	// return "Image".equals(property.getType().getName());
	// }
	//
	// public boolean isLink(Property property) {
	// return "Link".equals(property.getType().getName());
	// }
	//
	// public boolean isDecimal(Property property) {
	// return "Decimal".equals(property.getType().getName());
	// }

	public String getDefaultValue(Class<?> aClass) {
		String result = DataStore.getInstance().getSectionValue("java.default.value", aClass.getSimpleName());
		if (result != null && result.trim().length() == 0) {
			result = "null";
		}
		return result;
	}

	public boolean hasDefaultValue(Class<?> aClass) {
		String dv = this.getDefaultValue(aClass);
		return dv != null && dv.length() > 0;
	}

	public String format(List<Fw> fields, String formatString, String separator) {
		List<String> formattedFields = new ArrayList<String>();
		for (Fw fw : fields) {
			formattedFields.add(fw.format(formatString));
		}
		return nu.join(formattedFields, separator);
	}

	// ------- static ----------------------------------------
	static {
		CONCRETE_TYPES = new HashMap<Class<?>, Class<?>>();
		CONCRETE_TYPES.put(Set.class, HashSet.class);
		CONCRETE_TYPES.put(List.class, ArrayList.class);
		CONCRETE_TYPES.put(Map.class, HashMap.class);
		CONCRETE_TYPES.put(Collection.class, HashSet.class);

		COLLECTION_TYPES = new HashSet<String>();
		COLLECTION_TYPES.add(Set.class.getName());
		COLLECTION_TYPES.add(List.class.getName());
		COLLECTION_TYPES.add(HashSet.class.getName());
		COLLECTION_TYPES.add(ArrayList.class.getName());
		COLLECTION_TYPES.add(Collection.class.getName());
	}

	public class FwComparator implements Comparator<Fw> {
		@Override
		public int compare(Fw o1, Fw o2) {
			if (o1.isId()) {
				return -1;
			} else if (o2.isId()) {
				return 1;
			} else if (o1.isSpecial()) {
				return -1;
			} else if (o2.isSpecial()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
