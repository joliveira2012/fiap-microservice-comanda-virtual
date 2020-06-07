package br.com.fiap.microservice.comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.microservice.comanda.entity.Produtos;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {

}
