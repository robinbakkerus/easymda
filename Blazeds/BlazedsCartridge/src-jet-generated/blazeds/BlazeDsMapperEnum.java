package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsMapperEnum
{
  protected static String nl;
  public static synchronized BlazeDsMapperEnum create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMapperEnum result = new BlazeDsMapperEnum();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public class ";
  protected final String TEXT_2 = " {" + NL + "\t/**" + NL + "\t* returns the Pojo enum from the given Protobuf enum" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_3 = " fromPb(final ";
  protected final String TEXT_4 = "Msg sourceMsg) {" + NL + "\t\treturn ";
  protected final String TEXT_5 = ".valueOf(sourceMsg.name());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t* returns the Protobuf enum from the given Pojo enum" + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_6 = "Msg toPb(final ";
  protected final String TEXT_7 = " source) {" + NL + "\t\treturn ";
  protected final String TEXT_8 = "Msg.valueOf(source.name());" + NL + "\t}" + NL + "}";
  protected final String TEXT_9 = NL;

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
    stringBuffer.append(name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
