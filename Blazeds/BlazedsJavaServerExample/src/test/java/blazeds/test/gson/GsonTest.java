package blazeds.test.gson;

import org.junit.Test;

import com.google.gson.Gson;

import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeldImpl;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;

public class GsonTest {

	@Test
	public void test() {
		Gson gson = new Gson();
		GetKlantbeeld srv = new GetKlantbeeldImpl();
		SelectKlantbeeld selectKlantbeeld = new SelectKlantbeeld();
		Klantbeeld kb = srv.getKlantbeeld(selectKlantbeeld);
		String json = gson.toJson(kb, Klantbeeld.class);
		System.out.println(json);
	}

}
