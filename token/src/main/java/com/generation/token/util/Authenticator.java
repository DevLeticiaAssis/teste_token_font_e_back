package com.generation.token.util;

import javax.xml.bind.DatatypeConverter;

import com.generation.token.model.Usuario;

public class Authenticator {

private static final String prefix="LeTiCiAaSsIs|";
	
	public static String encode(Usuario usuario) {
		return DatatypeConverter.printHexBinary((prefix+usuario).getBytes());
	}
	
	public static boolean isValid(String token) {
		byte strByte[] = DatatypeConverter.parseHexBinary(token);
		String newToken = new String(strByte);
		return newToken.startsWith(prefix);	
	}
}
