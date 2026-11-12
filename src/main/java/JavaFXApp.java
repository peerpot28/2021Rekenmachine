import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class JavaFXApp extends Application {

    Pane rootPane;
    TextField txtResult;
    TextField txtNumber1;
    TextField txtNumber2;

    private static final String PLUS = "+";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    private void getNumberFromTextField (TextField textField) {
        textField.getText();
    }

    protected int computeAdd () {
        return 0;
    }

    protected int computeMultiply () {
        return 0;
    }

    protected int computeDivide () {
        return 0;
    }

    private void compute (String operator) {

        int result;
        getNumberFromTextField(txtNumber1);
        getNumberFromTextField(txtNumber2);

        switch (operator) {
            case PLUS:
                result = computeAdd ();
                break;
            case MULTIPLY:
                result = computeMultiply ();
                break;
            case DIVIDE:
                result = computeDivide ();
                break;
            default:
                result = 0;
        }

        txtResult.setText (String.valueOf (result));
    }

    EventHandler<MouseEvent> mouseHandler = mouseEvent -> compute (((Button) mouseEvent.getSource()).getText ());

    EventHandler<KeyEvent> keyHandler = keyEvent -> compute(((Button) keyEvent.getSource ()).getText ());

    /*
     * Er wordt een label toegevoegd aan het scherm.
     */
    private void addLabel (String text, int y) {
        Label label = new Label (text);
        label.setFocusTraversable (false);
        label.setPrefWidth (161);
        label.setPrefHeight (71);
        label.setLayoutX (10);
        label.setLayoutY (y);
        label.setFont (new Font ("System Bold", 18.0));
        rootPane.getChildren ().add (label);
    }

    /*
     * Er wordt een textfield toegevoegd aan het scherm.
     */
    private TextField addTextField (String prompt, boolean isFocusTraversable, int width, int x, int y) {

        TextField textField = new TextField ();
        textField.setPromptText (prompt);
        textField.setFocusTraversable (isFocusTraversable);
        textField.setPrefWidth (width);
        textField.setLayoutX (x);
        textField.setLayoutY (y);
        textField.setFont (new Font ("System Bold", 18.0));
        rootPane.getChildren ().add (textField);
        return textField;
    }

    /*
     * Er wordt een button toegevoegd aan het scherm en event handlers voor het verwerken van een mouse-klik of het
     * selecteren van een toets op het toetsenbord wordt gekoppeld aan deze button.
     */
    private void addButton (String label, int x) {
        Button button = new Button (label);
        button.setFocusTraversable (true);
        button.setPrefWidth (160);
        button.setLayoutX (x);
        button.setLayoutY (105);
        button.setFont (new Font ("System Bold", 18.0));
        button.addEventHandler (MOUSE_CLICKED, mouseHandler);
        button.addEventHandler (KEY_PRESSED, keyHandler);
        rootPane.getChildren ().add (button);
    }

    @Override
    public void start(Stage primaryStage) {

        rootPane = new Pane();
        rootPane.setMinSize (500,205);

        /*
         * Met de parameter isFocusTraversable wordt aangegeven of het element wel of niet in de tab-volgorde wordt
         * opgenomen (met andere woorden: als de gebruiker op tab klikt in een actief veld (bijv. een textfield), dan
         * is het veld wel of niet opgenomen in de volgorde van elementen die met tab kan worden bereikt.
         *
         * Hieronder worden 2 textfields (voor het invoeren van getallen), 3 buttons (voor bewerkingen op die getallen)
         * en 1 resultaatveld toegevoegd.
         */
        addLabel ("Getal1", 0);
        txtNumber1 = addTextField ("Voer hier het eerste getal in...", true, 310, 181, 15);
        addLabel ("Getal 2", 40);
        txtNumber2 = addTextField ("Voer hier het tweede getal in...", true, 310, 181, 55);
        addButton (PLUS, 10);
        addButton (MULTIPLY, 170);
        addButton (DIVIDE, 330);
        txtResult = addTextField ("Hier wordt het resultaat getoond...", false, 480, 10, 155);
        txtResult.setEditable (false);

        /*
         * De applicatie wordt opgestart.
         */
        Scene startScene = new Scene(rootPane);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("OPT3 - Rekenmachine");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch (args);
    }
}