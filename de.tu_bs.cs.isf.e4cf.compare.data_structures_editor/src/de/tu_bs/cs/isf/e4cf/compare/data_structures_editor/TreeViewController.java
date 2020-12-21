package de.tu_bs.cs.isf.e4cf.compare.data_structures_editor;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;

import de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces.Tree;
import de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.stringtable.DataStructuresEditorST;
import de.tu_bs.cs.isf.e4cf.compare.data_structures_editor.utilities.TreeViewUtilities;
import de.tu_bs.cs.isf.e4cf.core.util.ServiceContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

/**
 * Controller for VisualizeTreeView.fxml
 * 
 * @author Team05
 *
 */
public class TreeViewController {

	@Inject
	private ServiceContainer services;

	@FXML
	private MenuItem properties;

	@FXML
	private VBox background;

	@FXML
	private Button search;

	@FXML
	private Label testLabel;

	@FXML
	private TreeView<NodeUsage> treeView;

	@FXML
	private TextField searchTextField;
	
	private int searchCounter = 0;
	
	private String currentSearchText;

	/**
	 * switching View to TreeView if a .txt file is selected from the explorer
	 * 
	 * @param tree data structure of type tree
	 */
	@Optional
	@Inject
	public void openTree(@UIEventTopic("OpenTreeEvent") Tree tree) {
		TreeViewUtilities.switchToPart(DataStructuresEditorST.TREE_VIEW_ID, services);
		treeView = TreeViewUtilities.getTreeViewFromTree(tree, this.treeView);
		treeView = TreeViewUtilities.addListener(treeView, services);
	}

	/**
	 * A method to close a file
	 */
	@FXML
	void closeFile() {
		// set treeview and its values to null, then remove it from the background
		treeView.setRoot(null);
		services.eventBroker.send("EmptyPropertiesTableEvent", true);
	}

	/**
	 * Selects all elements in the treeview
	 */
	@FXML
	void selectAll() {
		treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		treeView.getSelectionModel().selectAll();
		services.eventBroker.send("EmptyPropertiesTableEvent", true);
	}

	/**
	 * Unselects all elements in the treeview
	 */
	@FXML
	void unselectAll() {
		treeView.getSelectionModel().clearSelection();
		services.eventBroker.send("EmptyPropertiesTableEvent", true);
	}

	/**
	 * searchButton to search what is typed in searchField
	 */
	@FXML
	void search() {
		treeView.getSelectionModel().clearSelection();
		String searchFieldTextToRead = searchTextField.getText();
		List <TreeItem<NodeUsage>> resultList = TreeViewUtilities.searchTreeItem(treeView.getRoot(), searchFieldTextToRead);
		treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		treeView.getSelectionModel().select(getCurrentSearchItem(resultList));
		treeView.scrollTo(treeView.getSelectionModel().getSelectedIndex());
		System.out.println(getSearchCounter() + "/" + resultList.size());
//		for (TreeItem<NodeUsage> t : TreeViewUtilities.searchTreeItem(treeView.getRoot(), searchFieldTextToRead)) {
//			treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//			treeView.getSelectionModel().select(t);
//			treeView.scrollTo(treeView.getSelectionModel().getSelectedIndex());
//		}
//		if (treeView.getSelectionModel().getSelectedItems().size() > 1) {
//			services.eventBroker.send("EmptyPropertiesTableEvent", true);
//		}
		resultList.clear();
		TreeViewUtilities.clearSearchList();
	}
	
    @FXML
    void onEnter(ActionEvent event) {
    	if (currentSearchText == null) {
    		currentSearchText = searchTextField.getText();
    		search();
    	} else {
    		if (currentSearchText.equals(searchTextField.getText())) {
    			search();
    		} else {
    			setSearchCounter(0);
    			search();
    			currentSearchText = searchTextField.getText();
    		}
    	}
    }
    
    TreeItem<NodeUsage> getCurrentSearchItem(List <TreeItem<NodeUsage>> resultList) {
    	TreeItem<NodeUsage> currentItem = new TreeItem<NodeUsage>();
    	if (getSearchCounter() < resultList.size()) {
    		currentItem = resultList.get(getSearchCounter());
    		incrementSearchCounter();
    	} else {
    		setSearchCounter(0);
    		currentItem = resultList.get(getSearchCounter());
    		incrementSearchCounter();
    	}
    	return currentItem;
    }
    
    int getSearchCounter() {
    	return searchCounter;
    }

    void setSearchCounter(int i) {
    	searchCounter = i;
    }
    
    void incrementSearchCounter() {
    	searchCounter++;
    }
	@FXML
	void deleteNode() {
		TreeItem<NodeUsage> deletingNode = treeView.getSelectionModel().getSelectedItem();
		treeView.getSelectionModel().getSelectedItem().setValue(null);
		treeView.getSelectionModel().getSelectedItem().setGraphic(null);
		deletingNode.getParent().getChildren().remove(deletingNode);

	}

	@FXML
	void renameNode() {
		
	}
	@FXML
	void addChild() {
		TreeItem<NodeUsage> treeV = new TreeItem<NodeUsage>();
		treeV.setValue(new NodeUsage("df"));
		treeView.getSelectionModel().getSelectedItem().getChildren().add(treeV);
	}
	@FXML
	void copy() {
		TreeItem<NodeUsage>  copiedNode = treeView.getSelectionModel().getSelectedItem();
		System.out.println(copiedNode + "sdhfg");
		System.out.println(treeView.getSelectionModel().getSelectedItem());
	}
}
