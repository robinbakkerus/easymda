/*
 * Generated via the com.flca generator
 */
	
package com.flca.test.data;
	
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import mda.type.IEntityType;
	
@Entity 
 
public  class CTest  implements IEntityType,Serializable
{
	private static final long serialVersionUID = 1357199428295L;
 
	/** @generated */
	public CTest() {
	}
	
	protected String cname = null;
	
	
@ManyToOne()
	protected ATest a = null;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
//--- getters and setters
 
	public String getCname() {
		return this.cname;
	}
	public void setCname(String aValue) {
		this.cname = aValue;
	}
 
	public ATest getA() {
		return this.a;
	}
	public void setA(ATest aValue) {
		this.a = aValue;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long aValue) {
		id = aValue;
	}
	public Long getPk() {
		return id;
	}
	public void setPk(Long aValue) {
		id = aValue;
	}
//--- generated methods ----
	public String toString() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("id=" + this.id + ", ");
		sb.append("cname=" + this.getCname() + ", ");
		return sb.toString();
	}
	public int hashCode() 
	{
		final int PRIME = 31;	
		int result = 1;		
		result += PRIME * result + ((this.getCname() == null) ? 0 : this.getCname().hashCode());				
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
