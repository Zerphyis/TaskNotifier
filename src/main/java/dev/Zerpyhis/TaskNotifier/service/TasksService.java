package dev.Zerpyhis.TaskNotifier.service;

import dev.Zerpyhis.TaskNotifier.entitys.TaskEntity;
import dev.Zerpyhis.TaskNotifier.exception.TasksNotFound;
import dev.Zerpyhis.TaskNotifier.repositorys.TasksRepository;
import dev.Zerpyhis.TaskNotifier.repositorys.UsersRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;

    private ThreadPoolTaskExecutor taskExecutor;

    public TaskEntity createTask(TaskEntity task) {
        var user = usersRepository.findById(task.getId())
                .orElseThrow(() -> new TasksNotFound("Usuário com ID " + task.getId() + " não encontrado"));

        TaskEntity savedTask = tasksRepository.save(task);

        taskExecutor.submit(() -> sendEmailAsync(user.getEmail(), task));

        return savedTask;
    }

    private void sendEmailAsync(String emailDestiny, TaskEntity task) {
        try {
            emailService.senderEmail(
                    emailDestiny,
                    "Nova Tarefa: " + task.getTitle(),
                    task.getTitle(),
                    task.getDatehourTimer().toLocalDate().toString(),
                    task.getDatehourTimer().toLocalTime().toString()
            );
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail para " + emailDestiny + ": " + e.getMessage());
        }
    }

    public List<TaskEntity> getAllTasks() {
        return tasksRepository.findAll();
    }

    public TaskEntity getTaskById(Long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new TasksNotFound("Tarefa com ID " + id + " não encontrada"));
    }

    public TaskEntity updateTask(Long id, TaskEntity taskDetails) {
        TaskEntity existingTask = tasksRepository.findById(id)
                .orElseThrow(() -> new TasksNotFound("Tarefa com ID " + id + " não encontrada"));

        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setDatehourTimer(taskDetails.getDatehourTimer());
        existingTask.setEmailDestiny(taskDetails.getEmailDestiny());

        TaskEntity updatedTask = tasksRepository.save(existingTask);

        taskExecutor.submit(() -> sendEmailAsync(existingTask.getEmailDestiny(), updatedTask));

        return updatedTask;
    }

    public boolean deleteTask(Long id) {
        TaskEntity task = tasksRepository.findById(id)
                .orElseThrow(() -> new TasksNotFound("Tarefa com ID " + id + " não encontrada"));

        tasksRepository.deleteById(id);
        return true;
    }
}
