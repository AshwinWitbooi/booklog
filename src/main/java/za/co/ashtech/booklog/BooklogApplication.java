package za.co.ashtech.booklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class BooklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooklogApplication.class, args);
	}

}
