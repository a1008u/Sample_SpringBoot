package com.example.sample_9_security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

// Spring Securityの基本的な設定を行えるようになる
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 特定のリクエストに対して「セキュリティ設定」を無視する設定など、全体に関わる設定ができる
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 性的リソースに対するアクセスにはセキュリティの設定を無視するようにしている
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    /**
     * 許可の設定やログイン、ログアウトに関する設定を行う
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() // 許可に関する設定を行う（指定のパス以外は、認証なしでアクセスできないようする）
                    .antMatchers("/loginForm").permitAll()
                    .anyRequest().authenticated()
            .and()
            .formLogin() // ログインの設定
                .loginProcessingUrl("/login") // 認証処理のパス
                .loginPage("/loginForm") // ログイン、フォーム表示のパス
                .failureUrl("/loginForm?error") // 認証失敗時の遷移先
                .defaultSuccessUrl("/customers", true) // 認証成功時の遷移先
                .usernameParameter("input_username") // ユーザ名のパラメータ名
                .passwordParameter("input_password") // パスワードのパラメータ名
            .and()
            .logout() // ログアウトに関する設定（POSTで指定のパスにアクセスさせる）
                    .logoutSuccessUrl("/loginForm");
    }

    /**
     * パスワードをハッシュ化するためのクラスを定義する
     * PasswordEncoderの実装を選ぶことで、ハッシュ化アルゴリズムを決める。
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}