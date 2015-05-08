package com.bodik;

import java.util.Random;

import org.jboss.resteasy.client.ClientRequest;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class SnapshotsFactory {

	static String[] tags = { "city", "url", "param", "location", "place",
			"kind", "street", "state", "sale_date", "price", "Product",
			"Price", "Country" };
	static Random rand = new Random();

	public static void main(String[] args) {
		String ROOT_URL = args[1];
		int numberOfRequest = Integer.parseInt(args[0]);
		for (int i = 0; i < numberOfRequest; i++) {
			try {
				ClientRequest request = new ClientRequest(ROOT_URL);
				request.accept("application/json");
				String input = generateRequest();
				request.body("application/json", input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String generateRequest() {
		final int NUMBER_DATA = rand.nextInt(tags.length);
		final int NUMBER_TAGS = rand.nextInt(tags.length - 1) + 1;
		JSONObject json = new JSONObject();
		json.put("type", "snapshot");
		json.put("userId", String.valueOf(rand.nextInt(99999)));
		json.put("avps", generateTags(NUMBER_TAGS));
		json.put("data", generateData(NUMBER_DATA));
		return json.toString();
	}

	public static String generateWord() {
		int k = rand.nextInt(10) + 3;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++) {
			sb.append((char) (97 + rand.nextInt(25)));
		}
		return sb.toString();
	}

	public static JSONObject generateData(int numberOfData) {
		JSONObject jdata = new JSONObject();
		while (jdata.length() < numberOfData) {
			String data = generateWord();
			if (!jdata.has(data)) {
				jdata.put(data, generateWord());
			}
		}
		return jdata;
	}

	public static JSONObject generateTags(int numberOfTags) {
		JSONObject jtags = new JSONObject();
		jtags.put("appurl", "/oss-ui/tools/toolbox.jsp");
		jtags.put("locationId", rand.nextInt(99999));
		while (jtags.length() < (numberOfTags + 2)) {
			String tag = tags[rand.nextInt(tags.length - 1)];
			if (!jtags.has(tag)) {
				jtags.put(tag, generateWord());
			}
		}
		return jtags;
	}

}
