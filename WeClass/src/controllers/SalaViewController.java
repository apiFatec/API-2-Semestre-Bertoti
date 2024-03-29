package controllers;

import dao.TarefaAlunoDao;
import dao.TarefaDAO;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Aluno;
import models.GraficoTarefaEntregue;
import models.Tarefa;
import models.TarefaAluno;
import models.Turma;
import views.WeClass;

public class SalaViewController {
    
    @FXML
    private Button btnEditTurma;
    
    @FXML
    private Button att;

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
    private Label lblAlunos;

    @FXML
    private Label lblEscola;

    @FXML
    private Label lblTurma;

    @FXML
    private TableColumn<TarefaAluno, String> nomeCol;

    @FXML
    private TableColumn<TarefaAluno, ProgressBar> progressoCol;

    @FXML
    private TableColumn<TarefaAluno, String> statusCol;

    @FXML
    private TableView<TarefaAluno> table;
    
    @FXML
    private BarChart barChartTarefa;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    
    ObservableList<Turma> listTurma;
    ObservableList<Tarefa> listTarefa;
    ObservableList<TarefaAluno> listTable;

    @FXML
    void att(ActionEvent event) {
        barChartTarefa.getData().clear();
        iniciar();
    }

    @FXML
    void btnNovaTarefa(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormTarefa.fxml"));
        root = loader.load();
        
        FormTarefaController controller= loader.getController();
        controller.btnAtualizar(event);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnNovoAluno(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormAluno.fxml"));
        root = loader.load();
        
        FormAlunoController controller= loader.getController();
        controller.btnAtualizar(event);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hlClasses(ActionEvent event) {
        cbTarefa.setItems(null);
        this.listTable = null;
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
    void showTarefas(ActionEvent event) throws IOException {
        Tarefa tarefa = new Tarefa();
        Turma turma = new Turma();
        
        tarefa = cbTarefa.getValue();
        turma = cbTurma.getValue();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/alunosView.fxml"));
        root = loader.load();
        alunosViewController controller = loader.getController();
        controller.setTurma(turma);
        controller.setTarefa(tarefa);
        controller.select2();
        controller.showTarefas(event);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void showTurma(ActionEvent event) {
        listarTarefa(cbTurma.getValue().getIdTurma());
        lblEscola.setText(cbTurma.getValue().getEscola());
        lblTurma.setText(cbTurma.getValue().getNome());
        cbTarefa.setItems(listTarefa);
        TurmaDao turmaDao = new TurmaDao();
        ArrayList<Aluno> listaAluno = new ArrayList<>();
        ArrayList<TarefaAluno> listaTarefa = new ArrayList<>();
        listaAluno = turmaDao.alunosSala(cbTurma.getValue().getIdTurma());
        TarefaAlunoDao tarefaAluno = new TarefaAlunoDao();
        for(Aluno aluno : listaAluno){
            listaTarefa.add(tarefaAluno.ProgressoAluno(aluno));
        }
        this.listTable = FXCollections.observableArrayList(listaTarefa);
        statusCol.setCellValueFactory(new PropertyValueFactory<TarefaAluno, String>("status"));
        progressoCol.setCellValueFactory(new PropertyValueFactory<TarefaAluno, ProgressBar>("barraDeProgresso"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<TarefaAluno, String>("NomeAluno"));
        table.setItems(listTable);
        
        popularGraficoTarefas(cbTurma.getValue().getIdTurma());
    }
       void popularGraficoTarefas(int id){
        barChartTarefa.getData().clear();
        TurmaDao dao = new TurmaDao();
        
        GraficoTarefaEntregue entrega = new GraficoTarefaEntregue();
        entrega = dao.getGraficoTarefa(id);
        
        XYChart.Series series2 = new XYChart.Series<>();
        
        entrega.getNumEntregas();
        series2.getData().add(new XYChart.Data<>("Entregues", entrega.getNumEntregas()));
        entrega.getNumNaoEntregue();
        series2.getData().add(new XYChart.Data<>("N�o Entregues", entrega.getNumNaoEntregue())); 
        
        barChartTarefa.getData().addAll(series2);
    }
    
    void iniciar() {
        TurmaDao turmas = new TurmaDao();
        ArrayList<Turma> listaTurma = new ArrayList<>();
        listaTurma = turmas.listTurma();
        this.listTurma = FXCollections.observableArrayList(listaTurma);
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
    
    @FXML
    void btnEditTurma(ActionEvent event) throws IOException {
        Turma turma = new Turma();
        turma = cbTurma.getValue();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormEditarTurma.fxml"));
        
        root = loader.load();
        
        FormEditarTurmaController controller = loader.getController();
        controller.iniciarTela(turma);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene =  new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

}
