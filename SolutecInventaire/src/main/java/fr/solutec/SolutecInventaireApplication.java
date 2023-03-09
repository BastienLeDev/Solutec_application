package fr.solutec;




import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.solutec.entities.Product;
import fr.solutec.entities.User;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.UserRepository;

@SpringBootApplication
public class SolutecInventaireApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SolutecInventaireApplication.class, args);
		System.out.println("Lancement terminé");
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		Product p1 = new Product(null, "PC", null, 1, "Gerti Gosselin",Date.valueOf("2020-3-09"),Date.valueOf("2023-1-09"), 150, 40);
		Product p2 = new Product(null, "Badge", null, 2, "Otto Yglesia",Date.valueOf("2021-08-25"),Date.valueOf("2022-09-29"), 200, 100);
		Product p3 = new Product(null, "Badge", null, 3, null,Date.valueOf("2022-01-19"),Date.valueOf("2022-5-09"), 200, 100);
		Product p4 = new Product(null, "Badge", null, 4, "Wynne Durtnall",Date.valueOf("2022-3-09"),Date.valueOf("2023-3-09"), 200, 100);
		Product p5 = new Product(null, "Souris", null, 5, null,Date.valueOf("2021-06-10"),Date.valueOf("2021-8-14"),30, 55);
		Product p6 = new Product(null, "PC", "145.100.227.74",6 , "Winn Heaphy",Date.valueOf("2019-3-09"),Date.valueOf("2021-3-09"), 146, 50);
		Product p7 = new Product(null, "Souris", null, 7, "Aloysia Folds",Date.valueOf("2017-3-15"),Date.valueOf("2019-12-09"), 30, 55);
		Product p8 = new Product(null, "Alimentation", null, 8, "Shaina Comfort",Date.valueOf("2020-3-09"),Date.valueOf("2022-3-04"), 60, 41);
		Product p9 = new Product(null, "PC", "171.206.89.210", 9, "Yalonda Brik",Date.valueOf("2016-07-19"),Date.valueOf("2017-3-20"), 150, 40);
		Product p10 = new Product(null, "PC", "51.141.222.105", 10, "Neala Ilyenko",Date.valueOf("2018-3-09"),Date.valueOf("2020-7-15"), 150, 40);
		
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
		
		userRepo.save(u1);
		userRepo.save(u2);
		
	}

}
