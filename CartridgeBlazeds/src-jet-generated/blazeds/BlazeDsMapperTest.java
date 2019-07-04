package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsMapperTest
{
  protected static String nl;
  public static synchronized BlazeDsMapperTest create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMapperTest result = new BlazeDsMapperTest();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/* generated with EasyMda  */" + NL + "" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + " ";
  protected final String TEXT_4 = NL + "  " + NL;
  protected final String TEXT_5 = NL + NL + "public class ";
  protected final String TEXT_6 = " extends AbstractTestBeanMapper {" + NL + "\t" + NL + "\t@Override" + NL + "\tprotected Set<String> ignoreMapToEntityProperties() {" + NL + "\t\tSet<String> result = new HashSet<>();" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tprotected Set<String> ignoreMapFromEntityProperties() {" + NL + "\t\tSet<String> result = new HashSet<>();" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t@Test" + NL + "\tpublic void testMapToEntity() {" + NL + "\t\t";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = "1 =(";
  protected final String TEXT_9 = ") populate(";
  protected final String TEXT_10 = ".class);" + NL + "\t\t";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ".mapFromEntity(";
  protected final String TEXT_13 = "1);" + NL + "\t\t";
  protected final String TEXT_14 = "2 = ";
  protected final String TEXT_15 = ".mapToEntity(";
  protected final String TEXT_16 = ");" + NL + "\t\ttestEquals(";
  protected final String TEXT_17 = "1, ";
  protected final String TEXT_18 = "2, ignoreMapToEntityProperties());" + NL + "\t}" + NL + "" + NL + "\t@Test" + NL + "\tpublic void testMapFromEntity() {" + NL + "\t\t";
  protected final String TEXT_19 = "1 = ";
  protected final String TEXT_20 = "1);" + NL + "\t\ttestEquals(";
  protected final String TEXT_21 = "2, ignoreMapFromEntityProperties());" + NL + "\t}" + NL + "\t" + NL + "}";
  protected final String TEXT_22 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     	// here we parse the input argument(s) and create the object JetArgument:arg and Object[]:args
	JetArgument arg = null;
	Object args[] = null;

	if (argument != null) { 
		if (argument instanceof JetArgument) {
			arg = (JetArgument) argument;
		} else if (argument.getClass().isArray()) { 
			args = (Object[]) argument;
			if (args[0] instanceof JetArgument) {
				arg = (JetArgument) args[0];
			}
		}
	} 

     BlazeDsTypeUtils tu = new BlazeDsTypeUtils();
     NameUtils nu = new NameUtils();
     TemplateUtils tplu = new TemplateUtils();
     InterfaceUtils iu = new InterfaceUtils(); 
     AppUtils au = new AppUtils(); 
     Object element = arg.getElement(); 
     Class cc = element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     GetFieldsModus EXC = GetFieldsModus.EXCLUDE; 
     GetFieldsModus INC = GetFieldsModus.INCLUDE; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_4);
     Class toClass = cc; 
     String toClassname = toClass.getSimpleName(); 
     Class entityClass = (Class) args[1]; 
     String entityClassname = entityClass.getSimpleName(); 
     List<Fw> toFws = tu.getFields(toClass, EXC, FwSelectType.ID); 
     List<Fw> entityFws = tu.getFields(entityClass, EXC, FwSelectType.ID); 
     String toUncapname = nu.uncapName(toClassname); 
     String entityUncapname = nu.uncapName(entityClassname); 
     String mapper = toClassname + "Mapper"; //tplu.getClassName(TidBeanMapper.BEANMAPPER_TO.name()); 
     impu.addImport(toClass);
     impu.addImport(entityClass);
     impu.addImport("org.junit.Test"); 
     impu.addImport("java.util.HashSet"); 
     impu.addImport("java.util.Set"); 
     impu.addImport("nl.apg.util.mapper.AbstractTestBeanMapper"); 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entityUncapname);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(toUncapname);
    stringBuffer.append(TEXT_21);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
