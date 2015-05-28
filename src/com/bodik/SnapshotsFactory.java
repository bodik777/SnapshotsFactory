package com.bodik;

import java.util.Random;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class SnapshotsFactory {

	static String[] tags = { "city", "url", "param", "location", "place",
			"kind", "street", "state", "sale_date", "price", "Product",
			"Price", "Country" };
	static Random rand = new Random();

	public static void main(String[] args) {
		if (args.length == 2) {
			int numberOfRequest = Integer.parseInt(args[0]);
			String ROOT_URL = args[1];

			System.out.println("Number: " + numberOfRequest);
			System.out.println("URL: " + ROOT_URL);
			System.out.println("Start factory)");
			for (int i = 0; i < numberOfRequest; i++) {
				try {
					ClientRequest request = new ClientRequest(ROOT_URL);
					request.accept("application/json");
					String input = generateRequest();
					request.body("application/json", input);
					ClientResponse<String> response = request
							.post(String.class);
					int status = response.getStatus();
					System.out.println(i + ": Request status: " + status);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Stop factory)");
		} else if (args.length == 4) {
			int numberOfRequest = Integer.parseInt(args[0]);
			String ROOT_URL = args[1];
			int timeSleep = Integer.parseInt(args[2]);
			int NumberBeforeSleep = Integer.parseInt(args[3]);
			for (int i = 0; i < numberOfRequest; i++) {
				if (i % NumberBeforeSleep == 0) {
					try {
						Thread.currentThread();
						Thread.sleep(timeSleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					ClientRequest request = new ClientRequest(ROOT_URL);
					request.accept("application/json");
					String input = generateRequest();
					request.body("application/json", input);
					ClientResponse<String> response = request
							.post(String.class);
					int status = response.getStatus();
					System.out.println(i + ": Request status: " + status);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Stop factory)");
		} else {
			System.out
					.println("To run the program, use the following options:");
			System.out.println("Number of requests, Url.");
			System.out.println("For example: 100 http://example.com");
			System.out.println("OR");
			System.out
					.println("Number of requests, Url, time delay (ms), each N requests.");
			System.out.println("For example: 100 http://example.com 5000 50");
		}

	}

	public static String generateRequest() {
		final int NUMBER_DATA = rand.nextInt(tags.length);
		final int NUMBER_TAGS = rand.nextInt(tags.length - 1) + 1;
		JSONObject json = new JSONObject();
		json.put("type", "snapshot");
		json.put("tags", generateTags(NUMBER_TAGS));
		json.put("data", generateData(NUMBER_DATA));
		json.put("userId", String.valueOf(rand.nextInt(99999)));
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
