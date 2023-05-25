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
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import models.Aluno;
import models.GraficoMediaTarefa;
import models.GraficoTarefaEntregue;

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
                //salvando os valores do objeto
                int id=rs.getInt("idTurma");
                String nome=rs.getString("nomeTurma");
                String escola=rs.getString("escola");
                
                //criando o objeto e adicionando valores aos seus atributos
                Turma turma=new Turma();
                turma.setEscola(escola);
                turma.setNome(nome);
                turma.setIdTurma(id);
                
                //criando um botão e setando o nome da turma e o estilo
                Button btn = new Button();
                btn.setText(nome+" | "+escola);
                btn.setStyle("-fx-background-color:   #1590c4; -fx-text-fill: #fff");
                btn.setMinWidth(280);
                btn.setCursor(Cursor.HAND);
                
                //adicionando o button no objeto turma
                turma.setBtn(btn);
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
                Button btnEdit = new Button();
                Button btnDel = new Button();
                btnEdit.setText("Editar");
                btnEdit.setStyle("-fx-background-color: #1590c4; -fx-text-fill: #fff");
                btnEdit.setCursor(Cursor.HAND);
                btnEdit.setMinWidth(90);
                
                btnDel.setText("Deletar");
                btnDel.setStyle("-fx-text-fill: #fff; -fx-background-color: #FF2E24; ");
                btnDel.setCursor(Cursor.HAND);
                btnDel.setMinWidth(90);
                
                aluno.setExcluir(btnDel);
                aluno.setEditar(btnEdit);
                
                alunos.add(aluno);
            }
            return alunos;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
    
    public ArrayList<GraficoMediaTarefa> getNotaPorPrazo(int id) {
        String sql = "SELECT CASE WHEN DataEntrega < data_fim THEN 'Antes do prazo' WHEN DataEntrega = data_fim THEN 'No prazo' ELSE 'Depois do prazo' END AS situacao_entrega, AVG(alunotarefa.nota) AS media FROM alunotarefa JOIN tarefa ON alunotarefa.Tarefa_idTarefa = tarefa.idTarefa WHERE tarefa.idTarefa = ? GROUP BY situacao_entrega;";
        ArrayList<GraficoMediaTarefa> mediaTarefa =new ArrayList<>(); 
       
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id); 
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String situacao = rs.getString("situacao_entrega");
                int media = rs.getInt("media");
                GraficoMediaTarefa tarefa = new GraficoMediaTarefa(situacao, media);
                mediaTarefa.add(tarefa);
            }              
            return mediaTarefa;
                   
        } catch (SQLException e) {
            
        }
        return null;
        
    }
    
    public GraficoTarefaEntregue getGraficoTarefa(int idTurma){
        String sql = "SELECT * FROM weclass.alunotarefa where Aluno_Turma_idTurma = ?;";
        int TarefasEntregues = 0;
        int TarefasNaoEntregues = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idTurma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                if( rs.getString("status").equals("Entregue") || rs.getString("status").equals("entregue")){
                    TarefasEntregues= TarefasEntregues+1;
                }
                else{
                    TarefasNaoEntregues = TarefasNaoEntregues+1;
                }
            }
            GraficoTarefaEntregue grafico = new GraficoTarefaEntregue(TarefasEntregues, TarefasNaoEntregues);
            return grafico;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao montar o gráfico de entregas de atividades "+ e.getMessage());
        }
        return null;
    }
    
    public GraficoTarefaEntregue getGraficoTarefaporAtividade(int idTurma){
        String sql = "SELECT * FROM weclass.alunotarefa where Tarefa_idTarefa = ?;";
        int TarefasEntregues = 0;
        int TarefasNaoEntregues = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idTurma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                if( rs.getString("status").equals("Entregue") || rs.getString("status").equals("entregue")){
                    TarefasEntregues= TarefasEntregues+1;
                }
                else{
                    TarefasNaoEntregues = TarefasNaoEntregues+1;
                }
            }
            GraficoTarefaEntregue grafico = new GraficoTarefaEntregue(TarefasEntregues, TarefasNaoEntregues);
            return grafico;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao montar o gráfico de entregas de atividades "+ e.getMessage());
        }
        return null;
    }
}