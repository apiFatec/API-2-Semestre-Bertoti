package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class editarAlunoController {

    @FXML
    private Button btnSalvarAlteracao;

    @FXML
    private Button btnVoltarTela;

    @FXML
    private ComboBox<?> cbEditarTurma;

    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;

    @FXML
    private TextField txtEditarNome;

    @FXML
    private TextField txtEditarRa;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void btnSalvarAlteracao(ActionEvent event) {

    }

    @FXML
    void btnVoltarTela(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SalaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hlClasses(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/SalaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hlHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
