package hu.kdiv.jasypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.kdiv.jasypt.model.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long>{

}
