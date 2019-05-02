package hu.kdiv.jasypt.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import hu.kdiv.jasypt.model.converter.JasyptByteArrayConverter;
import hu.kdiv.jasypt.model.converter.JasyptStringConverter;
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

	@Convert(converter = JasyptStringConverter.class)
	private String text;

	@Lob
	@Convert(converter = JasyptByteArrayConverter.class)
	private byte[] data;
}
