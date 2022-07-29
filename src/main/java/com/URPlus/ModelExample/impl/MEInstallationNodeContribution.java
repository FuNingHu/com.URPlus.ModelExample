package com.URPlus.ModelExample.impl;

import java.util.Collection;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.feature.Feature;
import com.ur.urcap.api.domain.feature.FeatureModel;
import com.ur.urcap.api.domain.payload.Payload;
import com.ur.urcap.api.domain.payload.PayloadContributionModel;
import com.ur.urcap.api.domain.payload.PayloadModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.tcp.TCP;
import com.ur.urcap.api.domain.tcp.TCPModel;
import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidationFactory;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputFactory;
import com.ur.urcap.api.domain.variable.Variable;
import com.ur.urcap.api.domain.variable.VariableModel;

public class MEInstallationNodeContribution implements InstallationNodeContribution{

	private final DataModel model;
	private final MEInstallationNodeView view;
	private final KeyboardInputFactory keyboardInputFactory;
	private final InputValidationFactory validationFactory;
	private final PayloadModel payloadModel;
	private final TCPModel tcpModel;
	private final VariableModel variableModel;
	private final FeatureModel featureModel;
	private static PayloadContributionModel payloadContribution;
	
	public MEInstallationNodeContribution(InstallationAPIProvider apiProvider, DataModel model, MEInstallationNodeView view) {
		this.model = model;
		this.view = view;
		this.keyboardInputFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getKeyboardInputFactory();
		this.validationFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getInputValidationFactory();
		this.variableModel = apiProvider.getInstallationAPI().getVariableModel();
		this.featureModel = apiProvider.getInstallationAPI().getFeatureModel();
		this.payloadModel = apiProvider.getInstallationAPI().getPayloadModel();
		this.tcpModel = apiProvider.getInstallationAPI().getTCPModel();
		this.payloadContribution = apiProvider.getInstallationAPI().getPayloadContributionModel();
	}
	
	public Collection<Payload> getPayloadList(){
		return payloadModel.getPayloads();
	}
	public Collection<TCP> getTcpList(){
		return tcpModel.getTCPs();
	}
	public Collection<Variable> getVariableList(){
		return variableModel.getAll();
	}
	public Collection<Feature> getFeatureList(){
		return featureModel.getGeomFeatures();
	}
	public Payload getPayload(String id) {
		return payloadContribution.getPayload(id); // to read from payloadContributionModel with name is difficult.
	}
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		
	}

}
