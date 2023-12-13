package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredienteService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class IngredienteController {

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/ingredienti")
	public String indexIngredienti(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "indexIngredienti";
	}

	@PostMapping("/ingredienti")
	public String addIngrediente(@ModelAttribute Ingrediente ingrediente) {
		ingredienteService.save(ingrediente);
		return "redirect:/indexIngredienti";
	}

	@GetMapping("/ingredienti/create")
	public String createIngredienti(Model model) {

		model.addAttribute("ingrediente", new Ingrediente());

		return "ingredienteForm";
	}

	@PostMapping("/ingredienti/create")
	public String storeIngredient(Model model, @Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("ingrediene", ingrediente);

			return "ingredienteForm";
		}

		ingredienteService.save(ingrediente);

		return "redirect:/ingredienti";
	}

	@PostMapping("/ingredienti/delete/{id}")
	public String deleteIngredient(@PathVariable int id) {

		Ingrediente ingrediente = ingredienteService.findById(id);

		List<Pizza> ingredientePizza = ingrediente.getPizzas();
		ingredientePizza.forEach(pizza -> {

			pizza.getIngredienti().remove(ingrediente);
			pizzaService.save(pizza);
		});

		ingredienteService.delete(ingrediente);

		return "redirect:/ingredienti";
	}
}