package com.webapp.utils.codecs;

import java.security.Signature;

import javax.crypto.Cipher;

import org.junit.Test;

import com.webapp.utils.codec.CodeUtils.AlgoAsym;
import com.webapp.utils.codec.CodeUtils.AlgoSym;
import com.webapp.utils.codec.CodeUtils.Decode;
import com.webapp.utils.codec.CodeUtils.Decrypt;
import com.webapp.utils.codec.CodeUtils.Encrypt;
import com.webapp.utils.codec.CodeUtils.KeyUtils;

public class CodeUtilsTest {


	public static void main(String[] args) {
//		KeyGenerator kgen=KeyGenerator.getInstance("AES");
//		  SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
//		  sr.setSeed(seed);
//		  kgen.init(128,sr);
//		  SecretKey skey=kgen.generateKey();
//		  byte[] raw=skey.getEncoded();


//		SecureRandom secureRandom=SecureRandom.getInstance(RANDOM_ALGORITHM);
//		  secureRandom.setSeed(secret);
//		  KeyGenerator keyGenerator=KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
//		  keyGenerator.init(KEY_SIZE,secureRandom);
//		  Key key=new SecretKeySpec(keyGenerator.generateKey().getEncoded(),ENCRYPTION_ALGORITHM);
//		  Cipher cipher=Cipher.getInstance(ENCRYPTION_ALGORITHM);
//		  cipher.init(mode,key);
    }

//	/**
//	 * Make a KeyBundle with two random 256 bit keys (encryption and HMAC).
//	 * @return A KeyBundle with random keys.
//	 */
//	public static KeyBundle withRandomKeys() throws CryptoException {
//	  KeyGenerator keygen;
//	  try {
//	    keygen=KeyGenerator.getInstance(KEY_ALGORITHM_SPEC);
//	  }
//	 catch (  NoSuchAlgorithmException e) {
//	    throw new CryptoException(e);
//	  }
//	  keygen.init(KEY_SIZE);
//	  byte[] encryptionKey=keygen.generateKey().getEncoded();
//	  byte[] hmacKey=keygen.generateKey().getEncoded();
//	  return new KeyBundle(encryptionKey,hmacKey);
//	}

	@Test
	public void encrypt() throws Exception {

		String data = "hello world";
		String seed = "king";

		String encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.AES_128, seed).toBase64Str(true);
		String decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoSym.AES_128, seed).toStr();
		System.out.println(encrypt + " -> " + decrypt);
		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.AES_128).toBase64Str(true);
		System.out.println(encrypt);


		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.DES, seed).toBase64Str(true);
		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoSym.DES, seed).toStr();
		System.out.println(encrypt + " -> " + decrypt);
		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.DES).toBase64Str(true);
		System.out.println(encrypt);


		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.DESede_112, seed).toBase64Str(true);
		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoSym.DESede_112, seed).toStr();
		System.out.println(encrypt + " -> " + decrypt);
		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.DESede_112).toBase64Str(true);
		System.out.println(encrypt);

//		KeyUtils.genKeyPair(AlgoAsym.RSA_128);
		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoAsym.RSA_128, "RSA_128_public_key.cer").toBase64Str(true);
		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoAsym.RSA_128, "RSA_128_private_key.pfx").toStr();
		System.out.println(encrypt + " -> " + decrypt);

//		KeyUtils.genKeyPair(AlgoAsym.DSA_128);
//		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoAsym.DSA_128, "DSA_128_public_key.cer").toBase64Str(true);
//		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoAsym.DSA_128, "DSA_128_private_key.pfx").toStr();
//		System.out.println(encrypt + " -> " + decrypt);

		Signature.getInstance("DSA");
//		Cipher.getInstance("DSA");


	}

}
