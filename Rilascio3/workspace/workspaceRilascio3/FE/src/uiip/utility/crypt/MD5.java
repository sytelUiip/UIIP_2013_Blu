package uiip.utility.crypt;

import java.io.UnsupportedEncodingException; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;



public class MD5 {

	public static String getHash(String message){ 
		String digest = null; 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] hash = md.digest(message.getBytes("UTF-8")); 
			//converting byte array to Hexadecimal String 
			StringBuilder sb = new StringBuilder(2*hash.length); 
			for(byte b : hash){ 
				sb.append(String.format("%02x", b&0xff));
			} digest = sb.toString(); 
		} catch (UnsupportedEncodingException ex) { 
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) { 
			ex.printStackTrace();
		} return digest; 
	}
	
	public static void main(String[] args) {
		System.out.println(getHash("antonio"));
		System.out.println(getHash("francesca"));
		System.out.println(getHash("genio"));
		System.out.println(getHash("francesco"));
		System.out.println(getHash("annamaria"));
		System.out.println(getHash("eu"));
		
		
	}
}