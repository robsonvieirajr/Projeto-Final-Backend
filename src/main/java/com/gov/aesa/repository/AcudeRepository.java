package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.AcudeVO;

public interface AcudeRepository extends JpaRepository<AcudeVO, Long> {

	AcudeVO findByNome(String nome);
}
