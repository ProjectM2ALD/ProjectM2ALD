package net.ald.projet;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.ald.projet.entities.Artiste;
import net.ald.projet.entities.Collection;
import net.ald.projet.entities.Oeuvre;
import net.ald.projet.entities.Photo;
import net.ald.projet.entities.Reproduction;
import net.ald.projet.entities.User;
import net.ald.projet.property.Connexion;
import net.ald.projet.property.Dimension;
import net.ald.projet.property.SupportReproduction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.*;
import org.junit.rules.TestName;
import org.slf4j.LoggerFactory;


public class ProjetTest {
	
	@Rule public TestName name = new TestName();
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProjetTest.class);
	
	
	static Dimension s1,s2;
	static Artiste a1,a2,a3;
	static String c1,c2,c3,c4;
	static Set<String> l1,l2,l3,l4,l5;
	static Oeuvre w1,w2,w3;
	
	static Collection c;
	
	static Reproduction reproduction;
	
	static User user ;
	
	static JAXBContext jc;
	
	@BeforeClass
	public static void setUp() throws Exception {
		s1 = new Dimension(10,20,30);
		s2 = new Dimension(1,2,3);
		c1 = "il est moyen ton tableau !";
		c2 = "un peu mieux";
		l1 = new HashSet<String>();
		l1.add(c1);
		l1.add(c2);
		
		c3 = "Mais cest nul !";
		c4 = "mouais ca passe";
		l2 = new HashSet<String>();
		l2.add(c3);
		l2.add(c4);

		l3 = new HashSet<String>();
		l4 = new HashSet<String>();
		l5 = new HashSet<String>();
		l3.add("ancien");
		l4.add("nouveaute");
		l5.add("classique");
		
		a1 = new Artiste(12, "Jean", "Bon");
		a2 = new Artiste(13, "Harry", "Covert");
		a3 = new Artiste(14, "Anne", "Onyme");
		
		w1 = new Oeuvre(1, a1,"peinture a l eau", 2000, "peinture");
		w2 = new Oeuvre(2, a2, "bois", 2001, "sculpture");
		w3 = new Oeuvre(3, a3, "papier glace", 2002, "photo");
		
		c = new Collection(1);
		
		reproduction = new Reproduction(w1, 35, SupportReproduction.CARTE);
		
		user = new User("login", "pass", "conservateur");
		
		jc = JAXBContext.newInstance(Oeuvre.class, Collection.class, Reproduction.class, Photo.class, Connexion.class, User.class);
		}
	
	private Object restMethodGet(String method, int id){
		try {
			ClientRequest request;
			if(id==0){
				request = new ClientRequest("http://localhost:8080/rest/" + method);
			}else{
				request = new ClientRequest("http://localhost:8080/rest/" + method + "/" + id);
			}		
			request.accept("application/xml");
			ClientResponse<String> response = request.get(String.class);
			if (response.getStatus() == 200)
			{
				Unmarshaller un = jc.createUnmarshaller();
				Object o = un.unmarshal(new StringReader(response.getEntity()));
				return o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Object restMethodGet(String method){
		return restMethodGet(method,0);
	}
	
	private void restMethodPost(String method, Object o, int id){
		try{
			ClientRequest request;
			if(id==0){
				request = new ClientRequest("http://localhost:8080/rest/" + method);
			}else{
				request = new ClientRequest("http://localhost:8080/rest/" + method + "/" + id);
			}
			if(o!=null){
				/*Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
				java.io.StringWriter sw = new StringWriter();
				marshaller.marshal(o, sw);*/
				
				//request = new ClientRequest("http://localhost:8080/rest/" + method);
				request.body("application/xml", o);
			}
			ClientResponse<String> response = request.post(String.class);
			
				String str = response.getEntity();
				LOG.debug("succes = service " + method + " " + str);
			
		}catch(Exception e){
			LOG.error("erreur lors de l'appel du service " + method);
			e.printStackTrace();
		}
	}
		
		/* UtilisÃ© pour rendre l'id optionnel*/
		private void restMethodPost(String method, Object o){
			restMethodPost(method,o,0);
		}
		
		@Test
		public final void testAddOeuvre() {
			try {
				
				restMethodPost("servicemusee/artiste/create", a1);
				restMethodPost("servicemusee/artiste/create", a2);
				restMethodPost("servicemusee/artiste/create", a3);
				restMethodPost("servicemusee/oeuvre/create", w1);
				restMethodPost("servicemusee/oeuvre/create", w2);
				restMethodPost("servicemusee/oeuvre/create", w3);
				
				List<Oeuvre> so = (List<Oeuvre>) restMethodGet("servicemusee/oeuvre/artiste", 12);
				System.out.println(so.size());
				for(Oeuvre o : so){
					System.out.println(o.getTitre());
				}
				
				//System.out.println("la taille: "+ oeuv.size());
				//System.out.println("artiste= "+ w.getArtiste().getFirst_name());
				//assertEquals(1,w.getId());
						
				//assertEquals(3, oeuv.size());
				
			} catch (Exception e) {
				LOG.error("erreur lors de l'execution");
				e.printStackTrace();
			}
		}
		
		@Test
		public final void testGetOeuvre() {
			try {
				
				
				//System.out.println("la taille: "+ oeuv.size());
				//System.out.println("artiste= "+ w.getArtiste().getFirst_name());
				//assertEquals(1,w.getId());
						
				//assertEquals(3, oeuv.size());
				
			} catch (Exception e) {
				LOG.error("erreur lors de l'execution");
				e.printStackTrace();
			}
		}
		
		
}

