package com.getui.push.v2.sdk.api;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class AESCoder {
	public static final String ALGORITHM = "AES/CFB128/NoPadding";
	public static final String KEY_ALGORITHM = "AES";
	private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() {
		protected synchronized Cipher initialValue() {
			try {
				return Cipher.getInstance(ALGORITHM);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};

	/**
	 * 转换密钥<br>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);

		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);

		Cipher cipher = localCipher.get();
		cipher.init(Cipher.DECRYPT_MODE, k);

		return cipher.doFinal(data);
	}

	public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
		Key k = toKey(key);
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));

		return cipher.doFinal(data);
	}

	public static byte[] decrypt(byte[] data, Key key) throws Exception {
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	public static byte[] decrypt(byte[] data, Key key, byte[] iv) throws Exception {
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.ENCRYPT_MODE, k);

		return cipher.doFinal(data);
	}

	// 带iv
	public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
		Key k = toKey(key);
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.ENCRYPT_MODE, k, new IvParameterSpec(iv));

		return cipher.doFinal(data);
	}

	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.ENCRYPT_MODE, key);

		return cipher.doFinal(data);
	}

	// 带iv
	public static byte[] encrypt(byte[] data, Key key, byte[] iv) throws Exception {
		Cipher cipher = localCipher.get();
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

		return cipher.doFinal(data);
	}

	/**
	 * （默认128bit）生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		return initKey(null);
	}

	/**
	 * (keySize 为bit长度，取值128，192，256)生成固定长度密钥 192,256需要替换jre/lib/security/
	 * *_policy.jar文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey(int keySize) throws Exception {
		return initKey(keySize, null);
	}

	/**
	 * 生成密钥
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey(Long seed) throws Exception {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(String.valueOf(seed).getBytes());
		} else {
			secureRandom = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(secureRandom);// secureRandom);

		SecretKey secretKey = kg.generateKey();

		return secretKey.getEncoded();
	}

	/**
	 * 生成固定长度的密钥()
	 * 
	 * @Title: initKey
	 */
	public static byte[] initKey(int keySize, Long seed) throws Exception {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(String.valueOf(seed).getBytes());
		} else {
			secureRandom = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(keySize, secureRandom);

		SecretKey secretKey = kg.generateKey();

		return secretKey.getEncoded();
	}

}
