package br.com.fiap.microservice.comanda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateComandaDTO implements Serializable {

	private static final long serialVersionUID = -3461901210133976525L;

	private Date dataCompra;

	private List<CreateProdutoDTO> produtos = new ArrayList<CreateProdutoDTO>();

	public CreateComandaDTO() {
	}

	public CreateComandaDTO(Date dataCompra, List<CreateProdutoDTO> produtos) {
		this.dataCompra = dataCompra;
		this.produtos = produtos;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public List<CreateProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<CreateProdutoDTO> produtos) {
		this.produtos = produtos;
	}

}
