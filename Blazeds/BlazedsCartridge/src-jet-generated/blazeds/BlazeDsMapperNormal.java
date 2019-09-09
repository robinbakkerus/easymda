package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsMapperNormal
{
  protected static String nl;
  public static synchronized BlazeDsMapperNormal create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMapperNormal result = new BlazeDsMapperNormal();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public class ";
  protected final String TEXT_2 = " {" + NL + "\t/**" + NL + "\t* Returns the Pojo object from the given Protobuf object." + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_3 = " fromPb(final ";
  protected final String TEXT_4 = "Msg sourceMsg) {" + NL + "\t\tfinal ";
  protected final String TEXT_5 = " result = new ";
  protected final String TEXT_6 = "();";
  protected final String TEXT_7 = NL + "\t\tresult.";
  protected final String TEXT_8 = "(sourceMsg.";
  protected final String TEXT_9 = "())";
  protected final String TEXT_10 = ";";
  protected final String TEXT_11 = NL + "\t\treturn result;" + NL + "\t}\t" + NL + "" + NL + "\t/**" + NL + "\t* Returns the Protobuf object from the given Pojo object" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_12 = "Msg toPb(final ";
  protected final String TEXT_13 = " source) {" + NL + "\t\tfinal ";
  protected final String TEXT_14 = "Msg.Builder builder = ";
  protected final String TEXT_15 = "Msg.newBuilder();";
  protected final String TEXT_16 = NL + "\t\t";
  protected final String TEXT_17 = "builder.";
  protected final String TEXT_18 = "(source.";
  protected final String TEXT_19 = NL + "\t\treturn builder.build();" + NL + "\t}" + NL + "\t" + NL + "\tpublic static List<";
  protected final String TEXT_20 = "> fromPb(final List<";
  protected final String TEXT_21 = "Msg> sourceMsgList) {" + NL + "\t\treturn sourceMsgList.stream().map(e -> fromPb(e)).collect(Collectors.toList());" + NL + "\t}" + NL + "" + NL + "\tpublic static List<";
  protected final String TEXT_22 = "Msg> toPb(final List<";
  protected final String TEXT_23 = "> sourceList) {" + NL + "\t\treturn sourceList.stream().map(e -> toPb(e)).collect(Collectors.toList());" + NL + "\t}" + NL + "}";
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
     OpenApiTypeUtils oatu = new OpenApiTypeUtils(); 
     SwaggerTypeUtils swtu = new SwaggerTypeUtils(); 
     Object element = arg.getElement(); 
     Class cc = (element instanceof Class<?>) ? (Class) element : element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     List<Fw> fws = tu.getFields(cc, FwSelect.newBuilder().withSuper(false).build()); 
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
    stringBuffer.append(name);
    stringBuffer.append(TEXT_6);
     for (Fw fw : fws) {
		String pbGetter = pbu.hasPbMapper(fw) ? "("+pbu.getPbGetter(fw) : "";
		String listStr = fw.isCollection() ? "List" : "";
		String closeStr = pbu.hasPbMapper(fw) ? ")" : "";

    stringBuffer.append(TEXT_7);
    stringBuffer.append(fw.setter());
    stringBuffer.append(pbGetter);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(fw.getter());
    stringBuffer.append(listStr);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(closeStr);
    stringBuffer.append(TEXT_10);
     } //for Fws 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_15);
     for (Fw fw : fws) {
		String pbSetter = pbu.hasPbMapper(fw) ? "("+pbu.getPbSetter(fw) : "";
		String closeStr = pbu.hasPbMapper(fw) ? ")" : "";
		String isNullCheck = fw.isPrimitive() ? "" : "if (source." + fw.getter() + "() != null) "; 
		String setter = fw.isCollection() ? "addAll"+nu.capName(fw.name()) : fw.setter();

    stringBuffer.append(TEXT_16);
    stringBuffer.append(isNullCheck);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(setter);
    stringBuffer.append(pbSetter);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(fw.getter());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(closeStr);
    stringBuffer.append(TEXT_10);
     } //for Fws 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
