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
    public void eualGreaterThan(String arg1, String arg2) {
    	if(!json.getString(arg1).equals(json.getString(arg2))) {
    		errorsList.add( arg1 + " not equals " + arg2);
    	}
    }
    
    public abstract List<String> formatCheck(JSONObject json) throws Exception;
}
