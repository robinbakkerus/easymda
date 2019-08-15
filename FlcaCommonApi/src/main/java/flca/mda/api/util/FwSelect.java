package flca.mda.api.util;

public class FwSelect {

	private Boolean incSimple = true;
	private Boolean incModelClass = true;
	private Boolean incCollection = true;
	private Boolean incEnum = true;
	private Boolean incRelations = true;
	private Boolean incOneToOne = true;
	private Boolean incOneToMany = true;
	private Boolean incManyToMany = true;
	private Boolean incManyToOne = true;
	private Boolean incSuper = true; // fields from the super class if any
	private Boolean incSpecials = false;
	private Boolean incId = false; // the implicit id, if no specific prop inc @ID is available
	private Boolean incDiscriminator = false; // the discriminator used for classes inc @Inheritance

	public Boolean isIncSimple() {
		return this.incSimple;
	}

	public Boolean isIncEnum() {
		return this.incEnum;
	}

	public Boolean isIncCollection() {
		return incCollection;
	}

	public Boolean isIncRelations() {
		return this.incRelations;
	}

	public Boolean isIncOneToOne() {
		return this.incOneToOne;
	}

	public Boolean isIncOneToMany() {
		return this.incOneToMany;
	}

	public Boolean isIncManyToMany() {
		return this.incManyToMany;
	}

	public Boolean isIncManyToOne() {
		return this.incManyToOne;
	}

	public Boolean isIncSuper() {
		return this.incSuper;
	}

	public Boolean isIncModelClass() {
		return this.incModelClass;
	}

	public Boolean isIncSpecials() {
		return this.incSpecials;
	}

	public Boolean isIncId() {
		return this.incId;
	}

	public Boolean isIncDiscriminator() {
		return this.incDiscriminator;
	}

	public static Builder newBuilder() {
		return new FwSelect.Builder();
	}

	public static Builder emptyBuilder() {
		return new FwSelect.Builder().withSimple(null).withEnum(null).withRelations(null).withCollection(null)
				.withOneToOne(null).withOneToMany(null).withManyToMany(null).withManyToOne(null).withSuper(null)
				.withModelClass(null).withSpecials(null).withId(null).withDiscriminator(null);
	}

	// ======== builder class ====================

	public static class Builder {
		private Boolean incSimple = true;
		private Boolean incModelClass = true;
		private Boolean incCollection = true;
		private Boolean incEnum = true;
		private Boolean incRelations = true;
		private Boolean incOneToOne = true;
		private Boolean incOneToMany = true;
		private Boolean incManyToMany = true;
		private Boolean incManyToOne = true;
		private Boolean incSuper = true; // fields from the super class if any
		private Boolean incSpecials = false;
		private Boolean incId = false; // the implicit id, if no specific prop inc @ID is available
		private Boolean incDiscriminator = false; // the discriminator used for classes inc @Inheritance

		public Builder() {
		}

		public Builder withSimple(Boolean value) {
			this.incSimple = value;
			return this;
		}

		public Builder withEnum(Boolean value) {
			this.incEnum = value;
			return this;
		}

		public Builder withRelations(Boolean value) {
			this.incRelations = value;
			return this;
		}

		public Builder withOneToOne(Boolean value) {
			this.incOneToOne = value;
			return this;
		}

		public Builder withOneToMany(Boolean value) {
			this.incOneToMany = value;
			return this;
		}

		public Builder withManyToMany(Boolean value) {
			this.incManyToMany = value;
			return this;
		}

		public Builder withManyToOne(Boolean value) {
			this.incManyToOne = value;
			return this;
		}

		public Builder withSuper(Boolean value) {
			this.incSuper = value;
			return this;
		}

		public Builder withModelClass(Boolean value) {
			this.incModelClass = value;
			return this;
		}

		public Builder withSpecials(Boolean value) {
			this.incSpecials = value;
			return this;
		}

		public Builder withId(Boolean value) {
			this.incId = value;
			return this;
		}

		public Builder withCollection(Boolean value) {
			this.incCollection = value;
			return this;
		}

		public Builder withDiscriminator(Boolean value) {
			this.incDiscriminator = value;
			return this;
		}

		public FwSelect build() {
			FwSelect r = new FwSelect();
			r.incSimple = this.incSimple;
			r.incEnum = this.incEnum;
			r.incCollection = this.incCollection;
			r.incRelations = this.incRelations;
			r.incOneToOne = this.incOneToOne;
			r.incOneToMany = this.incOneToMany;
			r.incManyToMany = this.incManyToMany;
			r.incManyToOne = this.incManyToOne;
			r.incSuper = this.incSuper;
			r.incModelClass = this.incModelClass;
			r.incSpecials = this.incSpecials;
			r.incId = this.incId;
			r.incDiscriminator = this.incDiscriminator;
			return r;
		}
	}
}
