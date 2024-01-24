package com.example.gestorjuegos.domain.juego;

import com.example.gestorjuegos.domain.DAO;
import com.example.gestorjuegos.domain.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log
public class GameDAO implements DAO<Game> {

    public static final HashMap<String,String> QUERY_ATTR;

    static{
        QUERY_ATTR = new HashMap<>();
        QUERY_ATTR.put("studio","select distinct(g.studio) from Game g");
        QUERY_ATTR.put("enterprise","select distinct(g.enterprise) from Game g");
        QUERY_ATTR.put("boxStatus","select distinct(g.boxStatus) from Game g");
        QUERY_ATTR.put("gameStatus","select distinct(g.gameStatus) from Game g");
        QUERY_ATTR.put("category","select distinct(g.category) from Game g");
        QUERY_ATTR.put("platform","select distinct(g.platform) from Game g");
        QUERY_ATTR.put("format","select distinct(g.format) from Game g");
    }

    @Override
    public ArrayList<Game> getAll() {
        return null;
    }

   /* public ArrayList<Game> getAllFromUser(User u) {
        ArrayList<Game> results = new ArrayList<>(0);

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Game> q = s.createQuery("from Game where usuarioId=:userId",Game.class);
            q.setParameter("userId",u.getId());
            results = (ArrayList<Game>) q.getResultList();
        }
        return results;
    }*/

    @Override
    public Game get(Long id) {
        return null;
    }

    @Override
    public Game save(Game data) {
        Game salida = null;
        try( org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.persist(data);
            t.commit();
            salida = data;
        }catch (Exception e){
            log.severe("Error al guardar juego " + data.toString());
        }
        return salida;
    }

    @Override
    public void update(Game data) {

        try( org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();

            Game g = s.get(Game.class, data.getId());
            Game.merge(data, g);
            t.commit();

        }

    }

    @Override
    public void delete(Game data) {

        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Game g = session.get(Game.class, data.getId());
            session.remove(g);
        });

    }

    public List<String> getCategories(){
        ArrayList<String> results = new ArrayList<>(0);

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<String> q = s.createQuery("select distinct(g.category) from Game g", String.class);
            results = (ArrayList<String>) q.getResultList();
        }
        return results;
    }

    public List<String> getDistinctFromAttribute(String attr){
        ArrayList<String> results = new ArrayList<>(0);

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<String> q = s.createQuery(QUERY_ATTR.get(attr), String.class);
            results = (ArrayList<String>) q.getResultList();
        }
        return results;
    }


}
