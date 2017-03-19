package se.repos.topics.demo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import se.repos.restclient.HttpStatusError;
import se.repos.restclient.RestGetClient;
import se.repos.restclient.RestResponse;
import se.repos.restclient.RestResponseBean;
import se.repos.restclient.auth.RestAuthenticationSimple;
import se.repos.restclient.javase.RestClientJavaNet;

public class CollabApiTest {

	@Test
	public void test() throws HttpStatusError, IOException {
		RestGetClient get = new RestClientJavaNet("http://topics-demo",
				new RestAuthenticationSimple("unittest", ""));
		RestResponse res = new RestResponseBean();
		get.get("/api/documents/example-doc", res);
		assertNotNull(res.toString());
		assertEquals("Should be JSON by default", '{', res.toString().charAt(0));
	}

}
