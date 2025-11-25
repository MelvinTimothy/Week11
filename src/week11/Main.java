package week11;

import java.util.ArrayList;
import java.util.Scanner;
import week11.AuthenticationException;
import week11.ExcessiveFailedLoginException;
import week11.InvalidPropertyException;

public class Main {
    static ArrayList<User> listOfUser = new ArrayList<>();
    static Scanner scn = new Scanner(System.in);

    public static void initialize() {
        User user = new User("John", "Doe", 'L', "Jl. Merpati No. 1 RT 1 RW 1, Banten", "admin", "admin");
        listOfUser.add(user);
    }

    public static void handleLogin() {
        boolean success = false;
        for (User user : listOfUser) {
            System.out.print("Username: ");
            String username = scn.nextLine();
            System.out.print("Password: ");
            String password = scn.nextLine();

            try {
                if (user.login(username, password)) {
                    System.out.println(user.greeting());
                    success = true;
                    break;
                }
            } catch (ExcessiveFailedLoginException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        if (!success) {
            try {
                throw new AuthenticationException();
            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void handleSignUp() {
        try {
            System.out.print("Nama Depan: ");
            String firstName = scn.nextLine();
            System.out.print("Nama Belakang: ");
            String lastName = scn.nextLine();
            System.out.print("Jenis Kelamin (L/P): ");
            char gender = scn.nextLine().charAt(0);
            System.out.print("Alamat: ");
            String address = scn.nextLine();
            System.out.print("Username: ");
            String username = scn.nextLine();
            System.out.print("Password: ");
            String password = scn.nextLine();

            if (username.length() <= 8) {
                throw new InvalidPropertyException("Username harus lebih dari 8 karakter");
            }

            if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{6,16}$")) {
                throw new InvalidPropertyException("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter");
            }

            User newUser = new User(firstName, lastName, gender, address, username, password);
            listOfUser.add(newUser);
            System.out.println("User telah berhasil didaftarkan");

        } catch (InvalidPropertyException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        initialize();
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan: ");
            int choice = Integer.parseInt(scn.nextLine());

            if (choice == 1) {
                handleLogin();
            } else if (choice == 2) {
                handleSignUp();
            }
        }
    }
}
