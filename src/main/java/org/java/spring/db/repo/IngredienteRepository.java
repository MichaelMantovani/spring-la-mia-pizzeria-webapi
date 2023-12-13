package org.java.spring.db.repo;

import org.java.spring.db.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}