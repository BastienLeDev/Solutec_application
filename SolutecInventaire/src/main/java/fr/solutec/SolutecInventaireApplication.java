package fr.solutec;




import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.solutec.entities.Product;
import fr.solutec.repository.ProductRepository;

@SpringBootApplication
public class SolutecInventaireApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SolutecInventaireApplication.class, args);
		System.out.println("Lancement terminé");
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Lancement en cours");
		
		Product p1 = new Product(null, "PC", null, 1, Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Gerti Gosselin", 150, 40);
		Product p2 = new Product(null, "Badge", null, 2,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Otto Yglesia", 200, 100);
		Product p3 = new Product(null, "Badge", null, 3,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , null, 200, 100);
		Product p4 = new Product(null, "Badge", null, 4,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Wynne Durtnall", 200, 100);
		Product p5 = new Product(null, "Souris", null, 5,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , null,30, 55);
		Product p6 = new Product(null, "PC", "145.100.227.74", 6,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Winn Heaphy", 146, 50);
		Product p7 = new Product(null, "Souris", null, 7,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Aloysia Folds", 30, 55);
		Product p8 = new Product(null, "Alimentation", null, 8,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Shaina Comfort", 60, 41);
		Product p9 = new Product(null, "PC", "171.206.89.210", 9,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Yalonda Brik", 150, 40);
		Product p10 = new Product(null, "PC", "51.141.222.105", 10,Date.valueOf("2022-03-09"), Date.valueOf("2023-03-09") , "Neala Ilyenko", 150, 40);
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
	}

}
