package flca.blazeds.api;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import flca.mda.api.util.Fw;
import flca.mda.api.util.TypeUtils;

/**
 * base class for SwaggerTypeUtils and OpenApiTypeUtils
 *
 */
public abstract class AbstractSwaggerTypeUtils {

	private final TypeUtils tu = new TypeUtils();

	protected abstract String schemePrefix();
	
	/**
	 * This returns when "post" should be used.
	 * Currently this always returns true unless this method has no arguments.
	 * @param method
	 * @return
	 */
	public boolean isPostMethod(final Method method) {
		return method.getParameterCount()>0;
	}
	
	/**
	 * This returns the OpenApi typename that corresponds with the given Fw
	 * For a modelclass this will be "$ref: '#/components/schemas/<%fw.name()%>'
	 * @param Fw
	 * @return
	 */
	public String getTypeName(final Fw fw) {
		return this.getTypeName(fw.genericType());
	}

	public String getTypeName(final Class<?> clz) {
		if (this.tu.isEnum(clz)) {
			return String.format("'#/%s/%s'", this.schemePrefix(), clz.getSimpleName());
		} else if (this.tu.isSimpleField(clz)) {
			return OpenApiTypeMapper.getOpenApiType(clz);
		} else {
			return String.format("'#/%s/%s'", this.schemePrefix(), clz.getSimpleName());
		}
	}

	public String getMethodArgName(final Method method, final int index) {
		Parameter[] params = method.getParameters();
		if (index < params.length) {
			return params[index].getName();
		} else {
			return "?? index out of range ??";
		}
	}
	
	public String getMethodArgTypeName(final Method method, final int index) {
		Parameter[] params = method.getParameters();
		if (index < params.length) {
			return this.getTypeName(params[index].getType());
		} else {
			return "?? index out of range ??";
		}
	}
}
