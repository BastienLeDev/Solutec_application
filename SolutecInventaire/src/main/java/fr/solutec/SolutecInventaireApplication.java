package fr.solutec;




import java.sql.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.solutec.entities.Alert;
import fr.solutec.entities.Product;
import fr.solutec.entities.TypeProduct;
import fr.solutec.entities.User;
import fr.solutec.repository.AlertRepository;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.TypeProductRepository;
import fr.solutec.repository.UserRepository;

@SpringBootApplication
public class SolutecInventaireApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TypeProductRepository typeProductRepo;


	
	public static void main(String[] args) {
		SpringApplication.run(SolutecInventaireApplication.class, args);
		System.out.println("Lancement terminé");
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		TypeProduct tp1 = new TypeProduct(null, "Poste Mission");
		TypeProduct tp2 = new TypeProduct(null, "Base");
		TypeProduct tp3 = new TypeProduct(null, "Poste Structure");
		TypeProduct tp4 = new TypeProduct(null, "Téléphone mission");
		TypeProduct tp5 = new TypeProduct(null, "Téléphone structure");
		TypeProduct tp6 = new TypeProduct(null, "Casque mission");
		TypeProduct tp7 = new TypeProduct(null, "Casque structure");
		TypeProduct tp8 = new TypeProduct(null, "Ecran télétravail");
		TypeProduct tp9 = new TypeProduct(null, "Ecran Structure");
		TypeProduct tp10 = new TypeProduct(null, "PC INT");
		TypeProduct tp11 = new TypeProduct(null, "Support PC");
		TypeProduct tp12 = new TypeProduct(null, "Clavier");
		TypeProduct tp13 = new TypeProduct(null, "Souris");
		TypeProduct tp14 = new TypeProduct(null, "Cables HDMI");
		TypeProduct tp15 = new TypeProduct(null, "Cables Ethernet");
		TypeProduct tp16 = new TypeProduct(null, "PC Intercontrat");
		TypeProduct tp17 = new TypeProduct(null, "Badge structure");
		TypeProduct tp18 = new TypeProduct(null, "Badge intercontrat");
		TypeProduct tp19 = new TypeProduct(null, "Coque / vitre téléphone");
		TypeProduct tp20 = new TypeProduct(null, "Autres");
		
		typeProductRepo.save(tp1);
		typeProductRepo.save(tp2);
		typeProductRepo.save(tp3);
		typeProductRepo.save(tp4);
		typeProductRepo.save(tp5);
		typeProductRepo.save(tp6);
		typeProductRepo.save(tp7);
		typeProductRepo.save(tp8);
		typeProductRepo.save(tp9);
		typeProductRepo.save(tp10);
		typeProductRepo.save(tp11);
		typeProductRepo.save(tp12);
		typeProductRepo.save(tp13);
		typeProductRepo.save(tp14);
		typeProductRepo.save(tp15);
		typeProductRepo.save(tp16);
		typeProductRepo.save(tp17);
		typeProductRepo.save(tp18);
		typeProductRepo.save(tp19);
		typeProductRepo.save(tp20);
		
		
		
		
		
		
		Product p1 = new Product(null, tp16, "112.100.400.74",  "Gerti Gosselin",null,Date.valueOf("2023-1-09"),false);
		Product p2 = new Product(null, tp17, null, "Otto Yglesia",null,Date.valueOf("2022-09-29"),false);
		Product p3 = new Product(null, tp18, null, null,Date.valueOf("2022-01-19"),null,false);
		Product p4 = new Product(null, tp17, null,"Wynne Durtnall",null,Date.valueOf("2023-3-09"),false);
		Product p5 = new Product(null, tp13, null,null,Date.valueOf("2021-06-10"),null,true);
		Product p6 = new Product(null, tp16, "145.100.227.74","Winn Heaphy",null,Date.valueOf("2021-3-09"),false);
		Product p7 = new Product(null, tp13, null,"Aloysia Folds",null,Date.valueOf("2019-12-09"),false);
		Product p8 = new Product(null, tp2, null,"Shaina Comfort",null,Date.valueOf("2022-3-04"),false);
		Product p9 = new Product(null, tp16, "171.206.89.210","Yalonda Brik",null,Date.valueOf("2017-3-20"),false);
		Product p10 = new Product(null, tp16, "51.141.222.105","Neala Ilyenko",null,Date.valueOf("2020-7-15"),false);
		Product p11 = new Product(null, tp1, null, null,Date.valueOf("2022-01-19"),null,false);
		Product p12 = new Product(null, tp1, null, "Léo Rick",null,Date.valueOf("2022-01-24"),false);
		Product p13 = new Product(null, tp1, null, null,Date.valueOf("2022-01-19"),null,false);
		Product p14 = new Product(null, tp2, null, null,Date.valueOf("2022-02-26"),null,false);
		Product p15 = new Product(null, tp2, null, null,Date.valueOf("2022-02-26"),null,false);
		Product p16 = new Product(null, tp2, null, null,Date.valueOf("2022-02-26"),null,true);
		Product p17 = new Product(null, tp2, null, null,Date.valueOf("2022-02-26"),null,true);
		Product p18 = new Product(null, tp3, null, null,Date.valueOf("2022-03-15"),null,true);
		Product p19 = new Product(null, tp3, null, null,Date.valueOf("2022-03-15"),null,false);
		Product p20 = new Product(null, tp3, null, null,Date.valueOf("2022-03-15"),null,false);
		Product p21 = new Product(null, tp4, null, "Léo Rick",null,Date.valueOf("2022-01-24"),false);
		
		
		User u1 = new User(null, "User1", "testUser", "Rm1", "123");
		User u2 = new User(null, "User2", "testUser2", "Rm2", "456");
		
		productRepo.save(p1);
		productRepo.save(p2);
		productRepo.save(p3);
		productRepo.save(p4);
		productRepo.save(p5);
		productRepo.save(p6);
		productRepo.save(p7);
		productRepo.save(p8);
		productRepo.save(p9);
		productRepo.save(p10);
		productRepo.save(p11);
		productRepo.save(p12);
		productRepo.save(p13);
		productRepo.save(p14);
		productRepo.save(p15);
		productRepo.save(p16);
		productRepo.save(p17);
		productRepo.save(p18);
		productRepo.save(p19);
		productRepo.save(p20);
		productRepo.save(p21);
		
		userRepo.save(u1);
		userRepo.save(u2);
		
		
	}

}
