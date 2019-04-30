package hu.kdiv.jasypt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.kdiv.jasypt.model.TestEntity;
import hu.kdiv.jasypt.model.dto.TestDto;
import hu.kdiv.jasypt.repository.TestRepository;

@Service
public class TestService {
	@Autowired
	private TestRepository testRepository;

	public void saveTest(TestDto testDto) {
		testRepository.save(testDto.convertEntity());
	}

	public TestDto readById(Long id) {
		Optional<TestEntity> testEntity = testRepository.findById(id);
		if (testEntity.isEmpty()) {
			return new TestDto();
		}

		return TestDto.convertFormEntity(testEntity.get());
	}

	public void saveFile(byte[] data) {
		testRepository.save(TestEntity.builder().data(data).build());
	}
}
