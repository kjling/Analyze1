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
		// ʹ��json��parse html
		String url = "http://www.hkex.com.hk/Market-Data/Securities-Prices/Equities?sc_lang=en";
		Document doc = Jsoup.parse(new URL(url), 3000);

		// ȡ�����е�script tag
		Elements eles = doc.getElementsByTag("script");
		for (Element ele : eles) {

			// �z���Ƿ���detailInfoObject�ִ�
			String script = ele.toString();
			if (script.indexOf("detailInfoObject") > -1) {

				// ֻȡ��script�ă���
				script = ele.childNode(0).toString();

				// ʹ��ScriptEngine��parse
				ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
				engine.eval(script);

				// ȡ����Ҫ��׃��
				Object obj = engine.get("detailInfoObject");
				System.out.println("detailInfoObject = " + obj);

				// ��obj�D��Json���
				JSONObject json = JSONObject.fromObject(obj);
				System.out.println("json = " + json);

				// ȡ�Ù�λ
				System.out.println("destInfo = " + json.get("destInfo"));

				// ȡ�Ù�λ(array type)
				JSONArray scans = json.getJSONArray("scans");
				for (int i = 0, max = scans.size(); i < max; i++) {
					JSONObject child = (JSONObject) scans.get(i);
					System.out.println("scans[" + i + "] = " + child);
				}

			}
		}
	}
}