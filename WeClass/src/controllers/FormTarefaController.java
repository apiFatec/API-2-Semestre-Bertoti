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
import javafx.scene.control.TextField;
import models.Turma;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormTarefaController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtEscola;

    @FXML
    private TextField txtNome;

    @FXML
    void btnSalvar(ActionEvent event) {
        String nome = txtNome.getText();
        String escola = txtEscola.getText();
        
        Turma turma = new Turma(nome, escola);
        TurmaDao dao = new TurmaDao();
        dao.addTurma(turma);
        
        txtNome.setText("");
        txtEscola.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
