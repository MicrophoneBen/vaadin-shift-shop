package com.lt.css.shiftShp;

import com.lt.css.shiftShp.conf.HttpClientConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	HttpClientConfig clientConfig;

	@Test
	public void contextLoads() {
	}


	@Autowired
	RestTemplate restTemplate;

	@Test
	public void getEmployees() {
		final String uri = "http://192.168.4.181:8080/#!shiftshop";

		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
	}

}

