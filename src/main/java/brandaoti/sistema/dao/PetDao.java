package brandaoti.sistema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Pet;

public interface PetDao extends JpaRepository<Pet, Integer> {
	@Query(" select p from Pet p inner join p.responsavel u where p.nome = ':nome' and u.cpf = ':cpfDono' ")
	Pet findNome(@Param("nome") String nome, @Param("cpfDono") String cpfDono);
}
