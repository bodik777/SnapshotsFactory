package com.bodik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import org.jboss.resteasy.client.ClientRequest;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class SnapshotsFactory {

	static String[] tagsk = { "city", "url", "param", "location", "place",
			"kind", "street", "state", "sale_date", "price", "Product",
			"Price", "Country" };
	static ArrayList<String> tags = new ArrayList<String>(Arrays.asList(tagsk));
	static Random rand = new Random();

	public static void main(String[] args) {
		String ROOT_URL = args[1];
		int numberOfRequest = Integer.parseInt(args[0]);
		for (int i = 1; i <= numberOfRequest; i++) {
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
		if (isTags) {
			jtags.put("appurl", "/oss-ui/tools/toolbox.jsp");
			jtags.put("locationId", rand.nextInt(99999));
		}
		int k = rand.nextInt(numberOfTags) + 1;
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < k; i++) {
			set.add(tags.get(rand.nextInt(tagsk.length)));
		}
		for (String el : set) {
			jtags.put(el, el + rand.nextInt(999));
		}
		return jtags;
	}

}
