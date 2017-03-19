package se.repos.topics.list.solr;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import se.repos.topics.config.ListModule;
import se.simonsoft.cms.item.CmsItem;
import se.simonsoft.cms.item.list.CmsItemList;
import se.simonsoft.cms.reporting.rest.ReportingResource3;

public class SearchTest {

	@Test
	public void testItem() {
		Injector injector = Guice.createInjector(new ListModule(), new AbstractModule() {
			@Override
			protected void configure() {
				bind(String.class).annotatedWith(Names.named("config:se.simonsoft.cms.hostname"))
					.toInstance("topics-demo");
				bind(String.class).annotatedWith(Names.named("config:se.simonsoft.cms.repoparent"))
					.toInstance("/svn");
			}
		});
	
		ReportingResource3 reporting = injector.getInstance(ReportingResource3.class);
		UriInfo uriInfo = Mockito.mock(UriInfo.class);
		Mockito.when(uriInfo.getPath()).thenReturn("/topics/list");
		@SuppressWarnings("unchecked") MultivaluedMap<String,String> qp = Mockito.mock(MultivaluedMap.class);
		Mockito.when(qp.get("name")).thenReturn(Arrays.asList("Topics test"));
		Mockito.when(uriInfo.getQueryParameters()).thenReturn(qp);
		
		// dismax is what we want, but getting NPE here
		//CmsItemList result = reporting.getSearchSimple("*", "topics", "xml", uriInfo, null);
		
		CmsItemList result = reporting.getItems("*:*",
				"", 
				"topics01",
				"revt desc, name asc",
				"100",
				"Recently updated topics", 
				uriInfo, null);
		assertNotNull(result);
		int hits = 0;
		for (CmsItem item : result) {
			hits++;
			System.out.println("Hit: " + item.getId().toString());
		}
		assertNotEquals("should have hits", 0, hits);
	}

}
