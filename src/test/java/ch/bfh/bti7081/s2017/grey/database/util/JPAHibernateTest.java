package ch.bfh.bti7081.s2017.grey.database.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class JPAHibernateTest {

  protected static EntityManagerFactory emf;
  protected static EntityManager em;

  @BeforeClass
  public static void init() throws FileNotFoundException, SQLException {
    emf = Persistence.createEntityManagerFactory("CRM-test");
    em = emf.createEntityManager();
    initializeDatabase();
  }


  public static void initializeDatabase() {
    Session session = em.unwrap(Session.class);
    session.doWork(new Work() {
      @Override
      public void execute(Connection connection) throws SQLException {
        try {
          File script = new File(getClass().getResource("/data.sql").getFile());
          RunScript.execute(connection, new FileReader(script));
        } catch (FileNotFoundException e) {
          throw new RuntimeException("could not initialize with script");
        }
      }
    });
  }

  @AfterClass
  public static void tearDown() {
    em.clear();
    em.close();
    emf.close();
  }
}