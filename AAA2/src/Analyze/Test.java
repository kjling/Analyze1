package Analyze;

import net.sf.json.JSONObject;

public class Test {
	public static void main(String args[]) throws Exception{
		
		Analyze a=new Analyze();
		String s=a.analyze("equity", "1");
		System.out.println(s);
	}
}
