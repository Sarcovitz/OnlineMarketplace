package pl.edu.wszib.marketplace.models.viewmodel;

import pl.edu.wszib.marketplace.models.User;

import javax.persistence.Entity;

@Entity
public class RegisterUser extends User
{
    private String password2;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
