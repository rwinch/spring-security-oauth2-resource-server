/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.resourceserver.ResourceServerConfigurer;

import java.security.PublicKey;
import java.util.Map;

/**
 * @author Josh Cummings
 */
@SpringBootApplication
public class LocalKeySetApplication {

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		Map<String, PublicKey> verify;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			resourceServer(http)
					.jwt().signature().keys(this.verify);
		}

		protected ResourceServerConfigurer resourceServer(HttpSecurity http) throws Exception {
			return http.apply(new ResourceServerConfigurer());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalKeySetApplication.class, args);
	}
}