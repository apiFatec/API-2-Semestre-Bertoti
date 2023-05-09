/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.TurmaDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Turma;
import views.WeClass;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormTurmaController implements Initializable {

    /**
     * Initializes the controller class.
     */
      
    @FXML
    private Button btnTurma;
        
    @FXML
    private Button btnVoltar;


    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;

    @FXML
    private TextField txtEscola;

    @FXML
    private TextField txtTurma;

    @FXML
    void btnSalvar(ActionEvent event) {
        String nome = txtTurma.getText();
        String escola =txtEscola.getText();
        Turma turma = new Turma(nome, escola);
        TurmaDao dao = new TurmaDao();
        dao.addTurma(turma);

        txtTurma.setText("");
        txtEscola.setText("");
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void hlClasses(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/alunosView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hlHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void btnVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();;
    }
}
