package flca.mda.api.util;

import java.lang.reflect.Field;
import java.util.List;

import flca.mda.codegen.helpers.AnnotationsHelper;
import flca.mda.codegen.helpers.ModelClasses;
import flca.mda.codegen.helpers.StrUtil;
import mda.annotation.jpa.Column;
import mda.annotation.jpa.Id;
import mda.annotation.jpa.JoinColumn;
import mda.annotation.jpa.ManyToMany;
import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.type.IEntityType;

/**
 * Helper class to wrap a Field, so that also not explicit Id fields can be
 * supported
 * 
 * @author robin
 *
 */
public class Fw implements TypeConstants {
	protected TypeUtils tu = new TypeUtils();
	protected NameUtils nu = new NameUtils();
	protected ValidatorUtils vu = new ValidatorUtils();
	private Field field; // a normal field
	private SpecialField specialField; // used for example, for the Id if there is no explicit Id field defined
	private Class<?> ownerClass;
	private boolean superField;

	/**
	 * ctor used for normal fields
	 * 
	 * @param field
	 * @param ownerClass
	 */
	public Fw(Field field, Class<?> ownerClass, boolean isSuperField) {
		super();
		this.field = field;
		this.ownerClass = ownerClass;
		this.superField = isSuperField;
	}

	/**
	 * ctor used for normal fields
	 * 
	 * @param tu
	 * @param field
	 */
	public Fw(TypeUtils tu, Field field, Class<?> ownerClass, boolean isSuperField) {
		this(field, ownerClass, isSuperField);
		this.tu = tu;
	}

	/**
	 * ctor used for special fields
	 * 
	 * @param tu
	 * @param specialField
	 */
	public Fw(TypeUtils tu, SpecialField specialField, Class<?> ownerClass) {
		this.tu = tu;
		this.specialField = specialField;
		this.ownerClass = ownerClass;
		this.superField = false;
	}

	/**
	 * return the corresponding property name (as defined in the model class).
	 * 
	 * @return
	 */
	public String name() {
		if (field != null)
			return field.getName();
		else
			return specialField.getName();
	}

	/**
	 * return the natural type that corresponds with this propery
	 * 
	 * @return
	 */
	public Class<?> type() {
		if (field != null)
			return field.getType();
		else
			return specialField.getType();
	}

	/**
	 * return the generic type of the given field or null if this is not a generic
	 * type
	 * 
	 * @param aField
	 * @return
	 */
	public Class<?> genericType() {
		if (field != null) {
			return tu.getGenericOrActualType(field);
		} else {
			return specialField.getType();
		}
	}

	/**
	 * return the name of the actual type or generic type it is a collection This
	 * may be cartridge dependent For example a field like: Set<Address> addresses;
	 * JavaTypeUtils -> Set<Address> ScalaTypeUtils -> Set[Address]
	 * 
	 * @return
	 */
	public String typeName() {
		if (field != null) {
			return tu.getTypeName(type(), genericType());
		} else {
			return tu.getTypeName(specialField.getType());
		}
	}

	/**
	 * return the name of the generic type (in case of a collection), or the type if
	 * it is not a collection . For example a field like: Set<Address> addresses;
	 * will return Address
	 * 
	 * @see genericType()
	 * @return
	 */
	public String genericTypeName() {
		if (field != null) {
			return tu.getTypeName(tu.getGenericOrActualType(field));
		} else {
			return specialField.getType().getSimpleName();
		}
	}

	/**
	 * return the name of the actual type (in case of a collection), or the type if
	 * it is not a collection This may be cartridge dependent For example a field
	 * like: Set<Address> addresses; JavaTypeUtils -> Set ScalaTypeUtils -> Set
	 * 
	 * @return
	 */
	public String actualTypeName() {
		String result = null;
		if (field != null) {
			result = tu.getTypeName(type(), genericType());
		} else {
			result = tu.getTypeName(specialField.getType());
		}
		if (result != null) {
			int n1 = result.indexOf("<");
			int n2 = result.indexOf("[");
			int n = (n1 > 0) ? n1 : n2;
			if (n > 0) {
				result = result.substring(0, n);
			}
		}
		return result;
	}

	public boolean isField() {
		return field != null;
	}

	public boolean isSpecial() {
		return specialField != null;
	}

	public boolean isId() {
		return AnnotationsHelper.hasAnnotation(this, Id.class);
	}

	/**
	 * returns true if this type is a nested Pojo
	 * 
	 * @return
	 */
	public boolean isBean() {
		if (field != null) {
			return !tu.isSimpleField(field.getType()) && !this.isEnum() && !this.isCollection();
		} else {
			return false;
		}
	}

	/**
	 * return true if is this (generic)type extends IEntityType.
	 * 
	 * @return
	 */
	public boolean isEntity() {
		return tu.hasType(genericType(), IEntityType.class);
	}

	public boolean isEnum() {
		if (field != null)
			return field.getType().isEnum();
		else
			return false;
	}

	/**
	 * Returns true if this property is coming from a superclass.
	 * @return
	 */
	public boolean isSuper() {
		return this.superField;
	}
	
	public boolean isRequired() {
		if (field != null)
			return vu.isRequired(field);
		else
			return false;
	}

	public boolean isManyToOneField() {
		if (field != null) {
			return AnnotationsHelper.hasAnnotation(this, ManyToOne.class);
		} else {
			return false;
		}
	}

	public boolean isOneToManyField() {
		if (field != null) {
			return AnnotationsHelper.hasAnnotation(this, OneToMany.class);
		} else {
			return false;
		}
	}

	public boolean isManyToManyField() {
		if (field != null) {
			return AnnotationsHelper.hasAnnotation(this, ManyToMany.class);
		} else {
			return false;
		}
	}

	public boolean isOneToOneField() {
		if (field != null) {
			return AnnotationsHelper.hasAnnotation(this, OneToOne.class);
		} else {
			return false;
		}
	}

	public boolean isModelClass() {
		return ModelClasses.getAllClasses().contains(this.type())
				|| ModelClasses.getAllClasses().contains(this.genericType());
	}

	public boolean isRelation() {
		return isManyToManyField() || isManyToOneField() || isOneToOneField() || isOneToManyField();
	}

	public boolean isComposite() {
		return (isManyToOneField() || isOneToManyField());
	}

	public boolean isSimple() {
		if (field != null)
			return tu.isSimpleField(field.getType());
		else
			return false;
	}

	public boolean isPrimitive() {
		if (isSpecial())
			return false;
		else
			return getField().getType().isPrimitive();
	}

	public boolean isString() {
		return tu.isStringType(type());
	}

	public boolean isBoolean() {
		return tu.isBooleanType(type());
	}

	public boolean isNumeric() {
		return tu.isNumericType(type());
	}

	public boolean isDecimal() {
		return tu.isDecimalType(type());
	}

	public boolean isDate() {
		return tu.isDateType(type());
	}

	public boolean isTime() {
		return tu.isTimeType(type());
	}

	public boolean isTimeStamp() {
		return tu.isTimestampType(type());
	}

	/**
	 * returns true if this field is the 'owner' of the relation. The result is:
	 * OneToMany : true ManyToOne : false OneToOne : true if the field also
	 * contains @JoinColumn annotation ManyToMany : TODO
	 * 
	 * @param aFieldWrapper
	 * @return
	 */
	public boolean isOwner() {
		if (tu.hasAnnotation(this, OneToMany.class)) {
			return true;
		} else if (tu.hasAnnotation(this, ManyToOne.class)) {
			return false;
		} else if (tu.hasAnnotation(this, OneToOne.class)) {
			return tu.hasAnnotation(this, JoinColumn.class);
		} else {
			return false; // TODO
		}
	}

	public boolean needsOption() {
		return tu.needsOption(this);
	}

	/**
	 * returns the Column name that belong to given Field. If the field is annotated
	 * with @Column and the name is provided, this will be returned else the
	 * fieldname is returned
	 * 
	 * @param aClass
	 * @return
	 */
	public String getColumnName() {
		String result = name();
		if (tu.hasAnnotation(this, Column.class)) {
			Column anno = (Column) tu.getAnnotation(this, Column.class);
			if (anno.name() != null && !anno.name().isEmpty())
				result = anno.name();
		}
		return result;
	}

	public String getRowTypeName() {
		return typeName();
	}

	public Field getField() {
		return field;
	}

	public SpecialField getIdfield() {
		return specialField;
	}

	public SpecialField getSpecialfield() {
		return specialField;
	}

	public boolean isCollection() {
		return (tu.isCollection(type())) || this.isArray();
	}

	public boolean isArray() {
		return this.typeName().endsWith("[]");
	}

	public String getAnnotations() {
		return tu.getAnnotations(this);
	}

	public String concreteTypeName() {
		return tu.getImportedConcreteType(type(), genericType());
	}

	/**
	 * rteurn true if this field should be skipped base on given selector
	 * 
	 * @param fw
	 * @param opts
	 * @return
	 */
	public boolean skipField(final FwSelect fwSelect) {
		if (fwSelect.isIncId() && isId()) {
			return true;
		}
		if ((fwSelect.isIncOneToMany() || fwSelect.isIncRelations()) && isOneToManyField()) {
			return true;
		}
		if ((fwSelect.isIncOneToOne() || fwSelect.isIncRelations()) && isOneToOneField()) {
			return true;
		}
		if ((fwSelect.isIncManyToOne() || fwSelect.isIncRelations()) && isManyToOneField()) {
			return true;
		}
		if ((fwSelect.isIncManyToMany() || fwSelect.isIncRelations()) && isManyToManyField()) {
			return true;
		}
		return false;
	}

	/**
	 * @see AnnotationsHelper.getMappedByFkField
	 * @param aField
	 * @return
	 */
	public boolean hasMappedByFkField() {
		return AnnotationsHelper.hasMappedByFkField(this);
	}

	/**
	 * @see AnnotationsHelper.getMappedByFkField
	 * @param aFieldWrapper
	 * @return
	 */
	public Fw getMappedByFkField() {
		return AnnotationsHelper.getMappedByFkField(this);
	}

	/**
	 * return the name mappedBy foreign key field
	 * 
	 * @param aFieldWrapper
	 * @return
	 */
	public String getMappedByFkFieldName() {
		Fw fw = getMappedByFkField();
		return (fw == null) ? "??fk??" : fw.name();
	}

	/**
	 * this the 'owner' field that corresponds with the given field, or null if not
	 * found
	 * 
	 * @param aMappedByClass
	 * @param aMappedByField
	 * @return
	 */
	public Fw getOwnerMappedByFkField() {
		System.out.println("getOwnerMappedByFkField voor " + this.name());
		for (Class<?> clz : tu.getAllModelEntities()) {
			for (Fw fw : tu.getAllFields(clz)) {
				Fw mappedby = fw.getMappedByFkField();
				if (mappedby != null && !mappedby.equals(this)
						&& mappedby.type().getSimpleName().equals(this.getOwnerClass().getSimpleName())) {
					return mappedby;
				}
			}
		}
		return null;
	}

	/**
	 * this returns the 'owner' class that corresponds with the given field, or null
	 * if not found
	 * 
	 * @param aMappedByClass
	 * @param aMappedByField
	 * @return
	 */
	public Class<?> getOwnerClassMappedByFkField() {
		for (Class<?> clz : tu.getAllModelEntities()) {
			for (Fw fw : tu.getAllFields(clz)) {
				if (fw.hasMappedByFkField()) {
					Fw mappedby = fw.getMappedByFkField();
					if (mappedby.equals(this)) {
						return clz;
					}
				}
			}
		}
		return null;
	}

	public String getDefaultValue() {
		return tu.getDefaultValue(type());
	}

//	protected void importIfNeeded(Fw aField) {
//		importIfNeeded(aField.getType(), aField.getGenericType());
//	}

	/**
	 * returns a string where the field formats are substituted ex: s =
	 * fieldFormat(fld, "private %t %n = %d;" => private String name = null;
	 * 
	 * @param field
	 * @param formatString
	 * @return
	 */
	public String format(final String formatString) {
		String result = formatString;
		result = StrUtil.replace(result, FieldFormat.NAME.getValue(), name());
		result = StrUtil.replace(result, FieldFormat.TYPE.getValue(), typeName());
		result = StrUtil.replace(result, FieldFormat.DEFAULT.getValue(), getDefaultValue());
		if (result.indexOf(FieldFormat.FUNCTION.getValue()) >= 0) {
			result = FwFormatHelper.fieldFormatFunction(this, result);
		}
		return result;
	}

	/**
	 * Return the getter, ex: "getName"
	 * 
	 * @return
	 */
	public String getter() {
		return String.format("get%s", nu.capName(this.getField()));
	}

	/**
	 * Return the setter, ex: "getName"
	 * 
	 * @return
	 */
	public String setter() {
		return String.format("set%s", nu.capName(this.getField()));
	}

	/**
	 * return the random value
	 * 
	 * @return
	 */
	public String randomValue() {
		return "?random?"; // TODO
	}

	public Class<?> getOwnerClass() {
		return ownerClass;
	}

	@Override
	public String toString() {
		return "FieldWrapper [name=" + name() + " field=" + field + ", specialField=" + specialField + " owner="
				+ getOwnerClass().getSimpleName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((specialField == null) ? 0 : specialField.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Fw other = (Fw) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (specialField == null) {
			if (other.specialField != null)
				return false;
		} else if (!specialField.equals(other.specialField))
			return false;
		return true;
	}

	// static helper to make a Fw instance
	
	private final static TypeUtils TU = new TypeUtils();
	
	/**
	 * Make a Fw instance given a class and the name of a class property
	 * @param clz
	 * @param propertyName
	 * @return
	 */
	public static Fw makeFw(Class<?> clz, String propertyName) {
		List<Fw> fws = TU.getAllFields(clz);
		for (Fw fw : fws) {
			if (fw.name().equals(propertyName)) {
				return fw;
			}
		}
		System.err.println("No matching field for " + propertyName + " found");
		return null;
	}
}
