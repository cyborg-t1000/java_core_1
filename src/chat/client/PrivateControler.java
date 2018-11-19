package chat.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;

public class PrivateControler {

    @FXML
    Button btn1;
    @FXML
    TextField textField;
    private Stage thisStage;
    private final MainController mainController;
    private String to;
    private DataOutputStream out;

    public PrivateControler(MainController mainController, String to) {
        this.mainController = mainController;
        this.to = to;
        this.out = this.mainController.out;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("privateWindow.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Private message to " + to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

        // отправка сообщений
    public void sendMsg() throws IOException {
        out.writeUTF("/w " + this.to + " " + textField.getText());
        textField.clear();
    }

}
