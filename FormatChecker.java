package mymule;

XXXXXXXXXXXXXXXXXXXXXXXXX
BBBBBBBBBBBBBBBBBBBBBBBB
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

	@Override
	public List<String> formatCheck(JSONObject json) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
public class FormatChecker {
	private static Map<String, String> checkClassMaster = null;
	
	static {
		try {
			System.out.println("*****************************Static Block init.");
			InputStream inputStream = new FileInputStream(new File("checkclass-master.yaml"));
			Yaml yaml = new Yaml();
			checkClassMaster = yaml.load(inputStream);
		}catch(Exception e) {
			checkClassMaster = null;
			e.printStackTrace();
		}
	}

    public static List<String> formatCheck(String json, String pageType) {
    	List<String> errorsList = new ArrayList<String>();
    	
    	System.out.println("*****************************yaml" + checkClassMaster.toString());
    	
    	// read json
    	JSONObject jsonObj = null;
    	try {
    	    jsonObj = new JSONObject(json);
    	}catch(Exception e) {
    		e.printStackTrace();
    		errorsList.add("E0001: JSON入力の解析に失敗しました。");
    		return errorsList;
    	}

    	// page data check
    	try {
        	CheckBase checkA = null;
        	if("A".equals(pageType)) {
        		checkA = new Check0003Impl(json);
        	} else if("B".equals(pageType)) {
        		checkA = new Check0004Impl(json);
        	}
        	//
        	JSONObject jsonO = new JSONObject(json);
        	System.out.println("***********************start read json apply_date");
        	String aaa = jsonO.getString("apply_date");
        	System.out.println("***********************after read json apply_date " + "a=" + aaa);
        	
        	// start check
    		errorsList = checkA.formatCheck(jsonObj);

    	}catch(Exception e) {
    		e.printStackTrace();
    		errorsList.add("E0002: システムエラーが発生しました。（" + e.getMessage() + "）");
    		return errorsList;
    	}

    	return errorsList;
    }
    
    /*	public FormatChecker(String json) {
	this.json = json;
	File f=new File("C:\\mule\\1.log");
	try {
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(json.getBytes());
		fos.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	JSONObject jsonObj = new JSONObject(json);
    		String aa = jsonObj.getString("a");
    		System.out.println(aa);
    		
    		JSONObject c = jsonObj.getJSONObject("c");
    		int ee = c.getInt("ee");
    		System.out.println(ee);
    		
    		JSONArray da = jsonObj.getJSONArray("d");
    		for(int i=0;i<da.length();i++) {
    			JSONObject  d = da.getJSONObject(i);
    			int k = d.getInt("k");
    			System.out.println(k);
    			
    		}
    		    		//ArrayList<String> errorsList = new ArrayList<>();
    		    		 * 
    		    		 * 
    		    		 * // TODO Auto-generated method stub
		JSONObject jsonObj = new JSONObject("{\"a\": \"1234g2333333\", \"b\": \"uiop\", \"c\": {\"ee\": 123, \"kk\": \"678\"}, \"d\": [{\"k\": -334.34},{\"k\":667.467}]}");
		String aa = jsonObj.getString("a");
		System.out.println(aa);
		
		JSONObject c = jsonObj.getJSONObject("c");
		int ee = c.getInt("ee");
		System.out.println(ee);
		
		JSONArray da = jsonObj.getJSONArray("d");
		for(int i=0;i<da.length();i++) {
			JSONObject  d = da.getJSONObject(i);
			int k = d.getInt("k");
			System.out.println(k);
			
		}
		
		
		
		JSONObject jsonObj = new JSONObject();
		String aa = jsonObj.getString("a");
		System.out.println(aa);
		
		JSONObject c = jsonObj.getJSONObject("c");
		int ee = c.getInt("ee");
		System.out.println(ee);
		
		JSONArray da = jsonObj.getJSONArray("d");
		for(int i=0;i<da.length();i++) {
			JSONObject  d = da.getJSONObject(i);
			int k = d.getInt("k");
			System.out.println(k);
			
		}
	*/
}
