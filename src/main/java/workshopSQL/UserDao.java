package pl.coderslab.mysql.workshop2;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String UPDATE_USER =
            "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

    private static final String REMOVE_USER =
            "DELETE FROM users WHERE id = ?";

    private static final String PRINT_ID_USER =
            "SELECT * FROM users WHERE id = ?";

    private static final String PRINT_ALL_USERS =
            "SELECT * FROM users";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // PreparedStatement preStmt =
    //  conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("User with this email already exists");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int UserId) {

        try (Connection conn = DbUtil.connect()) {
            User user = new User();
            PreparedStatement preST = conn.prepareStatement(PRINT_ID_USER);
            preST.setInt(1, UserId);
            ResultSet rs = preST.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement preST = conn.prepareStatement(UPDATE_USER);
            preST.setString(1, user.getUserName());
            preST.setString(2, user.getEmail());
            preST.setString(3, hashPassword(user.getPassword()));
            preST.setInt(4, user.getId());
            preST.executeUpdate();

            System.out.println("Update completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int UserId) {

        try (Connection conn = DbUtil.connect()) {
            PreparedStatement preST = conn.prepareStatement(REMOVE_USER);
            preST.setInt(1, UserId);
            preST.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        User[] users = new User[0];
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement preST = conn.prepareStatement(PRINT_ALL_USERS);
            ResultSet rs = preST.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User[] addToArray(User user, User[] users) {
        User[] newUsers = Arrays.copyOf(users, users.length + 1);
        newUsers[users.length] = user;
        return newUsers;
    }


}
