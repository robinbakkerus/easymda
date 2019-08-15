package blazeds;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsProtobufMsg
{
  protected static String nl;
  public static synchronized BlazeDsProtobufMsg create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsProtobufMsg result = new BlazeDsProtobufMsg();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + "message ";
  protected final String TEXT_4 = " {";
  protected final String TEXT_5 = NL + "\t";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = " = ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = "\t" + NL + "}";

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
    stringBuffer.append(TEXT_1);
     List<Fw> fwList = tu.getFields(cc, FwSelect.newBuilder().build()); 
     String name = pbu.protoMessageName(cc); 
     int index = 1; 
     if (tu.isEnum(cc)) { 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pbu.generateProtobufEnumMsg(cc));
     } else { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_4);
      for (Fw fw : fwList) { 
    	if (fw.isEnum()) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(pbu.protoMessageName(fw.getField().getType()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(index++ );
    stringBuffer.append(TEXT_8);
    	   } else { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(pbu.getProtobufTypename(fw));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(index++ );
    stringBuffer.append(TEXT_8);
    	   } 
      } 
    stringBuffer.append(TEXT_9);
     } 
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
