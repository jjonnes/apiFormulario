package periciapredial.ppcapi.repository.visita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import periciapredial.ppcapi.model.visita.Visita;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {

  @Query("SELECT v FROM Visita v WHERE v.dataHoraInicio >= :startDate AND v.dataHoraInicio <= :endDate")
  List<Visita> findVisitasBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}