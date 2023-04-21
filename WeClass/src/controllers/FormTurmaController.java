/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.TurmaDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
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
    private Hyperlink hlCalendar;

    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;

    @FXML
    private TextField txtEscola;

    @FXML
    private TextField txtTurma;

    @FXML
    void hlClasses(ActionEvent event) {
        WeClass.mudarTela("viewAlunos");
    }

    @FXML
    void hlHome(ActionEvent event) {
        WeClass.mudarTela("main");
    }

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}
