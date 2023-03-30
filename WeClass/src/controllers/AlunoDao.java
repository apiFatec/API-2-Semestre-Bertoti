/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.sql.Connection;
import factory.ConecctionFactory;

/**
 *
 * @author Mateus
 */
public class AlunoDao {
    ConecctionFactory conexao; ;
    Connection conn;
    
    public AlunoDao(){
        conexao = new ConecctionFactory();
        conn = conexao.getConnection();
    }

}
