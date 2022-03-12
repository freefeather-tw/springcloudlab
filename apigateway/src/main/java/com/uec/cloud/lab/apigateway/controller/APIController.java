package com.uec.cloud.lab.apigateway.controller;

import com.uec.cloud.lab.apigateway.facade.APIFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@Slf4j
public class APIController {

    /**
     * apiFacade.
     */
    @Autowired
    private APIFacade apiFacade;

    /**
     * apServerUrl.
     */
    @Value("${ap.server}")
    private String apServerUrl;

    /**
     * apServerPrefix.
     */
    @Value("${ap.prefix}")
    private String apServerPrefix;

    /**
     * HTTP_PORT.
     */
    private static final int HTTP_PORT = 80;

    /**
     * HTTPS_POST.
     */
    private static final int HTTPS_POST = 443;

    /**
     * catchAll.
     * @param request
     * @return Http回應資料
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/**",
                    method = {RequestMethod.GET,
                              RequestMethod.POST,
                              RequestMethod.PUT,
                              RequestMethod.DELETE})
    public final ResponseEntity catchAll(final HttpServletRequest request)
            throws URISyntaxException {

        ResponseEntity responseEntity = apiFacade.redirect(request,
                                                           apServerUrl,
                                                           apServerPrefix);

        Object body = responseEntity.getBody();
        if (body instanceof String) {
            String content = (String) body;
            body = content.replaceAll(apServerUrl,
                            getMyDomainName(request)
                                        + request.getContextPath()
                                        + apServerPrefix);
        }

        return ResponseEntity
                .status(responseEntity.getStatusCode())
                .headers(responseEntity.getHeaders())
                .body(body);
    }

    /**
     * getMyDomainName.
     * @param request
     * @return 本機server domain name
     */
    private static String getMyDomainName(final HttpServletRequest request) {
        String myDomainName = "";
        try {
            if (StringUtils.isBlank(myDomainName)) {
                URI uri = new URI(request.getRequestURL().toString());
                String showPort =
                        (uri.getPort() != HTTP_PORT
                                && uri.getPort() != HTTPS_POST)
                                ? ":" + uri.getPort() : "";

                myDomainName = uri.getScheme()
                        + "://"
                        + uri.getHost()
                        + showPort;
            }
        } catch (URISyntaxException se) {
            myDomainName = "http://localhost:8080";
        }

        return myDomainName;
    }
}
