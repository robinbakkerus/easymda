package flca.mda.api.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import flca.mda.common.api.helpers.ImportHelper;

public class MockDataUtil {

	private static final Random RANDOM = ThreadLocalRandom.current();
	private static final TypeUtils tu = new TypeUtils();

	/**
	 * return the string representation a random value that corresponds with the type of the given Fw
	 * @param fw
	 * @return
	 */
	public String randomValue(final Fw fw) {
		if (tu.isStringType(fw.type())) {
			return "\"" + this.randomString() + "\"";
		} else if (tu.isIntType(fw.type())) {
			return "" + this.randomint();
		} else if (tu.isBigDecimalType(fw.type())) {
			return "new BigDecimal(" + this.randomfloat() + ")";
		} else if (tu.isDecimalType(fw.type())) {
			return "" + this.randomdouble();
		} else if (tu.isDateType(fw.type())) {
			return "new Date()";
		} else if (tu.isTimestampType(fw.type())) {
			return "new Timestamp(new Date().getTime())";
		} else if (tu.isEnum(fw.type())) {
			return String.format("%s.%s", fw.typeName(), this.randomEnum(fw.type()));
		} 
		return "??";
	}
	
	public String randomValues(final Fw fw, int size) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("new %s[] {", fw.genericTypeName()));
		for (int i=0; i<size; i++) {
			sb.append(randomValue(fw));
			sb.append(",");
		}
		sb.append("}");
		
		return sb.toString();
	}

	public String randomString() {
		StringBuffer r = new StringBuffer();
		final int min = 5;
		final int max = 10;
		int l = ThreadLocalRandom.current().nextInt(min, max);

		int c = ThreadLocalRandom.current().nextInt(65, 90);
		r.append((char) c);

		for (int i = min; i < min + l; i++) {
			c = ThreadLocalRandom.current().nextInt(97, 122);
			r.append((char) c);
		}
		return r.toString();
	}

	public int randomint() {
		return RANDOM.nextInt();
	}

	public short randomshort() {
		return (short) RANDOM.nextInt(1000);
	}

	public long randomlong() {
		return RANDOM.nextLong();
	}

	public double randomdouble() {
		return RANDOM.nextDouble();
	}

	public float randomfloat() {
		return RANDOM.nextFloat();
	}

	public boolean randomboolean() {
		return RANDOM.nextBoolean();
	}

	public Short randomShort() {
		return Short.valueOf(randomshort());
	}

	public Integer randomInteger() {
		return Integer.valueOf(randomint());
	}

	public Long randomLong() {
		return Long.valueOf(randomlong());
	}

	public Double randomDouble() {
		return Double.valueOf(randomdouble());
	}

	public Float randomFloat() {
		return Float.valueOf(randomfloat());
	}

	public BigDecimal randomBigDecimal() {
		return BigDecimal.valueOf(randomdouble());
	}

	public Boolean randomBoolean() {
		return Boolean.valueOf(randomboolean());
	}

	public Date randomDate(final Date minDate, final Date maxDate) {
		long n = ThreadLocalRandom.current().nextLong(minDate.getTime(), maxDate.getTime());
		Date r = new Date();
		r.setTime(n);
		return r;
	}

	public Date randomDate() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return this.randomDate(df.parse("1990-01-01"), new Date());
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public Timestamp randomTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public Enum<?> randomEnum(Class<?> aEnumClass) {
		if (aEnumClass != null) {
			try {
				Method m = aEnumClass.getMethod("values", new Class[] {});
				Enum<?> emumvalues[] = (Enum[]) m.invoke(null, new Object[] {});
				int idx = RANDOM.nextInt(emumvalues.length - 1);
				return emumvalues[idx];
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * return the first subclasses (if any) from the given class.
	 * @param aClass
	 * @return
	 */
	public Class<?> randomSubClass(Class<?> aClass) {
		List<Class<?>> subclasses = tu.getSubClasses(aClass);
		if (subclasses != null && subclasses.size() > 0) {
			Class<?> r = subclasses.get(0);
			ImportHelper.addImport(r);
			return r;
		} else {
			return ClassNotFoundException.class;
		}
	}
}
