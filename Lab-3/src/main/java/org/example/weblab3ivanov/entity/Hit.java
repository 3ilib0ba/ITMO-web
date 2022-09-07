package org.example.weblab3ivanov.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.pow;

// поле name = X задает имя таблицы как Х, а не аналогичное названию класса
@Entity(name = "hits")
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double x;
    private double y;
    private double r;
    private String res;
    private double exec = 0;
    private LocalDateTime current = LocalDateTime.now();

    public double getExec() {
        return exec;
    }

    public Hit(){}

    public Hit(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.res = "no";
    }

    public void setExec(double exec) {
        this.exec = exec;
    }

    public String getCurrent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return current.format(formatter);
    }

    public void setCurrent(LocalDateTime current) {
        this.current = current;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {this.res = res;}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = BigDecimal.valueOf(x)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double getY() {
        String strY = String.valueOf(y);
        int minus = strY.charAt(0) == '-' ? 1 : 0;
        return Double.parseDouble(strY.length() > 6 + minus ? strY.substring(0, 6 + minus) : strY);
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = BigDecimal.valueOf(r)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();;
    }

    private boolean rectangle(double x, double y, double r) {
        return (x >= 0) && (y >= 0) && (x <= r) && (y <= r / 2);
    }

    private boolean circle(double x, double y, double r) {return (x <= 0) && (y >= 0) && (pow(x, 2) + pow(y, 2) <= (pow(r / 2, 2)));}

    private boolean triangle(double x, double y, double r) {return (x >= 0) && (y <= 0) && (y >= (x / 2 - r / 2));}

    public void checkHit() {
        if (rectangle(x, y, r) || circle(x, y, r) || triangle(x, y, r)) {
            setRes("yes");
        } else {
            setRes("no");
        }
    }
}
