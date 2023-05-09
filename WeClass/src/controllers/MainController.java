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
import dao.TarefaDAO;
import dao.TurmaDao;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Tarefa;
import models.Turma;



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
    private TableView<Tarefa> tableAtividade;

    @FXML
    private TableView<Turma> tableTurma;
    
    @FXML
    private TableColumn<Tarefa, String> AtividadeCol;
    
    @FXML
    private TableColumn<Tarefa, Date> entregaCol;

    @FXML
    private TableColumn<Turma, String> TurmaCol;
    
    @FXML
    private Button btnAtualizar;
    
    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;

    @FXML
    private Label lblAtividade;

    @FXML
    private Label lblTurmas;
    
    private Parent root;
    private Scene scene;
    private Stage stage;

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
    
    @FXML
    void btnTurma(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/FormTurma.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void btnTarefa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/FormTarefa.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    ObservableList<Turma> listTurma;
    ObservableList<Tarefa> listTarefa;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listarTurma();
         try {
             listarTarefa();
         } catch (SQLException ex) {
             Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
         }
        AtividadeCol.setCellValueFactory(new PropertyValueFactory<>("nomeTarefa"));
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        
        tableAtividade.setItems(listTarefa);
        
        TurmaCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableTurma.setItems(listTurma);
       
    }    
    
    public void listarTurma(){
        TurmaDao dao = new TurmaDao();
        this.listTurma = FXCollections.observableArrayList(dao.listTurma());
    }
        public void listarTarefa() throws SQLException{
        TarefaDAO dao = new TarefaDAO();
        this.listTarefa = FXCollections.observableArrayList(dao.listarTarefas());
    }
        
    @FXML
    void btnAtualizar(ActionEvent event) {
        listarTurma();
         try {
             listarTarefa();
         } catch (SQLException ex) {
             Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
         }
        AtividadeCol.setCellValueFactory(new PropertyValueFactory<>("nomeTarefa"));
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        
        tableAtividade.setItems(listTarefa);
        
        TurmaCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableTurma.setItems(listTurma);
    }

}
