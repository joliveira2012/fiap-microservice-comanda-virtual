package br.com.fiap.microserve.comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.microserve.comanda.entity.Produtos;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {

}
