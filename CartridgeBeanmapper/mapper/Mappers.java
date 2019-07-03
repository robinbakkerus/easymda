package mapper;

import java.util.*;
import flca.mda.api.util.*;
import mda.template.*;
import mda.template.type.*;
import flca.mda.codegen.data.*;

public class Mappers
{
  protected static String nl;
  public static synchronized Mappers create(String lineSeparator)
  {
    nl = lineSeparator;
    Mappers result = new Mappers();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL;

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

     BeanMapperTypeUtils tu = new BeanMapperTypeUtils();
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
     Object carttype = arg.getElement(); 
   if (carttype instanceof ITOMapper) {
     ITOMapper tomapper = (ITOMapper) carttype;  
     Class toClass = tomapper.getTO(); 
     Class entityClass = tomapper.getEntity(); 
     tu.recursiveGenerate(toClass, entityClass, true);
   }

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
