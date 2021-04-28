package courses.server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Exercise {

    @Basic @Column(name = "FILEPATH", nullable = false, length = 500)
    private String filePath;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    private Activity activity;

    @Basic @Column(name = "ID_ACTIVITY", nullable = false)
    private int idActivity;

    public Exercise(Activity activity, String filePath) {
        this.activity = activity;
        this.filePath = filePath;
    }

    public Exercise() {
    }
}
