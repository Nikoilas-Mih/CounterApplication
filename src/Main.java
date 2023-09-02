
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

    public class Main {
        private static final String COUNTER_FILE = "counter.dat";

        public static void main(String[] args) {
            Counter counter = loadCounter();
            System.out.println("Текущее значение счетчика: " + counter.getValue());

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.print("Введите команду: ");
                String command = scanner.nextLine();

                switch (command) {
                    case "/inc":
                        counter.increment();
                        System.out.println("Новое значение счетчика: " + counter.getValue());
                        break;
                    case "/stop":
                        saveCounter(counter);
                        System.out.println("Текущее значение счетчика: " + counter.getValue());
                        System.out.println("Завершаю...");
                        running = false;
                        break;
                    case "/reset":
                        counter.reset();
                        System.out.println("Счетчик обнулен.");
                        break;
                    default:
                        System.out.println("Неверная команда.");
                        break;
                }
            }
        }

        private static Counter loadCounter() {
            try (FileInputStream fileInputStream = new FileInputStream(COUNTER_FILE);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (Counter) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Не удалось загрузить счетчик. Создан новый счетчик.");
                return new Counter();
            }
        }

        private static void saveCounter(Counter counter) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(COUNTER_FILE);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(counter);
                System.out.println("Счетчик сохранен.");
            } catch (IOException e) {
                System.out.println("Не удалось сохранить счетчик.");
            }
        }
    }

