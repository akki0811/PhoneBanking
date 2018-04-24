package com.wildcard.phoneBanking.encoder;

import java.nio.charset.Charset;

public class EncodeUtility {
	
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	public byte[] getEncodedValue(String encodeValue){
		System.out.println("Encoding value before storing in DB");
		return encodeValue.getBytes(UTF_8);
	}
	
	public String getDecodedValue(byte[] bytes){
		System.out.println("Decoding value before fetching in DB");
		return new String(bytes, UTF_8);
	}
}
