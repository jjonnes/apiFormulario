package periciapredial.ppcapi.service.relatorio_mensal;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import periciapredial.ppcapi.model.visita.Visita;
import periciapredial.ppcapi.service.visita.VisitaService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WordGeneratorService {

  @Autowired
  private VisitaService visitaService;

  public void processDocument(XWPFDocument document, List<Visita> visitas) {
    // Encontre o parágrafo que contém o texto {{lista}}
    XWPFParagraph paragraph = null;
    for (XWPFParagraph p : document.getParagraphs()) {
      for (XWPFRun run : p.getRuns()) {
        if (run.getText(0) != null && run.getText(0).contains("{{lista}}")) {
          paragraph = p;
          break;
        }
      }
      if (paragraph != null) {
        break;
      }
    }

    // Se o parágrafo foi encontrado, substitua o texto {{lista}} pelo texto
    // formatado
    if (paragraph != null) {
      paragraph.removeRun(0); // Remove o texto {{lista}}
      appendFormattedVisitas(paragraph, visitas);
    }
  }

  private void appendFormattedVisitas(XWPFParagraph paragraph, List<Visita> visitas) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    for (Visita visita : visitas) {
      // Data em negrito
      XWPFRun boldRun = paragraph.createRun();
      boldRun.setBold(true);
      boldRun.setText("• " + dateFormat.format(visita.getDataHoraInicio()));
      boldRun.addBreak();

      // Conteúdo da visita
      XWPFRun normalRun = paragraph.createRun();
      normalRun.setText(visita.getFuncionarios() +
          " (" + visita.getAcompanhantes() + ", " +
          visita.getConteudoObs() + ")\n" +
          "Realizaram " + visita.getSubatividades() +
          " com o parecer: " + visita.getConteudoVisita());
      normalRun.addBreak();
      normalRun.addBreak(); // Adiciona espaço entre itens
    }
  }

  public void saveDocument(XWPFDocument document, String clientName) throws IOException {
    String monthDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    String outputPath = "src/main/resources/relatorios/" + monthDir;
    String fileName = String.format("%s-relatorio-%s.docx", clientName, monthDir);

    File outputDir = new File(outputPath);
    if (!outputDir.exists() && !outputDir.mkdirs()) {
      throw new IOException("Não foi possível criar o diretório: " + outputPath);
    }

    try (FileOutputStream out = new FileOutputStream(new File(outputDir, fileName))) {
      document.write(out);
    }
  }

  public Map<String, List<Visita>> getVisitasGroupedByClient() {
    LocalDate today = LocalDate.now();
    LocalDate firstDayOfMonth = today.withDayOfMonth(1);

    Date startDate = java.sql.Date.valueOf(firstDayOfMonth);
    Date endDate = java.sql.Date.valueOf(today);

    List<Visita> visitas = visitaService.getVisitasBetweenDates(startDate, endDate);
    Map<String, List<Visita>> visitasPorCliente = new HashMap<>();

    for (Visita visita : visitas) {
      visitasPorCliente
          .computeIfAbsent(visita.getCliente(), k -> new ArrayList<>())
          .add(visita);
    }

    return visitasPorCliente;
  }
}