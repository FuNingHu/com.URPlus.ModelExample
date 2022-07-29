package com.URPlus.ModelExample.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class MEProgramNodeView implements SwingProgramNodeView<MEProgramNodeContribution>{

	private final Style style;
	private final ViewAPIProvider apiProvider;
	private Locale locale;
	private JComboBox<String> tcpComboBox = new JComboBox<String>();
	private JComboBox<String> payloadComboBox = new JComboBox<String>();
	private JComboBox<String> variableComboBox = new JComboBox<String>();
	private JComboBox<String> featureComboBox = new JComboBox<String>();
	
	public MEProgramNodeView(ViewAPIProvider apiProvider, Style style, Locale locale) {
		// TODO Auto-generated constructor stub
		this.apiProvider = apiProvider;
		this.style = style;
		this.locale = locale;
	}
	
	@Override
	public void buildUI(JPanel panel, final ContributionProvider<MEProgramNodeContribution> provider) {
		// TODO Auto-generated method stub
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createComboBox("Payload list: ", payloadComboBox, provider, new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					provider.get().onPayloadSelection(e.getItem().toString());
				}
			}
		}));
		panel.add(createSpacing(0, 20));
		panel.add(createComboBox("Variable list: ", variableComboBox, provider, new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					provider.get().onVariableSelection(e.getItem().toString());
				}
			}
		}));
		panel.add(createSpacing(0, 20));
		panel.add(createComboBox("TCP list: ", tcpComboBox, provider, new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					provider.get().onTcpSelection(e.getItem().toString());
				}
			}
		}));
		panel.add(createSpacing(0, 20));
		panel.add(createComboBox("Feature list: ", featureComboBox, provider, new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					provider.get().onFeatureSelection(e.getItem().toString());
				}
			}
		}));
	}
	
	public void setTCPComboBoxItems(String[] items) {
		tcpComboBox.removeAllItems();
		tcpComboBox.setModel(new DefaultComboBoxModel<String>(items));
	}
	public void setPayloadComboBoxItems(String[] items) {
		payloadComboBox.removeAllItems();
		payloadComboBox.setModel(new DefaultComboBoxModel<String>(items));
	}
	public void setVariableComboBoxItems(String[] items) {
		variableComboBox.removeAllItems();
		variableComboBox.setModel(new DefaultComboBoxModel<String>(items));
	}
	public void setFeatureComboBoxItems(String[] items) {
		featureComboBox.removeAllItems();
		featureComboBox.setModel(new DefaultComboBoxModel<String>(items));
	}
	public void setTCPComboBoxSelection(String item) {
		tcpComboBox.setSelectedItem(item);
	}
	public void setPayloadComboBoxSelection(String item) {
		payloadComboBox.setSelectedItem(item);
	}
	public void setVariableComboBoxSelection(String item) {
		variableComboBox.setSelectedItem(item);
	}
	public void setFeatureComboBoxSelection(String item) {
		featureComboBox.setSelectedItem(item);
	}
	
	private Box createComboBox(String desc, JComboBox<String> combo, final ContributionProvider<MEProgramNodeContribution> provider, ItemListener itemListener) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel label = new JLabel(desc);
		combo.setPreferredSize(new Dimension(200,30));
		combo.setMaximumSize(combo.getPreferredSize());
		combo.addItemListener(itemListener);
		box.add(label);
		box.add(createHorizontalSpacing());
		box.add(combo);
		return box;
	}
	
	
	private Component createHorizontalSpacing() {
		return Box.createRigidArea(new Dimension(style.getHorizontalSpacing(),0));
	}
	private Component createVerticalSpacing(){
		return Box.createRigidArea(new Dimension(0,style.getHorizontalSpacing()));
	}
	private Component createSpacing(int width, int height) {
		return Box.createRigidArea(new Dimension(width, height));
	}

}
