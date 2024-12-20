package periciapredial.ppcapi.repository.interno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import periciapredial.ppcapi.model.interno.Acompanhante;

@Repository
public interface AcompanhanteRepository extends JpaRepository<Acompanhante, Long> {
}
