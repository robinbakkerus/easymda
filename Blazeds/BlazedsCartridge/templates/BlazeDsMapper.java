package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsMapper
{
  protected static String nl;
  public static synchronized BlazeDsMapper create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMapper result = new BlazeDsMapper();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/* generated with EasyMda  */" + NL + "" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + " ";
  protected final String TEXT_4 = NL + "  " + NL;
  protected final String TEXT_5 = NL;

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
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_4);
     List<Fw> fws = tu.getFields(cc, EXC, FwSelectType.ID); 
     String name = cc.getSimpleName(); 
     String toUncapname = nu.uncapName(cc); 
     impu.addImport(cc);
     impu.addImport("java.util.*"); 
     impu.addImport("java.util.stream.Collectors"); 
     impu.addImport(pbu.getGrpcImport(cc)); 
     impu.addImport("protobuf.mapper.*"); 
    stringBuffer.append(TEXT_5);
     if (tu.isEnum(cc)) { 
    		tu.include(BlazeDsMapperEnum.class, cc); 
     } else if (tu.isAbstract(cc)) { 
    		tu.include(BlazeDsMapperAbstract.class, cc); 
     } else { 
    		tu.include(BlazeDsMapperNormal.class, cc); 
     } 
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
