package za.co.ashtech.booklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableEncryptableProperties
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class BooklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooklogApplication.class, args);
	}

}
