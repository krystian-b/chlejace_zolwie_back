package com.back.chlejacezolwie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface DataRepository extends Repository <Data, Long> {

	List<Data> findAll();
	
	Optional<Data> findById(Long x);
	
}
