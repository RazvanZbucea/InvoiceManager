package ro.nexttech.internship.zbucearazvan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Scanner;

@SpringBootApplication
@EntityScan("ro.nexttech.internship.zbucearazvan")
public class ZbuceaRazvanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZbuceaRazvanApplication.class, args);
	}

}
