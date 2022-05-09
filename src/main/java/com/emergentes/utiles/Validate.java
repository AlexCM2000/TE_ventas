
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validate extends ConexionDB{
    
    Connection con = this.conectar();
    PreparedStatement ps;
    
    public boolean ckeckUser(String email, String password){
          boolean resultado = false;
        try {
          
            String sql="select * from Usuarios where email=? and password=sha1(?)";
            ps= con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            resultado = rs.next();
           
        } catch (SQLException ex) {
            System.out.println("error al autentificacion"+ex.getMessage());
        }
         return resultado;
    }
}
