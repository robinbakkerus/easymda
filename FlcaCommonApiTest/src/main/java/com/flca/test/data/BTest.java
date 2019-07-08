/*
 * Generated via the com.flca generator
 */
	
package com.flca.test.data;
	
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mda.type.IEntityType;
	
@Entity 
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"bname","uniek",})}) 
public  class BTest  implements IEntityType,Serializable
{
	private static final long serialVersionUID = 1357199428094L;
 
	/** @generated */
	public BTest() {
	}
	
@Id()
	protected Long id = null;
	
	
@Column(name="bname")
	protected String bname = null;
	
	
@Column(name="aantal")
	protected int baantal = 0;
	
	
@Column(name="uniek")
	protected int uniek = 0;
	
//--- getters and setters
 
	public Long getId() {
		return this.id;
	}
	public void setId(Long aValue) {
		this.id = aValue;
	}
 
	public String getBname() {
		return this.bname;
	}
	public void setBname(String aValue) {
		this.bname = aValue;
	}
 
	public int getBaantal() {
		return this.baantal;
	}
	public void setBaantal(int aValue) {
		this.baantal = aValue;
	}
 
	public int getUniek() {
		return this.uniek;
	}
	public void setUniek(int aValue) {
		this.uniek = aValue;
	}
	public Long getPk() {
		return this.id;
	}
	public void setPk(Long aValue) {
		 this.id = aValue;
	}
//--- generated methods ----
	public String toString() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("id=" + this.getId() + ", ");
		sb.append("bname=" + this.getBname() + ", ");
		sb.append("baantal=" + this.getBaantal() + ", ");
		sb.append("uniek=" + this.getUniek() + ", ");
		return sb.toString();
	}
	public int hashCode() 
	{
		final int PRIME = 31;	
		int result = 1;		
		result += PRIME * result + ((this.getId() == null) ? 0 : this.getId().hashCode());		
		result += PRIME * result + ((this.getBname() == null) ? 0 : this.getBname().hashCode());		
		result += PRIME * result + ("" + this.getBaantal()).toString().hashCode();		
		result += PRIME * result + ("" + this.getUniek()).toString().hashCode();				
		return result;
	}
 
	/** @generated */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else {
			return this.hashCode() == other.hashCode();
		}
	}		
}
