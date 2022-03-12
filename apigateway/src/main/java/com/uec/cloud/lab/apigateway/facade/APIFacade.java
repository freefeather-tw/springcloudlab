package com.uec.cloud.lab.apigateway.facade;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class APIFacade {

    /**
     * redirect.
     * @param request
     * @param routeUrl
     * @param prefix
     * @return HTTP 回應物件
     */
    public ResponseEntity<String> redirect(final HttpServletRequest request,
                                           final String routeUrl,
                                           final String prefix) {
        try {
            // build up the redirect URL
            String redirectUrl = createRedirectUrl(request, routeUrl, prefix);
            log.debug("redirectUrl => " + redirectUrl);

            return route(createRequestEntity(request, redirectUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("REDIRECT ERROR",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String createRedirectUrl(final HttpServletRequest request,
                                     final String routeUrl,
                                     final String prefix) {
        String queryString = request.getQueryString();
        return URI.create(routeUrl
                + request.getRequestURI().replace(
                        request.getContextPath() + prefix, "")
                + (queryString != null ? "?" + queryString : ""))
                .normalize().toString();
    }

    private RequestEntity<byte[]> createRequestEntity(
                                    final HttpServletRequest request,
                                    final String url)
            throws URISyntaxException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        MultiValueMap<String, String> headers = parseRequestHeader(request);
        byte[] body = parseRequestBody(request);

        return new RequestEntity<>(body, headers, httpMethod, new URI(url));
    }

    private ResponseEntity<String> route(
            final RequestEntity<byte[]> requestEntity) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(requestEntity, String.class);
    }

    private byte[] parseRequestBody(final HttpServletRequest request) {
        try (InputStream inputStream = request.getInputStream()) {
            return StreamUtils.copyToByteArray(inputStream);
        } catch (IOException ioe) {
            return null;
        }
    }

    private MultiValueMap<String, String> parseRequestHeader(
            final HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues =
                    Collections.list(request.getHeaders(headerName));

            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
    }
}
