package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.MedicaoVO;

public interface MedicaoRepository extends JpaRepository<MedicaoVO, Long> {
}
