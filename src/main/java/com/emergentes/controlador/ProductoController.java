
package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {
            Producto pro = new Producto();
            int id;
            ProductoDAO dao = new ProductoDAOimpl();
            String action = (request.getParameter("action") != null ? request.getParameter("action") : "view");

            switch (action) {
                case "add":
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pro = dao.getById(id);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);

                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("ProductoController");
                    break;
                case "view":
                    //obtener la lista de registros
                    List<Producto> lista = dao.getAll();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
               int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        float precio = Float.parseFloat(request.getParameter("precio"));

        Producto pro = new Producto();

        pro.setId(id);
        pro.setNombre(nombre);
        pro.setDescripcion(descripcion);
        pro.setPrecio(precio);

        ProductoDAO dao = new ProductoDAOimpl();
        if (id == 0) {
            try {
                //nuevo

                dao.insert(pro);
            } catch (Exception ex) {
                System.out.println("error al insertar" + ex.getMessage());
            }
        } else {
            try {
                //modificar
                dao.update(pro);
            } catch (Exception ex) {
                System.out.println("error al modificar" + ex.getMessage());
            }
        }
        response.sendRedirect("ProductoController");

    }
}
