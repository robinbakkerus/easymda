package blazeds;

import java.util.*;
import flca.mda.api.util.*;
import flca.blazeds.api.*;
import flca.blazeds.template.*;

public class BlazeDsMockDataJava
{
  protected static String nl;
  public static synchronized BlazeDsMockDataJava create(String lineSeparator)
  {
    nl = lineSeparator;
    BlazeDsMockDataJava result = new BlazeDsMockDataJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/* generated with EasyMda  */" + NL + "" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + " ";
  protected final String TEXT_4 = NL + "  " + NL;
  protected final String TEXT_5 = NL + NL + "public class ";
  protected final String TEXT_6 = " {" + NL + "\t";
  protected final String TEXT_7 = NL + "\tprivate static final ";
  protected final String TEXT_8 = "[] ";
  protected final String TEXT_9 = " = ";
  protected final String TEXT_10 = "; ";
  protected final String TEXT_11 = NL + "\t\t" + NL + "\t/**" + NL + "\t* Generate instance with mock data. " + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_12 = " build() {" + NL + "\t\treturn build(0);" + NL + "\t} " + NL + "\t" + NL + "\t/**" + NL + "\t* Generate List of instances with mock data. " + NL + "\t*/" + NL + "\tpublic static List<";
  protected final String TEXT_13 = "> buildList() {" + NL + "\t\tList<";
  protected final String TEXT_14 = "> result = new ArrayList<>();" + NL + "\t\tfor (int i=0; i<3; i++) {" + NL + "\t\t\tresult.add(build());" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "\t" + NL + "\tprivate static ";
  protected final String TEXT_15 = " build(int nr) {";
  protected final String TEXT_16 = NL + "\t\t";
  protected final String TEXT_17 = " r = new ";
  protected final String TEXT_18 = "();";
  protected final String TEXT_19 = NL + "\t\tr.";
  protected final String TEXT_20 = "(";
  protected final String TEXT_21 = "[nr]); ";
  protected final String TEXT_22 = ".buildList()); ";
  protected final String TEXT_23 = ".build()); ";
  protected final String TEXT_24 = NL + NL + "\t\treturn r;" + NL + "\t} " + NL + "}" + NL;
  protected final String TEXT_25 = NL;

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
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_4);
     MockDataUtil mdu = new MockDataUtil(); 
     String name = cc.getSimpleName(); 
     String toUncapname = nu.uncapName(cc); 
     impu.addImport(cc);
     impu.addImport("java.util.*"); 
     impu.addImport("java.util.stream.Collectors"); 
     for (String s : pbu.getGrpcImports(cc)) { 
		impu.addImport(s);
	} 

     impu.addImport("protobuf.mapper.*"); 
     List<Fw> fws = tu.hasSubClasses(cc) ? tu.getFields(mdu.randomSubClass(cc), FwSelect.newBuilder().build()) : tu.getFields(cc, FwSelect.newBuilder().build()); 
     String subClassName = cc.getSimpleName(); 
     if (tu.hasSubClasses(cc)) { 
	subClassName = mdu.randomSubClass(cc).getSimpleName();
 } 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    
	for (Fw fw : fws) {
		if (fw.isSimple()) {
			String constName = fw.name().toUpperCase();

    stringBuffer.append(TEXT_7);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(constName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(mdu.randomValues(fw,3) );
    stringBuffer.append(TEXT_10);
    		} 
    	} 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_15);
     if (tu.hasSubClasses(cc)) { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(subClassName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(subClassName);
    stringBuffer.append(TEXT_18);
     } else { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_18);
     } // hasSubclasses 
    
	for (Fw fw : fws) {
		String setter = "set" + nu.capName(fw.name());
		String constName = fw.name().toUpperCase();
		String mocker = fw.genericTypeName() + "Mock";
		if (fw.isEnum()) { 

    stringBuffer.append(TEXT_19);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(constName);
    stringBuffer.append(TEXT_21);
    		} else if (fw.isCollection() || fw.isArray()) { 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(mocker);
    stringBuffer.append(TEXT_22);
    		} else if (fw.isModelClass()) { 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(mocker);
    stringBuffer.append(TEXT_23);
    		} else { 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(constName);
    stringBuffer.append(TEXT_21);
    		} 
    	} 
    stringBuffer.append(TEXT_24);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
