package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;
import flca.mda.codegen.data.*;

public class BlazeDsMapperAbstract
{
  protected static String nl;
  public static synchronized BlazeDsMapperAbstract create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMapperAbstract result = new BlazeDsMapperAbstract();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public class ";
  protected final String TEXT_2 = " {" + NL + "\t/**" + NL + "\t* returns the Pojo object from the given Protobuf object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_3 = " fromPb(final ";
  protected final String TEXT_4 = "Msg sourceMsg) {" + NL + "\t\t";
  protected final String TEXT_5 = " result = null;";
  protected final String TEXT_6 = NL + "\t\tif (sourceMsg.get";
  protected final String TEXT_7 = "Case().equals(";
  protected final String TEXT_8 = "Case.";
  protected final String TEXT_9 = ")) {" + NL + "\t\t\tresult = ";
  protected final String TEXT_10 = "Mapper.fromPb(sourceMsg.get";
  protected final String TEXT_11 = "());" + NL + "\t\t}";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t\tresult.";
  protected final String TEXT_14 = "(";
  protected final String TEXT_15 = "(sourceMsg.";
  protected final String TEXT_16 = "()));";
  protected final String TEXT_17 = NL + "\t\treturn result;" + NL + "\t}\t" + NL + "" + NL + "\t/**" + NL + "\t* returns the Protobuf object from the given Pojo object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_18 = "Msg toPb(final ";
  protected final String TEXT_19 = " source) {" + NL + "\t\tfinal ";
  protected final String TEXT_20 = "Msg.Builder builder = ";
  protected final String TEXT_21 = "Msg.newBuilder();";
  protected final String TEXT_22 = NL + "\t\t";
  protected final String TEXT_23 = "builder.";
  protected final String TEXT_24 = "(source.";
  protected final String TEXT_25 = "())";
  protected final String TEXT_26 = ";";
  protected final String TEXT_27 = NL + "\t\treturn builder.build();" + NL + "\t}" + NL + "\t" + NL + "\tpublic static List<";
  protected final String TEXT_28 = "> fromPb(final List<";
  protected final String TEXT_29 = "Msg> sourceMsgList) {" + NL + "\t\treturn sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());" + NL + "\t}" + NL + "" + NL + "\tpublic static List<";
  protected final String TEXT_30 = "Msg> toPb(final List<";
  protected final String TEXT_31 = "> sourceList) {" + NL + "\t\treturn sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());" + NL + "\t}" + NL + "}";

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
     List<Fw> fws = tu.getFields(cc, EXC, FwSelectType.ID); 
     String name = cc.getSimpleName(); 
     String toUncapname = nu.uncapName(cc); 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_5);
    
	for (Class<?> subclz : tu.getSubClasses(cc)) {
		String subName = subclz.getSimpleName(); String caseName = subName.toUpperCase();

    stringBuffer.append(TEXT_6);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(caseName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(subName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(subName);
    stringBuffer.append(TEXT_11);
     } // for subClasses 
    stringBuffer.append(TEXT_12);
     for (Fw fw : fws) {
	String pbGetter = pbu.getPbGetter(fw); 

    stringBuffer.append(TEXT_13);
    stringBuffer.append(fw.setter());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(pbGetter);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(fw.getter());
    stringBuffer.append(TEXT_16);
     } // for Fws 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_21);
     for (Fw fw : fws) {
		String pbSetter = pbu.hasPbMapper(fw) ? "("+pbu.getPbSetter(fw) : "";
		String closeStr = pbu.hasPbMapper(fw) ? ")" : "";
		String isNullCheck = fw.isPrimitive() ? "" : "if (source." + fw.getter() + "() != null) "; 

    stringBuffer.append(TEXT_22);
    stringBuffer.append(isNullCheck);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(fw.setter());
    stringBuffer.append(pbSetter);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(fw.getter());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(closeStr);
    stringBuffer.append(TEXT_26);
     } // for Fws 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}
