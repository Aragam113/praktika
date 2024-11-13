package task3_6;
import java.io.*;
import java.util.*;

class Computer {
    private Winchester winchester;
    private DiskDrive diskDrive;
    private RAM ram;
    private Processor processor;
    private boolean isOn;

    public Computer(Winchester winchester, DiskDrive diskDrive, RAM ram, Processor processor) {
        this.winchester = winchester;
        this.diskDrive = diskDrive;
        this.ram = ram;
        this.processor = processor;
        this.isOn = false;
    }

    public void powerOn() {
        isOn = true;
        System.out.println("Компьютер включен.");
    }

    public void powerOff() {
        isOn = false;
        System.out.println("Компьютер выключен.");
    }

    public void checkForViruses() {
        if (isOn) {
            System.out.println("Компьютер проверен на вирусы.");
        } else {
            System.out.println("Компьютер выключен. Вирусы не проверены.");
        }
    }

    public void displayWinchesterSize() {
        System.out.println("Размер винчестера: " + winchester.getSize() + "GB");
    }

    @Override
    public String toString() {
        return "Computer{" +
                "winchester=" + winchester +
                ", diskDrive=" + diskDrive +
                ", ram=" + ram +
                ", processor=" + processor +
                ", isOn=" + isOn +
                '}';
    }
}

class Winchester {
    private int size;

    public Winchester(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер винчестера должен быть положительным.");
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Winchester{" + "size=" + size + "GB}";
    }
}

class DiskDrive {
    private String type;

    public DiskDrive(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип дисковода не может быть пустым.");
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return "DiskDrive{" + "type='" + type + "'}";
    }
}

class RAM {
    private int size;

    public RAM(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер оперативной памяти должен быть положительным.");
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "RAM{" + "size=" + size + "GB}";
    }
}

class Processor {
    private String model;

    public Processor(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель процессора не может быть пустой.");
        }
        this.model = model;
    }

    @Override
    public String toString() {
        return "Processor{" + "model='" + model + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Winchester winchester = new Winchester(512);
            DiskDrive diskDrive = new DiskDrive("Blu-ray");
            RAM ram = new RAM(16);
            Processor processor = new Processor("Intel i7");
            Computer computer = new Computer(winchester, diskDrive, ram, processor);
            saveComputerToFile(computer);
            Computer loadedComputer = loadComputerFromFile();
            System.out.println("Загруженный объект: " + loadedComputer);

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка: Не найден класс для десериализации.");
        }
    }

    public static void saveComputerToFile(Computer computer) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("computer.dat"))) {
            out.writeObject(computer);
            System.out.println("Компьютер сохранен в файл.");
        } catch (IOException e) {
            throw new IOException("Не удалось сохранить компьютер в файл", e);
        }
    }

    public static Computer loadComputerFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("computer.dat"))) {
            return (Computer) in.readObject();
        } catch (IOException e) {
            throw new IOException("Не удалось загрузить компьютер из файла", e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Не найден класс для загрузки объекта", e);
        }
    }
}

