import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

@SuppressWarnings("deprecation")
public class SnapshotsFactory {

	static String[] tagsk = { "city", "url", "param", "location", " place",
			"kind", "street", "state", "sale_date", "price", "Product",
			"Price", "Country" };
	static ArrayList<String> tags = new ArrayList<String>(Arrays.asList(tagsk));
	static Random rand = new Random();

	public static void main(String[] args) {
		String ROOT_URL = args[1];
		int kilkkL = Integer.parseInt(args[0]);
		for (int i = 0; i < kilkkL; i++) {
			try {
				ClientRequest request = new ClientRequest(ROOT_URL);
				request.accept("application/json");
				String input = getRequest();
				System.out.println(input);
				request.body("application/json", input);

				ClientResponse<String> response = request.post(String.class);
				int status = response.getStatus();
				System.out.println("Request Sales - putToTable: Status: "
						+ status
						+ (status == 200 ? "; Request success!"
								: "; Request failed!"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getRequest() {
		StringBuilder sb = new StringBuilder(
				"{\"type\": \"snapshot\",\"userId\":")
				.append(rand.nextInt(99999))
				.append(",\"avps\": {\"appurl\": \"/oss-ui/tools/toolbox.jsp\",\"locationId\":")
				.append(rand.nextInt(99999)).append(",").append(getTags(12))
				.append("} ,\"data\" : [{").append(getTags(4)).append("}]}");
		return sb.toString();
	}

	public static String getTags(int kk) {
		int kilk = 12;
		StringBuilder ss = new StringBuilder();
		int k = rand.nextInt(kk) + 1;
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < k; i++) {
			set.add(tags.get(rand.nextInt(kilk)));
		}
		for (String el : set) {
			ss.append("\"").append(el).append("\":\"").append(el)
					.append(rand.nextInt(kilk)).append("\",");
		}
		ss.deleteCharAt(ss.length() - 1);
		return ss.toString();
	}

}
