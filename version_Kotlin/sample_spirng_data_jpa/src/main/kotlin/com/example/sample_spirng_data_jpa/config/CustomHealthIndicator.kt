package com.example.sample_spirng_data_jpa.config

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

/**
 * Actuatorのカスタムhealth
 * 独自のヘルスチェックを実装する
 *
 * 下記から、全体のエンドポイントを確認できる
 * http://localhost:8080/"任意で設定したエンドポイント"
 *  autoconfig 自動設定 (auto-configuration) の候補とそれらが自動設定された/されなかった理由
 *  mappings   @RequestMapping のマッピング情報。
 *  beans      Spring コンテナに登録されている Bean の一覧。
 *  trace      デフォルトでは直近数件のアクセスログを表示してくれます
 *  dump       サーバー内で動いているスレッドの一覧をダンプ
 *  env        ConfigurableEnvironment からのプロパティ一覧。
 *  health     ヘルスチェック用の情報。デフォルトでは常に "ok" を返すので、運用で使用する場合は要カスタマイズ。
 *  shutdown   シャットダウン。デフォルトでは無効化されている。
 *  info       任意で色々な情報を表示させられる。
 *
 */
@Component
class CustomHealthIndicator:HealthIndicator {
    override fun health(): Health  = Health
                                        .down()
                                        .withDetail("test","this is a test of customIndeicator")
                                        .build()
}