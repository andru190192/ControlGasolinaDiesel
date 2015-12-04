package ec.com.distrito.tesisControlGasolina.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/javax.faces.resource/**", "/resources/**",
						"/login.jsf").permitAll()
				.antMatchers("/views/home.jsf")
				.access("isAuthenticated()")

				.antMatchers("/views/estudiantes/listadoEstudiantes.jsf")
				.hasAnyAuthority("ADMINISTRADOR", "SECRETARIA", "DOSCENTE")
				.antMatchers("/views/estudiantes/reportes.jsf")
				.hasAnyAuthority("ADMINISTRADOR", "SECRETARIA", "DOSCENTE")
				
				.antMatchers("/views/matriculacion/listadoMatriculas.jsf")
				.hasAnyAuthority("ADMINISTRADOR", "SECRETARIA")
				.antMatchers("/views/matriculacion/certificadoMatricula.jsf")
				.hasAnyAuthority("ADMINISTRADOR", "SECRETARIA")
				.antMatchers("/views/matriculacion/periodosLectivos.jsf")
				.hasAnyAuthority("ADMINISTRADOR")
				
				.antMatchers("/views/rrhh/empleado.jsf")
				.hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/views/rrhh/cargo.jsf")
				.hasAnyAuthority("ADMINISTRADOR")
				
				.antMatchers("/views/parametros/configuracion.jsf")
				.hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/views/matriculacion/bitacora.jsf")
				.hasAnyAuthority("ADMINISTRADOR")
				
				
				.antMatchers("/views/seguridad/404.jsf")
				.access("isAuthenticated()")
				.antMatchers("/views/seguridad/accesoDenegado.jsf")
				.access("isAuthenticated()")
				.antMatchers("/views/seguridad/cambiarClave.jsf")
				.access("isAuthenticated()")
				.antMatchers("/views/seguridad/cambiarClaveNueva.jsf")
				.access("isAuthenticated()")
				.antMatchers("/views/seguridad/error.jsf")
				.access("isAuthenticated()")
				.antMatchers("/views/seguridad/errorPago.jsf")
				.access("isAuthenticated()")
								
				.and().formLogin().loginPage("/login.jsf")
				.defaultSuccessUrl("/views/home.jsf")
				.and().logout().logoutUrl("/logout.jsf")
				.logoutSuccessUrl("/login.jsf")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and().sessionManagement()
				.invalidSessionUrl("/login.jsf")
				.maximumSessions(1);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		PersistenceConfig persistenceConfig = new PersistenceConfig();

		auth.jdbcAuthentication().dataSource(persistenceConfig.dataSource())
				.passwordEncoder(new ShaPasswordEncoder(256))
				.usersByUsernameQuery(getUserQuery())
				.authoritiesByUsernameQuery(getAuthoritiesQuery());
	}

	private String getAuthoritiesQuery() {
		return "select c.cedula , r.nombre "
				+ "from matriculacion.persona as p, matriculacion.rol as r, matriculacion.rolusuario as ur "
				+ "where p.personaid = ur.personaid and r.rolid = ur.rolid and ur.activo=true and p.cedula = ?";
	}

	private String getUserQuery() {
		return "select cedula, password, activo from matriculacion.persona "
				+ "where cedula = ?";
	}
}
