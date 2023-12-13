package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizza")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 60, unique = true, nullable = false)
	@Length(min = 5, message = "Il nome deve essere di almeno 5 caratteri")
	@NotBlank(message = "Deve esserci almeno un nome")
	private String name;

	@Column(columnDefinition = "TEXT")
	@Length(min = 5, max = 150, message = "La descrizione deve contenere dai 5 ai 150 caratteri")
	private String descrizione;

	@Column()
	@URL(protocol = "https", message = "Il link deve essere in protocollo  https")
	private String foto;

	@Column(nullable = false)
	@Range(min = 5, message = "Il prezzo deve essere di almeno 5€")
	@NotNull(message = "Devi inserire almeno un prezzo")
	private double prezzo;

	@OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
	private List<Offerta> offerte;

	@ManyToMany
	private List<Ingrediente> ingredienti;

	public Pizza() {
	}

	public Pizza(String name, String descrizione, String foto, double prezzo, Ingrediente... ingredienti) {

		setName(name);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		setIngredienti(ingredienti);

	}
	
	@JsonProperty("ingredienti")
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	@JsonIgnore
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	
	public void setIngredienti(Ingrediente... ingredienti) {
		setIngredienti(Arrays.asList(ingredienti));
	}

	public List<Offerta> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pizza: " + this.name + "\n" + this.descrizione + "\n" + this.prezzo;
	}

}
