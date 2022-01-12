package pl.edu.wszib.marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import pl.edu.wszib.marketplace.models.viewmodel.RegisterUser;
import pl.edu.wszib.marketplace.repositories.IUserRepository;

public interface IAuthenticationService
{
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
