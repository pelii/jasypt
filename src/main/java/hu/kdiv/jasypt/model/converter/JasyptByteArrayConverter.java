package hu.kdiv.jasypt.model.converter;

import javax.persistence.AttributeConverter;

public class JasyptByteArrayConverter extends JasyptConverter implements AttributeConverter<byte[], byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(byte[] attribute) {
		return encrypt(attribute);
	}

	@Override
	public byte[] convertToEntityAttribute(byte[] dbData) {
		return decrypt(dbData);
	}

}
