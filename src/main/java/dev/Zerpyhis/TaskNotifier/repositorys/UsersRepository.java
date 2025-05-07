package dev.Zerpyhis.TaskNotifier.repositorys;

import dev.Zerpyhis.TaskNotifier.entitys.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository  extends JpaRepository<UsersEntity,Long> {
}
