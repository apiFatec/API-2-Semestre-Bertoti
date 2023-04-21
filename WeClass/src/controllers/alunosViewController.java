/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.TarefaAlunoDao;
import dao.TarefaDAO;
import dao.TurmaDao;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Tarefa;
import models.TarefaAluno;
import models.Turma;
import views.WeClass;

/**
 *
 * @author Mateus
 */
public class alunosViewController implements Initializable{
    
    @FXML
    private ComboBox<Tarefa> cbTarefa;

    @FXML
    private ComboBox<Turma> cbTurma;
    @FXML
    private Hyperlink hlCalendar;

    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;
    
    @FXML
    private TableColumn<TarefaAluno, String> nomeCol;

    @FXML
    private TableColumn<TarefaAluno, String> statusCol;
    
    @FXML
    private TableColumn<TarefaAluno, Date> entregaCol;

    @FXML
    private TableView<TarefaAluno> table;

    @FXML
    void hlClasses(ActionEvent event) {

    }

    @FXML
    void hlHome(ActionEvent event) {
        WeClass.mudarTela("main");
    }

    @FXML
    void selectTurma(ActionEvent event) {
         WeClass.mudarTela("viewAlunos");
    }
    
    ObservableList<Turma> listTurma;
    ObservableList<Tarefa> listTarefa;
    ObservableList<TarefaAluno> listTable;
    
    @FXML
    void select(ActionEvent event) {
        listarTarefa(cbTurma.getValue().getIdTurma());
        cbTarefa.setItems(listTarefa);
    }

    @FXML
    void showTarefas(ActionEvent event) {
        listarTable(cbTarefa.getValue().getId());
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("entrega"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        
        table.setItems(listTable);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listarTurma();
        cbTurma.setItems(listTurma);
    } 
    
    public void listarTurma(){
        TurmaDao dao = new TurmaDao();
        this.listTurma = FXCollections.observableArrayList(dao.listTurma());
    }
    
    public void listarTarefa(int id){
        TarefaDAO dao = new TarefaDAO();
        this.listTarefa = FXCollections.observableArrayList(dao.tarefaPorTurma(id));
    }
    
    public void listarTable(int id){
        TarefaAlunoDao dao =new TarefaAlunoDao();
        this.listTable = FXCollections.observableArrayList(dao.listarPorTarefa(id));
    }
}
