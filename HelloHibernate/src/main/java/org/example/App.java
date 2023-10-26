package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.management.Query;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sf = configuration.buildSessionFactory();

        Usuario u = new Usuario();
        u.setNombre("Samuel");
        u.setEmail("samu@samu.samu");

        try(Session s =sf.openSession()){
            Transaction t = s.beginTransaction();
            s.persist(u);
            t.commit();

            Query q = (Query) s.createQuery("FROM Ususario");
            ArrayList<Usuario> salida = q.getResulstList();

            System.out.println(salida);
        }
    }
}
