package com.URPlus.ModelExample.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class MEInstallationNodeService implements SwingInstallationNodeService<MEInstallationNodeContribution, MEInstallationNodeView>{

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Model Example";
	}

	@Override
	public MEInstallationNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new MEInstallationNodeView();
	}

	@Override
	public MEInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			MEInstallationNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new MEInstallationNodeContribution(apiProvider, model, view);
	}

}
