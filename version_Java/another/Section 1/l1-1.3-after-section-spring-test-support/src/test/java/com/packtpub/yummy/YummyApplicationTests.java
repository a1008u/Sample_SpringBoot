package com.packtpub.yummy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YummyApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
    DatePrinter datePrinter;

	/**
	 * Factoryクラスをモックとして生成
	 */
	@MockBean
    TimeFactory timeFactory;

	@Test
    public void datePrinterWorks(){

		/**
		 * 振舞駆動開発(Behavior Driven Development)型で、//given //when //then のコメントでテストを書く
		 * 1.givenメソッドでモックでの利用メソッドを記載
		 * 2.willReturnで戻り値を記載
		 */
		given(timeFactory.now()).willReturn(LocalDateTime.of(2017,5,1,17,41,12));

	    assertEquals("Now it is 2017-05-01T17:41:12", datePrinter.printDate());
    }
}
