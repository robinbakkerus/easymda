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
  protected final String TEXT_1 = "syntax = \"proto3\";" + NL + "" + NL + "option java_multiple_files = true;" + NL + "option java_package = \"jrb.grpc.blazeds\";" + NL + "option java_outer_classname = \"";
  protected final String TEXT_2 = "Proto\";" + NL + "option objc_class_prefix = \"HLW\";" + NL + "" + NL + "package jrb.grpc;" + NL + "" + NL + "service ";
  protected final String TEXT_3 = " {";
  protected final String TEXT_4 = NL + "\trpc ";
  protected final String TEXT_5 = "(";
  protected final String TEXT_6 = ") returns (";
  protected final String TEXT_7 = ");";
  protected final String TEXT_8 = NL + "}\t";
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
     Object element = arg.getElement(); 
     Class cc = (element instanceof Class<?>) ? (Class) element : element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     GetFieldsModus EXC = GetFieldsModus.EXCLUDE; 
     GetFieldsModus INC = GetFieldsModus.INCLUDE; 
     cc = (Class) element; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cc.getSimpleName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cc.getSimpleName());
    stringBuffer.append(TEXT_3);
     for (Method method : iu.getMethods(cc)) { 
     String srvname = method.getName(); String respname = pbu.protoMessageName(iu.getReturn(method)); 
     Class arg0type = iu.getParameterType(method, 0); String arg0name = pbu.protoMessageName(arg0type); //todo support meerdere args 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(srvname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(arg0name);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(respname);
    stringBuffer.append(TEXT_7);
     } //for-loop 
    stringBuffer.append(TEXT_8);
     for (Class<?> clz : pbu.findAllMethodTypes(cc)) { 
     String includeCode = tu.include(BlazeDsProtobufMsg.class, clz);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(includeCode);
     } //for-loop 
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
