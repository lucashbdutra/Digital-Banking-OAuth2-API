package com.banking.account.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Habilitando essas três configurações o token do "oauth" ja funciona normalmente, ele gera um "user"
 * e um "password" para a aplicação (que tem que ser colocado no postman em Auth, como "Basic Auth"),
 * além de gerar uma senha pra usar com os usuários que forem criados. Isso tudo tem que ser mandado como um POST
 * no endereço "http://localhost:8080/oauth/token", pra que eu pegue o token que sera usado nas comunicações.
 * agora em todas as comunicações usando a API vai ser necessário colocar na aba "Auth" o type "Bearer Token"
 * e adicionar o token que for gerado no post.
 *
 */
@EnableWebSecurity
@EnableAuthorizationServer // Pesquisar depois os substitutos para essas anotações deprecateds
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //Esses beans são métodos necessário pra utilização do WebSecurity
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Jao").password("123").roles("ADMIN");
        //Aqui eu estou adicionando manualmente um clinte pra ser armazenado em memória
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
