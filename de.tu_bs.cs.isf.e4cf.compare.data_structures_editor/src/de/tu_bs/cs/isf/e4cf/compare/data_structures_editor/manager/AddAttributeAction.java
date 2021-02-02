package de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.manager;

import java.util.List;

import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.AbstractNode;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Attribute;


public class AddAttributeAction implements UndoAction {
	
	private String name;
	private AbstractNode treeItem;
	private List<Attribute> list;


	public AddAttributeAction(String name, AbstractNode treeItem, List<Attribute> list) {
		this.name = name;
		this.treeItem = treeItem;
		this.list = list;
	}

	@Override
	public void undo() {
		treeItem.setAttributes(list);
	}
}
