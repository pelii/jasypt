package hu.kdiv.jasypt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.binary.BasicBinaryEncryptor;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Data
@Builder
@Table(name = "test")
public class TestEntity {

	@Tolerate
	public TestEntity() {
		//
	}

	public TestEntity(Long id, String text, byte[] data) {
		setText(text);
		setId(id);
		setData(data);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String text;

	@Lob
	private byte[] data;

	public void setText(String value) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("jasypt"); // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally set the algorithm
		text = encryptor.encrypt(value);
	}

	public String getText() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("jasypt"); // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally set the algorithm

		return encryptor.decrypt(text); // myText.equals(plainText)
	}

	public void setData(byte[] value) {
		BasicBinaryEncryptor encryptor = new BasicBinaryEncryptor();
		encryptor.setPassword("jasypt"); // we HAVE TO set a password
		data = encryptor.encrypt(value);
	}

	public byte[] getData() {
		BasicBinaryEncryptor encryptor = new BasicBinaryEncryptor();
		encryptor.setPassword("jasypt"); // we HAVE TO set a password
		return encryptor.decrypt(data);
	}
}
