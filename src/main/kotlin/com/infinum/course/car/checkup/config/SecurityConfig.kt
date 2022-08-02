package com.infinum.course.car.checkup.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http {
            cors { }
            csrf { disable() }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeRequests {
                authorize(HttpMethod.POST, "/car", hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER"))
                authorize(HttpMethod.GET, "/car/*", hasAuthority("SCOPE_ADMIN"))
                authorize(HttpMethod.GET, "/cars", hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER"))
                authorize(HttpMethod.DELETE, "/car/*", hasAuthority("SCOPE_ADMIN"))

                authorize(HttpMethod.POST, "/checkup", hasAuthority("SCOPE_ADMIN"))
                authorize(HttpMethod.GET, "/checkup", hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER"))
                authorize(HttpMethod.GET, "/checkup-analytics", permitAll)
                authorize(HttpMethod.DELETE, "/checkup/*", hasAuthority("SCOPE_ADMIN"))

                authorize(anyRequest, authenticated)
            }
            oauth2ResourceServer {
                jwt {}
            }
        }

        return http.build()
    }
}