package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gov.aesa.model.AcudeVO;
import com.gov.aesa.model.ChuvaVO;

@Repository
public interface ChuvaRepository extends JpaRepository<ChuvaVO, Long> {
}
