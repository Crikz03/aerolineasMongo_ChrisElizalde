/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author Chris
 */
public class Aerolinea {

    private ObjectId id;
    private String country;
    private String currency;
    private String name;
    private boolean lowcost;

    public Aerolinea() {
    }

    public Aerolinea(ObjectId id) {
        this.id = id;
    }

    public Aerolinea(ObjectId id, String country, String currency, String name, boolean lowcost) {
        this.id = id;
        this.country = country;
        this.currency = currency;
        this.name = name;
        this.lowcost = lowcost;
    }

    public Aerolinea(String country, String currency, String name, boolean lowcost) {
        this.country = country;
        this.currency = currency;
        this.name = name;
        this.lowcost = lowcost;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLowcost() {
        return lowcost;
    }

    public void setLowcost(boolean lowcost) {
        this.lowcost = lowcost;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aerolinea other = (Aerolinea) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aerolinea{");
        sb.append("id=").append(id);
        sb.append(", country=").append(country);
        sb.append(", currency=").append(currency);
        sb.append(", name=").append(name);
        sb.append(", lowcost=").append(lowcost);
        sb.append('}');
        return sb.toString();
    }

}
