package com.URPlus.ModelExample.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.feature.Feature;
import com.ur.urcap.api.domain.payload.Payload;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.tcp.TCP;
import com.ur.urcap.api.domain.tcp.TCPModel;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;
import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidationFactory;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputFactory;
import com.ur.urcap.api.domain.value.dynamics.InertiaMatrix;
import com.ur.urcap.api.domain.value.simple.Length.Unit;
import com.ur.urcap.api.domain.value.simple.Mass;
import com.ur.urcap.api.domain.value.simple.MomentOfInertia;
import com.ur.urcap.api.domain.variable.Variable;

public class MEProgramNodeContribution implements ProgramNodeContribution{

	
	private final ProgramAPIProvider apiProvider;
	private final MEProgramNodeView view;
	private final DataModel model;
	private final UndoRedoManager undoRedoManager;
	private final InputValidationFactory validationFactory;
	private final KeyboardInputFactory keyboardInputFactory;
	
	private static final String TCP_KEY = "tcp";
	private static final String TCP_DEFAULT = null;
	private static final String PAYLOAD_KEY = "payload";
	private static final String PAYLOAD_DEFAULT = null;
	private static final String VARIABLE_KEY = "variable";
	private static final String VARIABLE_DEFAULT = null;
	private static final String FEATURE_KEY = "feature";
	private static final String FEATURE_DEFAULT = null;
//	private static Collection<Payload> payloadList;

	
//	private static final S
	
	public MEProgramNodeContribution(ProgramAPIProvider apiProvider, MEProgramNodeView view, DataModel model) {
		this.view = view;
		this.apiProvider = apiProvider;
		this.model = model;
		this.undoRedoManager = apiProvider.getProgramAPI().getUndoRedoManager();
		this.keyboardInputFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getKeyboardInputFactory();
		this.validationFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getInputValidationFactory();
		// TODO Auto-generated constructor stub
	}
	private String[] getPayloadItems() {
		Collection<Payload> payloadList = getInstallationNode().getPayloadList();
		String[] items = new String[payloadList.size()];
		Arrays.fill(items, null);
		Iterator<Payload> it = payloadList.iterator();
		int i=0;
		while(it.hasNext()) {
			Payload temp = it.next();
			model.set(temp.toString(), temp); //store in DataModel
			items[i] = temp.toString();
			i++;
		}
		return items;
	}
	private String[] getTCPItems() {
		Collection<TCP> tcpList = getInstallationNode().getTcpList();
		String[] items = new String[tcpList.size()];
		Arrays.fill(items, null);
		Iterator<TCP> it = tcpList.iterator();
		int i=0;
		while(it.hasNext()) {
			items[i] = it.next().toString();
			i++;
		}
		return items;
	}
	private String[] getVariableItems() {
		Collection<Variable> variableList = getInstallationNode().getVariableList();
		String[] items = new String[variableList.size()];
		Arrays.fill(items, null);
		Iterator<Variable> it = variableList.iterator();
		int i=0;
		while(it.hasNext()) {
			items[i] = it.next().toString();
			i++;
		}
		return items;
	}
	private String[] getFeatureItems() {
		Collection<Feature> featureList = getInstallationNode().getFeatureList();
		String[] items = new String[featureList.size()];
		Arrays.fill(items, null);
		Iterator<Feature> it = featureList.iterator();
		int i=0;
		while(it.hasNext()) {
			items[i] = it.next().toString();
			i++;
		}
		return items;
	}
	private String getActivePayload() {
		return model.get(PAYLOAD_KEY, PAYLOAD_DEFAULT);
	}
	
	private String getActiveTCP() {
		return model.get(TCP_KEY, TCP_DEFAULT);
	}
	private String getActiveVariable() {
		return model.get(VARIABLE_KEY, VARIABLE_DEFAULT);
	}
	private String getActiveFeature() {
		return model.get(FEATURE_KEY, FEATURE_DEFAULT);
	}
	private MEInstallationNodeContribution getInstallationNode() {
		return apiProvider.getProgramAPI().getInstallationNode(MEInstallationNodeContribution.class);
	}
	
	public void onPayloadSelection(final String output) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				// TODO Auto-generated method stub
				model.set(PAYLOAD_KEY, output);
				Payload temp = model.get(output, (Payload) null);
//				System.out.println("Selected payload: "+getInstallationNode().getPayload(output));
				System.out.println("Selected payload: "+temp.getMass()+", "+temp.getCenterOfGravity()+", "+temp.getInertiaMatrix());
			}
		});
	}
	public void onVariableSelection(final String output) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				// TODO Auto-generated method stub
				model.set(VARIABLE_KEY, output);
			}
		});
	}
	public void onTcpSelection(final String output) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				// TODO Auto-generated method stub
				model.set(TCP_KEY, output);
			}
		});
	}
	public void onFeatureSelection(final String output) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				// TODO Auto-generated method stub
				model.set(FEATURE_KEY, output);
			}
		});
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		view.setPayloadComboBoxItems(getPayloadItems());
		view.setTCPComboBoxItems(getTCPItems());
		view.setVariableComboBoxItems(getVariableItems());
		view.setFeatureComboBoxItems(getFeatureItems());
		view.setPayloadComboBoxSelection(model.get(PAYLOAD_KEY, PAYLOAD_DEFAULT));
		view.setTCPComboBoxSelection(model.get(TCP_KEY, TCP_DEFAULT));
		view.setVariableComboBoxSelection(model.get(VARIABLE_KEY, VARIABLE_DEFAULT));
		view.setFeatureComboBoxSelection(model.get(FEATURE_KEY, FEATURE_DEFAULT));
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "ME: p"+getActivePayload()+" v"+getActiveVariable()+" t"+getActiveTCP()+" f"+getActiveFeature();
//		return "ME: p"+getActivePayload();
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		if(model.get(PAYLOAD_KEY, PAYLOAD_DEFAULT)==PAYLOAD_DEFAULT)
			return false;
		else 
			return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		Payload temp = model.get(getActivePayload(), (Payload)null);
		Double CX = temp.getCenterOfGravity().getX(Unit.M);
		Double CY = temp.getCenterOfGravity().getY(Unit.M);
		Double CZ = temp.getCenterOfGravity().getZ(Unit.M);
		Double IXX = temp.getInertiaMatrix().getIxxComponent(MomentOfInertia.Unit.KG_M2);
		Double IXY = temp.getInertiaMatrix().getIxyComponent(MomentOfInertia.Unit.KG_M2);
		Double IXZ = temp.getInertiaMatrix().getIxzComponent(MomentOfInertia.Unit.KG_M2);
		Double IYY = temp.getInertiaMatrix().getIyyComponent(MomentOfInertia.Unit.KG_M2);
		Double IYZ = temp.getInertiaMatrix().getIyzComponent(MomentOfInertia.Unit.KG_M2);
		Double IZZ = temp.getInertiaMatrix().getIzzComponent(MomentOfInertia.Unit.KG_M2);
		writer.appendLine("set_target_payload("+temp.getMass().getAs(Mass.Unit.KG)+", ["+CX+", "+CY+", "+CZ+"],["+IXX+","+IYY+","+IZZ+","+IXY+","+IXZ+","+IYZ+"])");
		writer.sleep(0.5);
		writer.writeChildren();
		
	}
	

}
