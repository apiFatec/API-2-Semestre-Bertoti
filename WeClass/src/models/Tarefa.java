/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.util.Date;
/**
 *
 * @author Mateus
 */
public class Tarefa {
    private int id_turma;
    private int id_tarefa;
    private String nome_tarefa;
    private String desc;
    private Date inicio_prazo;
    private Date fim_prazo;

    public int getId_turma() {
        return id_turma;
    }

    public int getId_tarefa() {
        return id_tarefa;
    }

    public String getNome_tarefa() {
        return nome_tarefa;
    }

    public String getDesc() {
        return desc;
    }

    public Date getInicio_prazo() {
        return inicio_prazo;
    }

    public Date getFim_prazo() {
        return fim_prazo;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public void setNome_tarefa(String nome_tarefa) {
        this.nome_tarefa = nome_tarefa;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setInicio_prazo(Date inicio_prazo) {
        this.inicio_prazo = inicio_prazo;
    }

    public void setFim_prazo(Date fim_prazo) {
        this.fim_prazo = fim_prazo;
    }
    
    
    
}
