/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import views.WeClass;



/**
 *
 * @author Mateus
 */
public class MainController implements Initializable {
    
     @FXML
    private Button btnTarefa;

    @FXML
    private Button btnTurma;

    @FXML
    private Hyperlink hlCalendar;

    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;

    @FXML
    private Label lblAtividade;

    @FXML
    private Label lblTurmas;

    @FXML
    void hlClasses(ActionEvent event) {
        WeClass.mudarTela("viewAlunos");
    }

    @FXML
    void hlHome(ActionEvent event) {
         WeClass.mudarTela("main");
    }
    
    @FXML
    void btnTurma(ActionEvent event) {
        WeClass.mudarTela("formTurma");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
