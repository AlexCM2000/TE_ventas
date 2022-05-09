package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOimpl extends ConexionDB implements VentaDAO {

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();

            String sql = "insert into ventas (producto_id,cliente_id,fecha)values(?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Venta venta) throws Exception {
        try {
            this.conectar();
            String sql = "update ventas set producto_id=?, cliente_id=?,fecha=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from ventas where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Venta> getAll() throws Exception {
        List<Venta> lista = null;
        try {
            this.conectar();
            String sql = "select v.*,p.nombre as producto, c.nombre as cliente from ventas v ";
            sql += "LEFT JOIN productos p on v.producto_id=p.id";
            sql += " LEFT JOIN clientes c on v.cliente_id=c.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Venta>();
            while (rs.next()) {
                Venta ve = new Venta();

                ve.setId(rs.getInt("id"));
                ve.setCliente_id(rs.getInt("cliente_id"));
                ve.setProducto_id(rs.getInt("producto_id"));
                ve.setFecha(rs.getDate("fecha"));
                ve.setCliente(rs.getString("cliente"));
                ve.setProducto(rs.getString("producto"));

                lista.add(ve);

            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public Venta getById(int id) throws Exception {
        Venta ve = new Venta();
        try {
            this.conectar();
            String sql = "select * from ventas where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ve.setId(rs.getInt("id"));
                ve.setCliente_id(rs.getInt("cliente_id"));
                ve.setProducto_id(rs.getInt("producto_id"));
                ve.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return ve;
    }

}
