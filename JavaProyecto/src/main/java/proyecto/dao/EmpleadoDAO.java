package proyecto.dao;

import proyecto.model.Empleado;
import proyecto.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements IEmpleadoDAO {


    @Override
    public void create(Empleado empleado) {

        try (Connection connection = DataBaseUtil.getConnection()) {

            final String SQL = "INSERT INTO tb_empleado (nombre, apellidoPaterno, apellidoMaterno, edad, estado) VALUES (?,?,?,?,?) ";

            try (PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, empleado.getNombre());
                ps.setString(2, empleado.getApellidoPaterno());
                ps.setString(3, empleado.getApellidoMaterno());
                ps.setInt(4, empleado.getEdad());
                ps.setString(5, empleado.getEstado());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        empleado.setId(id);
                    }
                }

            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(Empleado empleado) {


        try(Connection connection = DataBaseUtil.getConnection()) {

            final String SQL = "UPDATE tb_empleado SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, edad=?, estado=?  WHERE id=?";

            try(PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, empleado.getNombre());
                ps.setString(2, empleado.getApellidoPaterno());
                ps.setString(3, empleado.getApellidoMaterno());
                ps.setInt(4, empleado.getEdad());
                ps.setString(5, empleado.getEstado());
                ps.setInt(6, empleado.getId());
                ps.executeUpdate();
            }
        }catch (Exception e){
            System.err.println(e);
        }

    }

    @Override
    public void delete(Empleado empleado) {

        try(Connection connection = DataBaseUtil.getConnection()) {

            final String SQL = "DELETE FROM tb_empleado WHERE id=?";

            try(PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setInt(1, empleado.getId());
                ps.executeUpdate();
            }
        }catch (Exception e){
            System.err.println(e);
        }
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<Empleado>();

        try(Connection connection = DataBaseUtil.getConnection()) {

            final String SQL = "SELECT * FROM tb_empleado";

            try(Statement statement = connection.createStatement()) {

                try(ResultSet resultSet = statement.executeQuery(SQL)) {
                    while (resultSet.next()){
                        int id = resultSet.getInt("id");
                        String nombre = resultSet.getString("nombre");
                        String apellidoPaterno = resultSet.getString("apellidoPaterno");
                        String apellidoMaterno = resultSet.getString("apellidoMaterno");
                        int edad = resultSet.getInt("edad");
                        String estado = resultSet.getString("estado");

                        Empleado empleado = new Empleado(id, nombre, apellidoPaterno, apellidoMaterno, edad, estado);
                        empleados.add(empleado);
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e);
        }

        return empleados;
    }

    @Override
    public Empleado findById(Integer id) {
        Empleado empleado = null;

        try(Connection connection = DataBaseUtil.getConnection()) {

            final String SQL = "SELECT * FROM tb_empleado WHERE id=?";

            try(PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setInt(1, id);

                try(ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()){
                        int current_id = resultSet.getInt("id");
                        String nombre = resultSet.getString("nombre");
                        String apellidoPaterno = resultSet.getString("apellidoPaterno");
                        String apellidoMaterno = resultSet.getString("apellidoMaterno");
                        int edad = resultSet.getInt("edad");
                        String estado = resultSet.getString("estado");

                        empleado = new Empleado(current_id, nombre, apellidoPaterno, apellidoMaterno, edad, estado);
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e);
        }

        return empleado;
    }
}
