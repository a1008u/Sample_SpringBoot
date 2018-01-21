package com.example.sample_1_di;

import com.example.sample_1_di.common.MainLogic;
import com.example.sample_1_di.mainLogic.config.AppConfig;
import com.example.sample_1_di.mainLogic.typeA_MainLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Scanner;

/*
 * @EnableAutoConfiguration
 *「@EnableAutoConfiguration」アノテーションがSpirngBootの自動設定を有効にするためのアノテーション。
 * Sptng Initializrde作成された雛形クラスには「@EnableAutoConfiguration」ではなく、「@Spring BootAplicaon」アノテーションが付与されている。
 * 「@Spring BootAplicaon」はSpring Boot 1.2から導入され、 下記3点のアノテーションを組み合わせたものある。（@Spring BootAplicaonに変更してもいい）
 * 　・「@EnableAutoConfiguration 」
 * 　・「@Configuration」
 * 　・「@ComponentScan」
 *
 * @Import(AppConfig.class)
 * 「JavaConfig」を読み込むために、「@import」で「@Configuration」アノテーションを付けた対象のクラスを指定。
 *
 */
@EnableAutoConfiguration
@Import(AppConfig.class)
public class Sample1DiApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Sample1DiApplication.class, args);
		MainLogic Logic = context.getBean(typeA_MainLogic.class);

		// 実行タイプを選ぶ
		System.out.print("どれにしますか？(数値を入力してください) \n"
				+ "JavaConfigを用いた実装::1 \n"
				+ "オートワイヤリングを用いた実装::2 \n"
				+ "入力値：：：");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();

		if(type == 1){
			Logic.exeMain(context);
		} else if(type == 2) {
			Logic.exeMain();
		}
	}
}


/*
「DI」とは 「DependencylIneciton」(依存性の注入)の略、「SpringFramework」の根幹となる技術です。
「DI」によってクラス間の依存関係が自動で解決されます。

「インスタンス」の管理は「DIコンテナ」が行ないます。
「DIコンテナ」が「インスタンス」を生成し、その「インスタンス」に必要な「インスタンス」を設定した状態で、アプリに返します。

「DIコンテナ」経由で「インスタンス」が生成されることによって、以下4点の副次効果を加えることができます。
・インスタンスの「スコープ」を制御できる(「シングルトンオブジェクトなのか」 「毎回新規生成するのか」など)。
・インスタンスの 「ライフ・サイクル」をイベント制御できる (「インスタンス生成時」 「破棄時」のイベント処理など)
・共通的な処理を埋め込める (「トランザクション管理」や 「ロキンク処理」など)
・オブジェクト間が疎結合になるため、「ユニット・テスト」がしやすくなるというメリットもあります。

「SpringFramework」によるDIを理解するために、「何らかの計算して、結果を表示する簡単なアプリ」を作りましょう。
このアプリをstep by stepで変更していくことで、「Spring Framework」 が提供するDIの機能を説明します。

DIコンテナにBean定義ファイルを管理させるために、Bean定義ファイルを作る
・XML * 使用しない
・Javaクラス(Config)　
・アノテーション
 */