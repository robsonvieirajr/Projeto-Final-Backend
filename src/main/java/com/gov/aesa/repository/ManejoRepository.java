package com.gov.aesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gov.aesa.model.ManejoVO;

public interface ManejoRepository extends JpaRepository<ManejoVO, Long> {
}
