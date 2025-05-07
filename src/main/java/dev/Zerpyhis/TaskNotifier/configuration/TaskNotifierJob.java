package dev.Zerpyhis.TaskNotifier.configuration;

import dev.Zerpyhis.TaskNotifier.entitys.TaskEntity;
import dev.Zerpyhis.TaskNotifier.repositorys.TasksRepository;
import dev.Zerpyhis.TaskNotifier.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TaskNotifierJob {
    @Autowired
    private TasksRepository repository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void notificarTarefas() {
        LocalDateTime agora = LocalDateTime.now();
        List<TaskEntity> tarefas = repository.findAll();

        tarefas.stream()
                .filter(t -> t.getDatehourTimer().withSecond(0).withNano(0).equals(agora.withSecond(0).withNano(0)))
                .forEach(tarefa -> {
                    try {
                        emailService.senderEmail(
                                tarefa.getEmailDestiny(),
                                "Lembrete de Tarefa: " + tarefa.getTitle(),
                                tarefa.getTitle(),
                                tarefa.getDatehourTimer().toLocalDate().toString(),
                                tarefa.getDatehourTimer().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                        );
                    } catch (MessagingException e) {
                        System.err.println("Erro ao enviar e-mail: " + e.getMessage());
                    }
                });
    }
}
