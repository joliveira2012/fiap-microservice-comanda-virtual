package br.com.fiap.microservice.comanda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.microservice.comanda.entity.Comanda;
import br.com.fiap.microservice.comanda.entity.Produtos;

public class ComandaDTO implements Serializable {

	private static final long serialVersionUID = 734845675267121951L;

	private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date dataCompra;

	private List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();

	public ComandaDTO() {
	}

	public ComandaDTO(Integer id, Date dataCompra, List<ProdutoDTO> produtos) {
		this.id = id;
		this.dataCompra = dataCompra;
		this.produtos = produtos;
	}

	public ComandaDTO(Comanda comanda) {
		this.id = comanda.getId();
		this.dataCompra = comanda.getDataCompra();
		this.produtos = converterProdutos(comanda.getProdutos());
	}

	private List<ProdutoDTO> converterProdutos(List<Produtos> produtos) {
		if (produtos.size() > 0) {
			List<ProdutoDTO> listaProd = new ArrayList<ProdutoDTO>();
			for (Produtos prod : produtos) {
				ProdutoDTO dto = new ProdutoDTO();
				dto.setId(prod.getId());
				dto.setNome(prod.getNome());
				dto.setQuantidade(prod.getQuantidade());
				dto.setPreco(prod.getPreco());
				listaProd.add(dto);
			}
			return listaProd;
		} else {
			return new ArrayList<ProdutoDTO>();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

}
