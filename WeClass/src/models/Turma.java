/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author willi
 */
public class Turma {
    private int idTurma;
    private String Nome;
    private String Escola;

    public String getNome() {
        return Nome;
    }

    public String getEscola() {
        return Escola;
    }

    public int getIdTurma() {
        return idTurma;
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
