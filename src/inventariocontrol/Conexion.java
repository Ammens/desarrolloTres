package inventariocontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_gestor";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private JTable tabla_productos;
    
    
    

    public Conexion(JTable tablaproductos) {
       
        tabla_productos=tablaproductos;
        
    }
    
    
    
    

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    

public void cargarDatos() {
    
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Stock");
    modelo.addColumn("Proveedor");
    modelo.addColumn("Precio");

    try (Connection con = Conexion.getConexion();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT * FROM productos")) {

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getInt("stock"),
                rs.getString("proveedor"),
                rs.getDouble("precio")
            });
        }
        
        tabla_productos.setModel(modelo);

    } catch (Exception e) {
        e.printStackTrace();
    }
}







}