package net.ald.projet.entities;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;

import net.ald.projet.DAO.ArtisteDAOImpl;
import net.ald.projet.DAO.CollectionDAO;
import net.ald.projet.DAO.OeuvreDAOImpl;
import net.ald.projet.DAO.PhotoDAO;
import net.ald.projet.DAO.ReproductionDAO;



public class ServiceMusee{

    @SuppressWarnings("unused")
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServiceMusee.class);        
    private static CollectionDAO collectionDAO = new CollectionDAO();
    private static OeuvreDAOImpl oeuvreDAO = new OeuvreDAOImpl();
    private static ArtisteDAOImpl artisteDAO = new ArtisteDAOImpl();
    private static ReproductionDAO reproductionDAO = new ReproductionDAO();
    private static PhotoDAO photoDAO = new PhotoDAO();

    public ServiceMusee(){
    }
    
    @POST
    @Path("/collection/create")
    @Consumes("application/xml")
    @Produces("application/xml") 
    public Response createCollection(Collection collection){
	collectionDAO.createCollection(collection);
	return Response.ok(collection).build();
    }
    

    @POST
    @Path("/oeuvre/create")
    @Consumes("application/xml")
    public Response createOeuvre(Oeuvre o){
	oeuvreDAO.createOeuvre(o);
	return Response.ok(o).build();
    }
    
    @POST
    @Path("/reproduction/create")
    @Consumes("application/xml")
    public Response createReproduction(Reproduction r){
	reproductionDAO.createReproduction(r);
	return Response.ok(r).build();
    }
    
    @POST
    @Path("/photo/create")
    @Consumes("application/xml")
    public Response createPhoto(Photo p){
	photoDAO.createPhoto(p);
	return Response.ok(p).build();
    }

    @POST
    @Path("/artiste/create")
    @Consumes("application/xml")
    public Response createArtiste(Artiste a){
	artisteDAO.createArtiste(a);
	return Response.ok(a).build();
    }

    @POST
    @Path("/oeuvre/update")
    @Consumes("application/xml")
    public Response updateOeuvre(Oeuvre o){
	//oeuvreDAO.updateOeuvre(o);
	return Response.ok(o).build();
    }

    @POST
    @Path("/collection/update")
    @Consumes("application/xml")
    public Response updateCollection(Collection collection){
	//collectionDAO.updateCollection(collection);
	return Response.ok(collection).build();
    }
     
    @GET
    @Path("/collection/{id}")
    @Produces("application/xml")
    public Collection displayCollection(@PathParam("id") int CollectionId){
	Collection collection = collectionDAO.findById(CollectionId);
	return collection;
    }


    @GET
    @Path("/collections")
    @Produces({"application/xml","application/json"})    
    public List<Collection> getCollections(){
	List<Collection> collection = collectionDAO.findAll();
	return collection;
    }
        
    @GET
    @Path("/collection/delete/{id}")    
    public void deleteCollection(@PathParam("id")int id) {
	Collection collection = collectionDAO.findById(id);
	collectionDAO.deleteCollection(collection);                        
    }


    @GET
    @Path("/oeuvres")
    @Produces("application/xml")    
    public List<Oeuvre> getAllOeuvres(){
	List<Oeuvre> oeuvres = oeuvreDAO.findAll();
	return oeuvres;
    }
    
    @GET
    @Path("/oeuvre/{id}")
    @Produces("application/xml")
    public Oeuvre getOeuvre(@PathParam("id")int id){
	Oeuvre oeuvre = oeuvreDAO.findById(id);
	return oeuvre;
    }

    @POST
    @Path("/oeuvres/criteria")
    @Produces("application/xml")
    public List<Oeuvre> getCriteriaOeuvre(Oeuvre oeuvre){
	List<Oeuvre>  oeuvres = oeuvreDAO.findByCriteria(oeuvre);
	return oeuvres;
    }
        
    @GET
    @Path("/oeuvre/delete/{id}")    
    public void deleteOeuvre(@PathParam("id")int id) {
	Oeuvre oeuvre = oeuvreDAO.findById(id);
	oeuvreDAO.removeOeuvre(oeuvre);                        
    }
        
    @GET
    @Path("/artiste/delete/{id}")    
    public void deleteArtiste(@PathParam("id")int id) {
	Artiste artiste = artisteDAO.findById(id);
	artisteDAO.deleteArtiste(artiste);                        
    }

    @GET
    @Path("/artistes")
    @Produces({"application/xml","application/json"})
    public Artiste getArtiste(@PathParam("id")int id){
	Artiste a = artisteDAO.findById(id);
	return a;
    }

    @GET
    @Path("/oeuvre/artiste/{id}")
    @Produces({"application/xml","application/json"})
    public Set<Oeuvre> findOeuvresOfArtiste(@PathParam("id")int ArtisteId)
    {
	Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
	oeuvres = artisteDAO.findOeuvresOfIdArtiste(ArtisteId);
	return oeuvres;        
    }

    @GET
    @Path("/artistes")
    @Produces({"application/xml","application/json"})
    public List<Artiste> getArtistes(){
	List<Artiste> a = artisteDAO.findAll();
	return a;
    }
    
    @GET
    @Path("/oeuvres/everReproduced")
    @Produces("application/xml")
    public List<Oeuvre> getAllOeuvreEverReproduced() {
	List<Oeuvre> oeuvres = oeuvreDAO.findOeuvresReproduced();
	return oeuvres;
    }

    @GET
    @Path("/oeuvres/neverReproduced")
    @Produces("application/xml")
    public List<Oeuvre> getAllOeuvreNeverReproduced() {
	List<Oeuvre> oeuvres = oeuvreDAO.findOeuvresNotReproduced();
	return oeuvres;
    }


    @GET
    @Path("/oeuvre/{id}/reproductions")
    @Produces("application/xml")
    public List<Reproduction> getReproductionsOfOeuvre(int id) {
	List<Reproduction> reproductions = new ArrayList<Reproduction>();
	reproductions = reproductionDAO.getAllReproductionOfOeuvre(id);
	return reproductions;
    }

    @GET
    @Path("/oeuvre/{id}/nbReproductions")
    public long getNbReproductionOfOeuvre(int id) {
	return reproductionDAO.getNbReproductionOfOeuvre(id);
    }

    @GET
    @Path("/reproduction/{id}/{price}")
    public double setReproductionPrice(int id, double price) {
	Reproduction reproduction = reproductionDAO.findById(id);
	reproduction.setPrix(price);
	//reproductionDAO.updateReproduction(reproduction);
	return reproduction.getPrix();
    }

    @GET
    @Path("/reproductions/collection/{id}")
    @Produces("application/xml")
    public List<Reproduction> getReproductionsOfCollection(int id) {
	List<Reproduction> reproductions = reproductionDAO.getAllReproductionOfCollection(id);
	return reproductions;
    }


    @GET
    @Path("/collection/{id}/oeuvres/notReproduced")
    @Produces("application/xml")
    public List<Oeuvre> getOeuvresOfCollectionNeverReproduced(int id) {
	List<Oeuvre> oeuvres = reproductionDAO.getOeuvresOfCollectionNeverReproduced(id);
	return oeuvres;
    }

    @GET
    @Path("/oeuvres/reproduced/collection/{id}")
    @Produces("application/xml")
    public List<Oeuvre> getAllOeuvreReproducedOfCollection(int id) {
	List<Oeuvre> oeuvres = reproductionDAO.getOeuvresOfCollectionReproduced(id);
	return oeuvres;
    }

    @GET
    @Path("/oeuvres/notReproduced/collection/{id}")
    @Produces("application/xml")
    public List<Oeuvre> getAllOeuvreNotReproducedOfCollection(int id) {
	List<Oeuvre> oeuvres = reproductionDAO.getOeuvresOfCollectionNotReproduced(id);
	return oeuvres;
    }        
        
}
