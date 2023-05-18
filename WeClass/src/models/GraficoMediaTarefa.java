/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author talison
 */
public class GraficoMediaTarefa {
    
    private String situacao;
    private int media;
    
    public GraficoMediaTarefa(String situacao, int media){
        this.situacao = situacao;
        this.media = media;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }
    
    public String toString() {
        return "Situa??o" + this.situacao;
    }
    
}
