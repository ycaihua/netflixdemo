package cn.ibabygroup.auth;

import cn.ibabygroup.auth.service.security.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by zengxiaobo on 2016/8/1 for netflixdemo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class AuthApplication {
    public static void main(String[] args){
        SpringApplication.run(AuthApplication.class,args);
    }
    @Configuration
    @EnableWebSecurity
    protected static class webSecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        private MongoUserDetailsService userDetailsService;
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    .and().csrf().disable();
        }

        @Override
        @Bean
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }
    }
    @Configuration
    @EnableAuthorizationServer
    private static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter{
        private TokenStore tokenStore=new InMemoryTokenStore();
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        @Autowired
        private MongoUserDetailsService userDetailsService;
        @Autowired
        private Environment environment;
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("browser")
                    .authorizedGrantTypes("refresh_token","password")
                    .scopes("ui")
                .and()
                    .withClient("account_service")
                    .secret(environment.getProperty("ACCOUNT_SERVICE_PASSWORD"))
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
                .and()
                    .withClient("statistics-service")
                    .secret(environment.getProperty("STATISTICS_SERVICE_PASSWORD"))
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
                    .and()
                    .withClient("notification-service")
                    .secret(environment.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }
    }
}
