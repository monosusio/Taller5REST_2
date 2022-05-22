package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.Usuario;
import co.edu.unbosque.taller5rest_3.services.UsersService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UsersResource {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "Santuario11";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public UsersResource() throws SQLException {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Connection conn = null;

        List<Usuario> users = null;

        try {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            UsersService usersService = new UsersService(conn);
            users = usersService.listUsers();

            //PetsService petsService = new PetsService(conn);
            //petsService.countBySpecies("dog");

            //OwnersService ownersService = new OwnersService(conn);
            //ownersService.updateOwner(new Owner(6697, null, "Pepe"));

            conn.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


        return Response.ok().entity(users).build();
    }

    /*@POST
    @Path("/found")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response found(Usuario user){
        Usuaarioresorce bass =new Usuaarioresorce(conn);
        Usuario n=new Usuario(null,null,null,null);
        String username_n=user.getUsername();
        String password_n=user.getPassword();

        System.out.println("linea 85");
        System.out.println(user.getUsername()+" este es el username");
        System.out.println(user.getPassword()+" este es el password");
        System.out.println();
        List<Usuario> users = bass.listusers();
        System.out.println("linea 57");
        Usuario user_n = users.stream()
                .filter(u -> u.getEmail().equals(username_n) && u.getPassword().equals(password_n))
                .findFirst()
                .orElse(null);
        System.out.println("linea 62");
        if (user_n != null) {
            System.out.println("este es el username en java "+user_n.getUsername());
            System.out.println("este es el password en java "+user_n.getPassword());
            System.out.println("este es el role en java "+user_n.getRole());
            System.out.println("este es el email en java "+user_n.getEmail());
            user.setUsername(user_n.getUsername());
            user.setPassword(user_n.getPassword());
            user.setRole(user_n.getRole());
            System.out.println("linea 64");
            System.out.println("linea nueva 65");
            System.out.println("Este el email usuario "+ user_n.getEmail());
            return Response.ok()
                    .entity(user)
                    .build();
        } else {
            System.out.println("esta es la linea 111");
            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();
        }

    }*/
    /*@GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("username") String username){
        System.out.println("linea 85");
        try{
            List<User> users = new UserService().getUsers().get();
            System.out.println("linea 57");
            User user = users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
            System.out.println("linea 62");
            if (user != null) {
                System.out.println("linea 64");
                System.out.println("linea nueva 65");
                return Response.ok()
                        .entity(user)
                        .build();
            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "User not found"))
                        .build();
            }

        } catch (IOException e) {
            System.out.println("linea 73");
            return Response.serverError().build();
        }
    }*/

    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response  createForm(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("role") String role
            ){
        
            UsersService usersService = new UsersService(conn);

        Usuario user_n=new Usuario(username, password, role);
        usersService.insertuser(user_n);

            /*if(user_n.getRole().equals("Artist")){
                System.out.println("se esta ingresando el artista");
                Artista nuevo_artista=new Artista(user_n.getEmail(),0,user_n.getPassword());
                artistaservice.insertArtist(nuevo_artista);
                System.out.println("se esta pasasndo despues de la insercion");
            }else if(user_n.getRole().equals("Costumer")){
                System.out.println("se esta ingresando el costumer");
                Coustomer new_costumer=new Coustomer(user_n.getEmail(),0,user_n.getPassword());
                costuemrservice.insertArtist(new_costumer);
                System.out.println("se esta pasasndo despues de la insercion");
            }*/

        return Response.created(UriBuilder.fromResource(UsersResource.class).path(username).build())
                 .entity(user_n)
                 .build();

    }

}