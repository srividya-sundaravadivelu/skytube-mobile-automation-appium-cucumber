package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import logger.Log;

public class PluginReporter {

	public static void setTestInfo(String sessionId, String testName, String testStatus, String error, URL serverUrl) {
		try {
			String baseUrl = serverUrl.toString();
			if (baseUrl.endsWith("/")) {
				baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
			}
			String url = baseUrl + "/setTestInfo";

			JSONObject jsonBody = new JSONObject();
			jsonBody.put("sessionId", sessionId);
			jsonBody.put("testName", testName);
			jsonBody.put("testStatus", testStatus);
			jsonBody.put("error", error);

			Log.info("url = " + url);
			Log.info("Body of setTestInfo = " + jsonBody.toString());

			HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(url).header("Content-Type", "application/json")
					.body(jsonBody.toString()).asJson();

			Log.info("Response Status: " + jsonNodeHttpResponse.getStatus());
		} catch (Exception e) {
			Log.error("Failed to set Test info");
			//e.printStackTrace();
		}
	}

	public static String getReport(URL serverUrl) throws IOException, InterruptedException {
		String baseUrl = serverUrl.toString();
		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}
		String url = baseUrl + "/getReport";		
		String s = Unirest.get(url).asString().getBody();
		return s;
	}

	public static void deleteReportData(URL serverUrl) throws IOException, InterruptedException {
		String baseUrl = serverUrl.toString();
		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}
		String url = baseUrl + "/deleteReportData";		
		Unirest.delete(url).asEmpty();
	}

	public static void createReportFile(String data, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/" + fileName + ".html");
		fileWriter.write(data);
		fileWriter.close();
	}
}
