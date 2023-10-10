package com.example.SpringBootSample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * セキュリティを適用しない
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**");
    }

    /**
     * セキュリティ各種設定
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ログイン不要ページの設定
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()//直リンクOK
                .antMatchers("/user/signup").permitAll()//直リンクOK
                .anyRequest().authenticated();//それ以外は直リンクNG
        http
                .formLogin()
                        .loginProcessingUrl("/login")//ログイン処理のパス
                .loginPage("/login")
                        .failureUrl("/login?error")//ログイン失敗時の遷移先
                .usernameParameter("userId")
                        .passwordParameter("password")
                                .defaultSuccessUrl("/user/list",true);
        // CSRF対策を無効に設定
        http.csrf().disable();
    }

}
