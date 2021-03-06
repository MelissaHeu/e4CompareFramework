package de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.manager.actions;

import de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.manager.UndoAction;
import de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.stringtable.DataStructuresEditorST;
import de.tu_bs.cs.isf.e4cf.core.util.ServiceContainer;

/**
 * Implementation of UndoAction for DeleteAttribute
 * 
 * @author Team05
 *
 */

public class DeleteAttributeAction implements UndoAction {

	private String name;
	private NodeAttributePair pair;
	private ServiceContainer services;

	public DeleteAttributeAction(String name, NodeAttributePair pair, ServiceContainer services) {
		this.name = name;
		this.pair = pair;
		this.services = services;
	}

	@Override
	public void undo() {
		services.eventBroker.send(DataStructuresEditorST.ADD_ATTRIBUTE_EVENT, pair);

	}

}
