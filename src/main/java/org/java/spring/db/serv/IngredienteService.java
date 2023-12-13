package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.repo.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

	@Autowired
	IngredienteRepository ingredienteRepository;

	public List<Ingrediente> findAll() {
		return ingredienteRepository.findAll();
	}

	public Ingrediente findById(int id) {
		return ingredienteRepository.findById(id).get();
	}

	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}

	public void delete(Ingrediente ingrediente) {

		ingredienteRepository.delete(ingrediente);
	}

}