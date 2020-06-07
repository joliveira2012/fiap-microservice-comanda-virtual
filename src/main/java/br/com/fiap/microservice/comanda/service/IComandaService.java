package br.com.fiap.microservice.comanda.service;

import java.util.List;

import br.com.fiap.microservice.comanda.dto.ComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateComandaDTO;

public interface IComandaService {

	List<ComandaDTO> findAll();
	
	ComandaDTO findById(Integer id);

	ComandaDTO create(CreateComandaDTO createComandaDTO);

	void delete(Integer id);
	
}
