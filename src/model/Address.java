package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * @mmmaimankarae
 */

@Entity
@Table(name = "ADDRESS")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "Address.findByZipcode", query = "SELECT a FROM Address a WHERE a.zipcode = :zipcode"),
    @NamedQuery(name = "Address.findByCountry", query = "SELECT a FROM Address a WHERE a.country = :country")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Column(name = "COUNTRY")
    private String country;
    @JoinColumn(name = "CUSTOMER_FK", referencedColumnName = "ID")
    @ManyToOne
    private Customer customerFk;

    public Address() {
    }

    public Address(Long id, String street, String city, String country, String zipcode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customer getCustomerFk() {
        return customerFk;
    }

    public void setCustomerFk(Customer customerFk) {
        this.customerFk = customerFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Address[ id=" + id + " ]";
    }
}
