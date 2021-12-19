public interface IDBAdmin {
    public void listAllDriverRequests();
    public void listAllDrivers();
    public void listAllUsers();
    public void suspendDriver(String userName);
    public void suspendUser(String userName);
    public void activateDriver(String userName);
    public void activateUser(String userName);
}
