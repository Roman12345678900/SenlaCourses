package senla.com.repository.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import senla.com.connectJdbc.ConnectionHolder;
import senla.com.entity.User;
import senla.com.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final ConnectionHolder connectionHolder;

    private static final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM users";
    private static final String SQL_INSERT_USER = "INSERT INTO users (id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    @Override
    public User findById(Long id) {
        User user = null;
        try (Connection connection = connectionHolder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }else {
                throw new RuntimeException("User with id" + id + "not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by id" + id, e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionHolder.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                userList.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all users", e);
        }
        return userList;
    }

    @Override
    public void save(User user) {
        try (Connection connection = connectionHolder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);

            statement.setLong(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connectionHolder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user with id" + id, e);
        }
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));

        return user;
    }
}
