package fr.solutec;




import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import fr.solutec.entities.Product;
import fr.solutec.entities.Stock;
import fr.solutec.entities.User;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.StockRepository;
import fr.solutec.repository.UserRepository;

@SpringBootApplication
public class SolutecInventaireApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StockRepository stockRepo;

	
	public static void main(String[] args) {
		SpringApplication.run(SolutecInventaireApplication.class, args);
		System.out.println("Lancement terminé");
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		Stock s = new Stock(null, null);
		
		Product p1 = new Product(null, "PC intercontrat", "112.100.400.74",  "Gerti Gosselin",null,Date.valueOf("2023-1-09"),false);
		Product p2 = new Product(null, "Badge structure", null, "Otto Yglesia",null,Date.valueOf("2022-09-29"),false);
		Product p3 = new Product(null, "Badge intercontrat", null, null,Date.valueOf("2022-01-19"),null,false);
		Product p4 = new Product(null, "Badge structure", null,"Wynne Durtnall",null,Date.valueOf("2023-3-09"),false);
		Product p5 = new Product(null, "Souris", null,null,Date.valueOf("2021-06-10"),null,true);
		Product p6 = new Product(null, "PC intercontrat", "145.100.227.74","Winn Heaphy",null,Date.valueOf("2021-3-09"),false);
		Product p7 = new Product(null, "Souris", null,"Aloysia Folds",null,Date.valueOf("2019-12-09"),false);
		Product p8 = new Product(null, "Alimentation", null,"Shaina Comfort",null,Date.valueOf("2022-3-04"),false);
		Product p9 = new Product(null, "PC intercontrat", "171.206.89.210","Yalonda Brik",null,Date.valueOf("2017-3-20"),false);
		Product p10 = new Product(null, "PC intercontrat", "51.141.222.105","Neala Ilyenko",null,Date.valueOf("2020-7-15"),false);
		Product p11 = new Product(null, "Poste mission", null, null,Date.valueOf("2022-01-19"),null,false);
		Product p12 = new Product(null, "Poste mission", null, "Léo Rick",null,Date.valueOf("2022-01-24"),false);
		Product p13 = new Product(null, "Poste mission", null, null,Date.valueOf("2022-01-19"),null,false);
		Product p14 = new Product(null, "Base", null, null,Date.valueOf("2022-02-26"),null,false);
		Product p15 = new Product(null, "Base", null, null,Date.valueOf("2022-02-26"),null,false);
		Product p16 = new Product(null, "Base", null, null,Date.valueOf("2022-02-26"),null,true);
		Product p17 = new Product(null, "Base", null, null,Date.valueOf("2022-02-26"),null,true);
		Product p18 = new Product(null, "Poste structure", null, null,Date.valueOf("2022-03-15"),null,true);
		Product p19 = new Product(null, "Poste structure", null, null,Date.valueOf("2022-03-15"),null,false);
		Product p20 = new Product(null, "Poste structure", null, null,Date.valueOf("2022-03-15"),null,false);
		Product p21 = new Product(null, "Téléphone mission", null, "Léo Rick",null,Date.valueOf("2022-01-24"),false);
		
		
		
		
		User u1 = new User(null, "User1", "testUser", "Rm1", "123");
		User u2 = new User(null, "User2", "testUser2", "Rm2", "456");
		

		stockRepo.save(s);
		
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
