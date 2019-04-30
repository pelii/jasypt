package hu.kdiv.jasypt.model.dto;

import hu.kdiv.jasypt.model.TestEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestDto {
	private Long id;
	private String text;
	private String uploadPath;
	private byte[] data;

	@Tolerate
	public TestDto() {
		//
	}

	public static TestDto convertFormEntity(TestEntity test) {
		return TestDto.builder()//
				.id(test.getId())//
				.text(test.getText())//
				.data(test.getData())//
				.build();
	}

	public TestEntity convertEntity() {
		return TestEntity.builder()//
				.id(getId())//
				.text(getText())//
				.data(getData())//
				.build();
	}

}
