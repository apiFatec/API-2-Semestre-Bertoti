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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Aluno;
import models.Turma;

/**
 * FXML Controller class
 *
 * @author Fatec
 */
public class FormEditarTurmaController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Turma turma;
    
    @FXML
    private TableView<Aluno> tableView;
    
    @FXML
    private TableColumn<Aluno, Button> colEditar;

    @FXML
    private TableColumn<Aluno, Button> colExcluir;

    @FXML
    private TableColumn<Aluno, String> colNome;

    @FXML
    private TableColumn<Aluno, String> colRA;
    
   @FXML
    private Hyperlink hlInicio;

    @FXML
    private Hyperlink hlClasse;
    
    @FXML
    private TextField txtTurma;

    @FXML
    private TextField txtEscola;
    
    private ObservableList<Aluno> obsListAluno;
    
    public void iniciarTela(Turma turma){
        this.turma = turma;
        
        txtTurma.setText(turma.getNome());
        txtEscola.setText(turma.getEscola());
        
        AlunoDao dao = new AlunoDao();
        
        listarAlunos(turma.getIdTurma());
        
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colRA.setCellValueFactory(new PropertyValueFactory<>("ra"));
        colEditar.setCellValueFactory(new PropertyValueFactory<>("editar"));
        colExcluir.setCellValueFactory(new PropertyValueFactory<>("excluir"));
        
        tableView.setItems(obsListAluno);
    }
    
    
    @FXML
    void hlClasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SalaView.fxml"));
        root = loader.load();
        
        SalaViewController controller = loader.getController();
        controller.att(event);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hlInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Main.fxml"));
        root = loader.load();
        
        MainController controller = loader.getController();
        controller.btnAtualizar(event);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    void listarAlunos(int id){
        TurmaDao dao = new TurmaDao();
        ArrayList<Aluno> alunos = dao.alunosSala(id);
        this.obsListAluno = FXCollections.observableArrayList(alunos);
    }
}
