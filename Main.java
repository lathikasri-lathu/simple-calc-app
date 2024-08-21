import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    private TextField display;
    private String operator = "";
    private double firstNumber = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create a GridPane for layout
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(5);
            gridPane.setHgap(5);

            // Create and configure the display (TextField)
            display = new TextField();
            display.setEditable(false);
            display.setStyle("-fx-font-size: 20px;");
            GridPane.setColumnSpan(display, 4);
            gridPane.add(display, 0, 0);

            // Create buttons for the calculator
            String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
            };

            int i = 0;
            for (String text : buttons) {
                Button button = new Button(text);
                button.setMinSize(50, 50);
                button.setOnAction(e -> handleButtonAction(text));
                int row = i / 4 + 1;
                int col = i % 4;
                gridPane.add(button, col, row);
                i++;
            }

            // Set up the scene and stage
            Scene scene = new Scene(gridPane, 300, 400);
            primaryStage.setTitle("Simple Calculator");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleButtonAction(String text) {
        switch (text) {
            case "C":
                display.clear();
                operator = "";
                firstNumber = 0;
                break;
            case "=":
                double secondNumber = Double.parseDouble(display.getText());
                double result;
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                    default:
                        return;
                }
                display.setText(String.valueOf(result));
                operator = "";
                break;
            default:
                if ("/*+-".contains(text)) {
                    firstNumber = Double.parseDouble(display.getText());
                    operator = text;
                    display.clear();
                } else {
                    display.appendText(text);
                }
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
