package pl.edu.wszib.marketplace.repositories.implementation.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.marketplace.models.User;
import pl.edu.wszib.marketplace.repositories.IUserRepository;

import java.sql.*;
import java.util.Optional;


public class UserRepository2 implements IUserRepository
{
    @Autowired
    Connection connection;

    @Override
    public Optional<User> getUserByLogin(String login)
    {
        try
        {
            String sql = "SELECT * FROM tuser WHERE user_login = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("user_login"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));

                return Optional.of(user);
            }

        }
        catch (SQLException throwables) { throwables.printStackTrace();}

    return Optional.empty();
    }

    @Override
    public void addUser(User user)
    {
        try
        {
            String sql = "INSERT INTO tuser VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next())  user.setId(rs.getInt(1));

        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @Override
    public Optional<User> getUserById(int id)
    {
        try
        {
            String sql = "SELECT * FROM tuser WHERE user_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("user_login"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));

                return Optional.of(user);
            }

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return Optional.empty();
    }
}
