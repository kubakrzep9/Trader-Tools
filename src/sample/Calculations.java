// The Calculations class contains all the necessary calculations required by the GUI. These calculations are required
// for the Trader Condition, Target Price, Stop Loss and Risk Sections.



package sample;

public class Calculations {

    // TRADE CONDITION FUNCTIONS //
    // These functions are used to calculate the third condition based upon the user input for the other
    // two conditions.
    public double calcPositionPrice(double pricePerShare, double numShares){ return pricePerShare*numShares; }

    public double calcNumShares(double pricePerShare, double positionPrice){ return positionPrice / pricePerShare; }

    public double calcPricePerShare(double numShares, double positionPrice){ return positionPrice / numShares; }


    // TARGET PRICE FUNCTIONS //
    // These functions are used to calculate the Target Price Section, which contains the target price, percent gain,
    // dollar gain, and total profit based upon the user selected and input goal gain condition.
    public double calcTargetPrice(int ggSelected, double goalGain, double pricePerShare, double numShares){
        switch(ggSelected){
            case 0: // goal % gain
                return pricePerShare * (1+goalGain/100);
            case 1: // goal $ gain
                return pricePerShare + (goalGain/numShares);
            case 2: // goal $ gain per share
                return pricePerShare + goalGain;
        }
        return 0.0; // will never execute
    }

    public double calcPercentGain(int ggSelected, double goalGain, double targetPrice, double purchasePrice) {
        switch (ggSelected) {
            case 0: // goal % gain
                return goalGain;
            case 1: // goal $ gain
            case 2: // goal $ gain per share
                return (targetPrice/purchasePrice-1)*100;
        }
        return 0.0; // will never execute
    }

    public double calcDollarGain(double targetPrice, double pricePerShare){ return targetPrice - pricePerShare; }

    public double calcProfit(double dollarGainPerShare, double numShares){ return dollarGainPerShare * numShares; }

    // STOP LOSS FUNCTIONS //
    // These functions are used to calculate the Stop Loss Section, which contains the stop loss, percent loss,
    // dollar loss, and total loss based upon the user selected and input max loss condition.
    public double calcStopLoss(int mlSelected, double maxLoss, double pricePerShare, double numShares){
        switch(mlSelected){
            case 0: // max % loss
                return pricePerShare * (1-maxLoss/100);
            case 1: // max $ loss
                return pricePerShare - (maxLoss/numShares);
            case 2: // max $ loss per share
                return pricePerShare - maxLoss;
        }

        return 0.0; // will never execute
    }

    public double calcPercentLoss(int mlSelected, double maxLoss, double stopLoss, double pricePerShare) {
        switch (mlSelected) {
            case 0: // max % loss
                return maxLoss;
            case 1: // max $ loss
            case 2: // max $ loss per share
                return (pricePerShare / stopLoss - 1)*100;
        }
        return 0.0; // will never execute
    }

    public double calcDollarLoss(double stopLoss, double pricePerShare){ return stopLoss-pricePerShare; }

    public double calcLoss(double dollarLossPerShare, double numShares){ return dollarLossPerShare * numShares;}

    // RISK FUNCTIONS //
    // These functions are used to calculate the account risk and risk reward, based upon the user input account
    // balance vs position price and profit vs loss.
    public double calcRiskReward(double profit, double loss){ return (profit/loss); }

    public double calcAccountRisk(double accountBalance, double positionPrice){ return (positionPrice / accountBalance) * 100; }
}
