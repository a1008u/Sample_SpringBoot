package com.example.sample_1_di.mainLogic.config;

import com.example.sample_1_di.common.MainLogic;
import com.example.sample_1_di.common.calculator.Calculator;
import com.example.sample_1_di.common.calculator.addCalculator;
import com.example.sample_1_di.common.argument.ArgumentResolver;
import com.example.sample_1_di.common.argument.ScannerArgumentResolver;
import com.example.sample_1_di.mainLogic.typeA_MainLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  JavaConfigの設定方法
 */

// このアノテーションより、このクラスがJavaConfig用のクラスであるとことを示す。
@Configuration
public class AppConfig {

    /* 「DIコンテナ」に管理させたい「Bean」を生成するメソッドに、「@Bean」アノテーションを付ける。
     * デフォルトでは「メソッド名」が「Bean名」になる。
     * また、デフォルトはこのメソッドで生成された「インスタンス」は「singleton」として管理され、
     *「DIコンテナ」につき「1つのインスタンス」のみ生成される。
     *
     * 下記コードの説明
     * 「AddCalculatorインスタンス」が「Calculator型」で「calculato」という名前で「DIコンテナ」に「singleton」として管理される。
     */

    /**
     * 計算用（JavaConfigを用いた実装で利用）
     * @return　addCalculator
     */
    @Bean
    Calculator calculator() {
        return new addCalculator();
    }

    /**
     * 入力用（JavaConfigを用いた実装で利用）
     * @return ScannerArgumentResolver
     */
    @Bean
    ArgumentResolver argumentResolver() {
        return new ScannerArgumentResolver();
    }

    /**
     * オートワイヤリング用（Sample1DiApplicationでメソッドを利用するため）
     * @return typeA_MainLogic
     */
    @Bean
    MainLogic mainLogic() {
        return new typeA_MainLogic();
    }

}

