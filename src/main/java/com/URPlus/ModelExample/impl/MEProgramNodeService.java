package com.URPlus.ModelExample.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class MEProgramNodeService implements SwingProgramNodeService<MEProgramNodeContribution, MEProgramNodeView>{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "ME_Node";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		configuration.setChildrenAllowed(false);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Model Example";
	}

	@Override
	public MEProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Locale locale = systemAPI.getSystemSettings().getLocalization().getLocaleForProgrammingLanguage();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >= 5? new V5Style(): new V3Style();
		
		return new MEProgramNodeView(apiProvider, style, locale);
	}

	@Override
	public MEProgramNodeContribution createNode(ProgramAPIProvider apiProvider, MEProgramNodeView view, DataModel model,
			CreationContext context) {
		// TODO Auto-generated method stub
		return new MEProgramNodeContribution(apiProvider, view, model);
	}

}
