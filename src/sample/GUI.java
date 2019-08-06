// The GUI class is essentially where the entirety of the program resides. This class designs and builds the GUI.
// The GUI contains input fields for the user to enter input for the two conditions they have selected, the max loss
// and goal gain conditions, account balance and total commission. The user has drop downs menus to select their
// trade conditions, max loss and goal gain. The calculations for the target price and stop loss sections are found
// in the Calculations class, which is inherited by the GUI class.



package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GUI extends Calculations{

    private double guiWidth;
    private double guiHeight;

    private int numRows;
    private int numCols;
    private int cellWidth;
    private double cellWidthWide;
    private int cellHeight;
    private double margins;

    private int indexCondition3;

    private double numShares;             // Amount of shares
    private double pricePerShare;         // Share purchase price
    private double goalGain;              // $ or % gain the user wants for price target
    private double maxLoss;               // Max $ or % loss the user is willing to set their stop loss
    private double targetPrice;           // Target price
    private double stopLoss;              // stop loss
    private double positionPrice;         // position price
    private double dollarGainPerShare;    // $ gain per share
    private double percentGainPerShare;   // % gain
    private double dollarLossPerShare;    // $ loss per share
    private double percentLossPerShare;   // % loss
    private double profit;                // net $ profit
    private double loss;                  // net $ loss
    private double riskReward;            // risk reward ratio based on profit/loss
    private double totalCommission;       // total commission cost for opening and closing a position
    private double accountBalance;        // Amount of money in account
    private double accountRisk;           // risk of account in comparison to position

    private Boolean boolCond1;  // flag if condition1 has been selected and input
    private Boolean boolCond2;  // flag if condition2 has been selected and input
    private Boolean boolGG;     // flag if goal gain has been selected and input
    private Boolean boolML;     // flag if max loss has been selected and input
    private Boolean boolTC;     // flag if total commission input field has been set
    private Boolean boolAB;     // flag if account balance has been set

    private Text textTitle;      // Title on GUI
    private String textERR;      // Is displayed when there is an invalid input
    private String stringMargin; // Used to format text on GUI

    private Text textNS;     // Number of shares
    private Text textPPS;    // Price per share
    private Text textPP;     // Position price
    private Text textTP;     // Target price
    private Text textSL;     // Stop loss
    private Text textPGPS;   // Percent gain per share
    private Text textDGPS;   // Dollar gain per share
    private Text textPLPS;   // Percent loss per share
    private Text textDLPS;   // Dollar loss per share
    private Text textP;      // Profit
    private Text textL;      // Loss
    private Text textRR;     // Risk reward
    private Text textTC;     // Total commissions
    private Text textAB;     // Account Balance
    private Text textAR;     // Account risk

    private Text textCalcNS;    // Calculated number of shares
    private Text textCalcPPS;   // Calculated price per share
    private Text textCalcPP;    // Calculated position price
    private Text textCalcTP;    // Calculated target price
    private Text textCalcSL;    // Calculated stop loss
    private Text textCalcPGPS;  // Calculated percent gain per share
    private Text textCalcDGPS;  // Calculated dollar gain per share
    private Text textCalcPLPS;  // Calculated percent loss per share
    private Text textCalcDLPS;  // Calculated dollar loss per share
    private Text textCalcP;     // Calculated profit
    private Text textCalcL;     // Calculated loss
    private Text textCalcRR;    // Calculated risk reward
    private Text textCalcAR;    // Calculated account risk

    private Rectangle rectangleTP;
    private Rectangle rectangleSL;
    private Rectangle rectangleBackGround;

    private ArrayList<Text> allText; // Holds all text fields

    private TextField inputCondition1;  // Input field for either number of shares, price per share or position price
    private TextField inputCondition2;  // Input field for either number of shares, price per share or position price
    private TextField inputGG;  // Input field for goal gain % or $
    private TextField inputML;  // Input field for max loss % or $
    private TextField inputTC;  // Input field for total commissions
    private TextField inputAB;  // Input Field for account balance

    private ObservableList<String> optionsGG;
    private ObservableList<String> optionsML;
    private ObservableList<String> optionsCondition1;
    private ObservableList<String> optionsCondition2;
    private ComboBox menuGG;
    private ComboBox menuML;
    private ComboBox menuCondition1;
    private ComboBox menuCondition2;

    // Format for $ value on screen
    private static DecimalFormat decimalFormat = new DecimalFormat("#.###");

    GUI(){
        numRows       = 16;
        numCols       = 6;
        cellHeight    = 25;
        cellWidth     = 100;
        cellWidthWide = cellWidth * 1.6;
        margins       = 15;
        guiWidth      = 2*cellWidth+2*cellWidthWide+2*margins;
        guiHeight     = (numRows-3)*cellHeight+3*margins;

        indexCondition3 = -1;

        numShares           = 0;
        pricePerShare       = 0.0;
        goalGain            = 0.0;
        maxLoss             = 0.0;
        targetPrice         = 0.0;
        stopLoss            = 0.0;
        positionPrice       = 0.0;
        dollarGainPerShare  = 0.0;
        percentGainPerShare = 0.0;
        dollarLossPerShare  = 0.0;
        percentLossPerShare = 0.0;
        profit              = 0.0;
        loss                = 0.0;
        riskReward          = 0.0;
        totalCommission     = 0.0;
        accountBalance      = 0.0;
        accountRisk         = 0.0;

        boolCond1 = false;
        boolCond2 = false;
        boolGG    = false;
        boolML    = false;
        boolTC    = false;
        boolAB    = false;

        textTitle    = new Text("Target Price and Stop Loss Calculator");
        textERR      = "---";
        stringMargin = "        ";

        textNS   = new Text("  # shares");
        textPPS  = new Text("  $ per share                ");
        textPP   = new Text("  Position price             ");
        textTP   = new Text(stringMargin + "  Target Price");
        textSL   = new Text(stringMargin + "  Stop Loss");
        textPGPS = new Text(stringMargin + "  % gain per share");
        textDGPS = new Text(stringMargin + "  $ gain per share");
        textPLPS = new Text(stringMargin + "  % loss per share");
        textDLPS = new Text(stringMargin + "  $ loss per share ");
        textP    = new Text(stringMargin + "  Profit");
        textL    = new Text(stringMargin + "  Loss");
        textRR   = new Text(stringMargin + "  Risk reward");
        textTC   = new Text("  Total commissions        $");
        textAB   = new Text("  Account Balance           $");
        textAR   = new Text(stringMargin + "  Account risk");

        textCalcNS   = new Text(textERR);
        textCalcPPS  = new Text(textERR);
        textCalcPP   = new Text(textERR);
        textCalcTP   = new Text(textERR);
        textCalcSL   = new Text(textERR);
        textCalcPGPS = new Text(textERR);
        textCalcDGPS = new Text(textERR);
        textCalcPLPS = new Text(textERR);
        textCalcDLPS = new Text(textERR);
        textCalcP    = new Text(textERR);
        textCalcL    = new Text(textERR);
        textCalcRR   = new Text(textERR);
        textCalcAR   = new Text(textERR);

        allText = new ArrayList<>();
        addToAllText();

        inputCondition1 = new TextField();
        inputCondition2 = new TextField();
        inputGG = new TextField();
        inputML = new TextField();
        inputTC = new TextField();
        inputAB = new TextField();

        optionsGG = FXCollections.observableArrayList( "goal % gain", "goal $ gain", "goal $ gain per share");
        optionsML = FXCollections.observableArrayList( "max % loss", "max $ loss", "max $ loss per share");
        optionsCondition1 = FXCollections.observableArrayList("# shares", "$ per share", "Position price $   ");
        optionsCondition2 = FXCollections.observableArrayList("# shares", "$ per share", "Position price $   ");

        menuGG = new ComboBox(optionsGG);
        menuML = new ComboBox(optionsML);
        menuCondition1 = new ComboBox(optionsCondition1);
        menuCondition2 = new ComboBox(optionsCondition2);

        // Manually setting conditions 1 2 and 3
        menuGG.getSelectionModel().selectFirst();
        menuML.getSelectionModel().selectFirst();
        menuCondition1.getSelectionModel().selectFirst(); // # shares
        menuCondition2.getSelectionModel().selectFirst(); // skipping # shares
        menuCondition2.getSelectionModel().selectNext();  // $ per share

        indexCondition3 = 2;   // position price
        hideCondition3Text();
        textPP.setVisible(true);
        textCalcPP.setVisible(true);

        rectangleTP = new Rectangle(cellWidthWide+cellWidth-50, cellHeight*4, Color.rgb(212, 255, 226));
        rectangleSL = new Rectangle(cellWidthWide+cellWidth-50, cellHeight*4, Color.rgb(255, 217, 204));
        rectangleBackGround = new Rectangle(guiWidth, guiHeight, Color.rgb(255, 255, 255));
    }

    // Where the GUI is built. This function sets the the background, text and input fields onto the screen.
    public void buildGUI(Stage primaryStage) {
        GridPane root = new GridPane();
        //root.setGridLinesVisible(true);              // Grid lines
        root.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Trader Tools");

        setFont();
        setModifiableFields();

        // Creating columns
        for(int i=0; i<numCols; i++){
            ColumnConstraints column;
            if( i == 0 || i == numCols-1){ column = new ColumnConstraints(margins); }
            else if( i == 1 || i == 3){ column = new ColumnConstraints(cellWidthWide); }
            else{ column = new ColumnConstraints(cellWidth); }
            root.getColumnConstraints().add(column);
        }

        // Creating rows
        for(int i=0; i<numRows; i++){
            RowConstraints row;
            if( i == 0 || i == 1 || i == numRows-1){ row = new RowConstraints(margins); }
            else{ row = new RowConstraints(cellHeight); }
            root.getRowConstraints().add(row);
        }

        // Positioning GUI elements
        // setConstraints(Node child, int columnIndex, int rowIndex, int columnspan, int rowspan, HPos halignment, VPos valignment, Priority hgrow, Priority vgrow)
        root.setConstraints(rectangleBackGround,0,0, numCols,numRows, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(rectangleTP,        1,9, 2,4, HPos.CENTER,   VPos.CENTER);
        root.setConstraints(rectangleSL,        3,9, 2,4, HPos.CENTER,   VPos.CENTER);

        root.setConstraints(textTitle,     1,1, 4,1, HPos.CENTER, VPos.TOP);
        root.setConstraints(textAB,        1,3, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textNS,        1,6, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textPPS,       1,6, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textPP,        1,6, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textTC,        3,6, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textTP,        1,9, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textSL,        3,9, 1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textPGPS,      1,10,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textDGPS,      1,11,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textPLPS,      3,10,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textDLPS,      3,11,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textP,         1,12,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textL,         3,12,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textRR,        1,14,1,1, HPos.LEFT,   VPos.CENTER);
        root.setConstraints(textAR,        3,14,2,1, HPos.LEFT,   VPos.CENTER);

        root.setConstraints(textCalcNS,    2,6, 1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcPPS,   2,6, 1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcPP,    2,6, 1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcTP,    2,9, 1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcSL,    4,9, 1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcPGPS,  2,10,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcDGPS,  2,11,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcPLPS,  4,10,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcDLPS,  4,11,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcP,     2,12,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcL,     4,12,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcRR,    2,14,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(textCalcAR,    4,14,1,1, HPos.LEFT, VPos.CENTER);

        root.setConstraints(inputAB,         2,3,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(inputCondition1, 2,5,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(inputCondition2, 4,5,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(inputTC,         4,6,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(inputGG,         2,7,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(inputML,         4,7,1,1, HPos.LEFT, VPos.CENTER);

        root.setConstraints(menuCondition1,1,5,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(menuCondition2,3,5,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(menuGG,        1,7,1,1, HPos.LEFT, VPos.CENTER);
        root.setConstraints(menuML,        3,7,1,1, HPos.LEFT, VPos.CENTER);

        root.getChildren().addAll(rectangleBackGround);
        root.getChildren().addAll(rectangleTP, rectangleSL);
        root.getChildren().addAll(textTitle, textAB, textNS, textPPS, textPP, textTC, textTP, textSL, textPGPS, textDGPS, textPLPS, textDLPS, textP, textL, textRR, textAR);
        root.getChildren().addAll(textCalcNS, textCalcPPS, textCalcPP, textCalcTP, textCalcSL, textCalcPGPS, textCalcDGPS, textCalcPLPS, textCalcDLPS, textCalcP, textCalcL, textCalcRR, textCalcAR);
        root.getChildren().addAll(inputAB, inputCondition1, inputCondition2, inputTC,inputGG, inputML);
        root.getChildren().addAll(menuGG, menuML ,menuCondition1, menuCondition2);

        primaryStage.setScene(new Scene(root, guiWidth, guiHeight));
        primaryStage.show();
    }


    // Sets all input field and menu behavior
    private void setModifiableFields(){
        setInputCondition1();
        setInputCondition2();
        setInputGG();
        setInputML();
        setInputTC();
        setInputAB();
        setMenuGG();
        setMenuML();
        setMenuCondition1();
        setMenuCondition2();
    }


    // Listening function for the Goal Gain menu. When the user is changing the goal gain condition, the goal gain
    // input field will clear and the text that displays the calculations for the target price will display "---".
    // If invalid input is entered, the Target Price Section will display "---" for all calculation fields.
    private void setMenuGG() {
        menuGG.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                inputGG.setText("");
                invalidInputHandler(4, false);
            }});
    }

    // Listening function for the Max Loss menu. When the user is changing the max loss condition, the max loss
    // input field will clear and the text that displays the calculations for the stop loss will display "---".
    // If invalid input is entered, the Max Loss Section will display "---" for all calculation fields.
    private void setMenuML() {
        menuML.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                inputML.setText("");
                invalidInputHandler(4, false);
            }});
    }


    // Listening function for the Condition 1 menu. Allows the user to choose between 3 conditions. If the user
    // selects a condition that has already been selected in condition 2, the condition in condition 2 will change
    // to something else and condition 3 is updated automatically. Clears input field text when switching between
    // conditions.
    private void setMenuCondition1() {
        menuCondition1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String cond1_Old, String cond1_New) {
                inputCondition1.setText("");
                invalidInputHandler(1, false);
                invalidInputHandler(4, false);
                handleSelectedConditions(cond1_New, menuCondition2);
            }});
    }

    // Listening function for the Condition 2 menu. Allows the user to choose between 3 conditions. If the user
    // selects a condition that has already been selected in condition 1, the condition in condition 1 will change
    // to something else and condition 3 is updated automatically. Clears input field text when switching between
    // conditions.
    private void setMenuCondition2() {
        menuCondition2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String cond2_Old, String cond2_New) {
                inputCondition2.setText("");
                invalidInputHandler(1, false);
                invalidInputHandler(4, false);
                handleSelectedConditions(cond2_New, menuCondition1);
            }});
    }


    // Validation function that makes sure the user selects two unique conditions. If the user selects a condition
    // that has already been selected, the old condition in the other menu will change.
    private void verifyUniqueSelectedConditions(String condSelected, ComboBox otherCondMenu){
        String otherCond = otherCondMenu.getSelectionModel().getSelectedItem().toString();

        // Change the other condition box to something else
        while(condSelected.equals(otherCond)){
            otherCondMenu.getSelectionModel().selectNext();
            otherCond = otherCondMenu.getSelectionModel().getSelectedItem().toString();
            if(condSelected.equals(otherCond)){
                otherCondMenu.getSelectionModel().selectPrevious();
                otherCond = otherCondMenu.getSelectionModel().getSelectedItem().toString();
            }
        }
    }


    // Convenience function to hide all possible condition 3 text
    private void hideCondition3Text(){
        textNS.setVisible(false);
        textPPS.setVisible(false);
        textPP.setVisible(false);
        textCalcNS.setVisible(false);
        textCalcPPS.setVisible(false);
        textCalcPP.setVisible(false);
    }


    // Verifies that the user has selected unique conditions. If the user had selected a condition that had already
    // been selected in the other condition box, the other condition box's condition will change. Once both conditions
    // are unique condition 3 can be calculated and displayed.
    private void handleSelectedConditions(String condSelected, ComboBox otherCondMenu){
        verifyUniqueSelectedConditions(condSelected, otherCondMenu);
        setIndexCondition3();     // figures out what index condition 3 is
        displayCondition3();
    }


    // Calculates the value of condition 3 based upon indexCondition3, which is set when the user selects the
    // other two conditions.
    private void calculateCondition3(){
             if(indexCondition3 == 0){ numShares     = calcNumShares(pricePerShare, positionPrice); }
        else if(indexCondition3 == 1){ pricePerShare = calcPricePerShare(numShares, positionPrice); }
        else if(indexCondition3 == 2){ positionPrice = calcPositionPrice(pricePerShare, numShares); }
    }


    // Figures out the third condition and sets the third condition space on the GUI to the
    // relevant condition text
    private void setIndexCondition3(){
        int indexCond1 = menuCondition1.getSelectionModel().getSelectedIndex();
        int indexCond2 = menuCondition2.getSelectionModel().getSelectedIndex();

        if(indexCond1 != 0 && indexCond2 != 0){ indexCondition3 = 0;      }
        else if(indexCond1 != 1 && indexCond2 != 1){ indexCondition3 = 1; }
        else if(indexCond1 != 2 && indexCond2 != 2){ indexCondition3 = 2; }
    }


    // Sets the appropriate condition 3 text to be visible and displayed
    private void displayCondition3(){
        hideCondition3Text();
        if(indexCondition3 == 0)     { textNS.setVisible(true); textCalcNS.setVisible(true); }
        else if(indexCondition3 == 1){ textPPS.setVisible(true); textCalcPPS.setVisible(true); }
        else if(indexCondition3 == 2){ textPP.setVisible(true); textCalcPP.setVisible(true); }
    }


    // Displays condition 3 and it's calculated value in the condition 3 section.
    private void setCondition3Text(){
        String margin = "    ";
        if(indexCondition3 == 0)     {
            textCalcNS.setText(margin + numShares);
            displayCalculation(textCalcNS, '.', numShares);
        }
        else if(indexCondition3 == 1){
            textCalcPPS.setText(margin + pricePerShare);
            displayCalculation(textCalcPPS, '$', pricePerShare);
        }
        else if(indexCondition3 == 2){
            textCalcPP.setText(margin + positionPrice);
            displayCalculation(textCalcPP, '$', positionPrice);
        }
    }


    // Once condition 1 and 2 are set, condition 3 and the account risk can be calculated. Once max loss and goal
    // gain conditions are set the stop loss and price target information can be calculated. If total commissions
    // are set they can be added to potential profit and loss.
    private void calculationFunction(Boolean boolSkipCommission){
        // If both conditions are set calculate condition 3
        if(boolCond1 && boolCond2){
            calculateCondition3();
            setCondition3Text();
            setAccountRisk();
        }
        else{ invalidInputHandler(1, true); }

        if(boolCond1 && boolCond2 && boolML){
            calculateSLinfo(); setSLinfo();
            if(boolTC && !boolSkipCommission){ setCommissions(textCalcL, 'l'); }
        }
        if(boolCond1 && boolCond2 && boolGG){
            calculateTPinfo(); setTPinfo();
            if(boolTC && !boolSkipCommission){ setCommissions(textCalcP, 'p'); }
            if(boolML){ setRiskReward(); }
        }
    }


    // Sets the value of the first condition. Once specific conditions have been set, the calculation function
    // can calculate the target price, stop loss, and risk information
    private void setInputCondition1(){
        inputCondition1.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    double inputCond1 = Double.parseDouble(newValue);
                    int indexCond1 = menuCondition1.getSelectionModel().getSelectedIndex();

                    if(indexCond1 == 0)     { numShares = inputCond1;     }
                    else if(indexCond1 == 1){ pricePerShare = inputCond1; }
                    else if(indexCond1 == 2){ positionPrice = inputCond1; }

                    boolCond1 = true;
                }
                catch(Exception e){  boolCond1 = false; }

                calculationFunction(false);
            }
        });
    }


    // Sets the value of the second condition. Once specific conditions have been set, the calculation function
    // can calculate the target price, stop loss, and risk information
    private void setInputCondition2(){
        inputCondition2.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    double inputCond2 = Double.parseDouble(newValue);
                    int indexCond2 = menuCondition2.getSelectionModel().getSelectedIndex();

                    if(indexCond2 == 0)     { numShares = inputCond2;     }
                    else if(indexCond2 == 1){ pricePerShare = inputCond2; }
                    else if(indexCond2 == 2){ positionPrice = inputCond2; }

                    boolCond2 = true;
                }
                catch(Exception e){  boolCond2 = false; }

                calculationFunction(false);
            }
        });
    }


    // Goal gain condition input field. Once both condition are set by the user and the goal gain condition is set,
    // the goal gain information can be calculated and displayed.
    private void setInputGG(){
        inputGG.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    calculationFunction(true);

                    // Setting goalGain variable, will be caught if invalid input
                    goalGain = Double.parseDouble(newValue);
                    boolGG = true;

                    // both numShare and pricePerShare must be set to calculate positionPrice
                    if(boolCond1 && boolCond2) {
                        calculateTPinfo();
                        setTPinfo();
                        if(boolGG && boolTC){ setCommissions(textCalcP, 'p'); }
                        if(boolML && boolTC){ setCommissions(textCalcL, 'l'); }
                        if(boolGG && boolML){ setRiskReward(); }
                    }
                }
                catch(Exception e){  invalidInputHandler(2, false); boolGG = false;}
            }
        });
    }


    // Max loss condition input field. Once both conditions are set by the user and the stop loss condition is set,
    // the stop loss information can be calculated and displayed.
    private void setInputML(){
        inputML.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    calculationFunction(true);

                    maxLoss = Double.parseDouble(newValue);
                    boolML = true;

                    if(boolCond1 && boolCond2){
                        calculateSLinfo();
                        setSLinfo();
                        if(boolGG && boolTC){ setCommissions(textCalcP, 'p'); }
                        if(boolML && boolTC){ setCommissions(textCalcL, 'l'); }
                        if(boolGG && boolML){ setRiskReward(); }
                    }
                }
                catch(Exception e){  invalidInputHandler(3, false); boolML = false; }
            }
        });
    }


    // Sets the text to display value with commission added.
    private void setTextWithCommissions(Text textUpdated, double value){
        textUpdated.setText(Double.toString(value));
        displayCalculation(textUpdated, '$', value);
    }


    // Adds commission to the potential profit or loss.
    private void setCommissions(Text text, char c){
        if(c == 'p'){
            profit -= totalCommission;
            setTextWithCommissions(text,profit);
        }
        else if(c == 'l'){
            loss -= totalCommission;
            setTextWithCommissions(text, loss);
        }
    }


    // Sets the total commissions of the trade. If valid and if the two conditions are set, the account risk can be set.
    // If the goal gain and max loss conditions are set, then commissions can be incorporated to the potential profit
    // and loss. If everything is set, the risk reward can be calculated.
    private void setInputTC(){
        inputTC.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    calculationFunction(true);
                    totalCommission = Double.parseDouble(newValue);
                    boolTC = true;

                    if(boolCond1 && boolCond2){ setAccountRisk(); }
                    if(boolCond1 && boolCond2 && boolGG){ setCommissions(textCalcP, 'p'); }
                    if(boolCond1 && boolCond2 && boolML){ setCommissions(textCalcL, 'l'); }
                    if(boolCond1 && boolCond2 && boolML && boolGG){ setRiskReward(); }
                }
                catch(Exception e){
                    boolTC = false;
                    calculationFunction(true);
                }
            }
        });
    }


    // Calculates account risk based upon position size in comparison to account size. Takes commissions into consideration.
    private void setAccountRisk(){
        double totalPositionPrice = positionPrice;

        if(boolTC){ totalPositionPrice += totalCommission; }

        accountRisk = calcAccountRisk(accountBalance, totalPositionPrice);
        textCalcAR.setText(Double.toString(accountRisk));
        displayCalculation(textCalcAR, '%', accountRisk);
    }


    // Sets the account balance, if valid and if the two conditions are set, then the account risk will be
    // calculated and displayed
    private void setInputAB(){
        inputAB.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    accountBalance = Double.parseDouble(newValue);
                    boolAB = true;

                    if(boolCond1 && boolCond2 ){ setAccountRisk(); }
                }
                catch(Exception e){
                    boolAB = false;
                    invalidInputHandler(5, false);
                }
            }
        });
    }


    // Once all input is entered and valid, this function will calculate the risk reward ratio based upon the
    // profit target and stop loss.
    private void setRiskReward(){
        riskReward = calcRiskReward(profit, loss) * -1;
        displayCalculation(textCalcRR, 'r', riskReward);
    }


    // Calculates target price information: target price, percentage gain per share, dollar gain per share, total profit
    private void calculateTPinfo(){
        int ggSelected      = menuGG.getSelectionModel().getSelectedIndex(); // index used to depict which option selected
        targetPrice         = calcTargetPrice(ggSelected, goalGain, pricePerShare, numShares);
        percentGainPerShare = calcPercentGain(ggSelected, goalGain, targetPrice, pricePerShare);
        dollarGainPerShare  = calcDollarGain(targetPrice, pricePerShare);
        profit              = calcProfit(dollarGainPerShare, numShares);
    }


    // Once the user enters in the two conditions and the target price condition, the third condition will be
    // calculated, then the target price, percent gain per share, dollar gain per share and total profit will be
    // displayed in the target price section.
    private void setTPinfo(){
        displayCalculation(textCalcTP,   '$', targetPrice);
        displayCalculation(textCalcPGPS, '%', percentGainPerShare);
        displayCalculation(textCalcDGPS, '$', dollarGainPerShare);
        displayCalculation(textCalcP,    '$', profit);
    }


    // Calculates stop loss information: stop loss, percent loss per share, dollar loss per share and total loss
    private void calculateSLinfo(){
        int mlSelected      = menuML.getSelectionModel().getSelectedIndex(); // index used to depict which option selected
        stopLoss            = calcStopLoss(mlSelected, maxLoss, pricePerShare, numShares);
        percentLossPerShare = calcPercentLoss(mlSelected, maxLoss, stopLoss, pricePerShare) * -1;
        dollarLossPerShare  = calcDollarLoss(stopLoss, pricePerShare);
        loss                = calcLoss(dollarLossPerShare, numShares);
    }


    // Once the user enters in the two conditions and the max stop loss condition, the third condition will be
    // calculated, and then the stop loss, percentage loss per share, dollar loss per share and total loss will be
    // displayed in the stop loss section
    private void setSLinfo(){
        displayCalculation(textCalcSL,   '$', stopLoss);
        displayCalculation(textCalcPLPS, '%', percentLossPerShare);
        displayCalculation(textCalcDLPS, '$', dollarLossPerShare);
        displayCalculation(textCalcL,    '$', loss);
    }


    // Method to easily display dollar amounts, percentages, ratios or numbers
    private void displayCalculation(Text text, char c, double calculation){
        switch(c){
            case '$':
                text.setText("$" + decimalFormat.format(calculation));
                break;
            case '%':
                text.setText(decimalFormat.format(calculation) + "%");
                break;
            case 'r':
                text.setText(decimalFormat.format(calculation) + " to 1");
                break;
            case '.':
                text.setText(decimalFormat.format(calculation));
                break;
        }
    }


    // Changes all text font size
    private void setFont() {
        for(Text text : allText) {
            text.setFont(Font.font(12)); // setting font size to 12px
        }
        textTitle.setFont(Font.font(16));
    }


    // Putting all text into one container to make modifications on all text, or a group, easier.
    // Example, changing font
    private void addToAllText(){    // allText indices
        allText.add(textAB);        // 0
        allText.add(textNS);        // 1
        allText.add(textPPS);       // 2
        allText.add(textPP);        // 3
        allText.add(textTP);        // 4
        allText.add(textSL);        // 5
        allText.add(textPGPS);      // 6
        allText.add(textDGPS);      // 7
        allText.add(textPLPS);      // 8
        allText.add(textDLPS);      // 9
        allText.add(textP);         // 10
        allText.add(textL);         // 11
        allText.add(textRR);        // 12
        allText.add(textTC);        // 13
        allText.add(textCalcPP);    // 14
        allText.add(textCalcTP);    // 15
        allText.add(textCalcSL);    // 16
        allText.add(textCalcPGPS);  // 17
        allText.add(textCalcDGPS);  // 18
        allText.add(textCalcPLPS);  // 19
        allText.add(textCalcDLPS);  // 20
        allText.add(textCalcP);     // 21
        allText.add(textCalcL);     // 22
        allText.add(textCalcRR);    // 23
        allText.add(textCalcAR);    // 24
        allText.add(textAR);        // 25
    }


    // When invalid input is entered by the user, this function displays '---' in each calculated field
    // to indicate that the value cannot be calculated. There are different cases since certain calculations
    // are unrelated and so they do not interfere with one another in case of an error.
    private void invalidInputHandler(int errorNum, boolean flag){
        switch(errorNum){
            case 1:
                textCalcNS.setText(textERR);
                textCalcPP.setText(textERR);
                textCalcPPS.setText(textERR);
                if(!flag){ break;}
            case 2:
                textCalcTP.setText(textERR);
                textCalcPGPS.setText(textERR);
                textCalcDGPS.setText(textERR);
                textCalcP.setText(textERR);
                textCalcRR.setText(textERR);
                if(!flag){ break;}
            case 3:
                textCalcSL.setText(textERR);
                textCalcPLPS.setText(textERR);
                textCalcDLPS.setText(textERR);
                textCalcL.setText(textERR);
                textCalcRR.setText(textERR);
                if(!flag){ break; }
            case 4:
                textCalcRR.setText(textERR);
                if(!flag){ break; }
            case 5:
                textCalcAR.setText(textERR);
                if(!flag){ break; }
        }
    }
}
