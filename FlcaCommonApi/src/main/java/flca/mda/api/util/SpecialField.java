package flca.mda.api.util;

public class SpecialField {

	private Class<?> type;
	private String name;
	private boolean isId;
	private String defaultValue;
	private SpecialType specialType;
	
	public SpecialField(Class<?> type, String name, boolean isId, String defValue, SpecialType specialType) {
		super();
		this.type = type;
		this.name = name;
		this.isId = isId;
		this.defaultValue = defValue;
		this.specialType = specialType;
	}
	
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isId() {
		return isId;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public SpecialType getSpecialFieldType() {
		return this.specialType;
	}

	public void setSpecialFieldType(SpecialType specialFieldType) {
		this.specialType = specialFieldType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((specialType == null) ? 0 : specialType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialField other = (SpecialField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (specialType != other.specialType)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	public enum SpecialType {
		ID,
		DISCRIMINATOR,
		OFD;
	}
}
