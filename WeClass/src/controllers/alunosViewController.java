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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
    private Label lbEscola;

    @FXML
    private Label lbTurma;
    
    @FXML
    private Button btnNovaTarefa;
    
    @FXML
    private Button btnNovoAluno;
    
    @FXML
    private ComboBox<Tarefa> cbTarefa;

    @FXML
    private ComboBox<Turma> cbTurma;


    @FXML
    private Hyperlink hlClasses;

    @FXML
    private Hyperlink hlHome;
    
    @FXML
    private TableColumn<TarefaAluno, String> selectCol;
    
    @FXML
    private Button btnEntrega;
    
    @FXML
    private TableColumn<TarefaAluno, String> nomeCol;

    @FXML
    private TableColumn<TarefaAluno, String> statusCol;
    
    @FXML
    private TableColumn<TarefaAluno, Date> entregaCol;

    @FXML
    private TableView<TarefaAluno> table;
    
    @FXML
    private Button att;

    @FXML
    void hlClasses(ActionEvent event) {
        cbTarefa.setItems(null);
        this.listTable = null;
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
        lbEscola.setText(cbTurma.getValue().getEscola());
        lbTurma.setText(cbTurma.getValue().getNome());
        
    }
    
      @FXML
    void btnEntrega(ActionEvent event) {
        for(TarefaAluno a : listTable){
            if(a.getSelect().isSelected() && a.getEntrega() == null){
                TarefaAlunoDao dao = new TarefaAlunoDao();
                dao.entregarTarefa(a.getSerial());
            }
        }
        listarTable(cbTarefa.getValue().getId());
        for(int i = 0; i<listTable.size(); i++){
            CheckBox cb = new CheckBox("");
            listTable.get(i).setSelect(cb);
            if(listTable.get(i).getEntrega() != null){
                listTable.get(i).getSelect().setSelected(true);
            }
        }
        selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("entrega"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        
        table.setItems(listTable);
    }

    @FXML
    void showTarefas(ActionEvent event) {
        listarTable(cbTarefa.getValue().getId());
        for(int i = 0; i<listTable.size(); i++){
            CheckBox cb = new CheckBox("");
            listTable.get(i).setSelect(cb);
            if(listTable.get(i).getEntrega() != null){
                listTable.get(i).getSelect().setSelected(true);
            }
        }
        selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("entrega"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        
        table.setItems(listTable);
        
    }
    
    @FXML
    void att(ActionEvent event) {
        listarTurma();
        cbTurma.setItems(listTurma);
    }

    
    @FXML
    void btnNovaTarefa(ActionEvent event) {
        WeClass.mudarTela("formTarefa");
        
    }

    @FXML
    void btnNovoAluno(ActionEvent event) {
        WeClass.mudarTela("formAluno");
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
