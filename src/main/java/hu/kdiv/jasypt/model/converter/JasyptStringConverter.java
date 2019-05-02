package hu.kdiv.jasypt.model.converter;

import javax.persistence.AttributeConverter;

public class JasyptStringConverter extends JasyptConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return decrypt(dbData);
	}

}
