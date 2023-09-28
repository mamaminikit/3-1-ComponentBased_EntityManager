package entitymanager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Address;
import model.Customer;

/*
 * @mmmaimankarae
 */

public class EntityManager {
        /* Entity Manager */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerPU");
    private static javax.persistence.EntityManager em = emf.createEntityManager();
    
    public static void main(String[] args) {
        em.getTransaction().begin();
        try {
            /* การสร้างข้อมูล */
            //createData();
            
            /* การพิมพ์ข้อมูล */
            printAllCustomer();
            
            /* การพิมพ์ข้อมูลโดยค้นหาจากcity */
            printCustomerByCity("Bangkok");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    /* การสร้างข้อมูล */
    private static void createData() {
            /* First Person */
        Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com");
        em.persist(customer1);
        Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "TH", "10900");
        em.persist(address1);  
        
            /* Second Person */
        Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        em.persist(customer2);
        Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "TH", "10900");
        em.persist(address2);  
        
            /* Third Person */
        Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        em.persist(customer3);
        Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "TH", "20900");
        em.persist(address3);  
        
            /* Fourth Person */
        Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
        em.persist(customer4);
        Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "TH", "30500");
        em.persist(address4);  
    }
    
    /* การพิมพ์ข้อมูล */
    private static void printAllCustomer(){
        System.out.println("\'Show all of customer details\'\n");
        for (long id = 1L; id <= 4L; id++) {
            printCustomerDetails(id);
        }
    }
    
    /* การพิมพ์ข้อมูลโดยค้นหาจากcity */
    private static void printCustomerByCity(String city) {
        Customer customer;
        Address address;
        TypedQuery<Address> query = em.createNamedQuery("Address.findByCity", Address.class);
        query.setParameter("city", city);
        List<Address> addressList = query.getResultList();
        Collections.sort(addressList, Comparator.comparing(Address::getId));
        System.out.println("\'Customer details form Bangkok city\'\n");
        for (Address id : addressList) {
            printCustomerDetails(id.getId());
        }
    }
    
    private static void printCustomerDetails(Object id){
        Customer customer = em.find(Customer.class, id);
        Address address = em.find(Address.class, id);

        System.out.println("First Name: " + customer.getFirstname());
        System.out.println("Last Name: " + customer.getLastname());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Street: " + address.getStreet());
        System.out.println("City: " + address.getCity());
        System.out.println("Country: " + address.getCountry());
        System.out.println("Zip Code: " + address.getZipcode());
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
    }
}
