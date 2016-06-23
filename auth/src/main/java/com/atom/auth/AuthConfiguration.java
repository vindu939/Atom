package com.atom.auth;

import com.google.api.client.util.Preconditions;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import static com.atom.auth.AuthConfiguration.EndpointRegion.WORLD;


/**
 * LoginConfiguration is used to setup primitives needed for the Uber SDK to authenticate.
 */
public class AuthConfiguration implements Serializable {
    /**
     * See
     * <a href="https://developer.uber.com/v1/sandbox">Sandbox</a> for more
     * information.
     */
    public enum Environment {
        PRODUCTION("api"),
        SANDBOX("sandbox-api");

        public String subDomain;

        Environment(String subDomain) {
            this.subDomain = subDomain;
        }
    }

    public enum EndpointRegion {
        WORLD("uber.com"),
        CHINA("uber.com.cn");

        public String domain;

        EndpointRegion(String domain) {
            this.domain = domain;
        }
    }

    /**
     * Builder for {@link AuthConfiguration}
     */
    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String serverToken;
        private String redirectUri;
        private EndpointRegion region = EndpointRegion.WORLD;
        private Environment environment;
        private Collection<String> scopes;
        private Collection<String> customScopes;
        private Locale locale;

        /**
         * The Uber API requires a registered clientId to be sent along with API requests and Deeplinks.
         * This can be registered and retrieved on the developer dashboard at https://developer.uber.com/
         *
         * @param clientId to be used for identification
         * @return
         */
        public Builder setClientId(@Nonnull String clientId) {
            this.clientId = clientId;
            return this;
        }

        /**
         * The Uber API requires a registered clientSecret to be used for Authentication.
         * This can be registered and retrieved on the developer dashboard at https://developer.uber.com/
         *
         * Do not set client secret for Android or client side applications.
         *
         * @param clientSecret to be used for identification
         * @return
         */
        public Builder setClientSecret(@Nonnull String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        /**
         * The Uber API can use a server token for some endpoints.
         *
         * @param serverToken to be used for identification
         * @return
         */
        public Builder setServerToken(@Nonnull String serverToken) {
            this.serverToken = serverToken;
            return this;
        }

        /**
         * Sets the redirect URI that is registered for this application.
         *
         * @param redirectUri the redirect URI {@link String} for this application.
         */
        public Builder setRedirectUri(@Nonnull String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }

        /**
         * Set the {@link EndpointRegion} your app is registered in.
         * Used to determine what endpoints to send requests to.
         *
         * @param region The {@link EndpointRegion} the SDK should use
         */
        public Builder setEndpointRegion(@Nonnull EndpointRegion region) {
            this.region = region;
            return this;
        }

        /**
         * Sets the {@link Environment} to be used for API requests
         *
         * @param environment to be set
         * @return
         */
        public Builder setEnvironment(@Nonnull Environment environment) {
            this.environment = environment;
            return this;
        }

        /**
         * Sets the Scope Collection to be used when requesting authentication
         *
         * @param scopes to be set
         * @return
         */
        public Builder setScopes(@Nonnull Collection<String> scopes) {
            this.scopes = scopes;
            return this;
        }

        /**
         * Sets a list of custom scopes that your application must be explicitly whitelisted
         * for. For any documented scopes, please use {@link #setScopes(Collection)} instead.
         */
        public Builder setCustomScopes(@Nonnull Collection<String> scopes) {
            this.customScopes = scopes;
            return this;
        }

        /**
         * Sets the requested Locale through the Accept-Language HTTP header. See
         * <a href="https://developer.uber.com/docs/localization">Localization</a>
         * for possible locales.
         */
        public Builder setLocale(@Nonnull Locale locale) {
            this.locale = locale;
            return this;
        }

        /**
         * Constructs {@link AuthConfiguration} from set Builder parameters.
         *
         * @return {@link AuthConfiguration}
         * @throws NullPointerException when clientId has not been set
         */
        public AuthConfiguration build() {
            Preconditions.checkNotNull(clientId, "Client must be set");

            if (region == null) {
                region = WORLD;
            }

            if (environment == null) {
                environment = Environment.PRODUCTION;
            }

            if (locale == null) {
                locale = Locale.US;
            }

            if (scopes == null) {
                scopes = new HashSet<>();
            } else {
                scopes = new HashSet<>(scopes);
            }

            if (customScopes == null) {
                customScopes = new HashSet<>();
            } else {
                customScopes = new HashSet<>(customScopes);
            }

            return new AuthConfiguration(
                    clientId,
                    clientSecret,
                    serverToken,
                    redirectUri,
                    region,
                    environment,
                    scopes,
                    customScopes,
                    locale);
        }
    }

    private final String clientId;
    private final String clientSecret;
    private final String serverToken;
    private final String redirectUri;
    private final EndpointRegion region;
    private final Environment environment;
    private final Collection<String> scopes;
    private final Collection<String> customScopes;
    private final Locale locale;

    protected AuthConfiguration(@Nonnull String clientId,
                                @Nonnull String clientSecret,
                                @Nonnull String serverToken,
                                @Nonnull String redirectUri,
                                @Nonnull EndpointRegion region,
                                @Nonnull Environment environment,
                                @Nonnull Collection<String> scopes,
                                @Nonnull Collection<String> customScopes,
                                @Nonnull Locale locale) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.serverToken = serverToken;
        this.redirectUri = redirectUri;
        this.region = region;
        this.environment = environment;
        this.scopes = scopes;
        this.customScopes = customScopes;
        this.locale = locale;
    }

    /**
     * Gets the Client ID to be used by the SDK for requests.
     *
     * @return The Client ID.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Gets the Client Secret to be used by the SDK for requests.
     *
     * @return The Client Secret.
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Gets the server Token to be used by the SDK for requests
     *
     * @return The Server Token.
     */
    public String getServerToken() {
        return serverToken;
    }

    /**
     * Gets the Redirect URI to be used for implicit grant.
     *
     * @return The Redirect URI.
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * Gets the current {@link EndpointRegion} the SDK is using.
     * Defaults to {@link EndpointRegion#WORLD}.
     *
     * @return The {@link EndpointRegion} the SDK is using.
     */
    public EndpointRegion getEndpointRegion() {
        return region;
    }

    /**
     * Gets the environment configured, either {@link Environment#PRODUCTION} or {@link Environment#SANDBOX}
     *
     * @return {@link Environment} that is configured
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Gets the endpoint host used to hit the Uber API.
     */
    @Nonnull
    public String getEndpointHost() {
        return String.format("https://%s.%s", environment.subDomain, region.domain);
    }

    /**
     * Gets the Scope's set for authentication
     *
     * @return The Scope Collection
     */
    public Collection<String> getScopes() {
        return scopes;
    }

    /**
     * Gets a list of custom scopes that your application must be explicitly whitelisted
     * for. For any documented scopes, please use {@link #getScopes()} instead.
     *
     * @return The Scope Collection
     */
    public Collection<String> getCustomScopes() {
        return customScopes;
    }

    /**
     * Get the requested language Locale for requests
     */
    public Locale getLocale() {
        return locale;
    }

    public Builder newBuilder() {
        return new Builder()
                .setClientId(clientId)
                .setRedirectUri(redirectUri)
                .setEndpointRegion(region)
                .setEnvironment(environment)
                .setScopes(scopes);
    }
}
