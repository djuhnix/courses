package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Activity {

    private String name;
    private Date start;
    private Date end;
    private String subject;
    private Promotion promo;
    private Teacher teacher;
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private int idPromotion;
    private int idTeacher;

    public Activity(String name, Date start, Date end, String subject, Promotion promo, Teacher teacher) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.promo = promo;
        this.teacher = teacher;
    }

    public Activity() {
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 26)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Basic
    @Column(name = "SUBJECT", nullable = false, length = 25)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
    @Column(name = "START_DATE", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public _Dummy_ setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        return this;
    }

    @Basic
    @Column(name = "END_DATE", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public _Dummy_ setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        return this;
    }

    @Basic
    @Column(name = "ID_PROMOTION", nullable = false)
    public int getIdPromotion() {
        return idPromotion;
    }

    public _Dummy_ setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
        return this;
    }

    @Basic
    @Column(name = "ID_TEACHER", nullable = false)
    public int getIdTeacher() {
        return idTeacher;
    }

    public _Dummy_ setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (id != activity.id) return false;
        if (idPromotion != activity.idPromotion) return false;
        if (idTeacher != activity.idTeacher) return false;
        if (name != null ? !name.equals(activity.name) : activity.name != null) return false;
        if (subject != null ? !subject.equals(activity.subject) : activity.subject != null) return false;
        if (startDate != null ? !startDate.equals(activity.startDate) : activity.startDate != null) return false;
        if (endDate != null ? !endDate.equals(activity.endDate) : activity.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + idPromotion;
        result = 31 * result + idTeacher;
        return result;
    }
}
