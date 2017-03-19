package se.repos.topics.config;

import javax.inject.Inject;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.google.inject.name.Named;

import javax.inject.Provider;

/**
 * Reuses the same instance for all requests, assuming thread safe operation and auth.
 */
public abstract class SolrServerProvider implements
		Provider<SolrServer> {

	private SolrServer solrServer;
	
	protected abstract String getCoreName();
	
	@Inject
	public void setSolrHost(@Named("config:se.simonsoft.cms.hostname") String solrHost) {
		//this.solrHost = solrHost;
		
		// If we upgrade solrj
		//String urlString = "http://localhost:8983/solr/techproducts";
		//SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		
		this.solrServer = new HttpSolrServer("http://" + solrHost + "/solr/" + getCoreName() + "/");
	}

	public SolrServer get() {
		return solrServer;
	}

	public static class Reposxml extends SolrServerProvider {
		@Override protected String getCoreName() { return "reposxml"; }
	}
	
	public static class Repositem extends SolrServerProvider {
		@Override protected String getCoreName() { return "repositem"; }
	}
	
}
