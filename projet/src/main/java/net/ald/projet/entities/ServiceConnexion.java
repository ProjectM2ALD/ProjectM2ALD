package net.ald.projet.entities;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import net.ald.projet.DAO.ConnexionDAOImpl;

@Path("/ServiceConnexion")
public class ServiceConnexion {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServiceConnexion.class);
	private ConnexionDAOImpl dao = new ConnexionDAOImpl();
	
	@POST
	@Path("/createConnexion")
	@Produces("application/xml")
	public Response createConnexion(User u){
		String user ="";
		try {
			user = dao.createConnexion(u.getLogin(), u.getPassword());
			return Response.ok(user).build();
		} catch (Exception e) {
			LOG.error("erreur creation connection couche service");
			e.printStackTrace();
			return Response.status(400).entity("Connexion failed!").build();
		}
	}
	
	@POST
	@Path("/createUser")
	@Produces("application/xml")
	public Response createUser(User u){
		try {
			dao.createUser(u);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error("erreur creation user couche service");
			e.printStackTrace();
			return Response.status(400).entity("Connexion failed!").build();
		}
	}
	

}
