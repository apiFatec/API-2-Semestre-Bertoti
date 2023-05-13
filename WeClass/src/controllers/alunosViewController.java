/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.TarefaAlunoDao;
import dao.TarefaDAO;
import dao.TurmaDao;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.GraficoMediaTarefa;
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
    private BarChart barChart;
    
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
    private TableColumn<TarefaAluno, Integer> notaCol;
    
    @FXML
    private TableColumn<TarefaAluno, String> nomeCol;

    @FXML
    private TableColumn<TarefaAluno, String> statusCol;
    
    @FXML
    private TableColumn<TarefaAluno, Date> entregaCol;

    @FXML
    private TableView<TarefaAluno> table2;
    
    @FXML
    private Button att;
    
    @FXML
    private Label lblPrazo;
    
    private Stage stage2;
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    ObservableList<Turma> listTurma;
    ObservableList<Tarefa> listTarefa;
    ObservableList<TarefaAluno> listTable;

    @FXML
    void hlClasses(ActionEvent event) {
        cbTarefa.setItems(null);
        this.listTable = null;
    }
    
    

    public void setCbTurma(Turma turma){
        cbTurma.setValue(turma);
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
    void btnEditTarefa(ActionEvent event) throws IOException {      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormEditarTarefa.fxml"));
        root =loader.load();
        
        FormEditarTarefaController controller = loader.getController();
        controller.tarefa(cbTarefa.getValue());
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectTurma(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SalaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @FXML
    void select(ActionEvent event) throws IOException {
        Turma turma = new Turma();
        turma = cbTurma.getValue();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SalaView.fxml"));
        root = loader.load();
        SalaViewController controller = loader.getController();
        controller.iniciar();
        controller.setTurma(turma);
        controller.showTurma(event);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    void select2(){
        table2.setItems(null);
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
                dao.entregarTarefa(a);
            }
            else if(a.getSelect().isSelected() == false){
                TarefaAlunoDao dao = new TarefaAlunoDao();
                dao.reverterTarefa(a);
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
        notaCol.setCellValueFactory(new PropertyValueFactory<>("nota"));
        
        table2.setItems(listTable);
        showTarefas(event);
    }

    @FXML
    void showTarefas(ActionEvent event) {
        listarTable(cbTarefa.getValue().getId());
        lblPrazo.setText("Prazo de entrega: "+cbTarefa.getValue().getDataFim().toString());
        for(int i = 0; i<listTable.size(); i++){
            CheckBox cb = new CheckBox("");
            int serial = listTable.get(i).getSerial();
            int id = cbTarefa.getValue().getId();
            //PopUp
            cb.setOnMouseClicked(e ->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PopupEntrega.fxml"));
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(alunosViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                PopupEntregaController controller = loader.getController();
                try {
                    controller.setTarefaAluno(serial,id);
                    controller.aparecerPopUp(root);
                } catch (SQLException ex) {
                    Logger.getLogger(alunosViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            });
            
            listTable.get(i).setSelect(cb);
            if(listTable.get(i).getEntrega() != null){
                listTable.get(i).getSelect().setSelected(true);
            }
        }
        selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("entrega"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        notaCol.setCellValueFactory(new PropertyValueFactory<>("nota"));

        table2.setItems(listTable);
        popularGrafico(cbTarefa.getValue().getId());
    }
    
    void mostrarTarefasTabela(){
        listarTable(cbTarefa.getValue().getId());
        lblPrazo.setText("Prazo de entrega: "+cbTarefa.getValue().getDataFim().toString());
        for(int i = 0; i<listTable.size(); i++){
            CheckBox cb = new CheckBox("");
            int serial = listTable.get(i).getSerial();
            int id = cbTarefa.getValue().getId();
            //PopUp
            cb.setOnMouseClicked(e ->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PopupEntrega.fxml"));
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(alunosViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                PopupEntregaController controller = loader.getController();
                try {
                    controller.setTarefaAluno(serial,id);
                    controller.aparecerPopUp(root);
                } catch (SQLException ex) {
                    Logger.getLogger(alunosViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            });
            
            listTable.get(i).setSelect(cb);
            if(listTable.get(i).getEntrega() != null){
                listTable.get(i).getSelect().setSelected(true);
            }
        }
        selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));
        entregaCol.setCellValueFactory(new PropertyValueFactory<>("entrega"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        notaCol.setCellValueFactory(new PropertyValueFactory<>("nota"));

        table2.setItems(listTable);
        popularGrafico(cbTarefa.getValue().getId());
    }
    void fecharPopup(){
        stage2.close();
        Platform.exit();
        System.exit(0);
    }
    
    void popularGrafico(int id) {
        
        TurmaDao dao = new TurmaDao();

        ArrayList<GraficoMediaTarefa> mediaTarefa = new ArrayList<>();
        mediaTarefa = dao.getNotaPorPrazo(id);
        
        // Creating Series instance
       
        XYChart.Series series1 = new XYChart.Series();
        for (GraficoMediaTarefa media:mediaTarefa) {  
            series1.getData().add(new XYChart.Data(media.getSituacao(), media.getMedia())); 
            System.out.println(media.getSituacao() + " "+ media.getMedia());
        }
        barChart.getData().addAll(series1);
        
        // Adding series in bar chart

        table2.setItems(listTable);
        
    }
    
    @FXML
    void att(ActionEvent event) {
        select2();
        table2.setItems(null);
        barChart.getData().clear();
        popularGrafico(cbTarefa.getValue().getId());
        showTarefas(event);
    }

    void atualizarTela(){
        select2();
        table2.setItems(null);
        popularGrafico(cbTarefa.getValue().getId());
        mostrarTarefasTabela();
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
    
    void setTurma(Turma turma){
        cbTurma.setValue(turma);
    }
    
    void setTarefa(Tarefa tarefa){
        cbTarefa.setValue(tarefa);
    }
}
