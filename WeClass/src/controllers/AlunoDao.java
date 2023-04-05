/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.sql.Connection;
import factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Aluno;

/**
 *
 * @author Mateus
 */
public class AlunoDao {
    ConnectionFactory conexao; ;
    Connection conn;
    
    public AlunoDao(){
        conexao = new ConnectionFactory();
        conn = conexao.getConnection();
    }
     
    public void addAluno(Aluno aluno){
       String sql = "INSERT INTO weclass.aluno(RA,nome, turma) VALUES"+"(?,?,?)";
       
       try {
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(3, aluno.getTurma());
           stmt.setString(2, aluno.getNome());
           stmt.setInt(1, aluno.getRA());
           stmt.execute();
           stmt.close();
           JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!");
           
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"Erro ao adicionar aluno"+ e.getMessage());
       }
   }
    public void attAluno(Aluno aluno){
        String sql = "UPDATE weclass.aluno SET nome = ?, SET turma = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getTurma());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Erro ao atualizar aluno"+ e.getMessage());
        }
    }
    
    public void delAluno(int id){
        String sql = "Delete weclass.aluno where RA = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o aluno "+e.getMessage());
        }
    }
    
}
