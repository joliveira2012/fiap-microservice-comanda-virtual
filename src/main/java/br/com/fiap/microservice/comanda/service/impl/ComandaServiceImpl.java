package br.com.fiap.microservice.comanda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.microservice.comanda.dto.ComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateProdutoDTO;
import br.com.fiap.microservice.comanda.entity.Comanda;
import br.com.fiap.microservice.comanda.entity.Produtos;
import br.com.fiap.microservice.comanda.repository.ComandaRepository;
import br.com.fiap.microservice.comanda.repository.ProdutoRepository;
import br.com.fiap.microservice.comanda.service.IComandaService;

@Service
public class ComandaServiceImpl implements IComandaService {

	private ComandaRepository comandaRepository;

	private ProdutoRepository produtoRepository;

	public ComandaServiceImpl(ComandaRepository comandaRepository, ProdutoRepository produtoRepository) {
		this.comandaRepository = comandaRepository;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public List<ComandaDTO> findAll() {
		List<Comanda> comandaList = comandaRepository.findAll();
		return comandaList.stream().map(ComandaDTO::new).collect(Collectors.toList());
	}

	@Override
	public ComandaDTO findById(Integer id) {
		return new ComandaDTO(
				comandaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public ComandaDTO create(CreateComandaDTO createComandaDTO) {
		Comanda comanda = new Comanda(createComandaDTO);
		for (CreateProdutoDTO prod : createComandaDTO.getProdutos()) {
			comanda.getProdutos().add(produtoRepository.save(new Produtos(prod)));
		}
		return new ComandaDTO(comandaRepository.save(comanda));
	}

	@Override
	public void delete(Integer id) {
		comandaRepository.delete(comandaRepository.findById(id).get());

	}

}