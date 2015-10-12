package com.webapp.utils.codec;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class CodecUtils {

//	 如基本的单向加密算法：
//	    BASE64 严格地说，属于编码格式，而非加密算法
//	    MD5(Message Digest algorithm 5，信息摘要算法)
//	    SHA(Secure Hash Algorithm，安全散列算法)
//	    HMAC(Hash Message Authentication Code，散列消息鉴别码)
//
//	    复杂的对称加密（DES、PBE）、非对称加密算法：
//	    DES(Data Encryption Standard，数据加密算法)
//	    PBE(Password-based encryption，基于密码验证)
//	    RSA(算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman)
//	    DH(Diffie-Hellman算法，密钥一致协议)
//	    DSA(Digital Signature Algorithm，数字签名)
//	    ECC(Elliptic Curves Cryptography，椭圆曲线密码编码学)

	public static final String UNICODE = "unicode";
	public static final String UTF8 = "utf-8";
	private static final String FIXED = "fixed_key";

	public static String bytesToString(byte[] data) {
        return StringUtils.newStringUtf8(data);
    }

	public static class KeyUtils {

		public static SecretKey getSecretKey(AlgoSym algoSym) {
			return getSecretKey(algoSym, null, false);
	    }

		public static SecretKey getSecretKey(AlgoSym algoSym, String seed) {
			return getSecretKey(algoSym, seed, true);
	    }

		private static SecretKey getSecretKey(AlgoSym algoSym, String seed, boolean isSeed) {
			KeyGenerator instance = null;
	        try {
	            instance = KeyGenerator.getInstance(algoSym.getAlgorithm());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
			if(isSeed) {
				instance.init(algoSym.getKeySize(), new SecureRandom(seed.getBytes()));
			}else {
				instance.init(algoSym.getKeySize());
			}
			SecretKey deskey = instance.generateKey();

			return deskey;
	    }

		public static void genKeyPair(AlgoAsym algoAsym) throws Exception {
			KeyPairGenerator keyPairGen;
	        try {
	        	keyPairGen = KeyPairGenerator.getInstance(algoAsym.getAlgorithm());
	        	keyPairGen.initialize(1024);
		        KeyPair keyPair = keyPairGen.genKeyPair();

		        PublicKey pubKey = keyPair.getPublic();
		        PrivateKey priKey = keyPair.getPrivate();

		        String pubName = algoAsym.name() + "_public_key.cer";
		        String priName = algoAsym.name() + "_private_key.pfx";

				writePubKey(pubName, pubKey);
		        writePriKey(priName, priKey);
		        if(algoAsym.getAlgorithm().contains("RSA")){
		        	RSAPublicKey rsaPubKey = (RSAPublicKey)pubKey;
		        	RSAPrivateKey rsaPriKey = (RSAPrivateKey)priKey;
		        	System.out.println(pubKey.toString());
					System.out.println("exponent 16 -> " + rsaPubKey.getPublicExponent().toString(16));
					System.out.println("byte -> " + rsaPubKey.getEncoded());
					System.out.println("10 -> " + rsaPubKey.getModulus().toString());
					System.out.println("16 -> " + rsaPubKey.getModulus().toString(16));
					System.out.println("base64 -> " + Encode.encodeBase64Str(rsaPubKey.getEncoded()));
					System.out.println("file -> " + System.getProperty("user.dir") + "\\" + pubName);
					System.out.println();
					System.out.println(rsaPriKey.getAlgorithm() + " private key ");
					System.out.println("byte -> " + rsaPriKey.getEncoded());
					System.out.println("10 -> " + rsaPriKey.getModulus().toString());
					System.out.println("16 -> " + rsaPriKey.getModulus().toString(16));
					System.out.println("base64 -> " + Encode.encodeBase64Str(priKey.getEncoded()));
					System.out.println("file -> " + System.getProperty("user.dir") + "\\" + priName);
		        }else if (algoAsym.getAlgorithm().contains("DSA")) {
		        	DSAPublicKey dsaPubKey = (DSAPublicKey)pubKey;
	            	DSAPrivateKey dsaPriKey = (DSAPrivateKey)priKey;

			        System.out.println(dsaPubKey.toString());
			        System.out.println("byte -> " + dsaPubKey.getEncoded());
					System.out.println("base64 -> " + Encode.encodeBase64Str(dsaPubKey.getEncoded()));
					System.out.println("file -> " + System.getProperty("user.dir") + "\\" + pubName);
					System.out.println();
					System.out.println(dsaPriKey.getAlgorithm() + " private key ");
					System.out.println("byte -> " + dsaPriKey.getEncoded());
					System.out.println("base64 -> " + Encode.encodeBase64Str(dsaPriKey.getEncoded()));
					System.out.println("file -> " + System.getProperty("user.dir") + "\\" + priName);
				}


	        } catch (NoSuchAlgorithmException e) {
		        e.printStackTrace();
	        }
	    }

		public static PublicKey getPublicKey(AlgoAsym algoAsym, byte[] key) {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);

			PublicKey publicKey = null;
            try {
	            publicKey = KeyUtils.getKeyFactory(algoAsym).generatePublic(keySpec);
            } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
            }
			return publicKey;
        }

		public static PrivateKey getPrivateKey(AlgoAsym algoAsym, byte[] key) {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);

			PrivateKey privateKey = null;
            try {
	            privateKey = KeyUtils.getKeyFactory(algoAsym).generatePrivate(keySpec);
            }catch (InvalidKeySpecException e) {
	            e.printStackTrace();
            }
			return privateKey;
        }

		private static KeyFactory getKeyFactory(AlgoAsym algoAsym) {
			KeyFactory keyFactory = null;
			try {
	            keyFactory = KeyFactory.getInstance(algoAsym.getAlgorithm());
            } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
            }
			return keyFactory;
        }

		@SuppressWarnings("unchecked")
        public static <T> T readKey(Class<T> clz, String path) {
			T key = null;
		    ObjectInputStream ois = null;
		    try {
		        ois = new ObjectInputStream(new FileInputStream(path));
		        key = (T)ois.readObject();
	        } catch (Exception e) {
		        e.printStackTrace();
	        }finally{
	        	try {
		            ois.close();
	            } catch (IOException e) {
		            e.printStackTrace();
	            }
	        }
			return key;
	    }

		private static Object readObject(String path) {
		    ObjectInputStream ois = null;
		    try {
		        ois = new ObjectInputStream(new FileInputStream(path));
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

		public static PublicKey readPubKey(String path) {
		    return (PublicKey)readObject(path);
	    }

		public static PrivateKey readPriKey(String path) {
		    return (PrivateKey)readObject(path);
	    }

		public static void writePubKey(String path, PublicKey publicKey) {
			writeObject(path, publicKey);
	    }

		public static void writePriKey(String path, PrivateKey privateKey) {
			writeObject(path, privateKey);
	    }

		private static void writeObject(String path, Object object) {
			ObjectOutputStream oos = null;
	        try {
		        oos = new ObjectOutputStream(new FileOutputStream(path));
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
    }

	public static class Encode {

		public static byte[] encodeBase64(byte[] data){
			return Base64.encodeBase64(data);
		}
		public static String encodeBase64Str(byte[] data){
			return Base64.encodeBase64String(data);
		}

		public static byte[] encodeBase64(byte[] data, boolean urlSafe){
			return Base64.encodeBase64(data, false, urlSafe);
		}
		public static String encodeBase64Str(byte[] data, boolean urlSafe){
			return bytesToString(encodeBase64(data, urlSafe));
		}

		public static String encodeHex(byte[] data) {
	        return Hex.encodeHexString(data);
        }

		public static byte[] encodeRadix(byte[] data, int radix) {
	        return encodeRadixStr(data, radix).getBytes();
        }

		public static String encodeRadixStr(byte[] data, int radix) {
	        return new BigInteger(1, data).toString(radix);
        }

		public static String encodeURL(String url) {
			String result = null;
	        try {
	        	result = URLEncoder.encode(url, UNICODE);
            } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
            }
	        return result;
        }

		public static String encodeUTF8(byte[] data) {
			String result = null;
	        try {
	            result = new String(data, UTF8);
            } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
            }
	        return result;
        }

		public static String escapeUnicode(String data) {
	        return StringEscapeUtils.escapeJava(data);
        }
	}

	public static class Decode {

		public static byte[] decodeBase64(byte[] data) {
			return Base64.decodeBase64(data);
        }

		public static String decodeBase64Str(byte[] data) {
			return bytesToString(Base64.decodeBase64(data));
        }

		public static byte[] decodeHex(String data) {
	        try {
	            return Hex.decodeHex(data.toCharArray());
            } catch (DecoderException e) {
	            e.printStackTrace();
            }
	        return null;
        }

		public static byte[] decodeRadix(String data, int radix) {
			return new BigInteger(data, radix).toByteArray();
		}

		public static String decodeRadixStr(String data, int radix) {
			return bytesToString(decodeRadix(data, radix));
		}

		public static byte[] decodeRadix(byte[] data, int radix) {
			return new BigInteger(new String(data), radix).toByteArray();
		}

		public static String decodeRadixStr(byte[] data, int radix) {
			return bytesToString(decodeRadix(data, radix));
		}

		public static String decodeURL(String url) {
			String result = null;
	        try {
	        	result = URLDecoder.decode(url, UNICODE);
            } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
            }
	        return result;
        }

		public static String unescapeUnicode(String data) {
	        return StringEscapeUtils.unescapeJava(data);
        }

	}

	public static class Hashing {

		public static String md5Hex(String data) {
	        return DigestUtils.md2Hex(data);
        }

		public static String sha256Hex(String data) {
	        return DigestUtils.sha256Hex(data);
        }

		public static String sha384Hex(String data) {
	        return DigestUtils.sha384Hex(data);
        }

		public static String sha512Hex(String data) {
	        return DigestUtils.sha512Hex(data);
        }

	}

	public enum AlgoSym {
		//对称加密
		AES_128("AES", 128),
//		AES_192("AES", 192),
//		AES_256("AES", 256),
		DES("DES", 56),
		DESede_112("DESede", 112),
		DESede_168("DESede", 168);

		private String algorithm;
		private int keySize;
		public String getAlgorithm() {
			return algorithm;
		}
		public int getKeySize() {
			return keySize;
		}
		AlgoSym(String algorithm, int keySize){
			this.algorithm = algorithm;
			this.keySize = keySize;
		}
	}

	public enum AlgoAsym {
		//数字签名的变种，非对称
		DSA_128("DSA", 128),
//		DSA_192("DSA", 192),
//		DSA_256("DSA", 256),

		//非对称加密
		RSA_128("RSA", 128);
//		RSA_192("RSA", 192),
//		RSA_256("RSA", 256),

		private String algorithm;
		private int keySize;

		public String getAlgorithm() {
			return algorithm;
		}
		public int getKeySize() {
			return keySize;
		}
		AlgoAsym(String algorithm, int keySize){
			this.algorithm = algorithm;
			this.keySize = keySize;
		}

		public boolean isRSA(){
			return this.getAlgorithm().equalsIgnoreCase("RSA");
		}

		public boolean isDSA(){
			return this.getAlgorithm().equalsIgnoreCase("DSA");
		}
	}

	public static class Encrypt {

		private byte[] data;
		public Encrypt (byte[] data){
			this.data = data;
		}

		public static Encrypt of(byte[] data) {
	        return new Encrypt(data);
        }

		private static byte[] encrypt(byte[] data, Key key) {
			byte[] result = null;
			try {
                Cipher cipher = Cipher.getInstance(key.getAlgorithm());
                cipher.init(Cipher.ENCRYPT_MODE, key);
                result = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}

		private static byte[] encryptSign(byte[] data, PrivateKey privateKey) {
			byte[] result = null;
            try {
                Signature sign = Signature.getInstance(privateKey.getAlgorithm());
                sign.initSign(privateKey);
                sign.update(data);
                result = sign.sign();
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}

		public Encrypt encrypt(AlgoSym algoSym) {
			data = encrypt(data, KeyUtils.getSecretKey(algoSym, DigestUtils.sha256Hex(FIXED)));
			return this;
        }

		public Encrypt encrypt(AlgoSym algoSym, byte[] key) {
			SecretKeySpec secretKey = new SecretKeySpec(key, algoSym.getAlgorithm());
			data = encrypt(data, secretKey);
			return this;
        }

		public Encrypt encrypt(AlgoSym algoSym, String base64Key) {
			encrypt(algoSym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
        }

		public Encrypt encrypt(Key key) {
			data = encrypt(data, key);
			return this;
        }

		public Encrypt encrypt(AlgoAsym algoAsym, byte[] key) {
			if(algoAsym.isRSA()){
				// RSA 公钥加密，私钥解密
				data = encrypt(data, KeyUtils.getPublicKey(algoAsym, key));
			}else if (algoAsym.isDSA()) {
				// DSA 私钥加密，公钥解密
				data = encryptSign(data, KeyUtils.getPrivateKey(algoAsym, key));
			}
			return this;
        }

		public Encrypt encrypt(AlgoAsym algoAsym, String base64Key) {
			encrypt(algoAsym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
        }

		public byte[] toByte() {
	        return data;
        }
		public byte[] toHex() {
			return toHexStr().getBytes();
        }
		public String toHexStr() {
			return Encode.encodeRadixStr(data, 16);
        }
		public byte[] toBase64() {
			return Encode.encodeBase64(data);
        }
		public String toBase64Str() {
			return Encode.encodeBase64Str(data);
		}
	}

	public static class Decrypt {
		private byte[] data;
		public Decrypt (byte[] data){
			this.data = data;
		}

		public static Decrypt of(byte[] data) {
	        return new Decrypt(data);
        }

		private static byte[] decrypt(byte[] data, Key key) {
			byte[] result = null;
			try {
                Cipher cipher = Cipher.getInstance(key.getAlgorithm());
                cipher.init(Cipher.DECRYPT_MODE, key);
                result = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}

		private static byte[] decryptSign(byte[] data, PublicKey publicKey) {
			byte[] result = null;
            try {
                Signature sign = Signature.getInstance(publicKey.getAlgorithm());
                sign.initVerify(publicKey);
                sign.update(data);
                result = sign.sign();
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}

		public Decrypt decrypt(AlgoSym algoSym) {
			data = decrypt(data, KeyUtils.getSecretKey(algoSym, DigestUtils.sha256Hex(FIXED)));
			return this;
        }

		public Decrypt decrypt(AlgoSym algoSym, byte[] key) {
			SecretKeySpec secretKey = new SecretKeySpec(key, algoSym.getAlgorithm());
			data = decrypt(data, secretKey);
			return this;
        }

		public Decrypt decrypt(AlgoSym algoSym, String base64Key) {
			decrypt(algoSym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
        }

		public Decrypt decrypt(Key key) {
			data = decrypt(data, key);
			return this;
        }

		public Decrypt decrypt(AlgoAsym algoAsym, byte[] key) {
			if(algoAsym.isRSA()){
				// RSA 公钥加密，私钥解密
				data = decrypt(data, KeyUtils.getPrivateKey(algoAsym, key));
			}else if (algoAsym.isDSA()) {
				// DSA 私钥加密，公钥解密
				data = decryptSign(data, KeyUtils.getPublicKey(algoAsym, key));
			}
			return this;
        }
		public Decrypt decrypt(AlgoAsym algoAsym, String base64Key) {
			decrypt(algoAsym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
		}

		public byte[] toByte() {
	        return data;
        }
		public String toStr() {
			return StringUtils.newStringUtf8(data);
        }

	}

}
