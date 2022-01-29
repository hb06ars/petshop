package brandaoti.sistema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Vacina;

public interface VacinaDao extends JpaRepository<Vacina, Integer> {
	@Query(" select v from Vacina v where v.nome = :nome ")
	Vacina findNome(@Param("nome") String nome);
}
