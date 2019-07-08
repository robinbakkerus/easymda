package com.flca.test.data;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.flca.frw.test.type.TestEnum;

import mda.type.IEntityType;
/**
 * @todo add comment for javadoc
 *
 * @author rbakkerus
 * @version $Id: EntityClass.javajet,v 1.3 2007/06/29 21:48:03 robin bakkerus Exp $
 * @generated
 */
	@Entity 
	@Table (name = "A",
	  uniqueConstraints = {@UniqueConstraint(columnNames={"name"})}
	)

public class ATest  implements Serializable, Cloneable, IEntityType
{
	private static final long serialVersionUID = 123L;

	/** @generated */
	public ATest() {
	}
	
	/** @generated */
	@NotNull
//	@Length(min=0, max=50)	TODO
	protected String name = null;

	/** @generated */
	@Basic
@Column(name = "notes", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected String notes = null;

	/** @generated */
	@Basic
@Column(name = "dob", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Date dob = null;

	/** @generated */
	@Basic
@Column(name = "nr", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Long nr = null;

	/** @generated */
	@Basic
@Column(name = "pct", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Double pct = null;

	/** @generated */
	@Basic
@Column(name = "salary", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected BigDecimal salary = null;

	/** @generated */
	@Basic
@Column(name = "start", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Timestamp start = null;

	/** @generated */
	@Basic
@Column(name = "longNr", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Long longNr = null;

	/** @generated */
	@Basic
@Column(name = "yesno", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Boolean yesno = null;

	/** @generated */
	@Basic
@Column(name = "value", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Double value = null;

	/** @generated */
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "b")
	protected BTest b = null;

	/** @generated */
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy="parent")
	protected Set<CTest> cs = null;

	/** @generated */
//	@Length(min=0, max=50) TODO
	protected String strmax = null;

	/** @generated */
//	@Length(min=5, max=20) TODO
	protected String strmin = null;

	/** @generated */
	@NotNull
	@Max(value=50000)
	@Min(value=10000)
	protected BigDecimal valmax = null;

	/** @generated */
	@Basic
@Column(name = "dateFuture", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Date dateFuture = null;

	/** @generated */
	@Basic
@Column(name = "datePast", length = 255, precision = 0, scale = 0, columnDefinition = "")
	protected Date datePast = null;


	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public String getName() {
		return name;
	}
	/** @generated */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public String getNotes() {
		return notes;
	}
	/** @generated */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Date getDob() {
		return dob;
	}
	/** @generated */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Long getNr() {
		return nr;
	}
	/** @generated */
	public void setNr(Long nr) {
		this.nr = nr;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Double getPct() {
		return pct;
	}
	/** @generated */
	public void setPct(Double pct) {
		this.pct = pct;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public BigDecimal getSalary() {
		return salary;
	}
	/** @generated */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Timestamp getStart() {
		return start;
	}
	/** @generated */
	public void setStart(Timestamp start) {
		this.start = start;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Long getLongNr() {
		return longNr;
	}
	/** @generated */
	public void setLongNr(Long longNr) {
		this.longNr = longNr;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Boolean getYesno() {
		return yesno;
	}
	/** @generated */
	public void setYesno(Boolean yesno) {
		this.yesno = yesno;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Double getValue() {
		return value;
	}
	/** @generated */
	public void setValue(Double value) {
		this.value = value;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public BTest getB() {
		return b;
	}
	/** @generated */
	public void setB(BTest b) {
		this.b = b;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Set<CTest> getCs() {
		if (this.cs == null) {
			this.cs = new java.util.HashSet<CTest>();        
		}
		return cs;
	}
	/** @generated */
	public void setCs(Set<CTest> cs) {
		this.cs = cs;
	}
	
	/**
	* Use this method to find out if corresponding set is not null
	*/
	public boolean isCsNotNull() {
		return (this.cs != null);
	}
	/**
	* Use this method to find out if corresponding set is null
	*/
	public boolean isCsNull() {
		return (this.cs == null);
	}
		
	/**
	 * Associate A with C
	 * @generated
	 */
	public void addC(CTest c) {
		if (c == null) return;
		getCs().add(c);
	}
	/**
	 * Unassociate A from C
	 * @generated
	 */
	public void removeC(CTest c) {
		if (c == null) return;
		getCs().remove(c);
	}

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public String getStrmax() {
		return strmax;
	}
	/** @generated */
	public void setStrmax(String strmax) {
		this.strmax = strmax;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public String getStrmin() {
		return strmin;
	}
	/** @generated */
	public void setStrmin(String strmin) {
		this.strmin = strmin;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public BigDecimal getValmax() {
		return valmax;
	}
	/** @generated */
	public void setValmax(BigDecimal valmax) {
		this.valmax = valmax;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Date getDateFuture() {
		return dateFuture;
	}
	/** @generated */
	public void setDateFuture(Date dateFuture) {
		this.dateFuture = dateFuture;
	}
	

	/**
	 * @todo add comment for javadoc
	 * Note a returned Set is always NOT null
	 * @generated
	 */
	public Date getDatePast() {
		return datePast;
	}
	/** @generated */
	public void setDatePast(Date datePast) {
		this.datePast = datePast;
	}
	

	protected TestEnum testEnum = null;

	/**
	 * @todo add comment for javadoc
	 * @generated
	 */
	public TestEnum getTestEnum() {
		return this.testEnum;       
	}

	public void setTestEnum(TestEnum testEnum) {
		this.testEnum = testEnum;
	}

	/** @generated */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}	
	/** @generated */
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	/** @generated */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ATest other = (ATest) obj;
		if (getId() != null) {
			return getId().equals(other.getId());
		} else {
			return false;
		}
	}		

	/** @generated */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	public Long getId() {
		return id;
	}

	public void setId(Long aValue) {
		if (aValue != null && aValue.longValue() == 0) {
			id = null;
		} else {
			id = aValue;
		}
	}
	
	//we need this because otherwise the AMF will not pass the id to the AS class
	
	@Transient
	protected long pkValue = 0;
	
	public long getPkValue() {
		if (id != null) {
			return id.longValue();
		} else {
			return 0;
		}
	}
	
	public void setPkValue(long aValue) {
		if (aValue != 0) {
			id = new Long(aValue);
		}
	}
	
}
