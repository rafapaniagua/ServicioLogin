/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxrs;

import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rafa Paniagua
 */
@Path("servicio")
public class SWLogin {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SWLogin
     */
    public SWLogin() {
    }

    /**
     * Retrieves representation of an instance of jaxrs.SWLogin
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    /*public List<String> getText(@QueryParam("usuario") String user,
                                @QueryParam("contrasenia") String password) {*/
    public String getText(@QueryParam("usuario") String user,
                                @QueryParam("contrasenia") String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServicioLoginPU");
        EntityManager em = emf.createEntityManager();
        
        //List<String> datos = new ArrayList<String>();
        
        String datos = "";
        
        try{
            Usuario usuario = em.find(Usuario.class, Integer.parseInt(user));
            
            if(usuario.getContrasenia().equals(password)){
                /*datos.add(usuario.getId().toString());
                datos.add(usuario.getNombre());
                datos.add(usuario.getApellidos());
                datos.add(usuario.getTipoUsuario());*/
                
                datos = usuario.getId().toString()+","+usuario.getNombre()+","+usuario.getApellidos()+","+usuario.getTipoUsuario();
                
                //return usuario.getTipoUsuario();
                return datos;
            }else{
                //return null;
                return "Incorrecto";
            }
        }catch(Exception e){
            //return null;
            return "Incorrecto";
        }
    }

    /**
     * PUT method for updating or creating an instance of SWLogin
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
