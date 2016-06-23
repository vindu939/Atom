package com.atom;

import com.atom.auth.AuthConfiguration;
import com.atom.auth.AuthException;
import com.atom.auth.OAuth2Credentials;

import com.atom.auth.utils.DataStore;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.vin.urlshortener.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by aravindp on 21/6/16.
 */
@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthConfiguration authConfiguration;

    @Value("${urlshortener.api.key}")
    private String urlShortenerApiKey;

    @RequestMapping(value = "Callback", method = RequestMethod.GET)
    public void authCallback(@RequestParam(value = "state", required = false) String state,
                               @RequestParam("code") String code, HttpServletResponse response){

        String redirectPage = "/com/vin/urlshortener/error";
        try {
            OAuth2Credentials oAuth2Credentials = getOAuth2Credentials(state);
            Credential oldCredentials = oAuth2Credentials.loadCredential(state);
            if (oldCredentials == null ) {
                Credential credential = oAuth2Credentials.authenticate(code, state);
            }
            redirectPage = "#/contact?abc=12345&";
        } catch (AuthException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        try {
            response.sendRedirect(redirectPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "isAuthenticated", method = RequestMethod.GET)
    @ResponseBody
    public void isAuthenticated(@RequestParam("id") String id, HttpServletResponse response){
        try {
            OAuth2Credentials oAuth2Credentials = getOAuth2Credentials(id);
            Credential credentials = oAuth2Credentials.loadCredential(id);

            if (credentials == null){
                String authUrl = oAuth2Credentials.getAuthorizationUrl();
                authUrl = authUrl + "&state="+id;
                UrlShortener urlShortener = new UrlShortener(urlShortenerApiKey);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, urlShortener.getShortUrl(authUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    private OAuth2Credentials getOAuth2Credentials(String id) throws IOException {
        AbstractDataStoreFactory dataStoreFactory = DataStore.getFileDataStore(System.getProperty("user.home") + File.separator + "uber" +
                File.separator + id + File.separator + ".uber_credentials");

        OAuth2Credentials oAuth2Credentials = new OAuth2Credentials.Builder()
                .setSessionConfiguration(authConfiguration)
                .setCredentialDataStoreFactory(dataStoreFactory)
                .build();

        return oAuth2Credentials;
    }
}
