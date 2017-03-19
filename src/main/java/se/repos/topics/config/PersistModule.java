package se.repos.topics.config;

import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNClientManager;

import com.google.inject.AbstractModule;

import se.repos.restclient.RestAuthentication;
import se.simonsoft.cms.backend.svnkit.commit.CmsCommitSvnkitEditor;
import se.simonsoft.cms.backend.svnkit.config.SvnKitAuthManagerProvider;
import se.simonsoft.cms.backend.svnkit.config.SvnKitClientManagerProvider;
import se.simonsoft.cms.backend.svnkit.config.SvnKitLowLevelProvider;
import se.simonsoft.cms.backend.svnkit.info.CmsItemLookupSvnkit;
import se.simonsoft.cms.item.commit.CmsCommit;
import se.simonsoft.cms.item.info.CmsItemLookup;

public class PersistModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RestAuthentication.class).to(RestAuthenticationInternal.class);
		
		bind(ISVNAuthenticationManager.class).toProvider(SvnKitAuthManagerProvider.class);
		bind(SVNClientManager.class).toProvider(SvnKitClientManagerProvider.class);
		// not needed: bind(CmsRepositoryLookup.class).to(CmsRepositoryLookupSvnkit.class);
		
		bind(SVNRepository.class).toProvider(SvnKitLowLevelProvider.class);
		bind(CmsItemLookup.class).to(CmsItemLookupSvnkit.class);
		bind(CmsCommit.class).to(CmsCommitSvnkitEditor.class);
	}

}
