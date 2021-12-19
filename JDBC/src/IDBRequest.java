public interface IDBRequest {
    public void requestRide(Person p, String source, String destination);
    public void viewRequests(Person p);
    public void acceptRequest(int id);
    public void cancelRequest(Person P, int id);
    public void makeOffer(Person p, int id, int price);
}
