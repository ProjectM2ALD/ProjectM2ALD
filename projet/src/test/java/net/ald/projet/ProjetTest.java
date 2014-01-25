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
import net.ald.projet.entities.Peinture;
import net.ald.projet.entities.Photo;
import net.ald.projet.entities.Reproduction;
import net.ald.projet.entities.User;
import net.ald.projet.property.Connexion;
import net.ald.projet.property.Dimension;
import net.ald.projet.property.Realisation;
import net.ald.projet.property.SupportOeuvre;

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
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProjetTest.class);        
    private static Dimension d;
    private static JAXBContext jc;

    @BeforeClass
    public static void setUp() throws Exception {
            d = new Dimension(10, 20, 40);
            jc = JAXBContext.newInstance(Oeuvre.class, Collection.class, Reproduction.class, Photo.class, Connexion.class);
    }


    /**
     * RESTeasy client instead of DefaultHttpClient because RESTeasy client is JAX-RS aware.
     * To use the RESTeasy client, you construct org.jboss.resteasy.client.ClientRequest 
     * objects and build up requests using its constructors and methods 
     */


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
                    User us = new User("omar","omar","CONSERVATEUR");
                    httpPostRequest(us,"employe/create");


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
            
        
           /* @Test
            public final void insertOeuvre(){

            	try{
            		Artiste artiste = new Artiste(12,"puma", "guerin");
                    Artiste artisteVerif = (Artiste) httpPostRequest(artiste,"artiste/create");
                    Peinture p = new Peinture(2, artisteVerif,"La joconde", 1111);                
                    Oeuvre peintureVerif = (Oeuvre) httpPostRequest(p,"oeuvre/create");
                    Assert.assertNotSame(peintureVerif,null);
                    }catch(RuntimeException re){
                    	LOG.error("insert oeuvre failed", re);
                    	throw re;
                    }
            }*/    

}
