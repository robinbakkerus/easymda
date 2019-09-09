package blazeds;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsOpenApi
{
  protected static String nl;
  public static synchronized BlazeDsOpenApi create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsOpenApi result = new BlazeDsOpenApi();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "openapi: '3.0.0'" + NL + "" + NL + "info:" + NL + "  description: generated api for ";
  protected final String TEXT_2 = NL + "  version: 1.0.0" + NL + "  title: ";
  protected final String TEXT_3 = NL + NL + "security:" + NL + "  - bearerAuth: []    " + NL + "" + NL + "paths:";
  protected final String TEXT_4 = NL + "  /";
  protected final String TEXT_5 = ":" + NL + "    post:" + NL + "      summary: TODO" + NL + "      description: TODO" + NL + "      operationId: ";
  protected final String TEXT_6 = NL + "      responses:" + NL + "        '200':" + NL + "          description: OK" + NL + "          content:" + NL + "            application/json:" + NL + "              schema:" + NL + "                $ref: '#/components/schemas/";
  protected final String TEXT_7 = "Response'" + NL + "        '500':" + NL + "          description: Unknown error encountered" + NL + "      requestBody:" + NL + "        content:" + NL + "            application/json:" + NL + "              schema:" + NL + "                $ref: '#/components/schemas/";
  protected final String TEXT_8 = "Request'";
  protected final String TEXT_9 = NL + "  TODO get ";
  protected final String TEXT_10 = NL + NL + "components:" + NL + "  securitySchemes:" + NL + "    bearerAuth:" + NL + "      type: http" + NL + "      scheme: bearer" + NL + "      bearerFormat: JWT" + NL + "  schemas:" + NL;
  protected final String TEXT_11 = NL + "    ";
  protected final String TEXT_12 = "Request:" + NL + "      type: object" + NL + "      properties:";
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = "      ";
  protected final String TEXT_15 = "Response:" + NL + "      type: object" + NL + "      properties:";
  protected final String TEXT_16 = NL + "# All (nested) types  ";
  protected final String TEXT_17 = NL + NL + "servers:" + NL + "  - description: TODO." + NL + "    url: TODO";

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
     Object element = arg.getElement(); 
     Class cc = (element instanceof Class<?>) ? (Class) element : element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     cc = (Class) element; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_3);
     for (Method method : iu.getMethods(cc)) { 
     String operation = nu.capName(method.getName()); 
     if (oatu.isPostMethod(method)) { 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(operation);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(operation);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(operation);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(operation);
    stringBuffer.append(TEXT_8);
     } else { 
    stringBuffer.append(TEXT_9);
     } // isPost() 
     } //for-loop 
    stringBuffer.append(TEXT_10);
     for (Method method : iu.getMethods(cc)) { 
     String operation = nu.capName(method.getName()); 
       if (oatu.isPostMethod(method)) { 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(operation);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(tu.include(BlazeDsOpenApiRequest.class, cc, method));
    stringBuffer.append(TEXT_14);
       } // if post 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(operation);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(tu.include(BlazeDsOpenApiResponse.class, cc, method));
    stringBuffer.append(TEXT_14);
     } // for getMethods loop 
    stringBuffer.append(TEXT_16);
        for (Class<?> clz : pbu.findAllMethodTypes(cc)) { 
          String includeCode = tu.include(BlazeDsOpenApiComp.class, clz);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(includeCode);
        } // for findAllMethodTypes loop 
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}
