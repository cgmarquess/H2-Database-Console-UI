package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private TodoDao database;

    public UserInterface(Scanner scanner, TodoDao database) {
        this.scanner = scanner;
        this.database = database;
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println();
            System.out.println("Enter command:");
            System.out.println("1) list");
            System.out.println("2) add");
            System.out.println("3) mark as done");
            System.out.println("4) remove");
            System.out.println("x) quit");

            System.out.print("> ");
            String command = this.scanner.nextLine();
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                System.out.println("Listing the database contents");
                List<Todo> todos = database.list();
                if (todos.isEmpty()) {
                    continue;
                } else {
                    for (Todo todo : todos) {
                        System.out.println(todo);
                    }
                }
            } else if (command.equals("2")) {
                System.out.println("Adding a new todo");
                System.out.println("Enter name");
                String name = scanner.nextLine();
                System.out.println("Enter description");
                String description = scanner.nextLine();

                Todo newTodo = new Todo(name, description, false);
                database.add(newTodo);
            } else if (command.equals("3")) {
                System.out.println("Which todo should be marked as done (give the id)?");
                System.out.print("> ");
                try {
                    int markAsDoneId = Integer.parseInt(scanner.nextLine());
                    database.markAsDone(markAsDoneId);
                } catch (Exception e) {
                    System.out.println("ID INVALID: " + e.getMessage());
                }
            } else if (command.equals("4")) {
                System.out.println("Which todo should be removed (give the id)?");
                try {
                    int toRemove = Integer.parseInt(scanner.nextLine());
                    database.remove(toRemove);
                } catch (Exception e) {
                    System.out.println("ID INVALID: " + e.getMessage());
                }
            }
        }

        System.out.println("Thank you!");
    }
}
