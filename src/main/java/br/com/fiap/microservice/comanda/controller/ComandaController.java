package br.com.fiap.microservice.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.microservice.comanda.dto.ComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateComandaDTO;
import br.com.fiap.microservice.comanda.service.IComandaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/comanda")
public class ComandaController {

	@Autowired
	private IComandaService comandaService;

	@GetMapping
	public ResponseEntity<List<ComandaDTO>> findAll() {
		return ResponseEntity.ok(comandaService.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<ComandaDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(comandaService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ComandaDTO> create(@RequestBody CreateComandaDTO createcomandaDTO,
			UriComponentsBuilder builder) {
		return new ResponseEntity<>(comandaService.create(createcomandaDTO), HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		comandaService.delete(id);
	}

}
