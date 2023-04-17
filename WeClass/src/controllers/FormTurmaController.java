/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Turma;

/**
 * FXML Controller class
 *
 * @author Mateus
 */
public class FormTurmaController implements Initializable {

    /**
     * Initializes the controller class.
     */
      
    @FXML
    private AnchorPane btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<Turma, String> escolaCol;

    @FXML
    private TableColumn<Turma, String> nomeCol;

    @FXML
    private DatePicker pickerEnt;

    @FXML
    private TableColumn<Turma, Boolean> selectCol;


    @FXML
    private TableView<Turma> tableTurma;

    @FXML
    private TextArea txtDesc;

    @FXML
    private TextField txtNome;

    @FXML
    void btnCancel(MouseEvent event) {

    }

    @FXML
    void btnSalvar(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        escolaCol.setCellValueFactory(new PropertyValueFactory<>("Escola"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>(""));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableTurma.setItems(listaDeTurma());
    }
        
        
        
        private ObservableList<Turma> listaDeTurma() {
            return FXCollections.observableArrayList(
                new Turma("ADS 2", "FATEC"),
                new Turma("ADS 1 ","FATEC"),
                new Turma("ADS 3", "FATEC"));
    }
}
