package nowjsio.ticketing.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.components(new Components())
			.info(new Info()
				.title("nowjsio Ticketing API")
				.description("nowjsio Ticketing REST API documentation.")
				.version("v0.1.0")
				.license(new License()
					.name("Apache 2.0")
					.url("https://www.apache.org/licenses/LICENSE-2.0"))
				.contact(new Contact()
					.name("Support Team")
					.email("nowjsio@gmail.com")))
			;
	}
}
