/**
 * Calculates the result for the cost of buying a house
 * and the cost of renting a house.
 */

package com.example.soh.cz2006testapp;

public class Calculate implements Calculators {

    public static double monInterest, oppCostDbl,netPriceIndex, mortgageLoan,netProfitDbl,
        leaseDutyRateDbl,leaseDutyDbl,brokerFeeDbl,rentalInitialDbl,rentalRecurring,
        rentalOppCost,initialOppCostDbl,RecOppCostDbl,rentalProceeds;
    private static Calculate ourInstance = new Calculate();
    public static Calculate getInstance() {
        return ourInstance;
    }

    /**
     * Calculates cost of buying and renting property
     * using user inputs.
     */

    public void calculate() {
        Tabs.downPaymentDbl = Tabs.downPaymentPctDbl * Tabs.priceDbl;
        if (Tabs.priceDbl <= 180000)
            Tabs.stampDbl = Tabs.priceDbl * 0.01;
        else if (Tabs.priceDbl - 180000 <= 180000)
            Tabs.stampDbl = 1800 + (Tabs.priceDbl - 180000) * 0.02;
        else
            Tabs.stampDbl = 1800 + 3600 + ((Tabs.priceDbl - 360000) * 0.03);

        if (Tabs.citizenShip.contains("Singaporean") && Tabs.firstTime.contains("Second"))
            Tabs.addstampDbl = Tabs.priceDbl * 0.07;
        else if (Tabs.citizenShip.contains("Singaporean") && Tabs.firstTime.contains("More"))
            Tabs.addstampDbl = Tabs.priceDbl * 0.10;
        else if (Tabs.citizenShip.contains("Resident") && Tabs.firstTime.contains("First"))
            Tabs.addstampDbl = Tabs.priceDbl * 0.05;
        else if (Tabs.citizenShip.contains("Resident") && Tabs.firstTime.contains("Second"))
            Tabs.addstampDbl = Tabs.priceDbl * 0.10;
        else if (Tabs.citizenShip.contains("For"))
            Tabs.addstampDbl = Tabs.priceDbl * 0.15;

        if ((Tabs.priceDbl - Tabs.downPaymentDbl) < 125000)
            Tabs.mortgagetaxDbl = (Tabs.priceDbl - Tabs.downPaymentDbl) * 0.4;
        else
            Tabs.mortgagetaxDbl = 500;

        Tabs.initialValueDbl = Tabs.downPaymentDbl + Tabs.stampDbl + Tabs.addstampDbl + Tabs.mortgagetaxDbl;

        monInterest = (Tabs.mortgageRateDbl / 100) / 12;
        Tabs.monMortgage = (Tabs.priceDbl - Tabs.downPaymentDbl) * monInterest * ((Math.pow((1 + monInterest), Tabs.mortgageDurDbl * 12)) / ((Math.pow((1 + monInterest), Tabs.mortgageDurDbl * 12)) - 1));

        if (Tabs.annualValueDbl < 8000)
            Tabs.propertyTaxDbl = 0;
        else if (Tabs.annualValueDbl <= 55000)
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 8000) * 0.04;
        else if (Tabs.annualValueDbl <= 70000)
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 55000) * 0.06 + 1880;
        else if (Tabs.annualValueDbl <= 85000)
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 70000) * 0.08 + 2780;
        else if (Tabs.annualValueDbl <= 100000)
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 85000) * 0.1 + 3980;
        else if (Tabs.annualValueDbl <= 115000)
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 100000) * 0.12 + 5480;
        else if (Tabs.annualValueDbl <= 130000)
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 115000) * 0.14 + 7280;
        else
            Tabs.propertyTaxDbl = (Tabs.annualValueDbl - 130000) * 0.16 + 9380;

        Tabs.recurringCost = (Tabs.monMortgage * 12 + Tabs.propertyTaxDbl + Tabs.insuranceDbl + Tabs.maintDbl + Tabs.monthlyUtilDbl * 12) * Tabs.durationDbl;

        oppCostDbl = Tabs.initialValueDbl * Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl)
                + (Tabs.recurringCost / Tabs.durationDbl) * (Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl) - 1) /
                Tabs.investmentGroDbl / 100;

        netPriceIndex = (Calculator_Form.priceIndexFromWeb - Calculator_Form.lastPriceIndexFromWeb) / Calculator_Form.lastPriceIndexFromWeb;

        Tabs.sellingPriceDbl = Tabs.priceDbl * Math.pow((1 + netPriceIndex / 100), Tabs.durationDbl);

        if (Tabs.durationDbl < 1)
            Tabs.sellerStampDbl = Tabs.sellingPriceDbl * 0.16;
        else if (1 < Tabs.durationDbl && Tabs.durationDbl < 2)
            Tabs.sellerStampDbl = Tabs.sellingPriceDbl * 0.12;
        else if (2 < Tabs.durationDbl && Tabs.durationDbl < 3)
            Tabs.sellerStampDbl = Tabs.sellingPriceDbl * 0.08;
        else if (3 < Tabs.durationDbl && Tabs.durationDbl < 4)
            Tabs.sellerStampDbl = Tabs.sellingPriceDbl * 0.04;
        else
            Tabs.sellerStampDbl = 0;

        mortgageLoan = Tabs.priceDbl - Tabs.downPaymentDbl;
        Tabs.remainingLoan = mortgageLoan * Math.pow((1 + monInterest), Tabs.mortgageDurDbl) - Tabs.monMortgage * (Math.pow((1 + monInterest), Tabs.mortgageDurDbl) - 1) / monInterest;
        Tabs.proceedsDbl = Tabs.sellingPriceDbl - Tabs.sellerStampDbl - Tabs.remainingLoan;
        netProfitDbl = Tabs.initialValueDbl + Tabs.recurringCost + oppCostDbl - Tabs.proceedsDbl;

        if (Tabs.durationDbl <= 4)
            leaseDutyRateDbl = 0.004 * 12 * Tabs.durationDbl;
        else
            leaseDutyRateDbl = 0.004 * 12 * 4;

        Tabs.rentDbl = (netProfitDbl - Tabs.durationDbl * Tabs.rentalInsurDbl - Tabs.rentalInsurDbl *
                ((Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl) - 1) / (Tabs.investmentGroDbl / 100)))/ (leaseDutyRateDbl + Tabs.brokerFeeRateDbl / 100 * 12 + (Tabs.durationDbl *
                12) + (leaseDutyRateDbl + Tabs.brokerFeeRateDbl / 100 * 12) *
                Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl) + 12 *
                ((Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl) - 1) / (Tabs.investmentGroDbl / 100)));

        leaseDutyDbl = leaseDutyRateDbl * Tabs.rentDbl;
        brokerFeeDbl = Tabs.brokerFeeRateDbl / 100 * 12 * Tabs.rentDbl;
        rentalInitialDbl = leaseDutyDbl + brokerFeeDbl + Tabs.secDepositDbl;
        rentalRecurring = (Tabs.durationDbl * 12) * Tabs.rentDbl + (Tabs.rentalInsurDbl * Tabs.durationDbl);
        rentalOppCost = rentalInitialDbl * Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl) +
                rentalRecurring / Tabs.durationDbl * (((Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl)) - 1) /
                        (Tabs.investmentGroDbl / 100));
        initialOppCostDbl = rentalInitialDbl * Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl);
        RecOppCostDbl = rentalRecurring / Tabs.durationDbl * (((Math.pow((1 + Tabs.investmentGroDbl / 100), Tabs.durationDbl)) - 1) /
                (Tabs.investmentGroDbl / 100));
        rentalProceeds = -(Tabs.secDepositDbl);

    }


}
