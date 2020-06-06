package br.com.fiap.microserve.comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.microserve.comanda.entity.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Integer> {

}
