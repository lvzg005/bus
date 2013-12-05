package com.company.bus.loaned.common.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.company.bus.loaned.common.comm.Constants;


public class BusinessUtil {
	
	/**
	 * 得到页面选中的数据
	 * */
	public static LinkedHashMap<String, String> getStorage(String _storage) {
		Map<String, ArrayList<LinkedHashMap<String, String>>> map = JsonUtils.jsonString2Map(_storage);
		if(map == null || map.isEmpty())
			return null;
		ArrayList<LinkedHashMap<String, String>> ids = map.get(Constants._storage);
		if(ids == null || ids.isEmpty())
			return null;
		LinkedHashMap<String, String> selectArr = ids.get(0);
		if(selectArr == null || selectArr.isEmpty())
			return null;
		return selectArr;
	}
	
	/**
	 * 替换卡号-前6后4当中用*代替
	 * @param cardNo
	 * @return
	 */
	public static String subCardNo(String cardNo) {
		if(StringUtils.isBlank(cardNo))
			return "";
		else if(cardNo.length() < 6)
			return cardNo;
		return cardNo.substring(0, 6).concat("******").concat(cardNo.substring(cardNo.length()-4,cardNo.length()));
	}
	

}
