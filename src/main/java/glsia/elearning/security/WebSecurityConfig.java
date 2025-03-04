package glsia.elearning.security;

import glsia.elearning.security.jwt.AuthEntryPointJwt;
import glsia.elearning.security.jwt.AuthTokenFilter;
import glsia.elearning.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

//	@Override
//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable()
//			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
//			.antMatchers("/api/test/**").permitAll()
//			.anyRequest().authenticated();
//
//		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/questions").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/professor").permitAll()
                .requestMatchers("/etudiant").permitAll()
                .requestMatchers("/exams").permitAll()
                .requestMatchers("/cours/**").permitAll()
                .requestMatchers("/lessons/**").permitAll()
                .requestMatchers("/questions/**").permitAll()
                .requestMatchers("/exams/**").permitAll()
                .requestMatchers("/inscription/**").permitAll()
                .requestMatchers("/results/**").permitAll()
                .requestMatchers("/professor/**").permitAll()
                .requestMatchers("/etudiant/**").permitAll()
                .requestMatchers("/api/cours").permitAll()
                .requestMatchers("/api/lessons").permitAll()
                .requestMatchers("/api/exams/**").permitAll()
                .requestMatchers("/api/inscription/**").permitAll()
                .requestMatchers("/api/questions/**").permitAll()
                .requestMatchers("/api/auth/signup").permitAll()
                .requestMatchers("/api/auth/signin").permitAll()
                .requestMatchers("/api/auth/signup/**").permitAll()
                .requestMatchers("/api/auth/signin/**").permitAll()
                .requestMatchers("/api/auth/signout/**").permitAll()
                .requestMatchers("/api/auth/refresh/**").permitAll()
                .requestMatchers("/api/auth/refreshToken").permitAll()
                .requestMatchers("/api/auth/refreshToken/**").permitAll()
                .requestMatchers("/api/auth/professor").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/auth/etudiant/**").permitAll()
                .requestMatchers("/api/auth/professor/**").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/dashboard/**").permitAll()
                .requestMatchers("/cours/edit/**").permitAll()
                .requestMatchers("/home/**").permitAll()
                .requestMatchers("/static/css/**", "/images/**", "/js/**").permitAll() // Autoriser les ressources statiques
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}