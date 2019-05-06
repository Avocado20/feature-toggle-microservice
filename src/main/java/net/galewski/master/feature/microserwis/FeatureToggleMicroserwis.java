package net.galewski.master.feature.microserwis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class FeatureToggleMicroserwis {

    public static void main(String[] args) {
		SpringApplication.run(FeatureToggleMicroserwis.class, args);
	}

	@PostConstruct
	public void loadData() throws IOException {
	}

}