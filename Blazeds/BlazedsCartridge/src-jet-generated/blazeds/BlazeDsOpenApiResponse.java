package blazeds;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsOpenApiResponse
{
  protected static String nl;
  public static synchronized BlazeDsOpenApiResponse create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsOpenApiResponse result = new BlazeDsOpenApiResponse();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        ";
  protected final String TEXT_2 = ":" + NL + "          $ref: ";

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
     Method m = (Method) args[1]; 
     for (int idx=0; idx < m.getParameterCount(); idx++) { 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(swtu.getMethodArgName(m, idx));
    stringBuffer.append(TEXT_2);
    stringBuffer.append(swtu.getMethodArgTypeName(m, idx));
     } 
    return stringBuffer.toString();
  }
}
