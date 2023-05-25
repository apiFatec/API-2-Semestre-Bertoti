/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javafx.scene.control.Button;

/**
 *
 * @author Mateus
 */
public class Aluno {

    private int ra;
    private String nome;
    private int turma;
    private Button excluir;
    private Button editar;

    public Button getExcluir() {
        return excluir;
    }

    public void setExcluir(Button excluir) {
        this.excluir = excluir;
    }

    public Button getEditar() {
        return editar;
    }

    public void setEditar(Button editar) {
        this.editar = editar;
    }
    
    public Aluno(int ra, String nome, int turma) {
        this.ra = ra;
        this.nome = nome;
        this.turma = turma;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }
    
}
