package pl.hubertgawrys.securityspring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.hubertgawrys.securityspring.models.SecureDetailsUserModel;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    final SecureDetailsUserModel secureDetailsUserModel;

    public SecurityConfig(SecureDetailsUserModel secureDetailsUserModel) {
        this.secureDetailsUserModel = secureDetailsUserModel;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/content")
                .authenticated()
//                .antMatchers("/login")
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("login")
//                .passwordParameter("password")
               .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//        auth.userDetailsService(secureDetailsUserModel).passwordEncoder(encoder);
    }
}
