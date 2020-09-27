package de.rheinfabrik.heimdalldroid.network.oauth2;

import de.rheinfabrik.heimdall2.accesstoken.OAuth2AccessToken;
import de.rheinfabrik.heimdall2.rxjava.grants.OAuth2RefreshAccessTokenGrant;
import de.rheinfabrik.heimdalldroid.network.TraktTvApiFactory;
import de.rheinfabrik.heimdalldroid.network.models.RefreshTokenRequestBody;
import io.reactivex.Single;

/**
 * TraktTv refresh token grant as described in http://docs.trakt.apiary.io/#reference/authentication-oauth/token/exchange-refresh_token-for-access_token.
 */
public class TraktTvRefreshAccessTokenGrant extends OAuth2RefreshAccessTokenGrant {

    // Properties

    public String clientSecret;
    public String clientId;
    public String redirectUri;

    // OAuth2RefreshAccessTokenGrant

    @Override
    public Single<OAuth2AccessToken> grantNewAccessToken() {
        RefreshTokenRequestBody body = new RefreshTokenRequestBody(getRefreshToken(), clientId, clientSecret, redirectUri, GRANT_TYPE);
        return TraktTvApiFactory.newApiServiceRxJava().refreshAccessToken(body).singleOrError();
    }
}
