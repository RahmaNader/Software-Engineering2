public class User extends Person {
    private double balance;
    private String birthDate;

    public User(){}
    public User(String name, String userName, String mobile, String email,String birthDate, String password) {
        super(name,userName,mobile,email,password);
        this.birthDate = birthDate;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String getBirthDate ( ) {
        return birthDate;
    }

    public void setBirthDate ( String birthDate ) {
        this.birthDate = birthDate;
    }
}