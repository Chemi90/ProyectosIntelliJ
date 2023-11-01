package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    private static Usuario u;
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sf = configuration.buildSessionFactory();

        u = new Usuario();
        u.setNombre("Sant");
        u.setEmail("santi@santi.san");

        sf.inSession(session -> {

            Query q = session.createQuery("from Usuario where id=:id", Usuario.class);
            q.setParameter("id",10);
            
            ArrayList<Usuario> salida = (ArrayList<Usuario>) q.getResultList();
            salida.forEach(System.out::println);
            System.out.println(salida.size());
        });

        /*
        sf.inTransaction((session) ->{
            //u = session.get(Usuario.class, 8);

            session.persist(u);
            System.out.println(u.getId());

            session.remove(u);
            System.out.println(u);
        });

         */
/*
        try(Session s =sf.openSession()){
            Transaction t = s.beginTransaction();
            s.persist(u);
            t.commit();

            Query q = (Query) s.createQuery("FROM Usuario");
            ArrayList<Usuario> salida = (ArrayList<Usuario>) q.getResultList();

            System.out.println(salida);
        }

 */
    }
}
