/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Tarefa {
    private int id;
    private String nomeTarefa;
    private String descricao;
    private float nota;
    private Date dataInicio;
    private Date dataFim;
    private int idTurma;

    public Tarefa() {}

    public Tarefa(int id, String nomeTarefa, String descricao, float nota, Date dataInicio, Date dataFim, int idTurma) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.nota = nota;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idTurma = idTurma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    @Override
    public String toString() {
        return "Tarefa [id=" + id + ", nomeTarefa=" + nomeTarefa + ", descricao=" + descricao + ", nota=" + nota
                + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", idTurma=" + idTurma + "]";
    }
}