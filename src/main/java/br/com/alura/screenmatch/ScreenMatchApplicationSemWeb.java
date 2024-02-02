//package br.com.alura.screenmatch;
//
//import br.com.alura.screenmatch.main.Main;
//import br.com.alura.screenmatch.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenMatchApplicationSemWeb implements CommandLineRunner {
//	@Autowired
//	private SerieRepository serieRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenMatchApplicationSemWeb.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//        Main main = new Main(serieRepository);
//        main.showMenu();
//	}
//}
