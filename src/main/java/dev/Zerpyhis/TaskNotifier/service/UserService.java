package dev.Zerpyhis.TaskNotifier.service;

import dev.Zerpyhis.TaskNotifier.entitys.UsersEntity;
import dev.Zerpyhis.TaskNotifier.exception.UsersNotFound;
import dev.Zerpyhis.TaskNotifier.repositorys.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity createUser(UsersEntity user) {
        return usersRepository.save(user);
    }

    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFound("Usuário com ID " + id + " não encontrado"));
    }

    public UsersEntity updateUser(Long id, UsersEntity userDetails) {
        if (!usersRepository.existsById(id)) {
            throw new UsersNotFound("Usuário com ID " + id + " não encontrado para atualização");
        }
        userDetails.setId(id);
        return usersRepository.save(userDetails);
    }

    public boolean deleteUser(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new UsersNotFound("Usuário com ID " + id + " não encontrado para exclusão");
        }
        usersRepository.deleteById(id);
        return true;
    }
}
