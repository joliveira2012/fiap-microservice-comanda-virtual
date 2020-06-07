package br.com.fiap.microservice.comanda.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.microservice.comanda.dto.ComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateProdutoDTO;
import br.com.fiap.microservice.comanda.entity.Comanda;
import br.com.fiap.microservice.comanda.repository.ComandaRepository;
import br.com.fiap.microservice.comanda.repository.ProdutoRepository;
import br.com.fiap.microservice.comanda.service.IComandaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComandaServiceImplTest {

	@Mock
	private static ProdutoRepository produtoRepository;

	@Mock
	private static ComandaRepository comandaRepository;

	@InjectMocks
	private static IComandaService comandaService = new ComandaServiceImpl(comandaRepository, produtoRepository);

	@Test
	public void test_find_all() {
		List<Comanda> comandaList = new ArrayList<Comanda>();
		comandaList.add(new Comanda());
		comandaList.add(new Comanda());
		comandaList.add(new Comanda());

		when(comandaRepository.findAll()).thenReturn(comandaList);

		assertEquals(comandaService.findAll().size(), 3);
	}

	@Test
	public void test_find_by_id() {

		Optional<Comanda> comandaOptional = Optional.of(gerarComanda());

		when(comandaRepository.findById(1)).thenReturn(comandaOptional);

		assertEquals(comandaService.findById(1).getId(), gerarComanda().getId());
	}

	@Test
	public void test_create() {
		when(comandaRepository.save(Mockito.any(Comanda.class))).thenReturn(gerarComanda());
		ComandaDTO comandaRetorno = comandaService.create(gerarCreateComandaDTO());

		assertNotNull(comandaRetorno);
		assertEquals(comandaRetorno.getId(), gerarComanda().getId());
	}

	@Test
	public void test_delete() {
		comandaRepository.deleteById(any());
		verify(comandaRepository, times(1)).deleteById(any());
	}

	public Comanda gerarComanda() {
		Comanda comanda = new Comanda();
		comanda.setId(999999999);
		comanda.setDataCompra(new Date());
		return comanda;
	}

	public CreateComandaDTO gerarCreateComandaDTO() {
		return new CreateComandaDTO(new Date(), new ArrayList<CreateProdutoDTO>());
	}

}
