import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    PersonDB personDB = new PersonDB();
        Scanner input = new Scanner(System.in);
        int choice;
        while (true){
            System.out.println("1- Register");
            System.out.println("2- Login");
            System.out.println("3- Exit");
            choice = input.nextInt();
            if(choice==1){
                personDB.register();
            }
            else if(choice==2){
                personDB.login();
            }
            else if(choice==3) System.exit(0);
        }
    }
}
