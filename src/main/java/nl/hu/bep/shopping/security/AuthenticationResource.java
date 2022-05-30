package nl.hu.bep.shopping.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.bep.shopping.model.Shopper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;

class AuthenticationRequest {
    public String username;
    public String password;
}

@Path("/login")
public class AuthenticationResource {
    public static Key key = MacProvider.generateKey();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginWebservice(AuthenticationRequest loginData) {
        for (Shopper shopper : Shopper.getAllShoppers()) {

            if (shopper.getName().equals(loginData.username) &&
                    shopper.getPassword().equals(loginData.password)) {

                String jwt = Jwts.builder()
                        .setSubject(loginData.username)
                        .claim("role", shopper.getRole())
                        .signWith(SignatureAlgorithm.HS512, key)
                        .compact();

                return Response.ok(new AbstractMap.SimpleEntry<>("JWT-token", jwt)).build();
            }
        }

        return Response.status(406).build();
    }
}
