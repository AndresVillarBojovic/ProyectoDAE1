package proyecto.dao;

import proyecto.model.Empleado;

import java.util.List;

public interface IEmpleadoDAO {

    public void create(Empleado empleado);
    public void update(Empleado empleado);
    public void delete(Empleado empleado);

    public List<Empleado> findAll();
    public Empleado findById(Integer id);

}
