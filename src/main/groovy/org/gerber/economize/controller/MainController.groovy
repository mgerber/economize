/**
 * 
 */
package org.gerber.economize.controller

import com.sun.javafx.scene.control.skin.LabeledText

import javax.annotation.Resource;

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent;
import javafx.event.Event
import javafx.event.EventTarget
import javafx.event.EventType;
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Cell
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.StackPane
import javafx.event.EventHandler

import org.gerber.economize.repositories.AccountInformationRepository
import org.gerber.economize.repositories.BankInformationRepository
import org.gerber.economize.view.NavigationTreeItem
import org.hibernate.metamodel.domain.Superclass;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

/**
 * @author Michael Gerber
 *
 */
@Controller
class MainController implements ChangeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController)

    public static final String NEW_BANK_VIEW = "/fxml/NewBankView.fxml"
    public static final String NEW_ACCOUNT_VIEW = "/fxml/NewAccountView.fxml"
	
	@FXML
	private TreeView navigationTree; 

    @FXML
    private StackPane actionView

	@Resource(name='ViewMap')
	private Map<String, Object> viewMap

    @Autowired
    BankInformationRepository bankInformationRepository

    @Autowired
    AccountInformationRepository accountInformationRepository

	// the initialize method is automatically invoked by the FXMLLoader - it's magic
	public void initialize() {
		loadTreeItems()
		this.navigationTree.getSelectionModel().selectedItemProperty().addListener(this)
	}

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		NavigationTreeItem treeItem = arg2	
        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(this.viewMap.get(treeItem.targetView))
	}

	// loads some strings into the tree in the application UI.
	public void loadTreeItems() {
		TreeItem<String> root = new TreeItem<String>("");
		root.setExpanded(true);
		def navigationTreeItem
		navigationTreeItem = new NavigationTreeItem("Neue Bank", NEW_BANK_VIEW)
		root.getChildren().add(navigationTreeItem)						
		navigationTreeItem = new NavigationTreeItem("Neues Konto", NEW_ACCOUNT_VIEW)
		root.getChildren().add(navigationTreeItem)

		this.navigationTree.setRoot(root);
	}

    @FXML
    public void showBanks() {
        bankInformationRepository.findAll().each { it -> LOGGER.info '{} found', it}
    }

    @FXML
    public void newBank() {
        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(this.viewMap.get(NEW_BANK_VIEW))
    }

    @FXML
    public void showAccounts() {
        accountInformationRepository.findAll().each {it -> LOGGER.info '{} found', it}
    }

    @FXML
    public void newAccount() {
        this.actionView.getChildren().clear()
        this.actionView.getChildren().addAll(this.viewMap.get(NEW_ACCOUNT_VIEW))
    }
	@FXML
	public void treeViewFired(Event event) {
		//println(event);
		//println event.eventType
		//println event.source
		//println event.target
	}	
}
