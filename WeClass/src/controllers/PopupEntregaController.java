/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.TarefaAlunoDao;
import dao.TarefaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import models.Tarefa;
import models.TarefaAluno;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class PopupEntregaController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private DatePicker pickerEntrega;

    @FXML
    private Spinner<Integer> spNota;
    
    private TarefaAluno tarefaAluno;
    private int serialTarefa;
    private int idTarefa;
    private Tarefa tarefa;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    void btnCancelar(ActionEvent event) throws IOException {
        stage.close();
        
    }

    @FXML
    void btnSalvar(ActionEvent event) {
        Date entrega = java.sql.Date.valueOf(pickerEntrega.getValue());
        int nota = spNota.getValue();
        tarefaAluno = new TarefaAluno(serialTarefa, entrega, nota);
        TarefaAlunoDao dao = new TarefaAlunoDao();
        dao.entregarTarefa(tarefaAluno);
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void setTarefaAluno(int serial, int id) throws SQLException{
        serialTarefa = serial;
        idTarefa = id;
        TarefaDAO dao = new TarefaDAO();
        tarefa = dao.buscarTarefa(idTarefa);
        
        pickerEntrega.setValue(LocalDate.now());
        SpinnerValueFactory<Integer> valueFactory = //
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1,tarefa.getNota(),1);
        spNota.setValueFactory(valueFactory);
    }
    void aparecerPopUp(Parent root){
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
