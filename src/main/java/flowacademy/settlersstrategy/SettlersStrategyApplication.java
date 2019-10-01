package flowacademy.settlersstrategy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SettlersStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlersStrategyApplication.class, args);

		Game game = new Game();

		game.mainMenu();

	}

}
