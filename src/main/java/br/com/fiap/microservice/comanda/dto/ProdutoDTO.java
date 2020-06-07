package br.com.fiap.microservice.comanda.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.fiap.microservice.comanda.entity.Produtos;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 3245914611701123051L;

	private Integer id;

	private String nome;

	private Integer quantidade;

	private BigDecimal preco;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Integer id, String nome, Integer quantidade, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;

	}

	public ProdutoDTO(CreateProdutoDTO produtoDTO, Integer id) {
		this.id = id;
		this.nome = produtoDTO.getNome();
		this.quantidade = produtoDTO.getQuantidade();
		this.preco = produtoDTO.getPreco();
	}

	public ProdutoDTO(Produtos produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.quantidade = produto.getQuantidade();
		this.preco = produto.getPreco();
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
