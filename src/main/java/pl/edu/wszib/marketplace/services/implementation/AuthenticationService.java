package pl.edu.wszib.marketplace.services.implementation;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.marketplace.exceptions.LoginInUseException;
import pl.edu.wszib.marketplace.models.User;
import pl.edu.wszib.marketplace.models.viewmodel.RegisterUser;
import pl.edu.wszib.marketplace.repositories.IUserRepository;
import pl.edu.wszib.marketplace.services.IAuthenticationService;
import pl.edu.wszib.marketplace.session.Session;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService
{
    @Autowired
    IUserRepository userRepository;

    @Resource
    Session session;

    @Override
    public void authenticate(String login, String password)
    {
        Optional<User> user = this.userRepository.getUserByLogin(login);

        if(user.isEmpty() || !user.get().getPassword().equals(DigestUtils.md5Hex(password))) return;

        this.session.setUser(user.get());
    }

    @Override
    public void register(RegisterUser registerUser)
    {
        Optional<User> userBox = this.userRepository.getUserByLogin(registerUser.getLogin());

        if(userBox.isPresent()) throw new LoginInUseException();

        registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword2()));
        this.userRepository.addUser(registerUser);
    }
}
