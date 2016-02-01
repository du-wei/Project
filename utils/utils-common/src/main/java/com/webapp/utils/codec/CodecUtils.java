package com.webapp.utils.codec;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.utils.codec.CodecUtils.AlgoAsymEnum.AlgoAsym;
import com.webapp.utils.codec.CodecUtils.AlgoAsymEnum.AlgoAsymMode;
import com.webapp.utils.codec.CodecUtils.AlgoAsymEnum.AlgoAsymPadding;
import com.webapp.utils.codec.CodecUtils.AlgoBuilder.AlgoAsymBuild;
import com.webapp.utils.codec.CodecUtils.AlgoBuilder.AlgoBuild;
import com.webapp.utils.codec.CodecUtils.AlgoBuilder.AlgoSymBuild;
import com.webapp.utils.codec.CodecUtils.AlgoSymEnum.AlgoSym;
import com.webapp.utils.codec.CodecUtils.AlgoSymEnum.AlgoSymMode;
import com.webapp.utils.codec.CodecUtils.AlgoSymEnum.AlgoSymPadding;
import com.webapp.utils.string.Utils;

public final class CodecUtils {

	private static final Logger logger = LoggerFactory.getLogger(CodecUtils.class);
	private static final String UNICODE = "unicode";
	private static final String FIXED = "fixed_key";

	public static String bytesToString(byte[] data) {
        return StringUtils.newStringUtf8(data);
    }

	public static class KeyUtils {

		/** KeyGenerator for AES(128) DES(56) DESede(168) HmacSHA1 HmacSHA256 */
		public static SecretKey genSecretKey(AlgoSym algoSym) {
			return genSecretKey(algoSym, null);
	    }

		/**
		 * Generate SecretKey by seed
		 * KeyGenerator for AES(128) DES(56) DESede(168) HmacSHA1 HmacSHA256
		 */
		public static SecretKey genSecretKey(AlgoSym algoSym, String seed) {
			KeyGenerator instance = null;
	        try {
	            instance = KeyGenerator.getInstance(algoSym.getName());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
			if(seed != null) {
				instance.init(algoSym.getKeySize(), new SecureRandom(seed.getBytes()));
			}else {
				instance.init(algoSym.getKeySize());
			}
			SecretKey deskey = instance.generateKey();

			return deskey;
	    }

		/**
		 * transform SecretKey
		 */
		public static SecretKey getSecretKey(AlgoSym algoSym, byte[] key){
			SecretKeySpec secretKey = new SecretKeySpec(key, algoSym.getName());
			return secretKey;
		}

		/** KeyPairGenerator for DSA(1024) RSA(1024, 2048) */
 		public static void genKeyPair(AlgoAsym algoAsym) throws Exception {
			KeyPairGenerator keyPairGen;
	        try {
	        	keyPairGen = KeyPairGenerator.getInstance(algoAsym.getName());
	        	keyPairGen.initialize(algoAsym.getKeySize());
		        KeyPair keyPair = keyPairGen.genKeyPair();

		        PublicKey pubKey = keyPair.getPublic();
		        PrivateKey priKey = keyPair.getPrivate();

		        String pubName = algoAsym.name() + "_public_key.cer";
		        String priName = algoAsym.name() + "_private_key.pfx";

		        writeKey(pubName, pubKey);
		        writeKey(priName, priKey);
		        if(algoAsym.getName().contains("RSA")){
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
		        }else if (algoAsym.getName().contains("DSA")) {
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

		/** KeyFactory for DSA RSA */
		private static KeyFactory getKeyFactory(AlgoAsym algoAsym) {
			KeyFactory keyFactory = null;
			try {
	            keyFactory = KeyFactory.getInstance(algoAsym.getName());
            } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
            }
			return keyFactory;
        }

		@SuppressWarnings("unchecked")
        public static <T> T readKey(Class<T> clz, InputStream is) {
			T key = null;
		    ObjectInputStream ois = null;
		    try {
		        ois = new ObjectInputStream(is);
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

		public static void writeKey(String path, Object object) {
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
	            result = new String(data, Utils.Charsets.uft8);
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

	public static class AlgoBuilder {

		/**
		 * 对称加密
		 * algorithm
		 * @param algoSym -> algorithm
		 */
		public static AlgoSymBuild build(AlgoSym algoSym){
			return new AlgoSymBuild(algoSym);
		}
		/**
		 * 对称加密
		 * algorithm/mode/padding
		 * @param algoSym -> algorithm
		 * @param mode -> mode
		 * @param padding -> padding
		 */
		public static AlgoSymBuild build(AlgoSym algoSym, AlgoSymMode mode, AlgoSymPadding padding){
			return new AlgoSymBuild(algoSym, mode, padding);
		}
		/**
		 * 对称加密
		 * algorithm/CBC/padding
		 * @param algoSym -> algorithm
		 * @param padding -> padding
		 * @param iv  IvParameterSpec
		 */
		public static AlgoSymBuild build(AlgoSym algoSym, AlgoSymPadding padding, String iv){
			return new AlgoSymBuild(algoSym, padding, iv);
		}
		/**
		 * 非对称加密
		 * algorithm
		 * @param algoSym -> algorithm
		 */
		public static AlgoAsymBuild build(AlgoAsym algoAsym){
			return new AlgoAsymBuild(algoAsym);
		}
		/**
		 * 非对称加密
		 * algorithm/mode/padding
		 * @param algoAsym -> algorithm
		 * @param mode -> mode
		 * @param padding -> padding
		 */
		public static AlgoAsymBuild build(AlgoAsym algoAsym, AlgoAsymMode mode, AlgoAsymPadding padding){
			return new AlgoAsymBuild(algoAsym, mode, padding);
		}
		/**
		 * 非对称加密
		 * algorithm/CBC/padding
		 * @param algoAsym -> algorithm
		 * @param padding -> padding
		 * @param iv  IvParameterSpec
		 */
		public static AlgoAsymBuild build(AlgoAsym algoAsym, AlgoAsymPadding padding, String iv){
			return new AlgoAsymBuild(algoAsym, padding, iv);
		}

		protected static class AlgoBuild {
			private static final String ivSpec = "1234567890123456";
			private static final String ivDESede = "12345678";
			protected String keyAlgo;
			protected String cipherAlgo;
			protected int keySize;
			protected boolean isCBC;
			protected boolean isNoPadding;
			protected String iv;

			private AlgoBuild(String algo, String cipherAlgo, int keySize, boolean isCBC, boolean isNoPadding, String iv) {
				this.keyAlgo = algo;
				this.cipherAlgo = cipherAlgo;
				this.keySize = keySize;
				this.isCBC = isCBC;
				this.isNoPadding = isNoPadding;
				this.iv = iv;
			}
			private AlgoBuild(String algo, int keySize) {
				this(algo, algo, keySize, false, false, null);
			}
			private AlgoBuild(String algo, String mode, String padding, int keySize) {
				this(algo,
						algo + "/" + mode + "/" + padding,
						keySize,
						mode.equals(AlgoSymMode.CBC.name()) ? true : false,
						padding.equals(AlgoSymPadding.NoPadding.name()) ? true : false,
						algo.contains(AlgoSym.DES.name()) ? ivDESede : ivSpec);
			}
			private AlgoBuild(String algo, String padding, int keySize, String iv) {
				this(algo,
						algo + "/" + AlgoSymMode.CBC.name() + "/" + padding,
						keySize,
						true,
						padding.equals(AlgoSymPadding.NoPadding.name()) ? true : false,
						iv);
			}

			public int getKeySize() {
				return keySize;
			}
			public String getKeyAlgo() {
				return keyAlgo;
			}
			public String getCipherAlgo() {
				return cipherAlgo;
			}
			public boolean isCBC() {
				return isCBC;
			}
			public boolean isNoPadding() {
				return isNoPadding;
			}
			public String getIv() {
				return iv;
			}
		}
		public static class AlgoSymBuild extends AlgoBuild {
			private AlgoSym algoSym;

			private AlgoSymBuild(AlgoSym algoSym) {
				super(algoSym.getName(), algoSym.getKeySize());
				this.algoSym = algoSym;
			}
			private AlgoSymBuild(AlgoSym algoSym, AlgoSymMode mode, AlgoSymPadding padding) {
				super(algoSym.getName(), mode.name(), padding.name(), algoSym.getKeySize());
				this.algoSym = algoSym;
			}
			private AlgoSymBuild(AlgoSym algoSym, AlgoSymPadding padding, String iv) {
				super(algoSym.getName(), padding.name(), algoSym.getKeySize(), iv);
				this.algoSym = algoSym;
			}
			public AlgoSym getAlgoSym() {
				return algoSym;
			}
		}

		public static class AlgoAsymBuild extends AlgoBuild{
			private AlgoAsym algoAsym;

			private AlgoAsymBuild(AlgoAsym algoAsym) {
				super(algoAsym.getName(), algoAsym.getKeySize());
				this.algoAsym = algoAsym;
			}
			private AlgoAsymBuild(AlgoAsym algoAsym, AlgoAsymMode mode, AlgoAsymPadding padding) {
				super(algoAsym.getName(), mode.name(), padding.name(), algoAsym.getKeySize());
				this.algoAsym = algoAsym;
			}
			private AlgoAsymBuild(AlgoAsym algoAsym, AlgoAsymPadding padding, String iv) {
				super(algoAsym.getName(), padding.name(), algoAsym.getKeySize(), iv);
				this.algoAsym = algoAsym;
			}
			public AlgoAsym getAlgoAsym() {
				return algoAsym;
			}
		}
	}

	/** 对称加密 Symmetric Algorithm*/
	public static class AlgoSymEnum {
		/** 对称加密 -> AES DES DESede */
		public enum AlgoSym {
			/** 对称加密  AES -> Advanced Encryption Standard*/
			AES("AES", 128),
			/** 对称加密 DES -> Digital Encryption Standard */
			DES("DES", 56),
			/** 对称加密 3DES(TripleDES) -> Triple Data Encryption Algorithm */
			DESede("DESede", 168);

			private AlgoSym(String name, int keySize) {
				this.name = name;
				this.keySize = keySize;
			}
			private String name;
			private int keySize;
			public String getName() {
				return name;
			}
			public int getKeySize() {
				return keySize;
			}
		}

		/** Algorithm Mode
		 * 	<pre>
		 *  EBC 电子密码本模式
		 *  CBC 加密分组链接模式
		 *  CFB 加密反馈模式
		 *  OFB 输出反馈模式
		 *  CIR 计数模式
		 *  <pre>
		 */
		public enum AlgoSymMode {
			ECB, CBC;// CFB, OFB, CIR;
		}

		/** Algorithm Padding
		 * 	<pre>
		 *  NoPadding
		 *  ZerosPadding
		 *  PKCS5Padding
		 *  <pre>
		 */
		public enum AlgoSymPadding {
			NoPadding, ZerosPadding, PKCS5Padding;
		}
	}

	/** 非对称加密 Non symmetric Algorithm*/
	public static class AlgoAsymEnum {
		/** 非对称加密 -> AES DES DESede */
		public enum AlgoAsym {
			RSA("RSA", 1024, false);
			/** 非对称加密(数字签名的变种) DSA 私钥加密，公钥解密  Digital Signature Algorithm*/
//			DSA("DSA", 1024, false);

			private AlgoAsym(String name, int keySize, boolean isSym) {
				this.name = name;
				this.keySize = keySize;
				this.isSym = isSym;
			}
			private String name;
			private int keySize;
			private boolean isSym;
			public String getName() {
				return name;
			}
			public int getKeySize() {
				return keySize;
			}
			public boolean isSym() {
				return isSym;
			}
		}

		/** Algorithm Mode
		 * 	<pre>
		 *  EBC 电子密码本模式
		 *  <pre>
		 */
		public enum AlgoAsymMode {
			ECB;
		}

		/** Algorithm Padding
		 * 	<pre>
		 *  PKCS1Padding
		 *  OAEPWithSHA-1AndMGF1Padding
		 *  OAEPWithSHA-256AndMGF1Padding
		 *  <pre>
		 */
		public enum AlgoAsymPadding {
			PKCS1Padding, OAEPWithSHA1AndMGF1Padding, OAEPWithSHA256AndMGF1Padding;
		}
	}

	public static class CryptBase {
		protected byte[] data;
		protected CryptBase (byte[] data){
			this.data = data;
		}
	}

	public static class Encrypt {

		private byte[] data;
		private static byte[] cbcPadding(byte[] data) {
			int len = data.length;
			if(len % 16 == 0) return data;
            /* 计算补0后的长度 */
            while(len % 16 != 0) len++;
            byte[] result = new byte[len];
            /* 在最后补0 */
            for (int i = 0; i < len; ++i) {
                if (i < data.length) {
                	result[i] = data[i];
                } else {
                	result[i] = 0;
                }
            }
            return result;
		}

		private Encrypt(byte[] data){
			this.data = data;
		}
		public static Encrypt of(byte[] data) {
	        return new Encrypt(data);
        }
		public static Encrypt of(String data) {
	        return new Encrypt(data.getBytes());
        }

		/** base encrypt */
		private static byte[] encrypt(byte[] data, AlgoBuild algo, Key key) {
			byte[] result = null;
			try {
                Cipher cipher = Cipher.getInstance(algo.getCipherAlgo());
                if(algo.isCBC()){
                	checkIV(algo);
                	cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(algo.getIv().getBytes()));
                }else {
                	cipher.init(Cipher.ENCRYPT_MODE, key);
				}
                if(algo.isNoPadding()) data = cbcPadding(data);
                result = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}

		/** encrypt by auto generate key */
		public Encrypt encrypt(AlgoSymBuild algoSym) {
			data = encrypt(data, algoSym, KeyUtils.genSecretKey(algoSym.getAlgoSym(), DigestUtils.sha256Hex(FIXED)));
			return this;
        }

		/** encrypt by byte[] key */
		public Encrypt encrypt(AlgoSymBuild algoSym, byte[] key) {
			data = encrypt(data, algoSym, KeyUtils.getSecretKey(algoSym.getAlgoSym(), key));
			return this;
        }

		/** encrypt by base64 key */
		public Encrypt encrypt(AlgoSymBuild algoSym, String base64Key) {
			encrypt(algoSym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
        }

		/** encrypt by key */
		public Encrypt encrypt(AlgoBuild algoSym, Key key) {
			data = encrypt(data, algoSym, key);
			return this;
        }

		/** encrypt by byte[] key */
		public Encrypt encrypt(AlgoAsymBuild algoAsym, byte[] key) {
			data = encrypt(data, algoAsym, KeyUtils.getPublicKey(algoAsym.getAlgoAsym(), key));
			return this;
        }

		/** encrypt by base64 key */
		public Encrypt encrypt(AlgoAsymBuild algoAsym, String base64Key) {
			encrypt(algoAsym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
        }

		/** return byte[] */
		public byte[] toByte() {
	        return data;
        }

		/** return byte[] by encode BigInteger-16 to byte[]*/
		public byte[] toHex() {
			return toHexStr().getBytes();
        }

		/** return string by encode BigInteger-16*/
		public String toHexStr() {
			return Encode.encodeRadixStr(data, 16);
        }

		/** return byte[] by encode base64 */
		public byte[] toBase64() {
			return Encode.encodeBase64(data);
        }

		/** return string by encode base64 */
		public String toBase64Str() {
			return Encode.encodeBase64Str(data);
		}
	}

	public static class Decrypt {
		private byte[] data;
		private Decrypt (byte[] data){
			this.data = data;
		}

		public static Decrypt of(byte[] data) {
	        return new Decrypt(data);
        }
		public static Decrypt of(String data) {
	        return new Decrypt(Decode.decodeBase64(data.getBytes()));
        }

		/** base encrypt */
		private static byte[] decrypt(byte[] data, AlgoBuild algo, Key key) {
			byte[] result = null;
			try {
                Cipher cipher = Cipher.getInstance(algo.getCipherAlgo());
                if(algo.isCBC()){
                	checkIV(algo);
                	cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(algo.getIv().getBytes()));
                }else {
                	cipher.init(Cipher.DECRYPT_MODE, key);
				}
                result = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}

		/** decrypt by auto generate key */
		public Decrypt decrypt(AlgoSymBuild algoSym) {
			data = decrypt(data, algoSym, KeyUtils.genSecretKey(algoSym.getAlgoSym(), DigestUtils.sha256Hex(FIXED)));
			return this;
        }

		/** encrypt by byte[] key */
		public Decrypt decrypt(AlgoSymBuild algoSym, byte[] key) {
			SecretKeySpec secretKey = new SecretKeySpec(key, algoSym.getKeyAlgo());
			data = decrypt(data, algoSym, secretKey);
			return this;
        }

		/** encrypt by base64 key */
		public Decrypt decrypt(AlgoSymBuild algoSym, String base64Key) {
			decrypt(algoSym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
        }

		/** encrypt by key */
		public Decrypt decrypt(AlgoBuild algoSym, Key key) {
			data = decrypt(data, algoSym, key);
			return this;
        }

		/** encrypt by byte[] key */
		public Decrypt decrypt(AlgoAsymBuild algoAsym, byte[] key) {
			data = decrypt(data, algoAsym, KeyUtils.getPrivateKey(algoAsym.getAlgoAsym(), key));
			return this;
        }

		/** encrypt by base64 key */
		public Decrypt decrypt(AlgoAsymBuild algoAsym, String base64Key) {
			decrypt(algoAsym, Decode.decodeBase64(base64Key.getBytes()));
			return this;
		}

		public byte[] toByte() {
	        return data;
        }
		public String toStr() {
			return StringUtils.newStringUtf8(data).trim();
        }

	}

	public static class Signing {
		private byte[] data;
		private Signing (byte[] data){
			this.data = data;
		}
		public static Signing of(byte[] data) {
	        return new Signing(data);
        }
		public static Signing of(String data) {
	        return new Signing(Decode.decodeBase64(data.getBytes()));
        }
		/** base signing encrypt */
		public String sign(PrivateKey privateKey) {
			byte[] result = null;
            try {
                Signature sign = Signature.getInstance(privateKey.getAlgorithm());
                sign.initSign(privateKey);
                sign.update(data);
                result = sign.sign();
            } catch (Exception e) {
                e.printStackTrace();
            }
			return Encode.encodeBase64Str(result);
		}

		/** base signing encrypt */
		public boolean verify(String sign, PublicKey publicKey) {
			boolean result = false;
            try {
                Signature signa = Signature.getInstance(publicKey.getAlgorithm());
                signa.initVerify(publicKey);
                signa.update(data);
                result = signa.verify(Decode.decodeBase64(sign.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
			return result;
		}
	}


	private static void checkIV(AlgoBuild algo){
		if(!algo.getKeyAlgo().contains(AlgoSym.DES.name()) && algo.getIv().length() != 16){
    		logger.error("The iv must be 16 bits");
    	}else if (algo.getKeyAlgo().contains(AlgoSym.DES.name()) && algo.getIv().length() != 8) {
    		logger.error("The iv must be 8 bits");
		}
	}

}
