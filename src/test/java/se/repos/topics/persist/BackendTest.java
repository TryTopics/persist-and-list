package se.repos.topics.persist;

import org.junit.Test;
import static org.junit.Assert.*;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import se.repos.topics.config.PersistModule;
import se.simonsoft.cms.item.CmsItem;
import se.simonsoft.cms.item.CmsItemId;
import se.simonsoft.cms.item.CmsItemKind;
import se.simonsoft.cms.item.CmsItemPath;
import se.simonsoft.cms.item.CmsRepository;
import se.simonsoft.cms.item.info.CmsItemLookup;

public class BackendTest {

	@Test
	public void test() {
		Injector injector = Guice.createInjector(new PersistModule(), new AbstractModule() {
			@Override
			protected void configure() {
				bind(CmsRepository.class).toInstance(new CmsRepository(
						"http://topics-demo",
						"/svn",
						"topics01"));
			}
		});
		
		CmsItemLookup lookup = injector.getInstance(CmsItemLookup.class);
		CmsRepository repo = injector.getInstance(CmsRepository.class);
		CmsItemPath path = new CmsItemPath("/topics/");
		CmsItemId itemId = repo.getItemId(path, null); // revision not know atm
		CmsItem item = lookup.getItem(itemId);
		assertNotNull(item);
		assertEquals(CmsItemKind.Folder, item.getKind());
		assertTrue(item.getRevisionChanged().getNumber() > 0);
		System.out.println("Topics persist folder is at " + item.getRevisionChanged());
	}

}
