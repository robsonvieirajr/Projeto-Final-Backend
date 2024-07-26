package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.PrecipitacaoVO;

public interface PrecipitacaoRepository extends JpaRepository<PrecipitacaoVO, Long> {
}