package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"
                , new Car("AUDI", 7)));
        userService.add(new User("Max", "Sidorov", "user5@mail.ru"
                , new Car("BMW", 3)));
        userService.add(new User("Ivan", "Ivanov", "user6@mail.ru"
                , new Car("KIA", 5)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println(user.getCar().getModel());
            }
            System.out.println();
        }

        List<User> userCar = userService.getUsersByCar("BMW", 3);

        if (!userCar.isEmpty()) {
            for (User u : userCar){
                System.out.println("Id = " + u.getId());
                System.out.println("First Name = " + u.getFirstName());
                System.out.println("Last Name = " + u.getLastName());
                System.out.println("Email = " + u.getEmail());
            }
        }


        context.close();
    }
}
