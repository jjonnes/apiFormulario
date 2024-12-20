package periciapredial.ppcapi.repository.interno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import periciapredial.ppcapi.model.interno.GrupoCliente;

@Repository
public interface GrupoClienteRepository extends JpaRepository<GrupoCliente, Long> {
}
