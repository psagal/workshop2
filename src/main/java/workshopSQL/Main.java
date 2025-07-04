package pl.coderslab.mysql.workshop2;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanData =  new Scanner(System.in);
        UserDao userDao = new UserDao();

        System.out.println("Username: ");
        String name = scanData.nextLine();
        System.out.println("Email: ");
        String email = scanData.nextLine();
        System.out.println("Password: ");
        String password = scanData.nextLine();
        User user = new User(name, email, password);

        userDao.create(user);
        System.out.println(user.getId());

         //Sprawdzenie metody read
        User newUser = new User();
        newUser = userDao.read(3);
        System.out.println(newUser.getId() + " " + newUser.getEmail() + " " +  newUser.getUserName() + " " + newUser.getPassword());

        /* // sprawdzanie metody update (z pomocą read)
        System.out.println("New username: ");
        newUser.setUserName(scanData.next());
        System.out.println("New email: ");
        newUser.setEmail(scanData.next());
        System.out.println("New password: ");
        newUser.setPassword(scanData.next());

        userDao.update(newUser);


         */
        //Sprawdzenie Delete
        //userDao.delete(2);

        //FindALL
        User[] users = userDao.findAll();
        System.out.println(users.length);
        for (User u : users) {
            System.out.println(u);
        }
    }

}
