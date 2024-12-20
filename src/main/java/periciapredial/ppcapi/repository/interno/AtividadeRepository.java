package periciapredial.ppcapi.repository.interno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import periciapredial.ppcapi.model.interno.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
