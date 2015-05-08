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
				String input = getRequest();
				request.body("application/json", input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getRequest() {
		JSONObject json = new JSONObject();
		json.put("type", "snapshot");
		json.put("userId", rand.nextInt(99999));
		json.put("avps", getTags(12, true));
		json.put("data", getTags(4, false));
		return json.toString();
	}

	public static JSONObject getTags(int numberOfTags, Boolean isTags) {
		JSONObject jtags = new JSONObject();
		int k = rand.nextInt(numberOfTags) + 1;
		if (isTags) {
			jtags.put("appurl", "/oss-ui/tools/toolbox.jsp");
			jtags.put("locationId", rand.nextInt(99999));
			while (jtags.length() < k + 2) {
				String tag = tags[rand.nextInt(tags.length)];
				if (!jtags.has(tag)) {
					jtags.put(tag, tag + rand.nextInt(999));
				}
			}
		} else {
			while (jtags.length() < k) {
				String tag = tags[rand.nextInt(tags.length)];
				if (!jtags.has(tag)) {
					jtags.put(tag, tag + rand.nextInt(999));
				}
			}
		}

		return jtags;
	}

}
