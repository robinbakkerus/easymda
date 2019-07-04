package blazeds;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsProtobuf
{
  protected static String nl;
  public static synchronized BlazeDsProtobuf create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsProtobuf result = new BlazeDsProtobuf();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "syntax = \"proto3\";" + NL + "" + NL + "package grpc.testing;" + NL + "" + NL + "service ";
  protected final String TEXT_2 = " {";
  protected final String TEXT_3 = NL + "\trpc ";
  protected final String TEXT_4 = "(";
  protected final String TEXT_5 = ") returns (";
  protected final String TEXT_6 = ");";
  protected final String TEXT_7 = NL + "} " + NL + "\t";
  protected final String TEXT_8 = NL + "\tmessage ";
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
     cc = (Class) element; 
     ProtobufTypeUtils pbu = new ProtobufTypeUtils(); 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cc.getSimpleName());
    stringBuffer.append(TEXT_2);
     for (Method method : iu.getMethods(cc)) { 
     String srvname = method.getName(); String respname = pbu.protoMessageName(iu.getReturn(method)); 
     Class arg0type = iu.getParameterType(method, 0); String arg0name = pbu.protoMessageName(arg0type.getSimpleName()); 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(srvname);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(arg0name);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(respname);
    stringBuffer.append(TEXT_6);
     } //for-loop 
    stringBuffer.append(TEXT_7);
     for (Method method : iu.getMethods(cc)) { 
     	 Class arg0type = iu.getParameterType(method, 0); String arg0name = pbu.protoMessageName(arg0type.getSimpleName()); 
    stringBuffer.append(TEXT_8);
     } //for-loop 
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
