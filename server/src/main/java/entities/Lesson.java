package entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lesson extends Aggregator{

    @Id @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    Activity activity;

    @Basic @Column(name = "FILEPATH", nullable = false, length = 500)
    private String filePath;


    @Basic
    @Column(name = "ID_ACTIVITY", nullable = false)
    private int idActivity;

    public Lesson(Activity activity, String filePath) {
        this.activity = activity;
        this.filePath = filePath;
    }

    public Lesson() {
    }
}
