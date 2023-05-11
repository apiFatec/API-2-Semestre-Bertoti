/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import models.Turma;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Aluno;

/**
 *
 * @author willi
 */
public class TurmaDao {
    ConnectionFactory Factory;
    Connection con; 
    
    public TurmaDao()   {
        Factory=new ConnectionFactory();
        con=Factory.getConnection();
        
    }
    
    public void addTurma(Turma turma) {
        String sql="INSERT INTO `weclass`.`turma` (`nomeTurma`, `escola`) VALUES (?,?);";
        try {
            PreparedStatement stmt=con.prepareStatement(sql); 
            stmt.setString(1,turma.getNome());
            stmt.setString(2,turma.getEscola());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao adcionar Turma" + e.getMessage());
            }
    }
    public void attTurma(Turma turma) {
        String sql="UPDATE `weclass`.`turma` SET `nomeTurma` = ?, `escola` = ? WHERE (`idTurma` = ?);";
        try {
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,turma.getNome());
            stmt.setString(2,turma.getEscola());
            stmt.setInt(3,turma.getIdTurma());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar Turma" +e.getMessage());
            
        }
    }
    public void delTurma(int id) {
        String sql="DELETE FROM `weclass`.`turma` WHERE (`idTurma` = ?);";
        try {
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao Exluir Turma"+ e.getMessage());
           
        }
    }
    public ArrayList<Turma> listTurma() {
        String sql="SELECT * FROM weclass.turma;";
        ArrayList<Turma> list =new ArrayList<>(); 
        try {
            PreparedStatement stmt=con.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("idTurma");
                String nome=rs.getString("nomeTurma");
                String escola=rs.getString("escola");
                Turma turma=new Turma();
                turma.setEscola(escola);
                turma.setNome(nome);
                turma.setIdTurma(id);
                list.add(turma);
                
          
            }
            return list;
            
                 
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao Buscar Turma" + e.getMessage());
        }
        return null;
            
}     
    public Turma retornaTurma(int id){
        String sql = "SELECT * FROM weclass.turma WHERE idTurma = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            String nome=rs.getString("nomeTurma");
            String escola=rs.getString("escola");
            Turma turma=new Turma();
            turma.setEscola(escola);
            turma.setNome(nome);
            turma.setIdTurma(id);
            return turma;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public ArrayList<Aluno> alunosSala(int id){
        String sql = "SELECT * FROM weclass.aluno WHERE Turma_idTurma = ?";
        ArrayList<Aluno> alunos = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                int ra = rs.getInt("RA");
                Aluno aluno = new Aluno(ra, nome,id);
                alunos.add(aluno);
            }
            return alunos;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
}