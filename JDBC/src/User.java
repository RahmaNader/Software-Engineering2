public class User extends Person {
    private double balance;

    public User(){}
    public User(String name, String userName, String mobile, String email, String password) {
        super(name,userName,mobile,email,password);
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }



}