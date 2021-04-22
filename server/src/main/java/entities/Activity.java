package entities;

import java.util.Date;

public class Activity {
    private String name;
    private Date start;
    private Date end;
    private String subject;
    private Promotion promo;
    private Teacher teacher;

    public Activity(String name, Date start, Date end, String subject, Promotion promo, Teacher teacher) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.promo = promo;
        this.teacher = teacher;
    }

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


}
