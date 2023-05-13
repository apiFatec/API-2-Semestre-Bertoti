/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;
import javafx.scene.control.CheckBox;


/**
 *
 * @author Mateus
 */
public class TarefaAluno {
    private int serial;
    private Date entrega;
    private CheckBox select;
    private String nomeAluno;
    private String status;
    private Double progresso;
    private int nota;

    public int getNota() {
        return nota;
    }

    public TarefaAluno(int serial, Date entrega, String nomeAluno, String status, int nota) {
        this.serial = serial;
        this.entrega = entrega;
        this.nomeAluno = nomeAluno;
        this.status = status;
        this.nota = nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public TarefaAluno(int serial, Date entrega, int nota) {
        this.serial = serial;
        this.entrega = entrega;
        this.nota = nota;
    }

    public TarefaAluno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }


    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
  

    public TarefaAluno(int serial,String nomeAluno, String status, Date entrega) {
        this.serial = serial;
        this.nomeAluno = nomeAluno;
        this.status = status;
        this.entrega = entrega;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }
    
    public Double getProgresso(){
        return progresso;
    }
    
    public void setProgresso(Double progresso){
        this.progresso = progresso;
    }
    
    public TarefaAluno(String nomeAluno, String status, Double progresso){
        this.nomeAluno = nomeAluno;
        this.status = status;
        this.progresso = progresso;
    }
    
}
