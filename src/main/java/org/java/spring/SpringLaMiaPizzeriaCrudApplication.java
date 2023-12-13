package org.java.spring;

import java.time.LocalDate;
import java.util.List;

import org.java.spring.auth.db.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.Offerta;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredienteService;
import org.java.spring.db.serv.OffertaService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OffertaService offertaService;

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ingredienteService.save(new Ingrediente("Mozzarella"));
		ingredienteService.save(new Ingrediente("Pomodoro"));
		ingredienteService.save(new Ingrediente("Funghi"));
		ingredienteService.save(new Ingrediente("Prosciutto cotto"));
		ingredienteService.save(new Ingrediente("Olive nere"));
		ingredienteService.save(new Ingrediente("Carciofi"));
		ingredienteService.save(new Ingrediente("Prosciutto crudo"));

		List<Ingrediente> ingredienti = ingredienteService.findAll();

		pizzaService.save(new Pizza("Margherita", "Pomodoro,mozzarella",
				"https://images.prismic.io/eataly-us/ed3fcec7-7994-426d-a5e4-a24be5a95afd_pizza-recipe-main.jpg?auto=compress,format",
				6, ingredienti.get(0),ingredienti.get(1)));
		pizzaService.save(new Pizza("4 stagioni", "Pomodoro,mozzarella,funghi, prosciutto cotto, olive nere,carciofi",
				"https://i0.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2016/07/3017_Pizza.jpg?resize=895%2C616&ssl=1",
				10, ingredienti.get(0),ingredienti.get(1),ingredienti.get(2),ingredienti.get(3),ingredienti.get(4), ingredienti.get(5)));
		pizzaService.save(new Pizza("Crudo", "Mozzarella,pomoro,prosciutto crudo",
				"https://images-tastehub.mdlzapps.cloud/images/b876fafd-cee1-440e-81e7-283ddc6c7cc6.jpg?fm=webp&q=80",
				10, ingredienti.get(0),ingredienti.get(1), ingredienti.get(6)));

		List<Pizza> pizze = pizzaService.findAll();
		offertaService.save(new Offerta("Sconto 20%", LocalDate.now(), LocalDate.now().plusDays(25), pizze.get(0)));
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		Role roleGod = new Role("GOD");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		roleService.save(roleGod); 
		
		
		String pws = AuthConf.passwordEncoder().encode("pws");
		
		User michUser = new User("MichUser", pws, roleUser);
		User michAdmin = new User("MichAdmin",pws, roleAdmin);
		User michGod = new User("MichGod", pws, roleGod);
		
		userService.save(michUser);
		userService.save(michAdmin);
		userService.save(michGod);
		
	}

}