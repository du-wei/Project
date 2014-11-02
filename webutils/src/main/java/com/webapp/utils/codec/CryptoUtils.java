package com.webapp.utils.codec;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class CryptoUtils {

	private static final String RSA = "RSA";
	private static final String DSA = "DSA";
	private static final String AES = "AES";
	private static final String DES = "DES";
	private static final String MD5 = "MD5";

	public static void main(String[] args) throws Exception{
		//secret
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		KeySpec keySpec = new PBEKeySpec("aaa".toCharArray());
		SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
		PBEParameterSpec paraSpec = new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8}, 1000);
		cipher.init(Cipher.ENCRYPT_MODE, key, paraSpec);
//		cipher.doFinal("ffff".getBytes());
	}

	public static void genKeyPair() {
		KeyPairGenerator keyPairGen;
        try {
        	keyPairGen = KeyPairGenerator.getInstance(RSA);
        	keyPairGen.initialize(1024);
	        KeyPair keyPair = keyPairGen.genKeyPair();
	        RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
	        RSAPrivateKey priKey = (RSAPrivateKey)keyPair.getPrivate();

			writePubKey("public_key.cer", pubKey);
	        writePriKey("private_key.pfx", priKey);

			System.out.println(pubKey.toString());
			System.out.println("exponent 16 -> " + pubKey.getPublicExponent().toString(16));
			System.out.println("10 -> " + pubKey.getModulus().toString());
			System.out.println("16 -> " + pubKey.getModulus().toString(16));
			System.out.println("file -> " + System.getProperty("user.dir") + "\\public_key.cer");
			System.out.println();
			System.out.println(priKey.getAlgorithm() + " private key ");
			System.out.println("10 -> " + priKey.getModulus().toString());
			System.out.println("16 -> " + priKey.getModulus().toString(16));
			System.out.println("file -> " + System.getProperty("user.dir") + "\\private_key.pfx");
        } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
        }
    }

	private static void writeObject(String name, Object object) {
		ObjectOutputStream oos = null;
        try {
	        oos = new ObjectOutputStream(new FileOutputStream(name));
	        oos.writeObject(object);
        } catch (Exception e) {
	        e.printStackTrace();
        }finally{
        	try {
	            oos.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
        }
    }
	private static Object readObject(String name) {
	    ObjectInputStream ois = null;
	    try {
	        ois = new ObjectInputStream(new FileInputStream(name));
	        return ois.readObject();
        } catch (Exception e) {
	        e.printStackTrace();
        }finally{
        	try {
	            ois.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
        }
		return ois;
    }

	public static void writePubKey(String name, PublicKey publicKey) {
		writeObject(name, publicKey);
    }

	public static void writePriKey(String name, PrivateKey privateKey) {
		writeObject(name, privateKey);
    }

	public static PublicKey readPubKey(String name) {
	    return (PublicKey)readObject(name);
    }

	public static PrivateKey readPriKey(String name) {
	    return (PrivateKey)readObject(name);
    }

	public static String encode(byte[] data) {
		return new BigInteger(1, data).toString();
	}
	public static String encodeHex(byte[] data) {
//		Hex.encodeHexString(data)
	    return new BigInteger(1, data).toString(16);
    }

	public static byte[] decode(String data) {
	    return new BigInteger(data, 10).toByteArray();
    }
	public static byte[] decodeHex(String data) {
//		Hex.decodeHex(data.toCharArray())
	    return new BigInteger(data, 16).toByteArray();
    }

	public static String encrypt(String data) {
	    try {
	    	Cipher cipher = Cipher.getInstance(RSA);
	        cipher.init(Cipher.ENCRYPT_MODE, readPubKey("public_key.cer"));
	        byte[] result = cipher.doFinal(data.getBytes());

	        return encodeHex(result);
        } catch (Exception e) {
	        e.printStackTrace();
        }
		return null;
    }

	public static String decrypt(String data) {
		byte[] byteArray = new BigInteger(data, data.contains("a") ? 16 : 10).toByteArray();
		try {
	    	Cipher cipher = Cipher.getInstance(RSA);
	        cipher.init(Cipher.DECRYPT_MODE, readPriKey("private_key.pfx"));
	        byte[] result = cipher.doFinal(byteArray);
	        return new String(result);
        } catch (Exception e) {
	        e.printStackTrace();
        }
		return null;
    }

	//des aes
	private static String des(String data){
		try {
	        Cipher cipher = Cipher.getInstance(DES);

//	        SecureRandom secureRandom = new SecureRandom();
	        KeyGenerator instance = KeyGenerator.getInstance(DES);
//	        instance.init(secureRandom);
	        SecretKey generateKey = instance.generateKey();
	        cipher.init(Cipher.ENCRYPT_MODE, generateKey);
	        cipher.doFinal(data.getBytes());
        } catch (Exception e) {
	        e.printStackTrace();
        }
		return null;
	}

	private static String mac(String data){
		Mac mac = null;
        try {
	        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
	        SecretKey secretKey = keyGen.generateKey();
	        mac = Mac.getInstance(secretKey.getAlgorithm());
	        mac.init(secretKey);
        } catch (Exception e) {
	        e.printStackTrace();
        }

		byte[] doFinal = mac.doFinal(data.getBytes());
		return new String(doFinal);
	}

	// md5 sha
	private static String MD5(String data){
		MessageDigest digest = null;
        try {
	        digest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
        }
		byte[] result = digest.digest(data.getBytes());

		return new String(result);
	}

}
