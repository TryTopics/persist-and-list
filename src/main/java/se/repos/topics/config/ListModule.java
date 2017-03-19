package se.repos.topics.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import se.simonsoft.cms.item.CmsRepository;
import se.simonsoft.cms.item.indexing.IdStrategy;
import se.simonsoft.cms.item.indexing.IdStrategyDefault;
import se.simonsoft.cms.reporting.ReportingConfiguration;
import se.simonsoft.cms.reporting.ReportingConfigurationDefault;
import se.simonsoft.cms.reporting.SearchCore;
import se.simonsoft.cms.reporting.repositem.CmsItemSearch;
import se.simonsoft.cms.reporting.repositem.CmsItemSearchRepositem;
import se.simonsoft.cms.reporting.solrj.SearchCoreRepositemSolrj;

public class ListModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SolrServer.class).annotatedWith(Names.named("repositem"))
			.toProvider(SolrServerProvider.Repositem.class);
		bind(SolrServer.class).annotatedWith(Names.named("reposxml"))
			.toProvider(SolrServerProvider.Reposxml.class);
		bind(SearchCore.class).annotatedWith(Names.named("repositem")).to(SearchCoreRepositemSolrj.class);
		
		bind(CmsItemSearch.class).to(CmsItemSearchRepositem.class);
		bind(IdStrategy.class).to(IdStrategyDefault.class);
		
		final ReportingConfiguration forAnyRepo = new ReportingConfigurationDefault();
		bind(new TypeLiteral<Map<CmsRepository, ReportingConfiguration>>() {})
			.toInstance(new HashMap<CmsRepository, ReportingConfiguration>() {
				private static final long serialVersionUID = 1L;
				@Override public ReportingConfiguration get(Object key) {
					return forAnyRepo;
				}
			});
	}

}
