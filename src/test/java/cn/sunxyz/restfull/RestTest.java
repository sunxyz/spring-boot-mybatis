package cn.sunxyz.restfull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import cn.sunxyz.SampleApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@WebAppConfiguration
public class RestTest {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void test() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080",
				String.class);
		System.out.println(responseEntity.getHeaders());
	}

}
