package org.progressivelifestyle.bustrip.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.MemoryCredentialStore;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * A helper class for Google's OAuth2 authentication API.
 * 
 * @version 20130224
 * @author Matyas Danter
 */
@Component
public final class GoogleAuthHelper implements InitializingBean {

	/**
	 * Please provide a value for the CLIENT_ID constant before proceeding, set
	 * this up at https://code.google.com/apis/console/
	 */
	@Value("${google.oauth.id}")
	private String clientId;
	/**
	 * Please provide a value for the CLIENT_SECRET constant before proceeding,
	 * set this up at https://code.google.com/apis/console/
	 */
	@Value("${google.oauth.secret}")
	private String clientSecret;

	/**
	 * Callback URI that google will redirect to after successful authentication
	 */
	@Value("${google.oauth.callback}")
	private String callbackURI;

	// start google authentication constants
	@Value("${google.oauth.spreadsheet.scope}")
	private String scope;
	
	private String userInfoURL;
	
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	// end google authentication constants

	private GoogleAuthorizationCodeFlow flow;

	/**
	 * Constructor initializes the Google Authorization Code Flow with CLIENT
	 * ID, SECRET, and SCOPE
	 */
	public GoogleAuthHelper() {

	}

	/**
	 * Builds a login URL based on client ID, secret, callback URI, and scope
	 */
	public String buildOAuthUrl() {

		final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();

		return url.setRedirectUri(callbackURI).setScopes(Arrays.asList(scope.split(";"))).setState(generateStateToken()).build();
	}

	/**
	 * Generates a secure state token
	 */
	private String generateStateToken() {

		SecureRandom sr1 = new SecureRandom();

		String stateToken = "google;" + sr1.nextInt();
		return stateToken;

	}


	/**
	 * Expects an Authentication Code, and makes an authenticated request for
	 * the user's profile information
	 * 
	 * @return JSON formatted user profile information
	 * @param authCode
	 *            authentication code provided by google
	 */
	public String getUserInfoJson(final String authCode, Credential credential) throws IOException {
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
		final GenericUrl url = new GenericUrl(userInfoURL);
		final HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		final String jsonIdentity = request.execute().parseAsString();
		return jsonIdentity;

	}

	public Credential generateCredential(final String authCode, String userId) throws IOException {
		final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(callbackURI).execute();
		return flow.createAndStoreCredential(response, userId);
	}
	
	public Credential reGenerateCredential(final String authCode, Credential expiredCredential) throws IOException {
		final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(callbackURI).execute();
		response.setRefreshToken(expiredCredential.getRefreshToken());
		System.out.println("Refresh Token: "+response.getRefreshToken()+", "+expiredCredential.getRefreshToken());
		return flow.createAndStoreCredential(response, authCode);
	}	
	
	public String reBuildLoginURL(String authCode) throws IOException{
		flow.getCredentialStore().delete(authCode, null);
		return buildOAuthUrl();
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setCallbackURI(String callbackURI) {
		this.callbackURI = callbackURI;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setUserInfoURL(String userInfoURL) {
		this.userInfoURL = userInfoURL;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientId, clientSecret, Arrays.asList(scope.split(";"))).setCredentialStore(new MemoryCredentialStore()).setAccessType("offline").build();
		generateStateToken();
	}

	public boolean checkCredentialExpiration(String authCode) throws IOException {
		Credential cred  = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod()).build();
		flow.getCredentialStore().load(authCode, cred);
		return cred.getExpirationTimeMilliseconds()<(System.currentTimeMillis()-10000);
	}
	
	public Credential getStoredCredential(String uniqueId) throws IOException{
		Credential credential = flow.loadCredential(uniqueId);
		if(credential!=null && credential.getExpirationTimeMilliseconds()<(System.currentTimeMillis()+10000))
			return flow.loadCredential(uniqueId);
		return null;
	}
}
