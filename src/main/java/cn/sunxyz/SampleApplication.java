package cn.sunxyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.sunxyz.mapper")
public class SampleApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
		
//		SpringApplication app = new SpringApplication(SampleApplication.class);
//		app.setShowBanner(false);
//		app.run(args);
		
	}

}
