package com.mirfit.mirfit;

import com.mirfit.mirfit.controllers.CardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MirFitApplicationTests {

	@Autowired
	private CardController bonusesController;

	@Test
	void contextLoads() {
		assertThat(bonusesController).isNotNull();
	}
}
