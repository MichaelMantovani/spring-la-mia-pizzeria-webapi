package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Offerta;
import org.java.spring.db.repo.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {

	@Autowired
	private OffertaRepository offertaRepository;

	public List<Offerta> findAll() {
		return offertaRepository.findAll();
	}

	public Offerta findById(int id) {
		return offertaRepository.findById(id).get();
	}

	public void save(Offerta offerta) {
		offertaRepository.save(offerta);
	}

}
