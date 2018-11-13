package Analyze;

import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class App {

	@SuppressWarnings("restriction")
	public static void main(String args[]) throws Exception {
		// 使用json來parse html
		String url = "http://www.hkex.com.hk/Market-Data/Securities-Prices/Equities?sc_lang=en";
		Document doc = Jsoup.parse(new URL(url), 3000);

		// 取得所有的script tag
		Elements eles = doc.getElementsByTag("script");
		for (Element ele : eles) {

			// 檢查是否有detailInfoObject字串
			String script = ele.toString();
			if (script.indexOf("detailInfoObject") > -1) {

				// 只取得script的內容
				script = ele.childNode(0).toString();

				// 使用ScriptEngine來parse
				ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
				engine.eval(script);

				// 取得你要的變數
				Object obj = engine.get("detailInfoObject");
				System.out.println("detailInfoObject = " + obj);

				// 將obj轉成Json物件
				JSONObject json = JSONObject.fromObject(obj);
				System.out.println("json = " + json);

				// 取得欄位
				System.out.println("destInfo = " + json.get("destInfo"));

				// 取得欄位(array type)
				JSONArray scans = json.getJSONArray("scans");
				for (int i = 0, max = scans.size(); i < max; i++) {
					JSONObject child = (JSONObject) scans.get(i);
					System.out.println("scans[" + i + "] = " + child);
				}

			}
		}
	}
}