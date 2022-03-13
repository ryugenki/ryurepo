package mymule;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public abstract class CheckBase {
	JSONObject json;
	List<String> errorsList = new ArrayList<String>();
	
	public CheckBase(String json) {
		this.json = new JSONObject(json);
	}
jjjjj
    
    public abstract List<String> formatCheck(JSONObject json) throws Exception;
}
