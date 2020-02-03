/**
 * Displays expandable list on buy tab
 */

package com.example.soh.cz2006testapp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.round;

public class Expandable_List_Data_Pump_Buy {
    static DecimalFormat decimalFormatter = new DecimalFormat("###,###,###,###,###");

    /**
     * Creates the hashmap to display the calculated result.
     * @return hashmap.
     */

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> initialCosts = new ArrayList<String>();
        initialCosts.add("Downpayment" + "\n$" + decimalFormatter.format(round(Tabs.downPaymentDbl)));
        initialCosts.add("Stamp Duty" + "\n$" + decimalFormatter.format(round(Tabs.stampDbl)));
        initialCosts.add("Additional Buyer Stamp Duty" + "\n$" + decimalFormatter.format(round(Tabs.addstampDbl)));
        initialCosts.add("Mortgage Tax" + "\n$" + decimalFormatter.format(round(Tabs.mortgagetaxDbl)));

        List<String> recurringCosts = new ArrayList<String>();
        recurringCosts.add("Mortgage Cost" + "\n$" + decimalFormatter.format
                (round(Tabs.monMortgage * 12 * Tabs.durationDbl)));
        recurringCosts.add("Property Tax" + "\n$" + decimalFormatter.format
                (round(Tabs.propertyTaxDbl * Tabs.durationDbl)));
        recurringCosts.add("Insurace" + "\n$" + decimalFormatter.format
                (round(Tabs.insuranceDbl * Tabs.durationDbl)));
        recurringCosts.add("Maintenance Fee" + "\n$" + decimalFormatter.format
                (round(Tabs.maintDbl * Tabs.durationDbl)));
        recurringCosts.add("Utilities Cost" + "\n$" + decimalFormatter.format
                (round(Tabs.monthlyUtilDbl * Tabs.durationDbl)));

        List<String> opportunityCosts = new ArrayList<String>();
        opportunityCosts.add("Initial Costs" + "\n$" + decimalFormatter.format
                (round(Tabs.initialValueDbl)));
        opportunityCosts.add("Recurring Costs" + "\n$" + decimalFormatter.format
                (round(Tabs.recurringCost)));

        List<String> proceeds = new ArrayList<String>();
        proceeds.add("Selling Price" + "\n$-" + decimalFormatter.format
                (round(Tabs.sellingPriceDbl)));
        proceeds.add("Seller's Stamp Duty" + "\n$" + decimalFormatter.format
                (round(Tabs.sellerStampDbl)));
        proceeds.add("Remaining Loan" + "\n$" + decimalFormatter.format
                (round(Tabs.remainingLoan)));

        expandableListDetail.put("Initial Costs" + "\n$" + decimalFormatter.format
                (round(Tabs.initialValueDbl)), initialCosts);
        expandableListDetail.put("Recurring Costs" + "\n$" + decimalFormatter.format
                (round(Tabs.recurringCost)), recurringCosts);
        expandableListDetail.put("Opportunity Costs" + "\n$" + decimalFormatter.format
                (round(Tabs.initialValueDbl + Tabs.recurringCost)), opportunityCosts);
        expandableListDetail.put("Proceeds" + "\n$-" + decimalFormatter.format
                (round(Tabs.proceedsDbl)), proceeds);
        return expandableListDetail;
    }
}
