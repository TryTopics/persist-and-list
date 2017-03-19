package se.repos.topics.config;

import javax.net.ssl.SSLContext;

import se.repos.restclient.RestAuthentication;

public class RestAuthenticationInternal implements RestAuthentication {

	public String getUsername(String root, String resource, String realm) {
		return "topics-persist-and-list";
	}

	public String getPassword(String root, String resource, String realm, String username) {
		return "";
	}

	public SSLContext getSSLContext(String root) {
		return null;
	}

}
