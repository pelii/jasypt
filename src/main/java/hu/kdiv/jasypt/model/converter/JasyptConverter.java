package hu.kdiv.jasypt.model.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JasyptConverter {
	@Value("${jasypt.encryptor.password}")
	private String password;

	@Value("${jasypt.encryptor.algorithm}")
	private String algorithm;

	public byte[] encrypt(byte[] data) {
		return null;
	}

	public byte[] decrypt(byte[] data) {
		return null;
	}

	public String encrypt(String data) {
		return null;
	}

	public String decrypt(String data) {
		return null;
	}

}
