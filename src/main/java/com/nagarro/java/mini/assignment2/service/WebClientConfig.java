package com.nagarro.java.mini.assignment2.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
	
	@Bean
	public ExecutorService executorService(){
		return Executors.newFixedThreadPool(2);
	}
	
	@Bean(name = "webClientApi1")
    public WebClient webClientApi1() {
        return WebClient.builder()
                .baseUrl("https://randomuser.me/api/")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                        .responseTimeout(Duration.ofMillis(2000))
                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS)))
                        .doOnConnected(conn -> conn.addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)))))
                .build();
    }
	
	@Bean(name = "webClientApi2")
    public WebClient webClientApi2(ExecutorService executorService) {
        return WebClient.builder()
                .baseUrl("https://api.nationalize.io/")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                        .responseTimeout(Duration.ofMillis(1000))
                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS)))
                        .doOnConnected(conn -> conn.addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)))))
                .build();
    }

    @Bean(name = "webClientApi3")
    public WebClient webClientApi3(ExecutorService executorService) {
        return WebClient.builder()
                .baseUrl("https://api.genderize.io/")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                        .responseTimeout(Duration.ofMillis(1000))
                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS)))
                        .doOnConnected(conn -> conn.addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)))))
                .build();
    }
	
	
	
	

}
