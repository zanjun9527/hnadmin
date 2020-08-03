package com.xiaoyuer.hn.admin.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoyuer
 */
@Configuration
public class TomcatConfig {


	/**
	 * tomcat版本升级，An invalid domain [] was specified for this cookie
	 */
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
		return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {
			@Override
			public void customize(TomcatServletWebServerFactory factory) {
				factory.addContextCustomizers(new TomcatContextCustomizer() {
					@Override
					public void customize(Context context) {
						context.setCookieProcessor(new LegacyCookieProcessor());
					}
				});
			}
		};
	}

}
