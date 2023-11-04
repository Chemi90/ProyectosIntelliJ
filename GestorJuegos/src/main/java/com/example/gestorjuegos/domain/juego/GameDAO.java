package com.example.gestorjuegos.domain.juego;

import com.example.gestorjuegos.domain.DAO;
import com.example.gestorjuegos.domain.HibernateUtil;
import com.example.gestorjuegos.domain.usuario.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class GameDAO implements DAO<Game> {
    @Override
    public ArrayList<Game> getAll() {
        return null;
    }

    public ArrayList<Game> getAllFromUser(User u) {
        ArrayList<Game> results = new ArrayList<>(0);
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Game> q = s.createQuery("from Game where usuarioId=:userId", Game.class);
            q.setParameter("userId", u.getId());
            results = (ArrayList<Game>) q.getResultList();
        }
        return results;
    }

    @Override
    public Game get(Long id) {
        return null;
    }

    @Override
    public Game save(Game data) {
        return null;
    }

    @Override
    public void update(Game data) {

    }

    @Override
    public void delete(Game data) {

    }
}
