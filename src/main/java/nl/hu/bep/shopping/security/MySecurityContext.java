package nl.hu.bep.shopping.security;

import nl.hu.bep.shopping.model.Shopper;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private Shopper shopper;
    private boolean isSecure;

    public MySecurityContext(Shopper shopper, boolean isSecure) {
        this.shopper = shopper;
        this.isSecure = isSecure;
    }

    @Override
    public Principal getUserPrincipal() {
        return shopper;
    }

    @Override
    public boolean isUserInRole(String s) {
        return shopper.getRole().equals(s);
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Token-Based-Auth-Scheme";
    }
}
