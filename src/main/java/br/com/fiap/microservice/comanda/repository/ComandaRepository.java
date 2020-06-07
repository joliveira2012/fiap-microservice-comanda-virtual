package br.com.fiap.microservice.comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.microservice.comanda.entity.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Integer> {

}
