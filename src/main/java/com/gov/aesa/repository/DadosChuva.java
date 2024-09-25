package com.gov.aesa.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dadosChuva", url = "http://www.aesa.pb.gov.br/aesa-website/resources/data/volumeChuvas")
public interface DadosChuva {

	@GetMapping("/{ano}/{mes}/data.json")
	String getDadosDeChuva(@PathVariable("ano") String ano, @PathVariable("mes") String mes);
}
