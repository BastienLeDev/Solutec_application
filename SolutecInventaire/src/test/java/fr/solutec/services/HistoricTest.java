package fr.solutec.services;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.solutec.entities.Historic;
import fr.solutec.entities.Product;
import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.TypeProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoricTest {
	@Autowired
	ProductRepository productRepo;
	@Autowired
	TypeProductRepository typeproductRepo;
	@Autowired
	HistoricServices historicServ;
	
	@Test
    public void testAdd() {
        // Créer un produit fictif pour les besoins du test
        Product product = new Product();
        product.setEntryDate(new Date(5));
        product.setExitDate(new Date(5));
        product.setOwner("John Doe");
        product.setRefProduct("123456");
        product.setReservation(true);
        TypeProduct t1 = new TypeProduct(null,"type");
        product.setTypeProduct(t1);

        // Créer un objet Historic pour stocker les valeurs attendues
        Historic expectedHistoric = new Historic();
        GregorianCalendar calendar = new GregorianCalendar();
        expectedHistoric.setDateHistoric(calendar.getTime());
        expectedHistoric.setUser("TestUser");
        expectedHistoric.setTypeModif("Ajout d'un produit");
        expectedHistoric.setEntryDateA(product.getEntryDate());
        expectedHistoric.setExitDateA(product.getExitDate());
        expectedHistoric.setOwnerA(product.getOwner());
        expectedHistoric.setRefProductA(product.getRefProduct());
        expectedHistoric.setReservationA(product.isReservation());
        expectedHistoric.setTypeProduct(product.getTypeProduct().getNameProduct());

        Historic actualHistoric = historicServ.add(product, "TestUser");

        // Vérifier si les valeurs retournées par la méthode correspondent aux valeurs attendues
        assertEquals(expectedHistoric.getDateHistoric(), actualHistoric.getDateHistoric());
        assertEquals(expectedHistoric.getUser(), actualHistoric.getUser());
        assertEquals(expectedHistoric.getTypeModif(), actualHistoric.getTypeModif());
        assertEquals(expectedHistoric.getEntryDateA(), actualHistoric.getEntryDateA());
        assertEquals(expectedHistoric.getExitDateA(), actualHistoric.getExitDateA());
        assertEquals(expectedHistoric.getOwnerA(), actualHistoric.getOwnerA());
        assertEquals(expectedHistoric.getRefProductA(), actualHistoric.getRefProductA());
        assertEquals(expectedHistoric.isReservationA(), actualHistoric.isReservationA());
        assertEquals(expectedHistoric.getTypeProduct(), actualHistoric.getTypeProduct());
    }
	
	@Test
	public void testDelete() {

	}
	
	@Test
	public void testModif() {
		TypeProduct t1 = new TypeProduct(null,"type");
		// Créer un objet de test pour productB
        Product productB = new Product();
        productB.setEntryDate(new Date(5));
        productB.setExitDate(new Date(5));
        productB.setOwner("John Doe");
        productB.setRefProduct("123456");
        productB.setReservation(true);
        productB.setTypeProduct(t1);
        
        // Créer un objet de test pour productA
        Product productA = new Product();
        productA.setEntryDate(new Date(5));
        productA.setExitDate(new Date(6));
        productA.setOwner("");
        productA.setRefProduct("123456");
        productA.setReservation(false);
        productA.setTypeProduct(t1);
        
        // Appeler la méthode modif
        Historic result = historicServ.modif(productB, productA, "utilisateur");

        // Vérifier les valeurs des propriétés de l'objet Historic
        assertEquals("utilisateur", result.getUser());
        assertEquals("Modification de produit", result.getTypeModif());
        assertEquals(productA.getTypeProduct().getNameProduct(), result.getTypeProduct());
        assertEquals(productA.getEntryDate(), result.getEntryDateA());
        assertEquals(productA.getExitDate(), result.getExitDateA());
        assertEquals(productA.getOwner(), result.getOwnerA());
        assertEquals(productA.getRefProduct(), result.getRefProductA());
        assertEquals(productA.isReservation(), result.isReservationA());
        assertEquals(productB.getEntryDate(), result.getEntryDateB());
        assertEquals(productB.getExitDate(), result.getExitDateB());
        assertEquals(productB.getOwner(), result.getOwnerB());
        assertEquals(productB.getRefProduct(), result.getRefProductB());
        assertEquals(productB.isReservation(), result.isReservationB());
	}
}
