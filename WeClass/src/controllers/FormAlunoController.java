/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.AlunoDao;
import dao.TurmaDao;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Aluno;
import models.Turma;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormAlunoController implements Initializable {
    
    @FXML
    private Button btnCriar;

    @FXML
    private ComboBox<Turma> cbTurma;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRa;

    @FXML
    void btnCriar(ActionEvent event) {
        String nome = txtNome.getText();
        int ra = Integer.parseInt(txtRa.getText());
        int id = cbTurma.getValue().getIdTurma();
        
        Aluno aluno = new Aluno(ra,nome, id);
        AlunoDao dao = new AlunoDao();
        dao.adicionarAluno(aluno);
        
        txtNome.setText("");
        txtRa.setText("");
    }
    
    ObservableList<Turma> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaTurma();
        cbTurma.setItems(list);
    }    
    public void listaTurma(){
        TurmaDao dao = new TurmaDao();
        ArrayList<Turma> l = new ArrayList<>();
        l = dao.listTurma();
        this.list = FXCollections.observableArrayList(l);
    }
}
