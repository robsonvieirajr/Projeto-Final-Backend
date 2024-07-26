package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.VazaoVO;

public interface VazaoRepository extends JpaRepository<VazaoVO, Long> {
}