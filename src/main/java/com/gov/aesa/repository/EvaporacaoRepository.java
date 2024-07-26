package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.EvaporacaoVO;

public interface EvaporacaoRepository extends JpaRepository<EvaporacaoVO, Long> {
}