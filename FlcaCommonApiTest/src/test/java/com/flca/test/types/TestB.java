package com.flca.test.types;

import mda.annotation.jpa.Column;
import mda.annotation.jpa.Table;
import mda.annotation.jpa.UniqueConstraint;

@Table(	uniqueConstraints=
	{@UniqueConstraint(columnNames={"bName"}), @UniqueConstraint(columnNames={"bAantal"}), @UniqueConstraint(columnNames={"xxx"})}
)
public class TestB
{
	@Column(name="bName")
	String bName;
	
	@Column(name="bAantal")
	int bAantal;

	@Column(name="xxx")
	int xxx;
}
