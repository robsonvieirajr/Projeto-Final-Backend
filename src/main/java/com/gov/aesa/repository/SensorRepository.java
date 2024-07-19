package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.SensorVO;

public interface SensorRepository extends JpaRepository<SensorVO, Long> {
}
