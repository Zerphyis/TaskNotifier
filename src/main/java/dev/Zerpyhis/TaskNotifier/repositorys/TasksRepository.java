package dev.Zerpyhis.TaskNotifier.repositorys;

import dev.Zerpyhis.TaskNotifier.entitys.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity ,Long> {
}
