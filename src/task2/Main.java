package task2;

import java.util.*;

class Phone {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String address;
    private String creditCardNumber;
    private double debit;
    private double credit;
    private double localCallTime;
    private double longDistanceCallTime;
    public Phone(int id, String lastName, String firstName, String patronymic,
                 String address, String creditCardNumber, double debit,
                 double credit, double localCallTime, double longDistanceCallTime) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.debit = debit;
        this.credit = credit;
        this.localCallTime = localCallTime;
        this.longDistanceCallTime = longDistanceCallTime;
    }
    public Phone(int id, String lastName, String firstName, String patronymic) {
        this(id, lastName, firstName, patronymic, "", "", 0.0, 0.0, 0.0, 0.0);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public double getDebit() {
        return debit;
    }
    public void setDebit(double debit) {
        this.debit = debit;
    }
    public double getCredit() {
        return credit;
    }
    public void setCredit(double credit) {
        this.credit = credit;
    }
    public double getLocalCallTime() {
        return localCallTime;
    }
    public void setLocalCallTime(double localCallTime) {
        this.localCallTime = localCallTime;
    }
    public double getLongDistanceCallTime() {
        return longDistanceCallTime;
    }
    public void setLongDistanceCallTime(double longDistanceCallTime) {
        this.longDistanceCallTime = longDistanceCallTime;
    }
    @Override
    public String toString() {
        return "Phone{id=" + id + ", lastName='" + lastName + "', firstName='" + firstName +
                "', patronymic='" + patronymic + "', address='" + address +
                "', creditCardNumber='" + creditCardNumber + "', debit=" + debit +
                ", credit=" + credit + ", localCallTime=" + localCallTime +
                ", longDistanceCallTime=" + longDistanceCallTime + "}";
    }
    public boolean hasLocalCallTimeGreaterThan(double time) {
        return this.localCallTime > time;
    }
    public boolean usedLongDistance() {
        return this.longDistanceCallTime > 0;
    }
    public static Comparator<Phone> comparatorByLastName = new Comparator<Phone>() {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.lastName.compareTo(p2.lastName);
        }
    };
}
public class Main {
    public static void main(String[] args) {
        Phone[] phones = new Phone[] {
                new Phone(1, "Ivanov", "Ivan", "Ivanovich", "Moscow", "1234", 100.0, 200.0, 150, 10),
                new Phone(2, "Petrov", "Petr", "Petrovich", "Saint-Petersburg", "5678", 200.0, 300.0, 30, 5),
                new Phone(3, "Sidorov", "Sidr", "Sidorovich", "Kazan", "9101", 50.0, 400.0, 200, 20),
                new Phone(4, "Fedorov", "Fedor", "Fedorovich", "Novosibirsk", "1122", 150.0, 250.0, 100, 0),
                new Phone(5, "Mikhailov", "Mikhail", "Mikhailovich", "Sochi", "3344", 300.0, 500.0, 180, 50)
        };
        double timeThreshold = 100;
        System.out.println("Абоненты с временем внутригородских разговоров больше " + timeThreshold + ":");
        for (Phone phone : phones) {
            if (phone.hasLocalCallTimeGreaterThan(timeThreshold)) {
                System.out.println(phone);
            }
        }
        System.out.println("\nАбоненты, использовавшие междугородную связь:");
        for (Phone phone : phones) {
            if (phone.usedLongDistance()) {
                System.out.println(phone);
            }
        }
        System.out.println("\nАбоненты в алфавитном порядке по фамилии:");
        Arrays.sort(phones, Phone.comparatorByLastName);
        for (Phone phone : phones) {
            System.out.println(phone);
        }
    }
}
