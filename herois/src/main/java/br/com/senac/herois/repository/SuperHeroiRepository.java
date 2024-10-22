package br.com.senac.herois.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.herois.entity.SuperHeroi;

public interface SuperHeroiRepository extends JpaRepository<SuperHeroi, Integer> {

}
