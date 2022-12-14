package ru.ifmo.se.web4.model;

import lombok.Data;
import ru.ifmo.se.web4.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
@Entity
@Table(name = "points")
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String creator_user;

    private double x;
    private double y;
    private double r;
    private String time = String.valueOf(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
        .format(Calendar.getInstance().getTime()));
    private boolean hit;


    public String toJSON() {
        return "{" +
                "\"x\":" + "\"" + this.x + "\"" + "," +
                "\"y\":" + "\"" + this.y + "\"" + "," +
                "\"r\":" + "\"" + this.r + "\"" + "," +
                "\"time\":" + "\"" + this.time + "\"" + "," +
                "\"hit\":" + "\"" + this.hit + "\"" +
                "}";
    }

    public String toMessage() {
        return "Проверка точки (" + this.x + "; " + this.y + ")\n" +
                "Параметр: " + this.r + "\n" +
                "Время отправки: " + this.time + "\n" +
                "Результат: " + this.hit;
    }

    public void setHit() {
        this.hit = checkHit(this.x, this.y, this.r);
    }

    private boolean checkTriangle(double x, double y, double r) {
        return ((x <= 0) &&
                (y >= 0) &&
                (y <= 0.5 * x + r / 2));
    }

    private boolean checkRectangle(double x, double y, double r) {
        return ((x >= 0) &&
                (y >= 0) &&
                (x <= r) && (y <= r / 2));
    }

    private boolean checkCircle(double x, double y, double r) {
        return ((x >= 0) && (y <= 0) && (x * x + y * y <= r / 2 * r / 2));
    }

    private boolean checkHit(double x, double y, double r) {
        if (r > 0) {
            return (checkTriangle(x, y, r) || checkRectangle(x, y, r) || checkCircle(x, y, r));
        } else if (r < 0){
            x = -x;
            y = -y;
            r = Math.abs(r);
            return (
                    ((x <= 0) && (y >= 0) && (x * x + y * y <= r / 2 * r / 2) ) ||
                    ((x <= 0) && (y <= 0) && (x >= -r) && (y >= -r / 2)) ||
                    ((x >= 0) && (y <= 0) && (y >= x * 0.5 - r / 2))
            );

        } else {
            return false;
        }
    }
}