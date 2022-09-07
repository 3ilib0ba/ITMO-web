package org.example.weblab3ivanov.bean;

import org.example.weblab3ivanov.entity.Hit;
import org.example.weblab3ivanov.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ApplicationScoped
public class HitBean implements Serializable {

    private Hit hit = new Hit();
    private List<Hit> hits = new ArrayList<>();
    private Session session = createSession();

    public Hit getHit() {
        return hit;
    }

    public void setHit(Hit hit) {
        this.hit = hit;
    }

    public List<Hit> getHits() {
        List<Hit> outputHits = new ArrayList<>(hits);
        Collections.reverse(outputHits);
        return outputHits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public void addHit() {
        long startTime = System.nanoTime();
        hit.checkHit();
        hit.setCurrent(LocalDateTime.now());
        hit.setExec((System.nanoTime() - startTime) / 1000000d);
        try {
            Transaction transaction = session.beginTransaction();
            session.save(hit);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        hits.add(hit);
        hit = new Hit(hit.getX(), hit.getY(), hit.getR());
    }

    private Session createSession() {
        try {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
