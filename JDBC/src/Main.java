import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Person logged;
    public static void userPanel() throws SQLException {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            System.out.println("1- Request ride"+"\n"+"2- View requests"+"\n"+"3- Accept request"+
                    "\n"+"4- Refuse request"+"\n"+"5- Rate driver"+"\n"+"6- Exit");
            choice = input.nextInt();
            RidesDB ridesDB = new RidesDB();
            if (choice == 1) {
                ridesDB.requestRide(logged);
            }
            else if (choice == 2) {
                ridesDB.viewRequests(logged);
            }
            else if (choice == 3) {
                ridesDB.acceptRequest(logged);
            }
            else if (choice == 4) {
                ridesDB.refuseRequest(logged);
            }
            else if (choice == 5) {
                UserDriverDB.rateDriver(logged);
            }
        }
    }

    public static void driverPanel() throws SQLException {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            System.out.println("1- View rides"+"\n"+"2- Make offer"+"\n"+"3- Exit");
            choice = input.nextInt();
            RidesDB ridesDB = new RidesDB();
            if (choice == 1) {
                ridesDB.loadDB();
            }
            if (choice == 2) {
                ridesDB.loadDB();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        UserDriverDB userDriverDB = new UserDriverDB();
        userDriverDB.loadUserDB();
        userDriverDB.loadDriverDB();
        int choice;
        while (true) {
            System.out.println("1- Register"+"\n"+"2- Login"+"\n"+"3- Exit");
            choice = input.nextInt();
            if (choice == 1) {
                userDriverDB.register();
            } else if (choice == 2) {
                int choice2;
                System.out.println("1- Login as User?"+"\n"+"2- Login as Driver?");
                choice2 = input.nextInt();
                logged = userDriverDB.login();
                if (logged!=null) {
                    if (choice2 == 1) userPanel();
                    else driverPanel();
                }
            } else if (choice == 3) System.exit(0);
        }
    }
}
