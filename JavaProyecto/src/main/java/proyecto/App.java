package proyecto;

import proyecto.dao.EmpleadoDAO;
import proyecto.model.Empleado;

public class App {


    public static void main(String[] args) {

        Empleado empleado = new Empleado();
        empleado.setNombre("Andres");
        empleado.setApellidoPaterno("Villar");
        empleado.setApellidoMaterno("Bojovic");
        empleado.setEdad(22);
        empleado.setEstado("Contagiado");

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        //create
        empleadoDAO.create(empleado);
        System.out.println(empleadoDAO.findById(empleado.getId()));

        //update
        empleado.setNombre("Andy");
        empleado.setEstado("curado");
        empleadoDAO.update(empleado);
        System.out.println(empleadoDAO.findById(empleado.getId()));

        //DELETE
        empleadoDAO.delete(empleado);
        System.out.println(empleadoDAO.findById(empleado.getId()));

        //list
        empleadoDAO.findAll().forEach(System.out::println);

    }


}
