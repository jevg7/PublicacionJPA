package Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="publicacion_postgres")
@NamedQueries({
        @NamedQuery(name="PublicacionPostgres.findAll", query="SELECT p FROM PublicacionPostgres p")
})
public class PublicacionPostgres {

    @Id
    @SequenceGenerator(name = "seqPublicacion", sequenceName = "seq_publicacion", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPublicacion")
    private Integer id;

    @Column(name="fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Column(name="nombre_publicacion", nullable = false, length=100)
    private String nombrePublicacion;

    @Column(name="descripcion_publicacion", length=500)
    private String descripcionPublicacion;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }

    public String getDescripcionPublicacion() {
        return descripcionPublicacion;
    }

    public void setDescripcionPublicacion(String descripcionPublicacion) {
        this.descripcionPublicacion = descripcionPublicacion;
    }

    @Override
    public String toString() {
        return "PublicacionPostgres{" +
                "id=" + id +
                ", fechaPublicacion=" + fechaPublicacion +
                ", nombrePublicacion='" + nombrePublicacion + '\'' +
                ", descripcionPublicacion='" + descripcionPublicacion + '\'' +
                '}';
    }
}

