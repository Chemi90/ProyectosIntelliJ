package com.example.gestorjuegos.domain.usuario;

import com.example.gestorjuegos.domain.DAO;
import com.example.gestorjuegos.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UserDao implements DAO<User> {
    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User save(User data) {
        return null;
    }

    @Override
    public void update(User data) {

    }

    @Override
    public void delete(User data) {

    }

    public User validateUser(String username, String password){
        User result = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> q = session.createQuery("FROM User where username =:u and password=:p", User.class);
            q.setParameter("u", username);
            q.setParameter("p", password);

            try {
                result = q.getSingleResult();
            }catch(Exception e){
            }
            return result;
        }
    }
}
