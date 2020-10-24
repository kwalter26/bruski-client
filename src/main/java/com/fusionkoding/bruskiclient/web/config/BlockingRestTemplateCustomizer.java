package com.fusionkoding.bruskiclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    @Value("${bruski.http.client.pooling.max-total}")
    private int maxTotal;
    @Value("${bruski.http.client.pooling.default-max-per-route-total}")
    private int defaultMaxPerRouteTotal;

    @Value("${bruski.http.client.request-timeout}")
    private int requestTimeout;

    @Value("${bruski.http.client.socket-timeout}")
    private int socketTimeout;

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRouteTotal);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(requestTimeout)
                .setSocketTimeout(socketTimeout).build();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

}
