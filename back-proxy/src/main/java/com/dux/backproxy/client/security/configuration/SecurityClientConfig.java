package com.dux.backproxy.client.security.configuration;

import com.dux.backproxy.client.security.exception.SecurityExceptionDecoder;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class SecurityClientConfig {

  @Bean
  public RequestInterceptor disableSslValidationInterceptor() {
    return new DisableSslValidationInterceptor();
  }

  private static class DisableSslValidationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
      try {
        // Desactivar la validación SSL
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }

          public void checkClientTrusted(X509Certificate[] certs, String authType) {
          }

          public void checkServerTrusted(X509Certificate[] certs, String authType) {
          }
        }}, new java.security.SecureRandom());

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

      } catch (NoSuchAlgorithmException | KeyManagementException e) {
        e.printStackTrace();
      }
    }
  }

  @Bean
  Logger.Level feignTokenDecoderLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  ErrorDecoder feignTokenDecoderErrorDecoder() {
    return new SecurityExceptionDecoder();
  }
}
