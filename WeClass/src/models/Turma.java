/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;



/**
 *
 * @author willi
 */
public class Turma {
    private CheckBox select;
    private Button btn;
    private int idTurma;
    private String Nome;
    private String Escola;
    
    public Turma(){
        
    }

    public Button getBtn(){
        return btn;
    }
    public void setBtn(Button btn){
        this.btn = btn;
    }
    
    public Turma(String nome, String escola){
        this.Nome = nome;
        this.Escola = escola;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    public String getNome() {
        return Nome;
    }

    public String getEscola() {
        return Escola;
    }

    public int getIdTurma() {
        return idTurma;
    }

    @Override
    public String toString() {
        return Nome+" | "+Escola;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setEscola(String Escola) {
        this.Escola = Escola;
    }
    
    
}
