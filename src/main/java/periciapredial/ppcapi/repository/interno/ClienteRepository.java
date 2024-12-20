package periciapredial.ppcapi.repository.interno;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import periciapredial.ppcapi.model.interno.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, BigDecimal> {

  @Query("SELECT MAX(c.sequencia) FROM Cliente c WHERE c.grupo.id = :grupoId")
  Integer findMaxSequenciaByGrupoId(@Param("grupoId") Long grupoId);
}
