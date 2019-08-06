// Written by: Jakub Krzeptowski-Mucha
//
// This program is a Target Price and Stop Loss Calculator. This is designed for those invested in the stock market,
// with a specification towards day traders. The purpose of this program is to give traders a quick and easy tool to
// determine a stop loss and a target price. From this information, and a balance, the risk reward and account risk
// can be calculated as well.
//
// The user is given two selections of three trade conditions: the position price, the price per share and the number
// of shares. The third condition is calculated based upon the user input for the other two conditions. Based upon
// these three conditions, a goal gain condition, and a max loss condition, the stop loss and target price information
// can be calculated. The user may also input their current account balance to assess account risk in comparison to
// their position price. There is also the ability to enter a total commission amount, which gets factored into risk
// reward, account risk, and total losses and profits of a trade.
//
// Wise traders know their absolute risk level where they will set their stop loss to protect themselves from
// incurring large losses. Wise traders also have an idea for where a stock may head to where they can sell their
// position. Having a stop loss and target price in mind gives you an idea of the risk reward behind a setup. The
// greater the risk reward, the greater the odds of a profit.
//
// The Target Price Section displays the target price, percent gain, dollar gain per share and profit. The Stop Loss
// Section displays the stop loss, percent loss, dollar loss per share and loss. The Risk Section displays the
// risk reward and account risk.

package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private GUI myGUI;

    @Override
    public void start(Stage primaryStage) throws Exception{
        myGUI = new GUI();
        myGUI.buildGUI(primaryStage);
    }

    public static void main(String[] args) { launch(args); }
}
