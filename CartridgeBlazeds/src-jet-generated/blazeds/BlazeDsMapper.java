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
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "public class ";
  protected final String TEXT_7 = " {" + NL + "\t/**" + NL + "\t* return the Pojo enum from the given Protobuf enum" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_8 = " fromProtobuf(final ";
  protected final String TEXT_9 = "Msg sourceMsg) {" + NL + "\t\treturn ";
  protected final String TEXT_10 = ".valueOf(sourceMsg.name());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t* return the Protobuf enum from the given Pojo enum" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_11 = "Msg toProtobuf(final ";
  protected final String TEXT_12 = " source) {" + NL + "\t\treturn ";
  protected final String TEXT_13 = "Msg.valueOf(source.name());" + NL + "\t}" + NL + "}";
  protected final String TEXT_14 = " {" + NL + "\t/**" + NL + "\t* return the Pojo object from the given Protobuf object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_15 = "Msg sourceMsg) {" + NL + "\t\tfinal ";
  protected final String TEXT_16 = " result = new ";
  protected final String TEXT_17 = "();";
  protected final String TEXT_18 = NL + "\t\tresult.";
  protected final String TEXT_19 = "(sourceMsg.";
  protected final String TEXT_20 = "());";
  protected final String TEXT_21 = "(";
  protected final String TEXT_22 = ".fromProtobuf(sourceMsg.";
  protected final String TEXT_23 = "()));";
  protected final String TEXT_24 = "\t\t" + NL + "\t\treturn result;" + NL + "\t}\t" + NL + "" + NL + "\t/**" + NL + "\t* return the Protobuf object from the given Pojo object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_25 = " source) {" + NL + "\t\tfinal ";
  protected final String TEXT_26 = "Msg.Builder builder = ";
  protected final String TEXT_27 = "Msg.newBuilder();";
  protected final String TEXT_28 = NL + "\t\tbuilder.";
  protected final String TEXT_29 = "(source.";
  protected final String TEXT_30 = ".toProtobuf(source.";
  protected final String TEXT_31 = "\t\t" + NL + "\t\treturn builder.build();" + NL + "\t}" + NL + "\t" + NL + "\tpublic static List<";
  protected final String TEXT_32 = "> fromProtobuf(final List<";
  protected final String TEXT_33 = "Msg> sourceMsgList) {" + NL + "\t\treturn sourceMsgList.stream().map(e -> fromProtobuf(e)).collect(Collectors.toList());" + NL + "\t}" + NL + "" + NL + "\tpublic static List<";
  protected final String TEXT_34 = "Msg> toProtobuf(final List<";
  protected final String TEXT_35 = "> sourceList) {" + NL + "\t\treturn sourceList.stream().map(e -> toProtobuf(e)).collect(Collectors.toList());" + NL + "\t}" + NL + "}";

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
     impu.addImport("java.util.*"); 
     impu.addImport("java.util.stream.Collectors"); 
     impu.addImport(pbu.getGrpcImport(cc)); 
     impu.addImport("protobuf.mapper.*"); 
    stringBuffer.append(TEXT_5);
     if (tu.isEnum(cc)) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_13);
     } else { // isEnm() =================== 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_17);
    
	for (Fw fw : fws) {
		String mapper = pbu.getMapper(fw); String capname = nu.capName(fw.name());
		if (mapper == null) {

     String setter = "set" + capname; 
     String getter = "get" + capname; 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(getter);
    stringBuffer.append(TEXT_20);
    
		} else {

     String setter = "set" + capname; 
     String getter = (fw.isCollection()) ? "get" + capname + "List" : "get" + capname; 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(getter);
    stringBuffer.append(TEXT_23);
    
		}
	}

    stringBuffer.append(TEXT_24);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_27);
    
	for (Fw fw : fws) {
		String mapper = pbu.getMapper(fw); String capname = nu.capName(fw.name());
		if (mapper == null) {

     String setter = "set" + capname; 
     String getter = "get" + capname; 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(getter);
    stringBuffer.append(TEXT_20);
    
		} else {

     String setter = (fw.isCollection()) ? "addAll" + capname : "set" + capname; 
     String getter = "get" + capname; 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(mapper);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(getter);
    stringBuffer.append(TEXT_23);
    
		}
	}

    stringBuffer.append(TEXT_31);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_35);
     } 
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
