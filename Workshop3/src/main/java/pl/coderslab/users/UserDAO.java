package pl.coderslab.users;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {

    private static final String CREATE_USER_QUERY =

            "INSERT INTO workshop2.users(username, email, password) VALUES (?, ?, ?)";

    public static final String UPDATE_USER_QUERY=
            "UPDATE workshop2.users SET username = ?, email = ?, password = ? WHERE id = ?;";

    public static final String DELETE_USER_QUERY="DELETE FROM workshop2.users WHERE id = ?;";

    public static final String SELECT_USER_BY_ID_QUERY="SELECT * FROM workshop2.users WHERE id = ?;";

    public static final String SELECT_USERS_QUERY ="SELECT * FROM workshop2.users;";

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

//        Testy: dodawanie użytkownika
/*        User newUser = new User();
        newUser.setUserName("User7");
        newUser.setEmail("user7@email.com");
        newUser.setPassword("pass7");
        userDAO.create(newUser);*/

//        Testy: zmiana danych użytkownika
/*        User userToUpdate = userDAO.read(4);
        userToUpdate.setUserName("User4");
        userToUpdate.setEmail("user4@email.com");
        userToUpdate.setPassword("pass4");
        userDAO.update(userToUpdate);*/

//        Testy: usuwanie użytkownika
//        userDAO.delete(2);

        User[] result = userDAO.findAll();
        for(User user: result){
            System.out.println(user);
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId){
        User selectedUser = new User();
        try (Connection conn = DbUtil.getConnection()){
            try(PreparedStatement statement = conn.prepareStatement(SELECT_USER_BY_ID_QUERY)){
                statement.setInt(1, userId);
                try(ResultSet result = statement.executeQuery()){
                    while(result.next()){
                        selectedUser.setId(result.getInt(1));
                        selectedUser.setEmail(result.getString(2));
                        selectedUser.setUserName(result.getString(3));
                        selectedUser.setPassword(result.getString(4));
                    }
                    return selectedUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4,user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_USER_QUERY);

            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        User[] allUsers = new User[0];
        try (Connection conn = DbUtil.getConnection();
            Statement statement = conn.createStatement()){
                try(ResultSet result = statement.executeQuery(SELECT_USERS_QUERY)){
                    while(result.next()){
                        User nextUser = new User();
                        nextUser.setId(result.getInt(1));
                        nextUser.setEmail(result.getString(2));
                        nextUser.setUserName(result.getString(3));
                        nextUser.setPassword(result.getString(4));
                        allUsers = addToArray(nextUser, allUsers);
                    }
                    return allUsers;
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
}
