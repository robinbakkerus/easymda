package blazeds;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsSwaggerDefinitions
{
  protected static String nl;
  public static synchronized BlazeDsSwaggerDefinitions create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsSwaggerDefinitions result = new BlazeDsSwaggerDefinitions();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "  ";
  protected final String TEXT_2 = NL + "    ";
  protected final String TEXT_3 = ":";
  protected final String TEXT_4 = NL + "      type: string" + NL + "      enum:";
  protected final String TEXT_5 = NL + "        - ";
  protected final String TEXT_6 = NL + "      type: object" + NL + "      properties:";
  protected final String TEXT_7 = NL + "        ";
  protected final String TEXT_8 = NL + "          $ref: ";
  protected final String TEXT_9 = NL + "          type: array" + NL + "          items:" + NL + "            $ref: ";
  protected final String TEXT_10 = NL + "          type: ";

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
     List<Fw> fwList = tu.getFields(cc, FwSelect.newBuilder().build()); 
     String name = cc.getSimpleName(); 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_3);
     if (tu.isEnum(cc)) { 
    stringBuffer.append(TEXT_4);
    	for (Object enumobj : cc.getEnumConstants()) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(enumobj.toString());
      } // enumloop 
     } else { // not an enum 
    stringBuffer.append(TEXT_6);
      for (Fw fw : fwList) { 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_3);
    	  if (fw.isEnum()) { 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(swtu.getTypeName(fw));
    	  } else if (fw.isArray() || fw.isCollection()) { 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(swtu.getTypeName(fw));
    	  } else if (fw.isModelClass()) { 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(swtu.getTypeName(fw) );
    	  } else { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(swtu.getTypeName(fw));
    	  } 
      }  
     } 
    return stringBuffer.toString();
  }
}
