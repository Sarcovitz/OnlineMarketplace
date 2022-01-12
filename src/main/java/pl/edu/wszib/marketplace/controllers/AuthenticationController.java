package pl.edu.wszib.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.marketplace.exceptions.LoginInUseException;
import pl.edu.wszib.marketplace.exceptions.OnLoginException;
import pl.edu.wszib.marketplace.models.viewmodel.RegisterUser;
import pl.edu.wszib.marketplace.services.IAuthenticationService;
import pl.edu.wszib.marketplace.session.Session;
import pl.edu.wszib.marketplace.validators.LoginValidator;
import pl.edu.wszib.marketplace.validators.RegisterValidator;

import javax.annotation.Resource;

@Controller
public class AuthenticationController
{
    @Autowired
    IAuthenticationService authenticateService;

    @Resource
    Session session;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password)
    {
        try
        {
            LoginValidator.validateLogin(login);
            LoginValidator.validatePassword(password);
        } catch (OnLoginException e) { return "redirect:/login";}

        this.authenticateService.authenticate(login, password);

        if(this.session.isLogged()) return "redirect:/main";
        else return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout()
    {
        this.session.setUser(null);
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        model.addAttribute("registerUser", new RegisterUser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser)
    {
        try
        {
            RegisterValidator.validateLogin(registerUser.getLogin());
            RegisterValidator.validatePassword(registerUser.getPassword());
            RegisterValidator.validateEmail(registerUser.getEmail());
            comparePasswords(registerUser.getPassword(), registerUser.getPassword2());
            this.authenticateService.register(registerUser);
        }
        catch (OnLoginException | LoginInUseException e) { return "redirect:/register"; }

        return "redirect:/main";
    }

    private void comparePasswords(String pass1, String pass2)
    {
        if(pass1 == null || !pass1.equals(pass2)) throw new OnLoginException("Incorrect passwords !");
    }
}
