package mymule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import 
public class Check0003Impl extends CheckBase{
	
	public Check0003Impl(String json) {
		super(json);
	}

	@Override
	public List<String> formatCheck(JSONObject json) throws Exception{
		System.out.println("******************************Check0003Impl.formatCheck");
		// 申請年月日 = 完了予定年月日
		eualGreaterThan("finish_date", "apply_date");
		
		// 申請年月日 = 完了予定年月日
		eualGreaterThan("a", "b");
		
		
		// 「申請年月日」（No.2）が前段の計画申請「完了予定年月日」（No.42）の翌日から2か月を経過している場合。,
		// xxxが入力されていることをチェックする
		/*Date calDate = CheckUtils.dateAdd(json.getString("finish_date"), Calendar.DAY_OF_MONTH, 1);
		calDate = CheckUtils.dateAdd(calDate, Calendar.MONTH, 2);
		if(CheckUtils.strToDate(json.getString("apply_date")).compareTo(calDate) > 0) {
			if("".equals(json.getString("my_name"))) {
			    errorsList.add("E0003: 「申請年月日」が、「終期」の翌日から起算して２か月を超えている場合。、my_nameが未入力になっている。");
			}    
		}*/
		
		// xxxxxx

		
		// yyyyyy
		
		
		return errorsList;
		
	}
}
