/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mateus
 */
public class WeClass extends Application {
    
    private static Scene main;
    private static Scene formAluno;
    private static Scene formTarefa;
    private static Scene formTurma;
    private static Scene viewsAlunos;
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage = primaryStage;
        Parent fxmlAlunosViews = FXMLLoader.load(getClass().getResource("alunosView.fxml"));
        viewsAlunos = new Scene(fxmlAlunosViews);
        Parent fxmlFormAluno = FXMLLoader.load(getClass().getResource("FormAluno.fxml"));
        formAluno = new Scene(fxmlFormAluno);
        Parent fxmlFormTarefa = FXMLLoader.load(getClass().getResource("FormTarefa.fxml"));
        formTarefa = new Scene(fxmlFormTarefa);
        Parent fxmlFormTurma = FXMLLoader.load(getClass().getResource("FormTurma.fxml"));
        formTurma = new Scene(fxmlFormTurma);
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("Main.fxml"));
        main = new Scene(fxmlMain);
        
        primaryStage.setScene(main);
        primaryStage.show();
    }

    public static void mudarTela(String scr){
        switch(scr){
            case "main":
                stage.setScene(main);
                break;
            case "formAluno":
                stage.setScene(formAluno);
                break;
            case "formTarefa":
                stage.setScene(formTarefa);
                break;
            case "formTurma":
                stage.setScene(formTurma);
                break;
            case "viewAlunos":
                stage.setScene(viewsAlunos);
                break;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
