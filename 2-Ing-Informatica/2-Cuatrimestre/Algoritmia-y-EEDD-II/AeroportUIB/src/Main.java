import airport.Airport;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport();
        airport.createCompanies(new String[]{"Air Nostrum", "Uep fly!", "Air Europa", "Vueling", "Iberia"});
        airport.createCounters();
        airport.entryPassenger("Marc", "13213", "312321", "Iberia", "M8");
        airport.entryPassenger("Carlo", "13213", "312321", "Vueling", "M5");
        airport.entryPassenger("jesus", "13213", "312321", "Vueling", "M2");
        airport.entryPassenger("Irene", "13213", "312321", "Air Nostrum", "M0");
        System.out.println("************ Companies *************");
        airport.printCompanies();
        System.out.println("************ Counters *************");
        airport.printCounters();
        airport.printPassengerCompany("Vueling");
        airport.printPassengerCounter("M0");
    }
}
