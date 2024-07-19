package com.gov.aesa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gov.aesa.model.AcudeVO;
import com.gov.aesa.repository.AcudeRepository;

@Service
public class AcudeRN {

	@Autowired
	private AcudeRepository acudeRepository;

	public AcudeVO save(AcudeVO acude) {
		return acudeRepository.save(acude);
	}

	public List<AcudeVO> findAll() {
		return acudeRepository.findAll();
	}
}