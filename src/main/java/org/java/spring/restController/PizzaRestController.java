package org.java.spring.restController;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizza")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex(Model model,
			@RequestParam(name = "searchValue", required = false) String searchValue) {

		List<Pizza> pizze = searchValue == null ? pizzaService.findAll() : pizzaService.findByName(searchValue);

		model.addAttribute("searchValue", searchValue);
		model.addAttribute("pizze", pizze);

		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPiza(@PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);

		if (pizza == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
		pizzaService.save(pizza);

		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

	@PutMapping("{id}/edit")
	public ResponseEntity<Pizza> editPizza(@PathVariable int id, @RequestBody Pizza nuovaPizza) {
		Pizza pizza = pizzaService.findById(id);
		pizza.setName(nuovaPizza.getName());
		pizza.setDescrizione(nuovaPizza.getDescrizione());
		pizza.setFoto(nuovaPizza.getFoto());
		pizza.setPrezzo(nuovaPizza.getPrezzo());
		
		pizzaService.save(pizza);
		
		return new ResponseEntity<Pizza>(pizza,HttpStatus.OK);
	}
}
