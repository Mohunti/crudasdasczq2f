package web.dao;

import org.springframework.stereotype.Component;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;




@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User showUserById(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
entityManager.merge(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

//    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
//
//
//    public List<User> index() {
//        List<User> users = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM user ";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()){
//                User user = new User();
//             user.setId(resultSet.getInt("id"));
//             user.setName(resultSet.getString("name"));
//             user.setEmail(resultSet.getString("email"));
//             user.setAge(resultSet.getInt("age"));
//
//             users.add(user);
//            }
//
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return users;
//    }
//
//      public User show(int id){
//        User user = null;
//
//          try {
//              PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE SELECT id=?");
//              preparedStatement.setInt(1,id);
//
//             ResultSet resultSet = preparedStatement.executeQuery();
//
//             resultSet.next();
//
//             user = new User();
//
//             user.setId(resultSet.getInt("id"));
//             user.setName(resultSet.getString("name"));
//             user.setEmail(resultSet.getString("email"));
//             user.setAge(resultSet.getInt("age"));
//
//          } catch (SQLException throwables) {
//              throwables.printStackTrace();
//          }
//          return user;
//      }
//
//    public void save(User user) {
//
//        try {
//          PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES(1, ?, ?, ?)");
//
//            preparedStatement.setString(1,user.getName());
//            preparedStatement.setInt(2,user.getAge());
//            preparedStatement.setString(3,user.getEmail());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//       public void update(int id,User updateUser){
//           try {
//               PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET name=?, age=?, email=?, WHERE id=?");
//
//               preparedStatement.setString(1,updateUser.getName());
//               preparedStatement.setInt(2,updateUser.getAge());
//               preparedStatement.setString(3,updateUser.getEmail());
//               preparedStatement.setInt(4,id);
//
//               preparedStatement.executeUpdate();
//           } catch (SQLException throwables) {
//               throwables.printStackTrace();
//           }
//      }
//
//    public void delete(int id) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id=?");
//            preparedStatement.setInt(1,id);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

}
