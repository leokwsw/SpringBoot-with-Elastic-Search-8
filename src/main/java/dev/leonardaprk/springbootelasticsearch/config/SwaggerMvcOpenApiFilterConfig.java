package dev.leonardaprk.springbootelasticsearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Component
public class SwaggerMvcOpenApiFilterConfig implements WebMvcOpenApiTransformationFilter {
  @Override
  public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
    OpenAPI openApi = context.getSpecification();
    Server localServer = new Server();
    localServer.setUrl(openApi.getServers().get(0).getUrl() + "/");

    openApi.setServers(Collections.singletonList(localServer));
    return openApi;
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return delimiter.equals(DocumentationType.OAS_30);
  }
}
