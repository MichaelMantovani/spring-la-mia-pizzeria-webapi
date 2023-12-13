package org.java.spring.db.repo;

import org.java.spring.db.pojo.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaRepository extends JpaRepository<Offerta, Integer>{

}
