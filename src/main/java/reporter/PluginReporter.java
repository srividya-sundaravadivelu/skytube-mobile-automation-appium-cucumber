package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class PluginReporter {

	public static void setTestInfo(String sessionId, String testName, String testStatus, String error, URL serverUrl) {
		try {
			String baseUrl = serverUrl.toString();
			if (baseUrl.endsWith("/")) {
				baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
			}
			String url = baseUrl + "/setTestInfo";

			String body = "{" + "\"sessionId\":\"" + sessionId + "\"," + "\"testName\":\"" + testName + "\","
					+ "\"testStatus\":\"" + testStatus + "\"," + "\"error\":\"" + error + "\"" + "}";
			System.out.println("url = " + url);
			System.out.println("Body of setTestInfo = " + body);
			HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(url).header("Content-Type", "application/json")
					.body(body).asJson();

			System.out.println("Response Status: " + jsonNodeHttpResponse.getStatus());			
		} catch (Exception e) {
			System.out.println("Failed to set Test info");
		}

	}

	public static String getReport(URL serverUrl) throws IOException, InterruptedException {
		String baseUrl = serverUrl.toString();
		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}
		String url = baseUrl + "/getReport";
		//String url = serverUrl.toString() + "/getReport";
		String s = Unirest.get(url).asString().getBody();
		return s;
	}

	public static void deleteReportData(URL serverUrl) throws IOException, InterruptedException {
		String baseUrl = serverUrl.toString();
		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}
		String url = baseUrl + "/deleteReportData";
		//String url = serverUrl.toString() + "/deleteReportData";
		Unirest.delete(url).asEmpty();
	}

	public static void createReportFile(String data, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/" + fileName + ".html");
		fileWriter.write(data);
		fileWriter.close();
	}
}
