package com.atom.auth;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.MemoryDataStoreFactory;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static com.atom.auth.AuthConfiguration.EndpointRegion.WORLD;

/**
 * Utility for creating and managing OAuth 2.0 Credentials.
 */
public class OAuth2Credentials {

    public static final String AUTHORIZATION_PATH = "/oauth/v2/authorize";
    public static final String TOKEN_PATH = "/oauth/v2/token";


    private AuthorizationCodeFlow authorizationCodeFlow;
    private Collection<String> scopes;
    private String redirectUri;

    /**
     * Builder for OAuth2Credentials.
     */
    public static class Builder {

        private AuthConfiguration.EndpointRegion loginRegion;
        private Set<String> scopes;
        private Set<String> customScopes;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private HttpTransport httpTransport;
        private AuthorizationCodeFlow authorizationCodeFlow;
        private AbstractDataStoreFactory credentialDataStoreFactory;

        /**
         * Set the {@link AuthConfiguration} information
         */
        public Builder setSessionConfiguration(AuthConfiguration configuration) {
            this.loginRegion = configuration.getEndpointRegion();
            if (scopes == null) {
                this.scopes = (HashSet) configuration.getScopes();
            }

            if (customScopes == null) {
                this.customScopes = new HashSet<>(configuration.getCustomScopes());
            }

            this.clientId = configuration.getClientId();
            this.clientSecret = configuration.getClientSecret();
            this.redirectUri = configuration.getRedirectUri();
            return this;
        }

        /**
         * Sets the authorization server domain.
         */
        public Builder setLoginRegion(AuthConfiguration.EndpointRegion loginRegion) {
            this.loginRegion = loginRegion;
            return this;
        }

        /**
         * Sets the scopes to request for authentication for.
         */
        public Builder setScopes(Collection<String> scopes) {
            this.scopes = new HashSet<>(scopes);
            return this;
        }

        /**
         * Sets a list of custom scopes that your application must be explicitly whitelisted
         * for. For any documented scopes, please use {@link #setScopes(Collection)} instead.
         */
        public Builder setCustomScopes(Collection<String> scopes) {
            this.customScopes = new HashSet<>(scopes);
            return this;
        }

        /**
         * Sets the client ID and secret retrieved from the
         * <a href="https://developer.uber.com/apps">Manage Apps</a> page.
         */
        public Builder setClientSecrets(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            return this;
        }

        /**
         * Sets the redirect URI for authentication request.
         */
        public Builder setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }

        /**
         * Sets the HTTP Transport. Optional and defaults to {@link NetHttpTransport}.
         */
        public Builder setHttpTransport(HttpTransport httpTransport) {
            this.httpTransport = httpTransport;
            return this;
        }

        /**
         * Sets the Credential DataStore factory used for storing and loading Credentials per user.
         * Optional and defaults to an {@link MemoryDataStoreFactory}.
         */
        public Builder setCredentialDataStoreFactory(AbstractDataStoreFactory credentialDataStoreFactory) {
            this.credentialDataStoreFactory = credentialDataStoreFactory;
            return this;
        }

        /**
         * Sets the authorization code flow.
         */
        public Builder setAuthorizationCodeFlow(AuthorizationCodeFlow authorizationCodeFlow) {
            this.authorizationCodeFlow = authorizationCodeFlow;
            return this;
        }

        private void validate() {
            Preconditions.checkState(clientId != null
                            && !clientId.isEmpty()
                            && clientSecret != null
                            && !clientSecret.isEmpty(),
                    "Client ID and secret must be set.");
        }

        /**
         * Builds an OAuth2Credentials.
         */
        public OAuth2Credentials build() {
            validate();
            OAuth2Credentials oAuth2Credentials = new OAuth2Credentials();
            oAuth2Credentials.redirectUri = redirectUri;

            Set<String> allScopes = new TreeSet<>();

            if (scopes != null) {
                allScopes.addAll(scopes);
            }

            if (customScopes != null) {
                allScopes.addAll(customScopes);
            }
            if (!allScopes.isEmpty()) {
                oAuth2Credentials.scopes = allScopes;
            }

            if (httpTransport == null) {
                httpTransport = new NetHttpTransport();
            }

            if (credentialDataStoreFactory == null) {
                credentialDataStoreFactory = MemoryDataStoreFactory.getDefaultInstance();
            }

            if (loginRegion == null) {
                loginRegion = WORLD;
            }

            if (authorizationCodeFlow == null) {
                try {
                    AuthorizationCodeFlow.Builder builder =
                            new AuthorizationCodeFlow.Builder(
                                    BearerToken.authorizationHeaderAccessMethod(),
                                    httpTransport,
                                    new JacksonFactory(),
                                    new GenericUrl(getLoginDomain(loginRegion) + TOKEN_PATH),
                                    new ClientParametersAuthentication(clientId, clientSecret),
                                    clientId,
                                    getLoginDomain(loginRegion) + AUTHORIZATION_PATH);
                    if (oAuth2Credentials.scopes != null && !oAuth2Credentials.scopes.isEmpty()) {
                        builder.setScopes(oAuth2Credentials.scopes);
                    }
                    authorizationCodeFlow =
                            builder.setDataStoreFactory(credentialDataStoreFactory).build();
                } catch (IOException e) {
                    throw new IllegalStateException("Unexpected exception while building OAuth2Credentials.", e);
                }
            }
            oAuth2Credentials.authorizationCodeFlow = authorizationCodeFlow;
            return oAuth2Credentials;
        }

        private String getLoginDomain(AuthConfiguration.EndpointRegion endpointRegion) {
            return "https://login." + endpointRegion.domain;
        }
    }

    private OAuth2Credentials() {}

    /**
     * Gets the authorization URL to retrieve the authorization code.
     */
    @Nullable
    public String getAuthorizationUrl() throws UnsupportedEncodingException {
        String authorizationCodeRequestUrl =
                authorizationCodeFlow.newAuthorizationUrl().setScopes(scopes).build();
        if (redirectUri != null) {
            authorizationCodeRequestUrl += "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8");
        }
        return authorizationCodeRequestUrl;
    }

    /**
     * Gets the underlying {@link AuthorizationCodeFlow}.
     */
    public AuthorizationCodeFlow getAuthorizationCodeFlow() {
        return authorizationCodeFlow;
    }

    /**
     * Authenticates using the authorization code for the user and stores the Credential in the
     * underlying {@link DataStore}.
     * @throws AuthException If the credential could not be created.
     */
    public Credential authenticate(String authorizationCode, String userId) throws AuthException {
        Preconditions.checkNotNull(authorizationCode, "Authorization code must not be null");
        AuthorizationCodeTokenRequest tokenRequest = authorizationCodeFlow.newTokenRequest(authorizationCode);

        TokenResponse tokenResponse;
        try {
            tokenResponse = tokenRequest
                    .setRedirectUri(redirectUri)
                    .setScopes(scopes)
                    .execute();
        } catch (IOException e) {
            throw new AuthException("Unable to request token.", e);
        }
        try {
            return authorizationCodeFlow.createAndStoreCredential(tokenResponse, userId);
        } catch (IOException e) {
            throw new AuthException("Unable to create and store credential.", e);
        }
    }

    /**
     * Loads the Credential for the user from the underlying {@link DataStore}.
     * @throws AuthException If the credential could not be loaded.
     */
    public Credential loadCredential(String userId) throws AuthException {
        try {
            return authorizationCodeFlow.loadCredential(userId);
        } catch (IOException e) {
            throw new AuthException("Unable to load credential.", e);
        }
    }

    /**
     * Clears the credential for the user in the underlying (@link DateStore}.
     * @throws AuthException If the credential could not be cleared.
     */
    public void clearCredential(String userId) throws AuthException {
        try {
            authorizationCodeFlow.getCredentialDataStore().delete(userId);
        } catch (IOException e) {
            throw new AuthException("Unable to clear credential.", e);
        }
    }

    /**
     * Gets the redirect URI.
     */
    public String getRedirectUri() {
        return redirectUri;
    }
}

