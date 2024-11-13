package task4;
public class Main {
    public static void main(String[] args) {
        Europa europa = new Europa();
        europa.addDivision(new Europa.HistoricalDivision("Римская Империя", 27, 476));
        europa.addDivision(new Europa.HistoricalDivision("Франкское королевство", 843, 987));
        europa.addDivision(new Europa.HistoricalDivision("Советский Союз", 1922, 1991));
        europa.addDivision(new Europa.HistoricalDivision("Германия", 1949, -1)); // Современная Германия
        System.out.println("История изменений территориального деления Европы:");
        europa.printDivisions();
    }
}
