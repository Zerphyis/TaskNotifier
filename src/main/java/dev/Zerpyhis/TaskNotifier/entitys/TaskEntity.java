package dev.Zerpyhis.TaskNotifier.entitys;

import dev.Zerpyhis.TaskNotifier.record.Tasks;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime datehourTimer;
    private String emailDestiny;

    public TaskEntity(){}

    public TaskEntity(Tasks data){
        this.title= data.title();
        this.description= data.description();
        this.emailDestiny= data.emailDestiny();
        this.datehourTimer=LocalDateTime.now();

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDatehourTimer() {
        return datehourTimer;
    }

    public void setDatehourTimer(LocalDateTime datehourTimer) {
        this.datehourTimer = datehourTimer;
    }

    public String getEmailDestiny() {
        return emailDestiny;
    }

    public void setEmailDestiny(String emailDestiny) {
        this.emailDestiny = emailDestiny;
    }
}
