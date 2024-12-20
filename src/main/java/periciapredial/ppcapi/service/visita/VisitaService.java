package periciapredial.ppcapi.service.visita;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import periciapredial.ppcapi.model.visita.Visita;
import periciapredial.ppcapi.repository.visita.VisitaRepository;

import java.util.List;
import java.util.Date;

@Service
public class VisitaService {

  @Autowired
  private VisitaRepository visitaRepository;

  public List<Visita> getAllVisitas() {
    return visitaRepository.findAll();
  }

  public Visita getVisitaById(Long id) {
    return visitaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Visita não encontrada com o ID: " + id));
  }

  public Visita createVisita(Visita visita) {
    return visitaRepository.save(visita);
  }

  public Visita updateVisita(Long id, Visita visita) {
    if (!visitaRepository.existsById(id)) {
      return null;
    }

    visita.setId(id);
    return visitaRepository.save(visita);
  }

  public void deleteVisita(Long id) {
    visitaRepository.deleteById(id);
  }

  public List<Visita> getVisitasBetweenDates(Date startDate, Date endDate) {
    return visitaRepository.findVisitasBetweenDates(startDate, endDate);
  }
}