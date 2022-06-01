package nl.hu.bep.shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Shopper implements NamedObject, Principal {
    private String name;
    private String role;
    private String password;
    private List<ShoppingList> allLists = new ArrayList<>();

    private static List<Shopper> allShoppers = new ArrayList<>();

    public Shopper(String nm, String role, String password) {
        this.name = nm;
        this.role = role;
        this.password = password;

        if (!allShoppers.contains(this)) allShoppers.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopper shopper = (Shopper) o;
        return name.equals(shopper.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public static List<Shopper> getAllShoppers() {
        return Collections.unmodifiableList(allShoppers);
    }

    public boolean addList(ShoppingList newList) {
        if (!allLists.contains(newList)) {
            return allLists.add(newList);
        }
        return false;
    }

    @JsonIgnore
    public List<ShoppingList> getAllLists() {
        return Collections.unmodifiableList(allLists);
    }

    public int getAmountOfLists() {
        return allLists.size();
    }
}
