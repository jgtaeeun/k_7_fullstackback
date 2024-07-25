package edu.pnu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomConfig implements WebMvcConfigurer{
	
	//기본 형태
//	@Override
//	public void addCorsMappings(@NonNull CorsRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addMapping("/**")   //모든 주소에 대해서
//				.allowedMethods(CorsConfiguration.ALL) 
//				.allowedOrigins(CorsConfiguration.ALL);
//	}
	
	//확장 형태
	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/board/**")   // /board 포함 하부 모든 주소에 대해서
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name()) 
				.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000");
		registry.addMapping("/member/**") //member 포함 하부 모든 주소에 대해서
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
				.allowedOrigins("http://localhost:3000");
		
		//인증
		registry.addMapping("/**")
				.allowCredentials(true)                    //클라이언트가 자격증명(쿠키/인증헤더)을 포함하도록 허용       
				.allowedHeaders(HttpHeaders.AUTHORIZATION) //클라이언트가 요청 시 사용할 수 있는 헤더 지정
				.exposedHeaders(HttpHeaders.AUTHORIZATION) //클라이언트가 응답에 접근할 수 있는 헤더 지정
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(),
						HttpMethod.PUT.name(), HttpMethod.DELETE.name())
				.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000"); // CORS 요청을 허용할 출처 지정

				
	}	
}
