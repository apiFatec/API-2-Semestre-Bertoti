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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Aluno;
import models.Tarefa;
import models.TarefaAluno;
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
    
    private ArrayList<Aluno> alunos;
    private ArrayList<Tarefa> tarefasTurma;
    

    @FXML
    private Button btnExcluir;
    
    @FXML
    private TableView<Aluno> tableView;
    
    @FXML
    private Button btnSalvar;
    
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
      btnSalvar.setCursor(Cursor.HAND);
      btnExcluir.setCursor(Cursor.HAND);
    }
    void listarAlunos(int id){
        TurmaDao dao = new TurmaDao();
        ArrayList<Aluno> alunos = dao.alunosSala(id);
        for(Aluno aluno : alunos){
            aluno.getExcluir().setOnAction(event->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Você deseja excluir o aluno "+ aluno.getNome());
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    TarefaAlunoDao daoTarefa = new TarefaAlunoDao();
                    AlunoDao daoAluno = new AlunoDao();
                    daoTarefa.DeletarTarefasAluno(aluno);
                    daoAluno.deletarAluno(aluno);
                    iniciarTela(turma);
                }
            }); 
            aluno.getEditar().setOnAction(event->{ 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormEditarAluno.fxml"));
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(FormEditarTurmaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                FormEditarAlunoController controller = loader.getController();
                controller.iniciarTela(aluno, turma);
                
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            });
        }
        this.obsListAluno = FXCollections.observableArrayList(alunos);
    }
    
    @FXML
    void salvaMudacas(ActionEvent event) throws IOException {
        //adicionando os dados do textfield no objeto
        turma.setEscola(txtEscola.getText());
        turma.setNome(txtTurma.getText());
        
        //atualizando a turma no banco de dados
        TurmaDao daoTurma = new TurmaDao();
        daoTurma.attTurma(turma);
        
        //trocando para a tela da salaView
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
    void ExcluirTurma(ActionEvent event) throws IOException, SQLException {
        alunosSala();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja realmente excluir a turma "+turma.getNome()+" da escola "+turma.getEscola());
        alert.setTitle("Deletar turma?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK ){
            //Instânciando os objetos que serão utilizadas
            TurmaDao turmaDao = new TurmaDao();
            AlunoDao alunoDao = new AlunoDao();
            TarefaDAO daoTarefa = new TarefaDAO();
            TarefaAlunoDao tarefaDao = new TarefaAlunoDao();
            
            //Deletando as tarefas e os alunos da sala
            for(Aluno aluno : alunos){
                try {
                    tarefaDao.DeletarTarefasAluno(aluno);
                } catch (Exception e) {
                }
                try {
                    alunoDao.deletarAluno(aluno);
                } catch (Exception e) {
                }
            }
            daoTarefa.deletarTarefasDaTurma(turma);
            //deletando a sala
            turmaDao.delTurma(turma.getIdTurma());
            
            //Carregando o fxml do salaView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SalaView.fxml"));
            root = loader.load();
            
            //Pegando o controller do salaView e atualizando  a tela
            SalaViewController controller = loader.getController();
            controller.att(event);
            
            //Trocando de telas
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    void alunosSala(){
        TurmaDao dao = new TurmaDao();
        this.alunos = dao.alunosSala(turma.getIdTurma());
    }
    
    void tarefasDaTurma(){
        TarefaDAO dao = new TarefaDAO();
        this.tarefasTurma = dao.tarefaPorTurma(turma.getIdTurma());
   }
}
