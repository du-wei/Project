package com.webapp.utils.codecs;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Test;

import com.webapp.utils.codec.CodeUtils.AlgoSym;
import com.webapp.utils.codec.CodeUtils.Decode;
import com.webapp.utils.codec.CodeUtils.Decrypt;
import com.webapp.utils.codec.CodeUtils.Encrypt;
import com.webapp.utils.codec.CodeUtils.KeyUtils;

public class CodeUtilsTest {

	String data = "hello world";
	String seed = "king";

	public static void main(String[] args) {
    }

	@Test
	public void encryptAlgoSym() throws Exception {

		String encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.AES_128).toBase64Str();
		String decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoSym.AES_128).toStr();
		System.out.println(encrypt + " -> " + decrypt);


//		encrypt = Encrypt.of(data.getBytes()).encrypt(KeyUtils.getSecretKey(AlgoSym.AES_128, seed)).toBase64Str(true);
//		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(KeyUtils.getSecretKey(AlgoSym.AES_128, seed)).toStr();
//		System.out.println(encrypt + " -> " + decrypt);
//
//
//		encrypt = Encrypt.of(data.getBytes()).encrypt(KeyUtils.getSecretKey(AlgoSym.DES, seed)).toBase64Str(true);
//		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(KeyUtils.getSecretKey(AlgoSym.DES, seed)).toStr();
//		System.out.println(encrypt + " -> " + decrypt);
//
//		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.DES).toBase64Str(true);
//		System.out.println(encrypt);
//
//
//		encrypt = Encrypt.of(data.getBytes()).encrypt(KeyUtils.getSecretKey(AlgoSym.DESede_112, seed)).toBase64Str(true);
//		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(KeyUtils.getSecretKey(AlgoSym.DESede_112, seed)).toStr();
//		System.out.println(encrypt + " -> " + decrypt);
//		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoSym.DESede_112).toBase64Str(true);
//
//		System.out.println(encrypt);

	}

	@Test
    public void encryptAlgoAsym() throws Exception {
//		KeyUtils.genKeyPair(AlgoAsym.RSA_128);

		PublicKey pubKey = KeyUtils.readPubKey("RSA_128_public_key.cer");
		PrivateKey priKey = KeyUtils.readPriKey("RSA_128_private_key.pfx");
		String encrypt = Encrypt.of(data.getBytes()).encrypt(pubKey).toBase64Str();
		String decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(priKey).toStr();
		System.out.println(encrypt + " -> " + decrypt);

//		KeyUtils.genKeyPair(AlgoAsym.DSA_128);
//		encrypt = Encrypt.of(data.getBytes()).encrypt(AlgoAsym.DSA_128, "DSA_128_public_key.cer").toBase64Str(true);
//		decrypt = Decrypt.of(Decode.decodeBase64(encrypt.getBytes())).decrypt(AlgoAsym.DSA_128, "DSA_128_private_key.pfx").toStr();
//		System.out.println(encrypt + " -> " + decrypt);
    }

}
