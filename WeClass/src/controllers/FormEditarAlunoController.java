/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.AlunoDao;
import dao.TarefaAlunoDao;
import dao.TarefaDAO;
import dao.TurmaDao;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormEditarAlunoController implements Initializable {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    private ObservableList<Turma> listaTurma;
    private Aluno aluno;
    private Turma turma;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private Hyperlink HlInicio;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<Turma> cbTurma;

    @FXML
    private Hyperlink hlInicio;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRa;

    @FXML
    void HlClasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SalaView.fxml"));
        root = loader.load();
        
        SalaViewController controller = loader.getController();
        controller.iniciar();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void HlInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Main.fxml"));
        root = loader.load();
        
        MainController controller = loader.getController();
        controller.btnAtualizar(event);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnSalvar(ActionEvent event) throws IOException{
        AlunoDao daoAluno = new AlunoDao();
        TarefaAlunoDao daoTarefa = new TarefaAlunoDao();
        daoTarefa.DeletarTarefasAluno(aluno);
        daoAluno.deletarAluno(aluno);

        aluno.setNome(txtNome.getText());
        aluno.setRa(Integer.parseInt(txtRa.getText()));
        aluno.setTurma(cbTurma.getValue().getIdTurma());

        daoAluno.adicionarAluno(aluno);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormEditarTurma.fxml"));
        root = loader.load();

        FormEditarTurmaController controller = loader.getController();
        controller.iniciarTela(turma);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnVoltar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormEditarTurma.fxml"));
        root = loader.load();
        
        FormEditarTurmaController controller = loader.getController();
        controller.iniciarTela(turma);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    void iniciarTela(Aluno aluno2, Turma turma2){
        this.turma = turma2;
        this.aluno = aluno2;
        txtNome.setText(aluno2.getNome());
        txtRa.setText(String.valueOf(aluno2.getRa()));
        
        TurmaDao dao = new TurmaDao();
        
        this.listaTurma = FXCollections.observableArrayList(dao.listTurma());
        cbTurma.setItems(listaTurma);
        cbTurma.setValue(turma2);
    }
}
