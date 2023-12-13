package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Offerta;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OffertaService;
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
public class OffertaController {

	@Autowired
	PizzaService pizzaService;

	@Autowired
	OffertaService offertaService;

	@GetMapping("/pizza/{pizzaId}/offerta/create")
	public String createOfferta(Model model, @PathVariable int pizzaId) {

		model.addAttribute("offerta", new Offerta());
		model.addAttribute("pizza", pizzaService.findById(pizzaId));

		return "offertaForm";
	}

	@PostMapping("/pizza/{pizzaId}/offerta/create")
	public String storeOfferta(Model model, @PathVariable int pizzaId, @Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			model.addAttribute("offerta", offerta);

			return "offertaForm";
		}

		Pizza pizza = pizzaService.findById(pizzaId);
		offerta.setPizza(pizza);
		
		System.out.println(offerta);

		offertaService.save(offerta);

		return "redirect:/pizza/" + pizzaId;
	}

	@GetMapping("/offerta/edit/{id}")
	public String offertaEdit(Model model, @PathVariable int id) {

		List<Offerta> offerte = offertaService.findAll();
		model.addAttribute("offerte", offerte);

		Offerta offerta = offertaService.findById(id);
		model.addAttribute("offerta", offerta);

		return "offertaForm";
	}

	@PostMapping("/offerta/edit/{id}")
	public String offertaUpdate(Model model, @PathVariable int id, @Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("offerta", offerta);

			return "offertaForm";
		}

		Pizza pizza = pizzaService.findById(id);
		offerta.setPizza(pizza);

		offertaService.save(offerta);

		return "redirect:/pizza/" + id;

	}
}
