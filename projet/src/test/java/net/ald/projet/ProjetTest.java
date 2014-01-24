package net.ald.projet;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Logger;

import net.ald.projet.entities.Artiste;
import net.ald.projet.entities.Collection;
import net.ald.projet.entities.Oeuvre;
import net.ald.projet.entities.Photo;
import net.ald.projet.entities.Reproduction;
import net.ald.projet.property.Connexion;
import net.ald.projet.property.Dimension;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.*;
import org.junit.rules.TestName;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Logger;
import java.util.Date;


public class ProjetTest {
	
	private static EntityManager entityManager;
    private EntityTransaction tx; 
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProjetTest.class);
    private static Dimension d;
    private static JAXBContext jc;


    @Rule public TestName name = new TestName();

 /******************** Appelé une fois à l'initialisation du test unitaire  ****************/

// similaire à createEntityManager
    @BeforeClass
    public static void initEntityManager()throws Exception
    {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Artiste");
	entityManager = entityManagerFactory.createEntityManager();
    }


    // similaire à closeEntityManager
     @AfterClass
     public static void closeEntityManager()throws Exception
     {
	 LOG.info("CloseEntityManager appelé");
	 entityManager.close();
     }
     
     /******************** Appelé plusieurs fois, avant chaque test unitaire **********************/
     // Démarre une transaction qui charge la base de données d'un jeu de données pré-établi (dataset.xml)
     @Before
 	public void initTransaction()throws Exception
     {
 	tx = entityManager.getTransaction(); 
 	seedData();
     }

     protected void seedData() throws Exception {
 	tx.begin();
 	//Connection connection = em.unwrap(java.sql.Connection.class);
 	Connection connection = entityManager.unwrap(java.sql.Connection.class);
 	try {
 	    IDatabaseConnection dbUnitCon = new DatabaseConnection(connection);
 	    dbUnitCon.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
 	    IDataSet dataset;
 	    FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
 	    flatXmlDataSetBuilder.setColumnSensing(true);
 	    InputStream in =Thread.currentThread().getContextClassLoader().getResourceAsStream("data/dataset.xml");
 	    if(in !=null){
 		LOG.warn("DataSet found");
 		dataset = flatXmlDataSetBuilder.build(in);
 	    } else {
 		LOG.warn("DataSet not found");
 		dataset= new DefaultDataSet();
 	    }
 	    DatabaseOperation.REFRESH.execute(dbUnitCon, dataset);
 	} finally {
 	    tx.commit();
 	    }
 	}
     
    /* @Test
     public final void testFindAll(){
	LOG.info("Test FindAll");
	try{
	    tx.begin();
	    List<Artiste> artistes = entityManager.createQuery("Select u from User u").getResultList();
	    LOG.debug("Result Size = "+ artistes.size());
	    Assert.assertEquals(9, artistes.size());
	}catch(RuntimeException re){
	    LOG.error("Find all failed", re);
	    throw re;
	}finally{
	    tx.commit();
	}
	}
*/
     @BeforeClass
     public static void setUp() throws Exception {
             d = new Dimension(10, 20, 40);
             jc = JAXBContext.newInstance(Oeuvre.class, Collection.class, Reproduction.class, Photo.class, Connexion.class);
     }

     public Object httpPostRequest(Object o, String resourceURI){
         try {


                 // sérialise l'objet pour l'envoyer via une requete POST
                 Marshaller marshaller = jc.createMarshaller();
                 marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                 java.io.StringWriter sw = new StringWriter();
                 marshaller.marshal(o, sw);


                 ClientRequest request = new ClientRequest("http://localhost:8080/rest/service/"+resourceURI);


                 // We're posting XML and a JAXB object
                 request.body("application/xml", sw.toString());
                 ClientResponse<String> response = request.post(String.class);


                 if (response.getStatus() == 200) 
                 {
                         Unmarshaller un = jc.createUnmarshaller();
                         Object object = (Object) un.unmarshal(new StringReader(response.getEntity()));
                         return object;
                 }
         } catch (Exception e) {
                 e.printStackTrace();
         }
         return null;
 }
     @Test
     public final void connexionTest(){


             try{        
                     
                     Connexion connexion = new Connexion("azerty","azerty");
                     //Employe employe = new Employe(Status.CONSERVATEUR, "Ben", "Stiler", connexion);
                     //httpPostRequest(employe,"employe/create");


                     Marshaller marshaller = jc.createMarshaller();
                     marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                     java.io.StringWriter sw = new StringWriter();
                     marshaller.marshal(connexion, sw);


                     ClientRequest request = new ClientRequest("http://localhost:8080/rest/service/connexion");
                     request.body("application/xml", sw.toString());
                     LOG.info(sw.toString());
                     ClientResponse<String> response = request.post(String.class);


                     if (response.getStatus() == 200) 
                     {
                             LOG.info(response.getEntity());
                             Assert.assertTrue(!response.getEntity().isEmpty());
                     }
             } catch (Exception e) {
                     e.printStackTrace();
                     LOG.error("connexion test failed", e);
             }


     }

    

}
