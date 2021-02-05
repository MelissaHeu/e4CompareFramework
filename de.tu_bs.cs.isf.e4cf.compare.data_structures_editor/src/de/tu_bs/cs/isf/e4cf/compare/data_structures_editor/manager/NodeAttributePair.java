package de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.manager;

import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Attribute;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Node;

/**
 * 
 * @author Team05 Encapsulates an attribute and its owner.
 *
 */
public class NodeAttributePair {

	private Node owner;

	private Attribute attribute;

	public NodeAttributePair(Node owner, Attribute attribute) {
		this.owner = owner;
		this.attribute = attribute;
	}

	public Node getOwner() {
		return this.owner;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

}
