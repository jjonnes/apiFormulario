package periciapredial.ppcapi.repository.interno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import periciapredial.ppcapi.model.interno.SubAtividade;

import java.math.BigDecimal;

@Repository
public interface SubAtividadeRepository extends JpaRepository<SubAtividade, BigDecimal> {

  @Query("SELECT MAX(c.sequencia) FROM SubAtividade c WHERE c.atividade.id = :atividadeId")
  Integer findMaxSequenciaByAtividadeId(@Param("atividadeId") Long atividadeId);
}