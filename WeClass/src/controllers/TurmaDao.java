/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import factory.ConnectionFactory;
import models.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Mateus
 */
public class TurmaDao {
    ConnectionFactory conexao;
    Connection conn;
    
    public TurmaDao(){
        conexao = new ConnectionFactory();
        conn = conexao.getConnection();
    }
    
    public void addTurma(Turma turma){
        String sql = "INSERT INTO weclass.turma (nome, escola) VALUES (?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, turma.getNome_turma());
            stmt.setString(2, turma.getEscola());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Turma adicionada com sucesso!");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao adicionar a turma "+ e.getMessage());
        }
    }
    
    public void attTurma(Turma turma){
        String sql = "UPDATE weclass.turma SET nome = ?, SET escola = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, turma.getNome_turma());
            stmt.setString(2, turma.getEscola());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Turma atualizada com sucesso! ");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar a turma "+ e.getMessage());
        }
    }
    
    public void delTurma( int id){
        String sql = "DELETE weclass.turma where id_turma = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Turma deletada com sucesso!");
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao deletar a turma"+ e.getMessage());
        }
    }
}
