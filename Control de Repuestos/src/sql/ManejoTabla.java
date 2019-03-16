/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * AND open the template in the editor.
 */
package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author joses
 */
public class ManejoTabla {

    //metodo utilizado para mostrar tablas en la interfaz grafica
    public static void mostrarTablas(JTable tabla, int consulta, String nombre) throws ClassNotFoundException {

        DefaultTaBleModel modelo = new DefaultTaBleModel();//instancia para mostrar las tablas
        tabla.setModel(modelo);//asignamos a la tabla de la interfaz que sera de tipo default model
        PreparedStatement ps = null;
        ResultSet rs = null;

        //variables para almacenar la sentencia sql segun la necesidad de la tabla a mostrar
        //se explica para que se usa cada varibale en el case correspondiente
        String query = "SELECT Nombre, Usuario FROM USUARIO WHERE Tipo=2";

        String query2 = "SELECT Nombre, Estado,Admin FROM PROYECTO WHERE Admin is null";

        String query3 = "SELECT IdProyecto,Nombre, Estado,Admin FROM PROYECTO WHERE Estado='Activo'";

        String query4 = "SELECT Idproyecto,Nombre, Estado,Admin FROM PROYECTO WHERE Admin=?";

        String query5 = "SELECT Nombre, Etapas FROM TIPO_CASO";

        String query6 = "SELECT IdProyecto,Nombre, Estado,Admin FROM PROYECTO "
                + "WHERE Admin=? AND Estado='Activo'";

        String query7 = "SELECT Nombre, Usuario FROM USUARIO WHERE Tipo=3";

        String query8 = "select c.idCaso, c.nombre,c.Estado, c.Idproyecto AS proyecto "
                + "FROM caso c,proyecto p "
                + "WHERE c.idproyecto=p.idproyecto AND p.ADMIN=? "
                + "AND c.estado='Activo' AND c.Finalizado='NO' AND p.Estado='Activo'";

        String query9 = "SELECT Nombre, Estado,Admin FROM PROYECTO WHERE Admin is not null";

        String query10 = "SELECT IdProyecto,Nombre, Estado,Admin FROM PROYECTO WHERE Estado='Inactivo'";

        String query11 = "SELECT C.IdCaso, C.Nombre, C.Fecha_Limite,c.Estado, C.Tipo "
                + "FROM CASO C, PROYECTO P"
                + " WHERE c.idproyecto=? AND P.IDPROYECTO=C.IDproyecto ";

        String query12 = "select etapa.idetapa, etapa.nombre, etapa.Costo, etapa.Desarrollador, "
                + "etapa.Aprobacion from etapa inner join caso on caso.idcaso=etapa.idcaso "
                + "inner join proyecto on proyecto.idproyecto=caso.idproyecto AND CASO.Finalizado='NO' "
                + "where proyecto.estado='activo' AND etapa.aprobacion='Rechazada' or etapa.aprobacion is null "
                + "AND etapa.comentario is null AND caso.estado='activo' "
                + "AND etapa.desarrollador=? ";

        String query13 = "SELECT etapa.idEtapa,etapa.IdCaso,etapa.Nombre, etapa.Desarrollador,etapa.Costo,"
                + "etapa.horas,etapa.Comentario FROM ETAPA "
                + "INNER JOIN CASO ON caso.idcaso=etapa.idcaso inner join proyecto "
                + "on proyecto.idproyecto=caso.idproyecto "
                + "WHERE proyecto.estado='activo' AND CASO.Finalizado='NO' "
                + "AND etapa.aprobacion='rechazada' or etapa.aprobacion is null AND etapa.desarrollador is not null "
                + "AND etapa.comentario is not null AND proyecto.admin=?";

        try {
//swicth que verifica que ,ostraremos en la tabla
            switch (consulta) {
                case 1://nos muestra los administrdores de proyectos
                    ps = Conexion.getConection().prepareStatement(query);
                    rs = ps.executeQuery();
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Usuario");
                    break;
                case 2://nos mostrara los proyectos sin administrador asignador
                    ps = Conexion.getConection().prepareStatement(query2);
                    rs = ps.executeQuery();
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Administrador");
                    break;
                case 3://nos mostrara los proyectos activos
                    ps = Conexion.getConection().prepareStatement(query3);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdProyecto");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    break;
                case 4:// nos muestra los proyectos asignados a cierto administrador
                    ps = Conexion.getConection().prepareStatement(query4);
                    ps.setString(1, nombre);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdProyecto");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Administrador");
                    break;
                case 5://muestra los tipos de caos
                    ps = Conexion.getConection().prepareStatement(query5);
                    rs = ps.executeQuery();
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Etapas");
                    break;
                case 6://muestra los proyectos asignados a ciertos admins y que esten activos
                    ps = Conexion.getConection().prepareStatement(query6);
                    ps.setString(1, nombre);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdProyecto");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Administrador");
                    break;
                case 7://muestra los desarrolladores
                    ps = Conexion.getConection().prepareStatement(query7);
                    rs = ps.executeQuery();
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Usuario");
                    break;
                case 8:// muestra los casos que estan activos asignados a los proyectos de cierto admin
                    ps = Conexion.getConection().prepareStatement(query8);
                    ps.setString(1, nombre);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdCaso");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Proyecto");
                    break;
                case 9://muestra los proyectos que tienen un admin asignado
                    ps = Conexion.getConection().prepareStatement(query9);
                    rs = ps.executeQuery();
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Administrador");
                    break;
                case 10://muestra los proyectos con estado inactivo
                    ps = Conexion.getConection().prepareStatement(query10);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdProyecto");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Estado");
                    break;
                case 11://muestra los casos de cierto proyecto
                    ps = Conexion.getConection().prepareStatement(query11);
                    ps.setInt(1, Integer.parseInt(nombre));
                    rs = ps.executeQuery();
                    modelo.addColumn("IdCaso");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Fecha_Limite");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Tipo");
                    break;
                case 12://muestra los proyectos asignados a ciertos admins y que esten activos
                    ps = Conexion.getConection().prepareStatement(query12);
                    ps.setString(1, nombre);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdEtapa");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Costo");
                    modelo.addColumn("Desarrollador");
                    break;
                case 13:
                    ps = Conexion.getConection().prepareStatement(query13);
                    ps.setString(1, nombre);
                    rs = ps.executeQuery();
                    modelo.addColumn("IdEtapa");
                    modelo.addColumn("IdCaso");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Desarrollador");
                    modelo.addColumn("Costo");
                    modelo.addColumn("horas");
                    modelo.addColumn("Comentario");
                    modelo.addColumn("MotivoRechazo");
                    break;
                //rs nos trae los datos de la consulta
//despues de crear las consultas asignamos los nombres de las columnas de la tabla
            }
//objeto que nos permite manejar los datos recibidos de la tabla de mysql
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();//nos almacena cuantos datos nos regresa la consulta

            //cilco para recorrer los datos de la consulta
            while (rs.next()) {
//nos proporciona los datos de la consulta de una fila

//creamos un vector de objetos para mostrar los datos ya que la tabla requiere un objeto
//tiene longitud la cantidad de columnas que nos regresa el rsmetadata
                Object[] filas = new Object[cantidadColumnas];
//ciclo que nos pasa todos los datos al objeto para el manejo de la tabla
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);//igualamos los elementos de la variable fila
                }
                modelo.addRow(filas);//agregamos los datos que obtuvimos de la consulta a nuestra tabla
            }

        } catch (SQLException ex) {
        }
    }

//metodo para seleccionar un elemento de una tabla y enviarlo a un textfield
    public static void selectTabla(JTable tabla, JTextField nombreText, String nombreColumna,
            String nombreTabla, int columnaSeleccionada)
            throws ClassNotFoundException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //variable para obtener la fila que seleccionaremos 
            int fila = tabla.getSelectedRow();
            //varibale para recuperar el valor que esta en la columna  y la fila seleccionada
            String valorRecuperado = tabla.getValueAt(fila, columnaSeleccionada).toString();
            String query = "SELECT " + nombreColumna + " FROM " + nombreTabla + " WHERE " + nombreColumna + "=?";
            //creacion de la consulta
            ps = Conexion.getConection().prepareStatement(query);
            ps.setString(1, valorRecuperado);
            rs = ps.executeQuery();
            //recorremos lo que la consulta nos regresa y lo mANDamos al textfield de la interfaz 
            while (rs.next()) {
                nombreText.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
        }

    }

}
