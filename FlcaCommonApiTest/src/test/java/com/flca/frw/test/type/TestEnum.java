/*
 * Generated via the com.flca.Vdsl4J generator
 */

package com.flca.frw.test.type;
import java.util.HashMap;
import java.util.Map;

import com.flca.frw.IEnum;
/**
 * @todo add comment for javadoc
 *
 * @author rbakkerus
 * @version $Id: Enumeration.javajet,v 1.1 2007/08/11 20:14:51 robin bakkerus Exp $
 * @generated
 */
public enum TestEnum implements IEnum {
	/** @generated */
	TEST_A,
	TEST_B,
	TEST_C,
	TEST_D
	;
	
	/**
	* return a Map with enum value (the key) and enum label (the value)	
	*/
	public static Map<String, String> toStringList() 
	{
		Map<String, String> result = new HashMap<String, String>();

		result.put(TEST_A.name(), TEST_A.name());
		result.put(TEST_B.name(), TEST_B.name());
		return result;
	}

	@Override
	public String getLabel() {
		return name();
	}
	
	
}
