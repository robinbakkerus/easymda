package com.flca.frw.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.flca.frw.validators.ValidationError;
import com.flca.frw.validators.Validator;

public class TestValidator extends TestCase {

	public void testTestNotEmptyString01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty("test", errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestNotEmptyString02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty(null, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestNotEmptyString03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty("", errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestNotEmptyString04() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty("", errors, "name", new String[] {"extra-param"} );
		assertTrue(errors.size() > 0);
	}

	public void testTestNotEmptyObject01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty(new Integer(123), errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestNotEmptyObject02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty(null, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestNotEmptyObject03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNotEmpty(new Integer(0), errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestCollectionNotEmpty01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		List<String> testList = new ArrayList<String>();
		testList.add("test");
		Validator.testCollectionNotEmpty(testList, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestCollectionNotEmpty02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		List<String> testList = new ArrayList<String>();
		Validator.testCollectionNotEmpty(testList, errors, "name", null);
		assertTrue(errors.size() >  0);
	}

	public void testTestNot0Int01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNot0(123, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestNot0Int02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNot0(0, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void _testTestNot0Double01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNot0(123.66, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void _testTestNot0Double02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testNot0(0.00, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterIntInt01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(100, 50, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterIntInt02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(100, 150, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestGreaterIntInt03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(100, 100, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestGreaterOrEqualsIntInt01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(200, 100, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterOrEqualsIntInt02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(100, 70, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterOrEqualsIntInt03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(100, 100, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterIntegerInt01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(new Integer(200), 100, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterIntegerInt02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(new Integer(100), 200, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestGreaterIntegerInt03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(new Integer(100), 100, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestGreaterOrEqualsIntegerInt01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(new Integer(200), 100, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterOrEqualsIntegerInt02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(new Integer(200), 100, errors, "name", null);
		assertTrue(errors.size() == 0);
	}
	
	public void testTestGreaterOrEqualsIntegerInt03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(new Integer(200), 100, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterDoubleDouble01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(200.00, 100.00, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestGreaterDoubleDouble02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(100.00, 200.00, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestGreaterDoubleDouble03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreater(100.00, 100.00, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void _testTestGreaterOrEqualsDoubleDouble01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(200.00, 100.00, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void _testTestGreaterOrEqualsDoubleDouble02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(100.00, 200.00, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void _testTestGreaterOrEqualsDoubleDouble03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testGreaterOrEquals(100.00, 100.00, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void _testTestSmallerIntInt() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerOrEqualsIntInt() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerIntegerInt() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerOrEqualsIntegerInt() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerDoubleDouble() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerOrEqualsDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testTestRangeIntIntInt01() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testRange(100, 0, 200, errors, "name", null);
		assertTrue(errors.size() == 0);
	}

	public void testTestRangeIntIntInt02() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testRange(-1, 0, 200, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestRangeIntIntInt03() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testRange(300, 0, 200, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestRangeIntIntInt04() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testRange(0, 0, 200, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void testTestRangeIntIntInt05() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		Validator.testRange(200, 0, 200, errors, "name", null);
		assertTrue(errors.size() > 0);
	}

	public void _testTestRangeDoubleDoubleDouble() {
		fail("Not yet implemented");
	}

	public void _testTestRangeBigDecimalDoubleDoubleBoolean() {
		fail("Not yet implemented");
	}

	public void _testTestStringLength() {
		fail("Not yet implemented");
	}

	public void _testTestEqualsDateDate() {
		fail("Not yet implemented");
	}

	public void _testTestGreaterDateDate() {
		fail("Not yet implemented");
	}

	public void _testTestGreaterOrEqualsDateDate() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerDateDate() {
		fail("Not yet implemented");
	}

	public void _testTestSmallerOrEqualsDateDate() {
		fail("Not yet implemented");
	}

	public void _testTestRangeDateDateDateBoolean() {
		fail("Not yet implemented");
	}

	public void _testTestEqualsIntInt() {
		fail("Not yet implemented");
	}

	public void _testTestEqualsShortShort() {
		fail("Not yet implemented");
	}

	public void _testTestEqualsObjectObject() {
		fail("Not yet implemented");
	}

	public void _testTestDateInFuture() {
		fail("Not yet implemented");
	}

	public void _testTestDateInPast() {
		fail("Not yet implemented");
	}

	public void _testTestValidAge() {
		fail("Not yet implemented");
	}

	public void _testTestValidBankAccount() {
		fail("Not yet implemented");
	}

	public void _testTestValidSofiNumber() {
		fail("Not yet implemented");
	}

	public void _testObject() {
		fail("Not yet implemented");
	}

	public void _testGetClass() {
		fail("Not yet implemented");
	}

	public void _testHashCode() {
		fail("Not yet implemented");
	}

	public void _testEquals() {
		fail("Not yet implemented");
	}

	public void _testClone() {
		fail("Not yet implemented");
	}

	public void _testToString() {
		fail("Not yet implemented");
	}

	public void _testNotify() {
		fail("Not yet implemented");
	}

	public void _testNotifyAll() {
		fail("Not yet implemented");
	}

	public void _testWaitLong() {
		fail("Not yet implemented");
	}

	public void _testWaitLongInt() {
		fail("Not yet implemented");
	}

	public void _testWait() {
		fail("Not yet implemented");
	}

	public void _testFinalize() {
		fail("Not yet implemented");
	}

	public void testSofi01() {
		boolean b = Validator.testValidSofiNumber("123456782");
		assertTrue(b);
	}

	public void testSofi02() {
		boolean b = Validator.testValidSofiNumber("111");
		assertFalse(b);
	}
}
