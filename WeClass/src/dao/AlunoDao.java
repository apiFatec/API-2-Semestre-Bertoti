/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import factory.ConnectionFactory;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import models.Aluno;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author Mateus
 */
public class AlunoDao {
    Connection conn;
    ConnectionFactory conexao;
    
    public AlunoDao(){
        conexao = new ConnectionFactory();
        conn = conexao.getConnection();
    }
 
    public void adicionarAluno(Aluno aluno){
        String sql = "INSERT INTO weclass.aluno(RA, nome, Turma_idTurma) VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getRa());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getTurma());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar aluno " + e.getMessage());
        }
    }
    
    public void atualizarAluno(Aluno aluno){
        String sql = "UPDATE weclass.aluno SET nome = ?, SET Turma_idTurma = ? WHERE RA = ?";
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(3, aluno.getRa());
           stmt.setString(1, aluno.getNome());
           stmt.setInt(2, aluno.getTurma());
           stmt.execute();
           stmt.close();
        } catch(SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno " + e.getMessage());
        }
    }
    
    public void deletarAluno(Aluno aluno){
        String sql = "DELETE weclass.aluno WHERE RA = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getRa());
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao deletar aluno" + e.getMessage());
        }
    }
    
    public ArrayList<Aluno> listarAluno(){
        String sql = "SELECT * FROM weclass.aluno";
        ArrayList<Aluno> list = new ArrayList<Aluno>();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            int ra = rs.getInt("RA");
            String nome = rs.getString("nome");
            int turma = rs.getInt("Turma_idTurma");
            
            Aluno aluno = new Aluno(ra, nome, turma);
            list.add(aluno);
            }
            return list;
        } catch(SQLException e){
            return null; 
        }

    }
}
