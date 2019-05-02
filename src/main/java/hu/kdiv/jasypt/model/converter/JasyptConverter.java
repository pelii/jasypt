package hu.kdiv.jasypt.model.converter;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JasyptConverter {
	@Value("${jasypt.encryptor.password}")
	private String password;

	@Value("${jasypt.encryptor.algorithm}")
	private String algorithm;

	private StandardPBEStringEncryptor standardPBEStringEncryptor;
	private BasicBinaryEncryptor basicBinaryEncryptor;

	public byte[] encrypt(byte[] data) {
		return getBasicBinaryEncryptor().encrypt(data);
	}

	public byte[] decrypt(byte[] data) {
		return getBasicBinaryEncryptor().decrypt(data);
	}

	public String encrypt(String data) {
		return getStandardPBEStringEncryptor().encrypt(data);
	}

	public String decrypt(String data) {
		return getStandardPBEStringEncryptor().decrypt(data);
	}

	public StandardPBEStringEncryptor getStandardPBEStringEncryptor() {
		if (standardPBEStringEncryptor == null) {
			standardPBEStringEncryptor = new StandardPBEStringEncryptor();
			standardPBEStringEncryptor.setPassword(password);
			standardPBEStringEncryptor.setAlgorithm(algorithm);
		}

		return standardPBEStringEncryptor;
	}

	public BasicBinaryEncryptor getBasicBinaryEncryptor() {
		if (basicBinaryEncryptor == null) {
			basicBinaryEncryptor = new BasicBinaryEncryptor();
			basicBinaryEncryptor.setPassword(password);
		}

		return basicBinaryEncryptor;
	}

}
