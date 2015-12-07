package com.webapp.utils.codecs;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.webapp.utils.codec.CodecUtils.AlgoAsymEnum.AlgoAsym;
import com.webapp.utils.codec.CodecUtils.AlgoAsymEnum.AlgoAsymMode;
import com.webapp.utils.codec.CodecUtils.AlgoAsymEnum.AlgoAsymPadding;
import com.webapp.utils.codec.CodecUtils.AlgoBuilder;
import com.webapp.utils.codec.CodecUtils.AlgoBuilder.AlgoAsymBuild;
import com.webapp.utils.codec.CodecUtils.AlgoBuilder.AlgoSymBuild;
import com.webapp.utils.codec.CodecUtils.AlgoSymEnum.AlgoSym;
import com.webapp.utils.codec.CodecUtils.AlgoSymEnum.AlgoSymMode;
import com.webapp.utils.codec.CodecUtils.AlgoSymEnum.AlgoSymPadding;
import com.webapp.utils.codec.CodecUtils.Decode;
import com.webapp.utils.codec.CodecUtils.Decrypt;
import com.webapp.utils.codec.CodecUtils.Encrypt;
import com.webapp.utils.codec.CodecUtils.KeyUtils;
import com.webapp.utils.codec.CodecUtils.Signing;

public class CodecUtilsTest {

	String data = "helloworld helloffff";
	String seed = "kingkingkingking2";

	public static void main(String[] args) {
	}
	@Test
	public void encryptAlgoSym() throws Exception {
		String encrypt;
		String decrypt;
		List<AlgoSymBuild> list = new ArrayList<>();
		Map<String, List<AlgoSymBuild>> map = new HashMap<>();
		map.put(AlgoSym.AES.name(), Arrays.asList(
				AlgoBuilder.build(AlgoSym.AES),
				AlgoBuilder.build(AlgoSym.AES, AlgoSymMode.CBC, AlgoSymPadding.NoPadding),
				AlgoBuilder.build(AlgoSym.AES, AlgoSymMode.CBC, AlgoSymPadding.PKCS5Padding),
				AlgoBuilder.build(AlgoSym.AES, AlgoSymMode.ECB, AlgoSymPadding.NoPadding),
				AlgoBuilder.build(AlgoSym.AES, AlgoSymMode.ECB, AlgoSymPadding.PKCS5Padding)
		));
		map.put(AlgoSym.DES.name(), Arrays.asList(
				AlgoBuilder.build(AlgoSym.DES),
				AlgoBuilder.build(AlgoSym.DES, AlgoSymMode.CBC, AlgoSymPadding.NoPadding),
				AlgoBuilder.build(AlgoSym.DES, AlgoSymMode.CBC, AlgoSymPadding.PKCS5Padding),
				AlgoBuilder.build(AlgoSym.DES, AlgoSymMode.ECB, AlgoSymPadding.NoPadding),
				AlgoBuilder.build(AlgoSym.DES, AlgoSymMode.ECB, AlgoSymPadding.PKCS5Padding)
		));
		map.put(AlgoSym.DESede.name(), Arrays.asList(
				AlgoBuilder.build(AlgoSym.DESede),
				AlgoBuilder.build(AlgoSym.DESede, AlgoSymMode.CBC, AlgoSymPadding.NoPadding),
				AlgoBuilder.build(AlgoSym.DESede, AlgoSymMode.CBC, AlgoSymPadding.PKCS5Padding),
				AlgoBuilder.build(AlgoSym.DESede, AlgoSymMode.ECB, AlgoSymPadding.NoPadding),
				AlgoBuilder.build(AlgoSym.DESede, AlgoSymMode.ECB, AlgoSymPadding.PKCS5Padding)
		));

		list = map.get(AlgoSym.AES.name());
		for(AlgoSymBuild algoAES : list){
			//AES
			encrypt = Encrypt.of(data).encrypt(algoAES).toBase64Str();
			decrypt = Decrypt.of(encrypt).decrypt(algoAES).toStr();
			System.out.println(encrypt + " -> " + decrypt);

			//AES Key
			encrypt = Encrypt.of(data).encrypt(algoAES, KeyUtils.genSecretKey(algoAES.getAlgoSym(), seed)).toBase64Str();
			decrypt = Decrypt.of(encrypt).decrypt(algoAES, KeyUtils.genSecretKey(algoAES.getAlgoSym(), seed)).toStr();
			System.out.println(encrypt + " -> " + decrypt);
		}


		list = map.get(AlgoSym.AES.name());
		for(AlgoSymBuild algoDES : list){
			//DES
			encrypt = Encrypt.of(data).encrypt(algoDES).toBase64Str();
			decrypt = Decrypt.of(encrypt).decrypt(algoDES).toStr();
			System.out.println(encrypt + " -> " + decrypt);

			//DES Key
			encrypt = Encrypt.of(data).encrypt(algoDES, KeyUtils.genSecretKey(algoDES.getAlgoSym(), seed)).toBase64Str();
			decrypt = Decrypt.of(encrypt).decrypt(algoDES, KeyUtils.genSecretKey(algoDES.getAlgoSym(), seed)).toStr();
			System.out.println(encrypt + " -> " + decrypt);
		}

		list = map.get(AlgoSym.AES.name());
		for(AlgoSymBuild algoDESede : list){
			//DESede
			encrypt = Encrypt.of(data).encrypt(algoDESede).toBase64Str();
			decrypt = Decrypt.of(encrypt).decrypt(algoDESede).toStr();
			System.out.println(encrypt + " -> " + decrypt);

			//DESede Key
			encrypt = Encrypt.of(data).encrypt(algoDESede, KeyUtils.genSecretKey(algoDESede.getAlgoSym(), seed)).toBase64Str();
			decrypt = Decrypt.of(encrypt).decrypt(algoDESede, KeyUtils.genSecretKey(algoDESede.getAlgoSym(), seed)).toStr();
			System.out.println(encrypt + " -> " + decrypt);
		}

	}

	@Test
    public void encryptAlgoAsym() throws Exception {
		String encrypt;
		String decrypt;
		PublicKey pubKey;
		PrivateKey priKey;

		AlgoAsymBuild algoRSA = AlgoBuilder.build(AlgoAsym.RSA, AlgoAsymMode.ECB, AlgoAsymPadding.PKCS1Padding);
//		KeyUtils.genKeyPair(AlgoAsym.RSA_128);
		pubKey = KeyUtils.readKey(PublicKey.class, new FileInputStream("RSA_128_public_key.cer"));
		priKey = KeyUtils.readKey(PrivateKey.class, new FileInputStream("RSA_128_private_key.pfx"));
		encrypt = Encrypt.of(data).encrypt(algoRSA, pubKey).toBase64Str();
		decrypt = Decrypt.of(encrypt).decrypt(algoRSA, priKey).toStr();
		System.out.println(encrypt + " -> " + decrypt);

//		KeyUtils.genKeyPair(AlgoAsym.DSA_128);
		pubKey = KeyUtils.readKey(PublicKey.class, new FileInputStream("DSA_128_public_key.cer"));
		priKey = KeyUtils.readKey(PrivateKey.class, new FileInputStream("DSA_128_private_key.pfx"));
		String sign = Signing.of(data).sign(priKey);
		boolean isSign = Signing.of(data).verify(sign, pubKey);
		System.out.println(sign + " -> " + isSign);
    }

}
