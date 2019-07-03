package flca.mda.codegen.helpers;

import java.util.ArrayList;
import java.util.List;

import flca.mda.codegen.data.ITemplate;

/**
 * helper class to maintain addtional classes that should be generated with a
 * given template. this class is triggered by the engine after all selected
 * classes and templates have been processed. during this process, one (or more)
 * generators may put an additional class plus template to the list maintained
 * here that will then be processed by the engine. For example, the Rest jet
 * template put's the Entity or Dto class plus the JsonArray template to this
 * list when it finds out the return parameter is a collection.
 * 
 * @author robin
 * 
 */
public class GenerateQueue {

	private static List<Data> queue; // we can use not a Map because we may have several templates for the same class

	/**
	 * Append the given class that we want generate with the given template and optonal argumments
	 * @param aClass
	 * @param aTemplate
	 * @param aArguments
	 */
	public static void append(Class<?> aClass, ITemplate aTemplate, Object ... aArguments) {
		if (queue == null) {
			queue = new ArrayList<Data>();
		}

		GenerateQueue.Data adddata = new GenerateQueue.Data(aClass, aTemplate, aArguments);
		if (!queue.contains(adddata)) {
			queue.add(adddata);
		}
	}
	
	public static List<Data> getQueue() {
		return queue;
	}

	public static void reset() {
		queue = new ArrayList<GenerateQueue.Data>();
	}

	// ----------- inner class to hold class + corr template ---
	public static class Data {
		Class<?> inputClass;
		ITemplate template;
		Object arguments[];

		public Data(Class<?> clazz, ITemplate aTemplate, Object ... aArguments) {
			super();
			this.inputClass = clazz;
			this.template = aTemplate;
			this.arguments = aArguments;			
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((inputClass == null) ? 0 : inputClass.hashCode());
			result = prime * result + ((template == null) ? 0 : template.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Data other = (Data) obj;
			if (!inputClass.equals(other.inputClass))
				return false;
			if (!template.equals(other.template))
				return false;
			return true;
		}

		public Class<?> getInputClass() {
			return inputClass;
		}

		public ITemplate getTemplate() {
			return template;
		}

		public Object[] getArguments() {
			return arguments;
		}
	}
}
