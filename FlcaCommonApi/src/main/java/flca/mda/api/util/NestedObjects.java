package flca.mda.api.util;

import java.util.ArrayList;
import java.util.List;

/**
 * helper class to keep track of all (deeply) nested objects of a given class.
 * In this NestedObjects the tree is flattened into a list of NestedObject's
 * For, for example: Testa this should result in NestedOnjects like:
 * NestedObject({}, {tstb}, Testb.class) // first {} are the parent class(es),
 * second {} the fields NestedObject({}, {tstsc}, Testc.class)
 * NestedObject({Testc.class}, {tstsc,tstsd}, Testd.class)
 * NestedObject({Testc.class}, {tstsc,tstse}, Teste.class)
 *
 */
public class NestedObjects implements TypeConstants {

	private static final TypeUtils tu = new TypeUtils();

	private Class<?> root;
	private List<NestedObject> nestedObjects = new ArrayList<NestedObject>();

	public NestedObjects(Class<?> root) {
		super();
		this.root = root;
		this.nestedObjects = this.fillNested(root, this.nestedObjects, -1);
	}

	public Class<?> getRoot() {
		return this.root;
	}

	public List<NestedObject> getNestedObjects() {
		return this.nestedObjects;
	}

	private List<NestedObject> fillNested(Class<?> aClass, List<NestedObject> aNestedObjects, int aIndex) {
		List<NestedObject> result = aNestedObjects;
		// -- in 4 separate steps so that the order is more predictable
		FwSelect fwSelect = FwSelect.emptyBuilder().withOneToMany(true).build();
		List<Fw> fields = tu.getFields(aClass, fwSelect);
		fwSelect = FwSelect.emptyBuilder().withManyToOne(true).build();
		fields.addAll(tu.getFields(aClass, fwSelect));
		fwSelect = FwSelect.emptyBuilder().withManyToMany(true).build();
		fields.addAll(tu.getFields(aClass, fwSelect));
		fwSelect = FwSelect.emptyBuilder().withOneToOne(true).build();
		fields.addAll(tu.getFields(aClass, fwSelect));

		for (Fw fw : fields) {
			if (fw.isOwner()) {
				NestedObject lastone = (aIndex < 0) ? null : result.get(aIndex);
				NestedObject nestobj = new NestedObject(fw, aClass, lastone);
				result.add(nestobj);

				Class<?> currtype = fw.genericType();
				if (!this.recursive(currtype, result)) {
					result = this.fillNested(currtype, result, aIndex + 1);
				}
			}
		}
		return result;
	}

	private boolean recursive(Class<?> aCurrType, List<NestedObject> aNestedObjects) {
		for (NestedObject nestobj : aNestedObjects) {
			for (Class<?> clz : nestobj.getParents()) {
				if (clz.equals(aCurrType))
					return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("root=" + this.root.getSimpleName() + "\n");
		for (NestedObject nested : this.nestedObjects) {
			sb.append(nested.toString() + "\n");
		}
		return sb.toString();
	}
}
