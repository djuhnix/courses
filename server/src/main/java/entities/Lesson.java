package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Lesson {
    Activity activity;
    private String filePath;
    private int id;
    private String filepath;
    private int idActivity;

    public Lesson(Activity activity, String filePath) {
        this.activity = activity;
        this.filePath = filePath;
    }

    public Lesson() {
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public _Dummy_ setId(int id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "FILEPATH", nullable = false, length = 500)
    public String getFilepath() {
        return filepath;
    }

    public _Dummy_ setFilepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    @Basic
    @Column(name = "ID_ACTIVITY", nullable = false)
    public int getIdActivity() {
        return idActivity;
    }

    public _Dummy_ setIdActivity(int idActivity) {
        this.idActivity = idActivity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (id != lesson.id) return false;
        if (idActivity != lesson.idActivity) return false;
        if (filepath != null ? !filepath.equals(lesson.filepath) : lesson.filepath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (filepath != null ? filepath.hashCode() : 0);
        result = 31 * result + idActivity;
        return result;
    }
}
