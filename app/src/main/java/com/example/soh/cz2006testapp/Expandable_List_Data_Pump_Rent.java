/**
 * Displays expandable list on rent tab
 */

package com.example.soh.cz2006testapp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.round;

public class Expandable_List_Data_Pump_Rent {
    static DecimalFormat decimalFormatter = new DecimalFormat("###,###,###,###,###");

    /**
     * Creates the hashmap to display the calculated result.
     * @return hashmap.
     */

    public static HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> initialCosts = new ArrayList<String>();
        initialCosts.add("Security Deposit" + "\n$" + decimalFormatter.format(
                round(Tabs.secDepositDbl)));
        initialCosts.add("Lease Duty" + "\n$" + decimalFormatter.format
                (round(Calculate.leaseDutyDbl)));
        initialCosts.add("Broker Fee" + "\n$" + decimalFormatter.format
                (round(Calculate.brokerFeeDbl)));

        List<String> recurringCosts = new ArrayList<String>();
        recurringCosts.add("Rental Insurance for" + decimalFormatter.format(Tabs.durationDbl) + " year(s)\n$" + decimalFormatter.format
                (round(Tabs.rentalInsurDbl * Tabs.durationDbl)));
        recurringCosts.add("Rent Cost for " + decimalFormatter.format(Tabs.durationDbl) + " year(s)\n$" + decimalFormatter.format
                (round(Tabs.rentDbl * 12 * Tabs.durationDbl)));

        List<String> opportunityCosts = new ArrayList<String>();
        opportunityCosts.add("Initial Opportunity Cost" + "\n$" + decimalFormatter.format
                (round(Calculate.initialOppCostDbl)));
        opportunityCosts.add("Recurring Opportunity Cost" + "\n$" + decimalFormatter.format
                (round(Calculate.RecOppCostDbl)));

        List<String> proceeds = new ArrayList<String>();
        proceeds.add("Security Deposit Cost" + "\n$" + decimalFormatter.format
                (round(Calculate.rentalProceeds)));

        expandableListDetail.put("Initial Costs" + "\n$" + decimalFormatter.format
                (round(Calculate.rentalInitialDbl)), initialCosts);
        expandableListDetail.put("Recurring Costs" + "\n$" + decimalFormatter.format
                (round(Calculate.rentalRecurring)), recurringCosts);
        expandableListDetail.put("Opportunity Costs" + "\n$" + decimalFormatter.format
                (round(Calculate.rentalOppCost)), opportunityCosts);
        expandableListDetail.put("Proceeds" + "\n$" + decimalFormatter.format
                (round(Calculate.rentalProceeds)), proceeds);
        return expandableListDetail;
    }
}