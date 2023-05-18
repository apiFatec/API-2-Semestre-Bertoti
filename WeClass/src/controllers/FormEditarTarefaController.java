/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.TarefaDAO;
import dao.TurmaDao;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Tarefa;
import models.Turma;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormEditarTarefaController implements Initializable {

    private Tarefa tarefa;
            
            
    @FXML
    private Button btnAtualizar;

    @FXML
    private AnchorPane btnCancelar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker pickerEnt;

    @FXML
    private Spinner<Integer> spNota;

    @FXML
    private TextArea txtDesc;

    @FXML
    private TextField txtNome;
    
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    void btnDeletar(ActionEvent event) throws SQLException, IOException {
        TurmaDao turmadao = new TurmaDao();
        TarefaDAO dao = new TarefaDAO();
        dao.excluirTarefa(tarefa);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/alunosView.fxml"));
        root =loader.load();
        
        alunosViewController alunosView = loader.getController();
        alunosView.att(event);
        
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/alunosView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void btnAtualizar(ActionEvent event) throws SQLException, IOException{
        TurmaDao turmadao = new TurmaDao();
        Turma turma = new Turma();
        turma = turmadao.retornaTurma(tarefa.getIdTurma());
        
        TarefaDAO dao = new TarefaDAO();
        this.tarefa.setNomeTarefa(txtNome.getText());
        this.tarefa.setDescricao(txtDesc.getText());
        this.tarefa.setNota(spNota.getValue());
        this.tarefa.setDataFim(java.sql.Date.valueOf(pickerEnt.getValue()));
        dao.atualizarTarefa(tarefa);

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewa/alunosView.fxml"));
        root = loader.load();
        
        alunosViewController controlador = loader.getController();
        controlador.showTarefas(event);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void tarefa(Tarefa tarefa){
        this.tarefa = tarefa;
        txtDesc.setText(tarefa.getDescricao());
        txtNome.setText(tarefa.getNomeTarefa());
        pickerEnt.setValue(tarefa.getDataFim().toLocalDate());
        SpinnerValueFactory<Integer> valueFactory = //
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,tarefa.getNota());
        spNota.setValueFactory(valueFactory);
        
    }
}
