/*package Analyze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("/Analyze")
public class Analyze {
	
	@GET
	@Path("/name") 
	@Produces(MediaType.APPLICATION_JSON)   
	public JSONObject analyze(@QueryParam("name") String name,@QueryParam("keyword") String keyword) throws Exception{
		
		String strurl="http://www.hkex.com.hk/Market-Data/Securities-Prices/Equities?sc_lang=en";
		URL tpurl = new URL(strurl); 
	    HttpURLConnection httpConn = (HttpURLConnection) tpurl.openConnection();
	    //设置编码
	    InputStreamReader input = new InputStreamReader(httpConn.getInputStream(), "utf-8");
	    BufferedReader bufReader = new BufferedReader(input);  
	    String line = "";  
	    StringBuilder contentBuf = new StringBuilder();  
	    while ((line = bufReader.readLine()) != null) {  
	        contentBuf.append(line);  
	    }
	    System.out.println("captureJavascript()的结果：\n" + contentBuf);
	    //截取token值
	    String tptoken=contentBuf.substring(contentBuf.indexOf("evLt"), contentBuf.indexOf("// For returning the current UI language"));
	    String token=tptoken.substring(tptoken.indexOf("evLt"), tptoken.indexOf("\";"));
	    System.out.println("captureJavascript()的结果：\n" + token);
		
		List<EquitiesInfo> listEquities = new ArrayList<EquitiesInfo>(0);
		//设置要爬的URL		
		String strURL = "https://www1.hkex.com.hk/hkexwidget/data/get"+name+
				"filter?lang=eng&token="+token+
				"&keyword="+keyword+
				"&sort=5&order=0&qid=1528872805130&callback=jQuery31100032130576705982072_1528872798215&_=1528872798216";  
	    URL url = new URL(strURL); 
	    HttpURLConnection httpConn1 = (HttpURLConnection) url.openConnection();
	    //设置编码
	    InputStreamReader input1 = new InputStreamReader(httpConn1.getInputStream(), "utf-8");
	    //获取HTML页面  
	    BufferedReader bufReader1 = new BufferedReader(input1);  
	    String line1 = "";  
	    StringBuilder contentBuf1 = new StringBuilder();  
	    while ((line1 = bufReader1.readLine()) != null) {  
	        contentBuf1.append(line1);  
	    }
	    System.out.println(contentBuf1);
	    String date=contentBuf1.substring(contentBuf1.indexOf("lastupd"), contentBuf1.indexOf("\",\"lastupd_l"));
	    System.out.println(date);
	    String date1=date.substring(10,31);	    
	    System.out.println(date1);
	    System.out.println("\n");
	    //截取JSON值
	    Object tpcode=contentBuf1.substring(contentBuf1.indexOf("[{"), contentBuf1.indexOf("},\""));
	    //转换为JSONArray
        JSONArray array = JSONArray.fromObject(tpcode) ;
        for (int i = 0; i < array.size(); i++) {  
            System.out.println(array.getJSONObject(i).getString("sym"));  
            System.out.println(array.getJSONObject(i).getString("nm"));  
            System.out.println(array.getJSONObject(i).getString("ls"));
            System.out.println("\n");
            listEquities.add(new EquitiesInfo(array.getJSONObject(i).getString("sym"),array.getJSONObject(i).getString("nm"),array.getJSONObject(i).getString("ls"),array.getJSONObject(i).getString("pc")));             
            }
        //将List数组转化为JSONArray
        JSONArray jsonListEquities = JSONArray.fromObject(listEquities);  
        System.out.println( "jsonListUsers:" + jsonListEquities.toString() );
        //将JSONArray转换为JSONObject
        JSONObject jsonObject = new JSONObject();  
        jsonObject.put("data", jsonListEquities);
        jsonObject.put("date", date1);      
        System.out.println(jsonObject); 
        return jsonObject ;
		
	}

}
*/
package Analyze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("/Analyze")
public class Analyze {
	
	@GET
	@Path("/name")
	@Produces(MediaType.TEXT_PLAIN)   
	public String analyze(@QueryParam("name") String name,@QueryParam("keyword") String keyword) throws Exception{
		
		/*
		 * 爬取HKEX网址的token
		 */		
		//设置爬取token的网址
		String strurl="http://www.hkex.com.hk/Market-Data/Securities-Prices/Equities?sc_lang=en";
		URL tpurl = new URL(strurl); 
	    HttpURLConnection httpConn = (HttpURLConnection) tpurl.openConnection();
	    //设置编码
	    InputStreamReader input = new InputStreamReader(httpConn.getInputStream(), "utf-8");
	    BufferedReader bufReader = new BufferedReader(input);  
	    String line = "";  
	    StringBuilder contentBuf = new StringBuilder();  
	    while ((line = bufReader.readLine()) != null) {  
	        contentBuf.append(line);  
	    }
	    //截取token值
	    String tptoken=contentBuf.substring(contentBuf.indexOf("evLt"), contentBuf.indexOf("// For returning the current UI language"));
	    String token=tptoken.substring(tptoken.indexOf("evLt"), tptoken.indexOf("\";"));
	    //测试输出token值
	    System.out.println("token：\n" + token);
	    
	    /*
	     * 爬取HKEX上的信息
	     * 将爬取需要的信息重新封装成json对象返回
	     */	    
		//建立数组以备存储爬虫数据
		List<EquitiesInfo> listEquities = new ArrayList<EquitiesInfo>(0);
		//设置要爬的URL		
		String strURL = "https://www1.hkex.com.hk/hkexwidget/data/get"+name+
				"filter?lang=eng&token="+token+
				"&keyword="+keyword+
				"&sort=5&order=0&qid=1528872805130&callback=jQuery31100032130576705982072_1528872798215&_=1528872798216";  
	    URL url = new URL(strURL); 
	    HttpURLConnection httpConn1 = (HttpURLConnection) url.openConnection();
	    //设置编码
	    InputStreamReader input1 = new InputStreamReader(httpConn1.getInputStream(), "utf-8");
	    //获取HTML页面  
	    BufferedReader bufReader1 = new BufferedReader(input1);  
	    String line1 = "";  
	    StringBuilder contentBuf1 = new StringBuilder();  
	    while ((line1 = bufReader1.readLine()) != null) {  
	        contentBuf1.append(line1);  
	    }
	    Object tpcode=contentBuf1.substring(contentBuf1.indexOf("{\""), contentBuf1.indexOf(")"));
	    System.out.println(tpcode);
	    JSONObject jsonObject = JSONObject.fromObject(tpcode);
	    System.out.println(jsonObject.toString());
	    System.out.println(jsonObject.size());
	    /*//获取日期
	    String date=contentBuf1.substring(contentBuf1.indexOf("lastupd"), contentBuf1.indexOf("\",\"lastupd_l"));
	    String date1=date.substring(10,31);	    
	    //截取JSON值
	    Object tpcode=contentBuf1.substring(contentBuf1.indexOf("[{"), contentBuf1.indexOf("},\""));
	    //转换为JSONArray
        JSONArray array = JSONArray.fromObject(tpcode) ;
        for (int i = 0; i < array.size(); i++) {  
            System.out.println(array.getJSONObject(i).getString("sym"));  
            System.out.println(array.getJSONObject(i).getString("nm"));  
            System.out.println(array.getJSONObject(i).getString("ls"));
            System.out.println("\n");
            listEquities.add(new EquitiesInfo(array.getJSONObject(i).getString("sym"),array.getJSONObject(i).getString("nm"),array.getJSONObject(i).getString("ls"),array.getJSONObject(i).getString("pc")));             
        }
        
        //将List数组转化为JSONArray
        JSONArray jsonListEquities = JSONArray.fromObject(listEquities);  
        System.out.println( "jsonListUsers:" + jsonListEquities.toString() );
        
        //将JSONArray转换为JSONObject
        JSONObject jsonObject = new JSONObject();  
        jsonObject.put("data", jsonListEquities);
        jsonObject.put("date", date1); 
        System.out.println(jsonObject);*/
        
        //返回json对象
        return jsonObject.toString() ;		
	}

}
