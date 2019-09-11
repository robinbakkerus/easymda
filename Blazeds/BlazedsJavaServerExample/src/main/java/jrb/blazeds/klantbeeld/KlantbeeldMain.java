package jrb.blazeds.klantbeeld;

import static spark.Spark.after;
import static spark.Spark.options;
import static spark.Spark.post;

import com.google.gson.Gson;

import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeldImpl;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;
import spark.Filter;
import spark.Request;
import spark.Response;

public class KlantbeeldMain {

	public static void main(String[] args) {
		after((Filter) (request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "*");
			response.header("Access-Control-Allow-Headers", "*");
		});

		options("/*", (request, response) -> {
			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}
			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		post("/GetKlantbeeld", (req, res) -> getKlantbeeld(req, res));
	}

	private static Object getKlantbeeld(final Request req, final Response res) {
		try {
			res.status(200);
			Gson gson = new Gson();
			System.out.println("GetKlantbeeld called ..");
			GetKlantbeeld srv = new GetKlantbeeldImpl();
			SelectKlantbeeld selectKlantbeeld = gson.fromJson(req.body(), SelectKlantbeeld.class);
			Klantbeeld kb = srv.getKlantbeeld(selectKlantbeeld);
			String json = gson.toJson(kb, Klantbeeld.class);
			System.out.println(json);
			return json;
		} catch(Throwable t) {
			res.status(500);
			return t.toString();
		}
	}
}
