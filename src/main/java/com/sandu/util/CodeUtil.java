package com.sandu.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		//?
		//String verifyCodeExpected = HttpServletRequestUtil.getString(request, (String)request.getSession()
		//		.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY));
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		System.out.println("verifyCodeExpected"+verifyCodeExpected);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		System.out.println("verifyCodeActual"+verifyCodeActual);
		 if(verifyCodeActual==null ||!verifyCodeExpected.equals(verifyCodeActual)) {
			 return false;
		 }
		return true;
	}

}
