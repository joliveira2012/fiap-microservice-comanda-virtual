package br.com.fiap.microservice.comanda.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.microservice.comanda.dto.ComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateProdutoDTO;
import br.com.fiap.microservice.comanda.service.IComandaService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ComandaControllerTest {

	@MockBean
	private IComandaService comandaService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_find_all_sucess() throws Exception {
		List<ComandaDTO> comandaDTOList = new ArrayList<ComandaDTO>();
		comandaDTOList.add(gerarComandaDTO());

		given(comandaService.findAll()).willReturn(comandaDTOList);

		mockMvc.perform(get("/v1/comanda")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());

		verify(comandaService, times(1)).findAll();
	}

	@Test
	public void test_find_by_id_sucess() throws Exception {

		given(comandaService.findById(gerarComandaDTO().getId())).willReturn(gerarComandaDTO());

		mockMvc.perform(get("/v1/comanda/{id}", 999999999)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		verify(comandaService, times(1)).findById(gerarComandaDTO().getId());
	}

	@Test
	public void test_create_sucess() throws Exception {
		when(comandaService.create(Mockito.any(CreateComandaDTO.class))).thenReturn(gerarComandaDTO());

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/comanda")
				.content(asJsonString(new CreateComandaDTO(new Date(), new ArrayList<CreateProdutoDTO>())))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		verify(comandaService, times(1)).create(any(CreateComandaDTO.class));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test_delete_sucess() throws Exception {

		doNothing().when(comandaService).delete(gerarComandaDTO().getId());

		mockMvc.perform(delete("/v1/comanda/{id}", gerarComandaDTO().getId())).andExpect(status().isNoContent());

		verify(comandaService, times(1)).delete(gerarComandaDTO().getId());

	}

	public ComandaDTO gerarComandaDTO() {
		ComandaDTO comanda = new ComandaDTO();
		comanda.setId(999999999);
		comanda.setDataCompra(new Date());
		return comanda;
	}

}
