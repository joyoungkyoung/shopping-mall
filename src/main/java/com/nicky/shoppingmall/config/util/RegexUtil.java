package com.nicky.shoppingmall.config.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    /**
	 * 이메일 정규식 체크
	 * @param email
	 * @return
	 */
    public static boolean regexEmail(String email) {
		String regex = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]@([\\w\\.\\_\\-])*[a-zA-Z0-9]+\\.+([a-zA-Z0-9]{2,8})$"; 
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(email); 
		if(m.matches()) { 
			return true;
		} 
		return false;
	}

    /**
	 * 비밀번호 정규식 체크
	 * 최소 요건
	 * 8자리 이상의 글자수
	 * 1개 이상의 숫자
	 * 1개 이상의 대문자
	 * 1개 이상의 특수문자
	 * @param password
	 * @return 
	 */
	public static boolean regexPassword(String password) {
		String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,20}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		if(m.matches()) {
			return true;
		}
		return false;
	}
}
