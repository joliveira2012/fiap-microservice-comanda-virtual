package br.com.fiap.microservice.comanda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.fiap.microservice.comanda.dto.ComandaDTO;
import br.com.fiap.microservice.comanda.dto.CreateComandaDTO;
import br.com.fiap.microservice.comanda.dto.ProdutoDTO;

@Entity
@Table(name = "comanda")
public class Comanda implements Serializable {

	private static final long serialVersionUID = 6650254311018170412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_compra")
	private Date dataCompra;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Produtos> produtos = new ArrayList<>(); 

	public Comanda() {
	}
	
	public Comanda(CreateComandaDTO createComandaDTO) {
		this.dataCompra = createComandaDTO.getDataCompra();
	}
	
	public Comanda(ComandaDTO comandaDTO) {
		this.id = comandaDTO.getId();
		this.dataCompra = comandaDTO.getDataCompra();
		this.produtos = converterProdutos(comandaDTO.getProdutos());
	}
	
	private List<Produtos> converterProdutos(List<ProdutoDTO> listProduto) {
		if (listProduto.size() > 0) {
			List<Produtos> listaP = new ArrayList<Produtos>();
			for (ProdutoDTO endereco : listProduto) {
				Produtos dto = new Produtos(endereco);
				listaP.add(dto);
			}
			return listaP;
		} else {
			return new ArrayList<Produtos>();
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

}
