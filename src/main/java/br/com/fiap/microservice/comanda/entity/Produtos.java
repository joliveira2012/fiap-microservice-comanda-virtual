package br.com.fiap.microservice.comanda.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.microservice.comanda.dto.CreateProdutoDTO;
import br.com.fiap.microservice.comanda.dto.ProdutoDTO;

@Entity
@Table(name = "produtos")
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "preco")
	private BigDecimal preco;

	public Produtos() {
	}

	public Produtos(CreateProdutoDTO createProdutoDTO) {
		this.nome = createProdutoDTO.getNome();
		this.quantidade = createProdutoDTO.getQuantidade();
		this.preco = createProdutoDTO.getPreco();
	}
	
	public Produtos(ProdutoDTO ProdutoDTO) {
		this.id = ProdutoDTO.getId();
		this.nome = ProdutoDTO.getNome();
		this.quantidade = ProdutoDTO.getQuantidade();
		this.preco = ProdutoDTO.getPreco();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
