package com.flca.frw.test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

import com.flca.frw.util.TransformUtils;

import junit.framework.TestCase;

public class TestTransformUtils extends TestCase {

	public static TransformUtils tf = TransformUtils.getInstance();
	
	public void _testStr2DateString() {
		fail("Not yet implemented");
	}

	public void _testStr2DateStringSimpleDateFormat() {
		fail("Not yet implemented");
	}

	public void _testStr2DateStringDate() {
		fail("Not yet implemented");
	}

	public void _testStr2DateStringSimpleDateFormatDate() {
		fail("Not yet implemented");
	}

	public void _testInt2String() {
		fail("Not yet implemented");
	}

	public void _testStr2intString() {
		fail("Not yet implemented");
	}

	public void _testStr2intStringInt() {
		fail("Not yet implemented");
	}

	public void _testStr2Integer() {
		fail("Not yet implemented");
	}

	public void _testStr2LongString() {
		fail("Not yet implemented");
	}

	public void _testStr2LongStringLong() {
		fail("Not yet implemented");
	}

	public void _testStr2short() {
		fail("Not yet implemented");
	}

	public void _testStr2Short() {
		fail("Not yet implemented");
	}

	public void _testFloat2StrDouble() {
		fail("Not yet implemented");
	}

	public void _testFloat2StrDouble1() {
		fail("Not yet implemented");
	}

	public void _testStr2doubleString() {
		fail("Not yet implemented");
	}

	public void _testStr2doubleStringDouble() {
		fail("Not yet implemented");
	}

	public void _testStr2Double() {
		fail("Not yet implemented");
	}

	public void _testStr2float() {
		fail("Not yet implemented");
	}

	public void _testStr2Float() {
		fail("Not yet implemented");
	}

	public void _testStr2boolean() {
		fail("Not yet implemented");
	}

	public void _testStr2Boolean() {
		fail("Not yet implemented");
	}

	public void _testSqlDate2UtilDateDate() {
		fail("Not yet implemented");
	}

	public void _testUtilDate2UtilDate() {
		fail("Not yet implemented");
	}

	public void _testSqlDate2UtilDateDate1() {
		fail("Not yet implemented");
	}

	public void _testDate2SqlDate() {
		fail("Not yet implemented");
	}

	public void _testDate2SqlTime() {
		fail("Not yet implemented");
	}

	public void _testDate2SqlTimeStamp() {
		fail("Not yet implemented");
	}

	public void _testDate2StrDate() {
		fail("Not yet implemented");
	}

	public void _testDate2StrDateString() {
		fail("Not yet implemented");
	}

	public void _testDate2StrDateSimpleDateFormat() {
		fail("Not yet implemented");
	}

	public void _testDate2StrDateSimpleDateFormatString() {
		fail("Not yet implemented");
	}

	public void _testInt2StrInt() {
		fail("Not yet implemented");
	}

	public void _testInt2StrLong() {
		fail("Not yet implemented");
	}

	public void _testInt2StrShort() {
		fail("Not yet implemented");
	}

	public void _testInt2StrShort1() {
		fail("Not yet implemented");
	}

	public void _testInt2StrInteger() {
		fail("Not yet implemented");
	}

	public void _testInt2StrLong1() {
		fail("Not yet implemented");
	}

	public void _testInt2StrIntegerString() {
		fail("Not yet implemented");
	}

	public void _testInt2StrLongString() {
		fail("Not yet implemented");
	}

	public void _testStr2Str() {
		fail("Not yet implemented");
	}

	public void _testRemoveZeros() {
		fail("Not yet implemented");
	}

	public void _testGetDefaultDateFmt() {
		fail("Not yet implemented");
	}

	public void _testObject2Str() {
		fail("Not yet implemented");
	}

	public void _testTimestamp2Date() {
		fail("Not yet implemented");
	}

	public void _testTime2StrDate() {
		fail("Not yet implemented");
	}

	public void _testTime2StrDateString() {
		fail("Not yet implemented");
	}

	public void _testTime2StrDateSimpleDateFormat() {
		fail("Not yet implemented");
	}

	public void testDouble2BigDecimal() {
		BigDecimal bigdec = tf.double2BigDecimal(new Double(456.45));
		double d = tf.bigDecimal2double(bigdec);
		BigDecimal bigdec2 = tf.double2BigDecimal(d);
		assertTrue(bigdec.equals(bigdec2));
	}
	
	public void testDateToXMLGregorianCalendar() {
		try {
			Date d1 = new Date();
			XMLGregorianCalendar xmlcal = tf.dateToXMLGregorianCalendar(d1);
			Date d2 = tf.xMLGregorianCalendarToDate(xmlcal);
			assertTrue(d1.equals(d2));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * ad-hoc methode om converters.xml te genereren
	 */
	public void testMakeConverters() 
	{
		StringBuffer result = new StringBuffer();
		
		Method methods[] = TransformUtils.class.getMethods();
		
		for (Method m : methods) {
			Class<?> rettyp = m.getReturnType();
			Class<?> parms[] = m.getParameterTypes();
			if (rettyp != null && parms != null && parms.length == 1) {
				String s = getConverterTemplate();
				s = StringUtils.replace(s, "{0}", rettyp.getName());
				s = StringUtils.replace(s, "{1}", parms[0].getName());
				s = StringUtils.replace(s, "{2}", rettyp.getName() + " result = tf." + m.getName() +  "(aValue);");
				
				result.append(s);
			}
		}
		
		System.out.println(result.toString());
	}
	
	private static String getConverterTemplate() 
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n\t<com.flca.vom.engine.dto.TransformDataItem>");
		sb.append("\n\t\t<targetDatatype>{0}</targetDatatype>");
		sb.append("\n\t\t<sourceDatatype>{1}</sourceDatatype>");
		sb.append("\n\t\t<transformStatement>{2}</transformStatement>");
		sb.append("\n\t</com.flca.vom.engine.dto.TransformDataItem>");
		
		return sb.toString();
	}
}
