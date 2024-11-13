package task7_8_9;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MainApplication extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<Shape> shapes = new ArrayList<>();
    private MathGame mathGame;
    private boolean showShip = false;
    private boolean showShapes = false;
    private boolean showGame = false;

    public MainApplication() {
        setTitle("Multi-task Java Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mathGame = new MathGame();
        JPanel panel = new JPanel();
        JButton shipButton = new JButton("Показать корабль");
        JButton shapesButton = new JButton("Создать фигуры");
        JButton gameButton = new JButton("Играть в игру");

        shipButton.addActionListener(e -> {
            showShip = true;
            showShapes = false;
            showGame = false;
            repaint();
        });

        shapesButton.addActionListener(e -> {
            showShip = false;
            showShapes = true;
            showGame = false;
            createShapes();
            repaint();
        });

        gameButton.addActionListener(e -> {
            showShip = false;
            showShapes = false;
            showGame = true;
            mathGame.playGame();
            repaint();
        });

        panel.add(shipButton);
        panel.add(shapesButton);
        panel.add(gameButton);
        add(panel, BorderLayout.SOUTH);
    }

    private void drawShip(Graphics g) {
        g.setColor(Color.BLACK);
        int[] xPoints = {100, 300, 260, 140};
        int[] yPoints = {250, 250, 300, 300};
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(Color.GRAY);
        int[] xLeftSail = {250, 150, 200};
        int[] yLeftSail = {250, 250, 100};
        g.fillPolygon(xLeftSail, yLeftSail, 3);
        g.setColor(Color.BLACK);
        int[] xRightSail = {250, 200, 200};
        int[] yRightSail = {250, 250, 100};
        g.fillPolygon(xRightSail, yRightSail, 3);
        g.setColor(Color.WHITE);
        g.fillOval(150, 260, 30, 30);
        g.fillOval(190, 260, 30, 30);
        g.fillOval(230, 260, 30, 30);
    }

    private void createShapes() {
        shapes.clear();
        shapes.add(new Circle(100, 100, 50));
        shapes.add(new Square(200, 100, 50));
        shapes.add(new Rectangle(350, 100, 100, 50));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (showShip) {
            drawShip(g);
        }
        if (showShapes) {
            for (Shape shape : shapes) {
                shape.draw(g);
            }
        }
        if (showGame) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true);
        });
    }
}

abstract class Shape {
    protected double x, y;
    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public abstract void move(double dx, double dy);
    public abstract void resize(double factor);
    public abstract void rotate(double angle);

    public abstract void draw(Graphics g);
}

class Circle extends Shape {
    private double radius;

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double factor) {
        this.radius *= factor;
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x, (int) y, (int) (2 * radius), (int) (2 * radius));
    }
}
class Square extends Shape {
    private double side;

    public Square(double x, double y, double side) {
        super(x, y);
        this.side = side;
    }
    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
    @Override
    public void resize(double factor) {
        this.side *= factor;
    }
    @Override
    public void rotate(double angle) {
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y, (int) side, (int) side);
    }
}
class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
    @Override
    public void resize(double factor) {
        this.width *= factor;
        this.height *= factor;
    }
    @Override
    public void rotate(double angle) {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }
}
class MathGame {
    private List<Card> cards;
    private int[][] board;

    public MathGame() {
        cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(i));
            }
        }
        Collections.shuffle(cards);
        board = new int[5][5];
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int filledCells = 0;
        while (filledCells < 25) {
            if (cards.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Больше нет карт!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            Card drawnCard = cards.remove(cards.size() - 1);
            String input = JOptionPane.showInputDialog("Вытащенная карта: " + drawnCard + "\nВведите строку (0-4) и столбец (0-4):");
            String[] inputs = input.split(" ");
            int row = Integer.parseInt(inputs[0]);
            int col = Integer.parseInt(inputs[1]);

            if (row < 0 || row >= 5 || col < 0 || col >= 5 || board[row][col] != 0) {
                JOptionPane.showMessageDialog(null, "Неверная позиция или ячейка уже заполнена. Попробуйте снова.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            board[row][col] = drawnCard.getNumber();
            filledCells++;
            printBoard();
        }
        JOptionPane.showMessageDialog(null, "Игра окончена!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
        printBoard();
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Игровая доска", JOptionPane.INFORMATION_MESSAGE);
    }
}

class Card {
    private final int number;

    public Card(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
