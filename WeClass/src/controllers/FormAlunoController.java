/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.AlunoDao;
import dao.TurmaDao;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Aluno;
import models.Turma;
import views.WeClass;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormAlunoController implements Initializable {
    
@FXML
    private Button btnCriar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<Turma> cbTurma;


    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRa;
    
    @FXML
    private Button btnAtualizar;
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    @FXML
    void btnAtualizar(ActionEvent event) {
        listaTurma();
        cbTurma.setItems(list);
    }

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
    
        @FXML
    void btnvVoltar(ActionEvent event) {
            WeClass.mudarTela("viewAlunos");
    }

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
