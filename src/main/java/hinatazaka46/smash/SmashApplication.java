package hinatazaka46.smash;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmashApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmashApplication.class, String.valueOf(args));
	}

}
