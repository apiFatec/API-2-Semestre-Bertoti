/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Aluno;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormAlunoController implements Initializable {
    
    @FXML
    private Button btnCriar;

    @FXML
    private ComboBox<?> cbTurma;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRa;

    @FXML
    void btnCriar(ActionEvent event) {
        String nome = txtNome.getText();
        int ra = Integer.parseInt(txtRa.getText());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
