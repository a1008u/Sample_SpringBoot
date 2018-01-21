package com.example.sample_1_di.mainLogic;

import com.example.sample_1_di.common.calculator.Calculator;
import com.example.sample_1_di.common.MainLogic;
import com.example.sample_1_di.common.argument.Argument;
import com.example.sample_1_di.common.argument.ArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/*
 * 入力と計算を抽象化
 */
public class typeA_MainLogic implements MainLogic {

    /*
     * オートワイヤリング
     * 「@Autowired」アノテーションを付けた 「フィールド」をもつクラスが「DIコンテナ」で管理されるとします。
     * 　すると、「DIコンテナ」は自動的に「@Autowired」アノテーションを付けた 「フィールド」に対して、
     * 　合致する型のオブジェクトを管理内のオブジェクトから探し出して、インジェクションすること。
     *
     *　@Autowiredアノテーション
     * 「DIコンテナ」が「インジェクション」すべき「フィールド」であることを示す。
     */
    @Autowired
    ArgumentResolver argumentResolver;

    @Autowired
    Calculator calculator;

    /**
     * DI(オートワイヤリング)を用いた実装
     * DIコンテナにインスタンスへBeanをインジェクションしてもらう
     * ＊＊＊configファイルでBeanを管理する必要がなくなる＊＊＊
     *
     * AppConfigのargumentResolverとcalculatorはオートワイヤリングで対応する。
     * AppConfigのargumentResolverとcalculatorを削除しても問題ない。
     */
    @Override
    public void exeMain() {
        System.out.print("Enter 2 numbers like 'a b' : ");
        Argument argument = argumentResolver.resolve(System.in);
        int result = calculator.calc(argument.getA(), argument.getB());
        System.out.println("result = " + result);
    }

    /**
     * JavaConfigを用いた実装
     * DIコンテナから明示的にBeanを取得する
     * ＊＊＊configファイルでBeanを管理する＊＊＊
     *
     * @param context
     */
    @Override
    public void exeMain(ApplicationContext context) {
        System.out.print("Enter 2 numbers like 'a b' : ");

        // 入力
        ArgumentResolver argumentResolver = context.getBean(ArgumentResolver.class);
        Argument argument = argumentResolver.resolve(System.in);

        // 計算
        Calculator calculator = context.getBean(Calculator.class);
        int result = calculator.calc(argument.getA(), argument.getB());

        System.out.println("result = " + result);
    }
}
