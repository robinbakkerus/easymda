package com.flca.frw.test.type;

import java.util.Set;

import mda.annotation.jpa.CascadeType;
import mda.annotation.jpa.FetchType;
import mda.annotation.jpa.JoinTable;
import mda.annotation.jpa.ManyToMany;
import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.Table;
import mda.annotation.jpa.UniqueConstraint;
import mda.type.IEntityType;

import com.flca.test.types.TestB;


@Table(	uniqueConstraints={@UniqueConstraint(columnNames={"c1", "c2"})})
public class ClassWithJpaAnnotations implements IEntityType
{

	@OneToMany
	public Set<TestB> one2many_unidirect;

	@OneToMany(mappedBy="MySelf")
	public Set<TestB> one2many_bidirect;

	@OneToMany(cascade=CascadeType.ALL)
	public Set<TestB> one2many_bidirect_cascadeall;

	@OneToMany(fetch=FetchType.EAGER)
	public Set<TestB> one2many_bidirect_eager;
	
	@OneToMany(mappedBy="a")
	public Set<TestB> one2many_bidirect_mappedby;
	
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.PERSIST}, fetch=FetchType.EAGER, mappedBy="a")
	public Set<TestB> one2many_eager_mappedby_bidirect;

	@ManyToOne
	public TestB many2one_unidirect;

	@ManyToOne
	@JoinTable
	public TestB many2one_jointable;
	
	@ManyToMany
	public TestB many2many;
	
}
