package de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.manager;

import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.AbstractNode;
import javafx.scene.control.TreeItem;

public class DeleteNodeAction implements UndoAction {

	private String name;
	private TreeItem<AbstractNode> treeItem;
	private TreeItem<AbstractNode> parent;

	public DeleteNodeAction(String name, TreeItem<AbstractNode> treeItem, TreeItem<AbstractNode> parent) {
		this.name = name;
		this.treeItem = treeItem;
		this.parent = parent;

	}

	@Override
	public void undo() {
		parent.getChildren().add(treeItem);
	}
}
