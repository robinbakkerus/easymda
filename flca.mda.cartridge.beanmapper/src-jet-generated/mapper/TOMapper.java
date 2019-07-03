package mapper;

import java.util.*;
import flca.mda.api.util.*;
import mda.template.*;
import mda.template.type.*;
import flca.mda.codegen.data.*;

public class TOMapper
{
  protected static String nl;
  public static synchronized TOMapper create(String lineSeparator)
  {
    nl = lineSeparator;
    TOMapper result = new TOMapper();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/* generated with BeanMapper */" + NL + "" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + " ";
  protected final String TEXT_4 = NL + "  " + NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + NL + "public class ";
  protected final String TEXT_7 = " {" + NL + "\t/**" + NL + "\t* map entity class to To" + NL + "\t* @param entity, ";
  protected final String TEXT_8 = NL + "\t* @result ";
  protected final String TEXT_9 = " " + NL + "\t*/" + NL + "\tpublic static ";
  protected final String TEXT_10 = " mapFromEntity(";
  protected final String TEXT_11 = " entity) {" + NL + "\t\t";
  protected final String TEXT_12 = " result = new ";
  protected final String TEXT_13 = "();";
  protected final String TEXT_14 = NL + "\t\tmapFrom";
  protected final String TEXT_15 = "(entity, result);";
  protected final String TEXT_16 = "\t\t" + NL + "\t\treturn result;" + NL + "\t}" + NL + " ";
  protected final String TEXT_17 = NL + "\tprivate static void mapFrom";
  protected final String TEXT_18 = "(final ";
  protected final String TEXT_19 = " entity, final ";
  protected final String TEXT_20 = " result) {";
  protected final String TEXT_21 = NL + "\t\tresult.set";
  protected final String TEXT_22 = "(entity.get";
  protected final String TEXT_23 = "());";
  protected final String TEXT_24 = NL + " \t\tif (entity.get";
  protected final String TEXT_25 = "() != null) { " + NL + "\t\t\tresult.set";
  protected final String TEXT_26 = "(new ";
  protected final String TEXT_27 = "()); " + NL + "\t\t\tfor (";
  protected final String TEXT_28 = " item : entity.get";
  protected final String TEXT_29 = "()) {";
  protected final String TEXT_30 = NL + "\t\t\t\t";
  protected final String TEXT_31 = " elem = ";
  protected final String TEXT_32 = ".mapFromEntity(item);" + NL + "\t\t\t\telem.";
  protected final String TEXT_33 = "(result);" + NL + "\t\t\t\tresult.get";
  protected final String TEXT_34 = "().add(elem);";
  protected final String TEXT_35 = NL + "\t\t\t\tresult.get";
  protected final String TEXT_36 = "().add(";
  protected final String TEXT_37 = ".mapFromEntity(item));";
  protected final String TEXT_38 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_39 = NL + "\t\t// ivm recursiviteit niet verder gemapped";
  protected final String TEXT_40 = NL + "\t\tif (entity.get";
  protected final String TEXT_41 = "() != null) {" + NL + "\t\t\tresult.set";
  protected final String TEXT_42 = "()) {" + NL + "\t\t\t\tresult.get";
  protected final String TEXT_43 = "().add(item);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_44 = "(";
  protected final String TEXT_45 = ".mapFromEntity(entity.get";
  protected final String TEXT_46 = "()));";
  protected final String TEXT_47 = NL + "\t\tresult.get";
  protected final String TEXT_48 = "().";
  protected final String TEXT_49 = "(result);";
  protected final String TEXT_50 = NL + "\t\t// ivm recursiviteit niet verder gemapped" + NL + "\t\t// result.set";
  protected final String TEXT_51 = NL + "\t\t//TODO property entity.get";
  protected final String TEXT_52 = "() heeft een ander datatype (";
  protected final String TEXT_53 = " ipv ";
  protected final String TEXT_54 = ")";
  protected final String TEXT_55 = NL + "\t\t//TODO onbekende property entity.get";
  protected final String TEXT_56 = "()";
  protected final String TEXT_57 = NL + "\t}" + NL + " ";
  protected final String TEXT_58 = NL + " " + NL + "\t/**" + NL + "\t* map from To to entity" + NL + "\t* @param sourceTO, ";
  protected final String TEXT_59 = " mapToEntity(";
  protected final String TEXT_60 = " sourceTO) {" + NL + "\t\t";
  protected final String TEXT_61 = NL + "\t\tmapTo";
  protected final String TEXT_62 = "(sourceTO, result);";
  protected final String TEXT_63 = NL + "\tprivate static void mapTo";
  protected final String TEXT_64 = ".mapToEntity(item);" + NL + "\t\t\t\telem.";
  protected final String TEXT_65 = ".mapToEntity(item));";
  protected final String TEXT_66 = "\t\t";
  protected final String TEXT_67 = ".mapToEntity(entity.get";
  protected final String TEXT_68 = "()));" + NL + "\t\tresult.get";
  protected final String TEXT_69 = "\t\t\t";
  protected final String TEXT_70 = NL + " }" + NL;

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
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_4);
     Class toClass = cc; 
     String toClassname = toClass.getSimpleName(); 
     Class entityClass = (Class) args[1]; 
     String entityClassname = entityClass.getSimpleName(); 
     List<Fw> toFws = tu.getFields(toClass, EXC, FwSelectType.ID); 
     List<Fw> entityFws = tu.getFields(entityClass, EXC, FwSelectType.ID); 
    stringBuffer.append(TEXT_5);
     impu.addImport(toClass);
     impu.addImport(entityClass);
     tu.addToMappedClasses(toClass); tu.addToMappedClasses(entityClass); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_13);
     for (Fw fw : toFws) { 
     String fldname = fw.name(); String capname = nu.capName(fldname); 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_15);
     } //for loop 
    stringBuffer.append(TEXT_16);
     for (Fw fw : toFws) { 
       String fldname = fw.name(); String capname = nu.capName(fldname); String typ = fw.genericTypeName(); String concreteType = fw.concreteType(); 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_20);
       if (tu.hasOtherProperty(fw, entityFws)) { // ifA 
          Fw otherFw = tu.getOtherFw(fw, entityFws); 
          String otherTyp = otherFw.genericTypeName(); 
          if (tu.isSameType(fw, otherFw)) { // ifB 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_23);
          } else { // ifB 
             if (fw.isCollection()) { // ifC 
                if (tu.belongsTo(toClass, fw)) { // ifD 
          Class nestedMapperClass = fw.genericType(); String nestedMapper = fw.genericTypeName() + "Mapper"; 
          tu.recursiveGenerate(fw.genericType(), otherFw.genericType(), false); 
          impu.addImport(nestedMapperClass.getName() + "Mapper"); 
                   if (!tu.isAlreadyMapped(otherFw.genericType())) { // if isAlr 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(concreteType);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(otherTyp);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_29);
                       String setter = tu.getMethodName(otherFw.genericType(), void.class, new Class[] {entityClass}); 
                       if (setter != null) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(fw.genericTypeName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_34);
                       } else { 
    stringBuffer.append(TEXT_35);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_37);
                       } 
    stringBuffer.append(TEXT_38);
                   } else { // else isAlr 
    stringBuffer.append(TEXT_39);
                   } 
                } else { 
    stringBuffer.append(TEXT_40);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(concreteType);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(typ);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_43);
                } // ifD 
             } else { // ifC 
                if (tu.belongsTo(toClass, fw)) { // ifE 
          tu.recursiveGenerate(fw.type(), otherFw.type(), false); 
          Class nestedMapperClass = fw.type(); String nestedMapper = fw.typeName() + "Mapper"; 
          impu.addImport(nestedMapperClass.getName() + "Mapper"); 
                   if (!tu.isAlreadyMapped(otherFw.genericType())) { // if isAlr 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_46);
                       String setter = tu.getMethodName(otherFw.genericType(), void.class, new Class[] {entityClass}); 
                       if (setter != null) { 
    stringBuffer.append(TEXT_47);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_49);
                       } 
                   } else { 
    stringBuffer.append(TEXT_50);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_46);
                   } 
                } else { 
    stringBuffer.append(TEXT_51);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(otherFw.typeName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_54);
                } // ifE 
             } // ifC 
          } //ifB 
        } else { // ifA 
    stringBuffer.append(TEXT_55);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_56);
        } // ifA 
    stringBuffer.append(TEXT_57);
     } //for loop 
    stringBuffer.append(TEXT_58);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_13);
     for (Fw fw : tu.getFields(entityClass, EXC, FwSelectType.ID)) { 
     String fldname = fw.name(); String capname = nu.capName(fldname);  
    stringBuffer.append(TEXT_61);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_62);
     } //for loop 
    stringBuffer.append(TEXT_16);
     for (Fw fw : entityFws) { 
       String fldname = fw.name(); String capname = nu.capName(fldname); String typ = fw.genericTypeName(); String concreteType = fw.concreteType(); 
    stringBuffer.append(TEXT_63);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(toClassname);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entityClassname);
    stringBuffer.append(TEXT_20);
       if (tu.hasOtherProperty(fw, toFws)) { // ifA 
          Fw otherFw = tu.getOtherFw(fw, toFws); 
          String otherTyp = otherFw.genericTypeName(); 
          if (tu.isSameType(fw, otherFw)) { // ifB 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_23);
          } else { // ifB 
             if (fw.isCollection()) { // ifC 
                if (tu.belongsTo(toClass, fw)) { // ifD 
          Class nestedMapperClass = fw.genericType(); String nestedMapper = fw.genericTypeName() + "TOMapper"; 
          impu.addImport(nestedMapperClass.getName() + "TOMapper"); 
                   if (!tu.isAlreadyMapped(otherFw.genericType())) { // if isAlr 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(concreteType);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(otherTyp);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_29);
                       String setter = tu.getMethodName(otherFw.genericType(), void.class, new Class[] {toClass}); 
                       if (setter != null) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(fw.genericTypeName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_34);
                       } else { 
    stringBuffer.append(TEXT_35);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_65);
                       } 
    stringBuffer.append(TEXT_38);
                   } else { // else isAlr 
    stringBuffer.append(TEXT_39);
                   } 
    stringBuffer.append(TEXT_66);
                } else { 
    stringBuffer.append(TEXT_40);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(concreteType);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(typ);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_43);
                } // ifD 
             } else { 
                if (tu.belongsTo(toClass, fw)) { // ifE 
          Class nestedMapperClass = fw.type(); String nestedMapper = fw.typeName() + "TOMapper"; 
          impu.addImport(nestedMapperClass.getName() + "TOMapper"); 
                   if (!tu.isAlreadyMapped(otherFw.genericType())) { // if isAlr 
    stringBuffer.append(TEXT_5);
                       String setter = tu.getMethodName(otherFw.genericType(), void.class, new Class[] {toClass}); 
                       if (setter != null) { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(setter);
    stringBuffer.append(TEXT_49);
                       } else { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_46);
                       } 
                   } else { // else isAlr 
    stringBuffer.append(TEXT_50);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(nestedMapper);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_46);
                   } 
    stringBuffer.append(TEXT_69);
                } else { 
                } // ifE 
             } // ifC 
          } //ifB 
        } else { // ifA 
    stringBuffer.append(TEXT_55);
    stringBuffer.append(capname);
    stringBuffer.append(TEXT_56);
        } // ifA 
    stringBuffer.append(TEXT_57);
     } //for loop 
    stringBuffer.append(TEXT_70);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
