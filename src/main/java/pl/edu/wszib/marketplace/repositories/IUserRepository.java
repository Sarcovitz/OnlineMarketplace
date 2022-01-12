package pl.edu.wszib.marketplace.repositories;

import pl.edu.wszib.marketplace.models.User;

import java.util.Optional;

public interface IUserRepository
{
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}
