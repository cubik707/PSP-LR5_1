package org.example.lr5_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    public TextField firstNumTF;
    public TextField secondNumTF;
    public CheckBox sumCB;
    public CheckBox subtractCB;
    public CheckBox multiplyCB;
    public CheckBox divideCB;
    public TextField resultTF;
    public Button calcButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Калькулятор");
        stage.show();
    }

    public void initialize() {
        calcButton.setOnAction(event -> calculate());

        // ограничение на один выбранный чекбокс
        sumCB.setOnAction(event -> uncheckOthers(sumCB));
        subtractCB.setOnAction(event -> uncheckOthers(subtractCB));
        multiplyCB.setOnAction(event -> uncheckOthers(multiplyCB));
        divideCB.setOnAction(event -> uncheckOthers(divideCB));
    }

    private void uncheckOthers(CheckBox selectedCheckBox) {
        if (selectedCheckBox != sumCB) sumCB.setSelected(false);
        if (selectedCheckBox != subtractCB) subtractCB.setSelected(false);
        if (selectedCheckBox != multiplyCB) multiplyCB.setSelected(false);
        if (selectedCheckBox != divideCB) divideCB.setSelected(false);
    }

    private void calculate() {
        double firstNum, secondNum;
        double result = 0;
        try {
            firstNum = Double.parseDouble(firstNumTF.getText());
            secondNum = Double.parseDouble(secondNumTF.getText());
        } catch (NumberFormatException e) {
            showError("Ошибка", "Пожалуйста, введите корректные числа");
            return;
        }

        if (!anyOperationSelected()) {
            showError("Ошибка", "Пожалуйста, выберите хотя бы одну операцию");
            return;
        }

        if (sumCB.isSelected()) {
            result = firstNum + secondNum;
        }
        if (subtractCB.isSelected()) {
            result = firstNum - secondNum;
        }
        if (multiplyCB.isSelected()) {
            result = firstNum * secondNum;
        }
        if (divideCB.isSelected()) {
            if (secondNum == 0) {
                showError("Ошибка", "Деление на ноль невозможно");
                return;
            }
            result = firstNum / secondNum;
        }

        //если целое число отображаем без точки
        if (result == (long) result) {
            resultTF.setText(String.format("%d", (long) result));
        } else {
            resultTF.setText(String.valueOf(result));
        }
    }

    private boolean anyOperationSelected() {
        return sumCB.isSelected() || subtractCB.isSelected() || multiplyCB.isSelected() || divideCB.isSelected();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
