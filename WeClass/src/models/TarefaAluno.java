/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;


/**
 *
 * @author Mateus
 */
public class TarefaAluno {
    private String nomeAluno;
    private String status;
    private Date entrega;

    public TarefaAluno(String nomeAluno, String status, Date entrega) {
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
    
}
