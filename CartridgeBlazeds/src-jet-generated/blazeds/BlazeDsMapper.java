package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsMapper
{
  protected static String nl;
  public static synchronized BlazeDsMapper create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMapper result = new BlazeDsMapper();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/* generated with EasyMda  */" + NL + "" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + " ";
  protected final String TEXT_4 = NL + "  " + NL;
  protected final String TEXT_5 = NL + NL + "public class ";
  protected final String TEXT_6 = " {" + NL + "\t/**" + NL + "\t* return the Pojo object from the given Protobuf object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_7 = " fromMsg(final AdresMsg sourceMsg) {" + NL + "\t\tfinal ";
  protected final String TEXT_8 = " result = new ";
  protected final String TEXT_9 = "();";
  protected final String TEXT_10 = NL + "\t\tresult.set";
  protected final String TEXT_11 = "(sourceMsg.get";
  protected final String TEXT_12 = "());";
  protected final String TEXT_13 = "(";
  protected final String TEXT_14 = ".fromProtobuf(sourceMsg.get";
  protected final String TEXT_15 = "()));";
  protected final String TEXT_16 = "\t\t" + NL + "\t\treturn result;" + NL + "\t}\t" + NL + "" + NL + "\t/**" + NL + "\t* return the Protobuf object from the given Pojo object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_17 = "Msg toMsg(final AdresMsg source) {" + NL + "\t\tfinal ";
  protected final String TEXT_18 = "Msg builder = ";
  protected final String TEXT_19 = "Msg.newBuilder();";
  protected final String TEXT_20 = NL + "\t\tbuilder.set";
  protected final String TEXT_21 = "(source.get";
  protected final String TEXT_22 = ".toProtobuf(source.get";
  protected final String TEXT_23 = "\t\t" + NL + "\t\treturn builder.build();" + NL + "\t}\t" + NL + "" + NL + "}";
  protected final String TEXT_24 = NL;

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
			} else {
				arg = new JetArgument();
				arg.setElement(args[0]);
			}
		}
	} 

     BlazeDsTypeUtils tu = new BlazeDsTypeUtils();
     NameUtils nu = new NameUtils();
     InterfaceUtils iu = new InterfaceUtils(); 
     AppUtils au = new AppUtils(); 
     ProtobufTypeUtils pbu = new ProtobufTypeUtils(); 
     Object element = arg.getElement(); 
     Class cc = (element instanceof Class<?>) ? (Class) element : element.getClass(); 
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
     List<Fw> fws = tu.getFields(cc, EXC, FwSelectType.ID); 
     String name = cc.getSimpleName(); 
     String toUncapname = nu.uncapName(cc); 
     impu.addImport(cc);
     impu.addImport("org.junit.Test"); 
     impu.addImport("java.util.HashSet"); 
     impu.addImport("java.util.Set"); 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_9);
    
	for (Fw fw : fws) {
		String mapper = pbu.getMapper(fw); String capname = nu.capName(fw.name());
		if (mapper == null) {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_12);
    
		} else {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_15);
    
		}
	}

    stringBuffer.append(TEXT_16);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_19);
    
	for (Fw fw : fws) {
		String mapper = pbu.getMapper(fw); String capname = nu.capName(fw.name());
		if (mapper == null) {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_12);
    
		} else {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_15);
    
		}
	}

    stringBuffer.append(TEXT_23);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
