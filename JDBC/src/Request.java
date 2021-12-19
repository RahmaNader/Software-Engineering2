public class Request {

    static IDBRequest dbrequest = new DBRequest();

    public static void requestRide(Person p, String source, String destination) {
        dbrequest.requestRide(p, source, destination);
    }

    public static void viewRequests(Person p){
        dbrequest.viewRequests(p);
    }

    public static void acceptRequest(int id){
        dbrequest.acceptRequest(id);
    }

    public static void cancelRequest(Person P, int id){
        dbrequest.cancelRequest(P, id);
    }

    public static void makeOffer(Person p, int id, int price){
        dbrequest.makeOffer(p, id, price);
    }

}