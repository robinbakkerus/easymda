package com.flca.api.test;

import mda.type.IEntityType;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.Template;
import flca.mda.codegen.data.TemplateMergeStrategy;

public class TemplatesBuilder
{
	public static ITemplate makeEntityTemplate()
	{
		return new Template("Entity", "/backend/Entity.jet", "backend.Entity", 
				"<%=target-dir%>/<%=src-gen%>", "<%=PACKAGE%>", "<%=CLASSNAME%>", ".java", null,
				new Class[] { IEntityType.class }, TemplateMergeStrategy.MERGE, 5, null);
	}

	public static ITemplate makeServiceTemplate()
	{
		return new Template("Service", "/backend/Service.jet", "backend.Service", 
				"<%=target-dir%>/<%=src-gen%>", "<%=PACKAGE%>", "<%=CLASSNAME%>", ".java", null,
				new Class[] { IEntityType.class }, TemplateMergeStrategy.MERGE, 5, null);
	}

	public static ITemplate makeServiceIntfTemplate()
	{
		return new Template("ServiceIntf", "/backend/ServiceIntf.jet", "backend.ServiceIntf", 
				"<%=target-dir%>/<%=src-gen%>", "<%=PACKAGE}.intf", "<%=CLASSNAME}Intf", ".java", null,
				new Class[] { IEntityType.class }, TemplateMergeStrategy.MERGE, 5, null);
	}
	
}
