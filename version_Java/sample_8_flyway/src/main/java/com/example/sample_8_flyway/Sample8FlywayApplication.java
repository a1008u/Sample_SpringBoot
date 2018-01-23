package com.example.sample_8_flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Sample8FlywayApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Sample8FlywayApplication.class, args);
	}

	/**
	 * エラーメッセージを記載しているファイルのエンコードを指定する
	 *
	 * @return ReloadableResourceBundleMessageSource
	 */
	@Bean
	ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/messages"); //（※２）
		messageSource.setCacheSeconds(0);
		messageSource.setDefaultEncoding("UTF-8"); // エンコード設定
		return messageSource;
	}

	/**
	 * LocalValidatorFactoryBeanのsetValidationMessageSourceで
	 * バリデーションメッセージをValidationMessages.propertiesからSpringの
	 * MessageSource(messages.properties)に上書きする
	 *
	 * @return localValidatorFactoryBean
	 */
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}

	/**
	 * WebMvcConfigurerAdapterを用いて、任意のエラーメッセージを表示させる
	 *
	 * @return Validator
	 */
	@Override
	public Validator getValidator() {
		return validator();
	}
}
