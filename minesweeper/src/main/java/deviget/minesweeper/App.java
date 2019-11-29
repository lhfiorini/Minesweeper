package deviget.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "deviget.minesweeper" })
public class App 
{
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
}
