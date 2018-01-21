package com.example.sample_2_di;


import com.example.sample_2_di.common.argument.Argument;
import com.example.sample_2_di.common.argument.ArgumentResolver;
import com.example.sample_2_di.common.calculator.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Sample1DiApplicationクラス上で、オートワイヤリングを利用して、処理を実行する方法
 *
 * CommandLineRunnerインターフェイス
 *  「クラス」を「DIコンテナ」から取得するコードから、
 *  「DIコンテナ」に「インジェクション」するコードに修正するために「MainLogicクラス」を作り、
 *  「Sample1DiApplicationクラス」からは「MainLogicクラス」を呼び出すようにしました。
 *  実は「SpringBoot」には「MainLogicクラス」相当の処理を「Sample1DiApplicationクラス」ができるように「CommandLineRunner」インターフェイスが用意されています。
 *  このインターフェイスを「Sample1DiApplicationクラス」が実装することで「Sample1DiApplicationクラス」にも「DIコンテナ」がインジェクションされる。
 *
 */
@SpringBootApplication
public class Sample2DiApplication  implements CommandLineRunner {

	@Autowired
	ArgumentResolver argumentResolver;

	@Autowired
	Calculator calculator;

	@Override
	public void run(String... strings) throws Exception {
		System.out.print("Enter 2 numbers like 'a b' : ");
		Argument argument = argumentResolver.resolve(System.in);
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result = " + result);
	}

	public static void main(String[] args) {
		SpringApplication.run(Sample2DiApplication.class, args);
	}
}