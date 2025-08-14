package br.com.cauagilberto.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
