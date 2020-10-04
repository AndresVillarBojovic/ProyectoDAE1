package proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Empleado {

    private Integer id;
    private String  nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer edad;
    private String estado;

}
