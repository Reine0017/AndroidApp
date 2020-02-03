/**
 *  Calculator Form request user input to calculate
 *  renting cost and buying cost.
 */

package com.example.soh.cz2006testapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class Calculator_Form extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static double inflationFromWeb,lastPriceIndexFromWeb,priceIndexFromWeb;
    private FirebaseAuth mAuth;

    /**
     * Creates the view using the activity_calculator__form xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator__form);

        mAuth = FirebaseAuth.getInstance();

        new getJsonData().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_calculator_form);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_calculator_form);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_view_display_name);
        nav_user.setText(mAuth.getCurrentUser().getDisplayName());
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Closes the navigation drawer on pressing back.
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_calculator_form);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Menu for navigation drawer.
     * @param item
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.district_map) {
            Intent intent = new Intent(this, District_Map.class);
            startActivity(intent);
            finish();
        }

        /*
        if (id == R.id.calculator) {
            Intent intent = new Intent(this, Calculator_Form.class);
            startActivity(intent);
        }*/

        else if (id == R.id.messages) {
            Intent intent = new Intent(this, View_Chat.class);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.property_list) {
            Intent intent = new Intent(this, Post_Listing_Form.class);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.listed_property) {
            Intent intent = new Intent(this, View_User_Post.class);
            intent.putExtra("district", "0");
            startActivity(intent);
            finish();
        }

        else if (id == R.id.main_menu) {
            Intent intent = new Intent(this, Member_Main.class);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Guest_Main.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_calculator_form);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Reads user input and send to tabs.java
     * @param view
     */

    public void calculate(View view) {
        // Do something in response to button

        //getting input from user to pass to next activity
        Spinner citizenShip = (Spinner) findViewById(R.id.spinner_citizen);
        String citizenShipTxt = citizenShip.getSelectedItem().toString();
        Spinner firstTime = (Spinner) findViewById(R.id.spinner_first_time);
        String firstTimeSelection = firstTime.getSelectedItem().toString();
        EditText duration = (EditText) findViewById(R.id.edit_text_duration_of_stay);
        String durationTxt = duration.getText().toString();
        EditText price = (EditText) findViewById(R.id.edit_text_price);
        String priceTxt = price.getText().toString();
        EditText downPayment = (EditText) findViewById(R.id.edit_text_down_payment);
        String downPaymentTxt = downPayment.getText().toString();
        EditText mortgageRate = (EditText) findViewById(R.id.edit_text_mortgage_rate);
        String mortgageRateTxt = mortgageRate.getText().toString();
        EditText mortgageDur = (EditText) findViewById(R.id.edit_text_mortgage_duration);
        String mortgageDurTxt = mortgageDur.getText().toString();
        EditText investmentGrowth = (EditText) findViewById(R.id.edit_text_investment);
        String investmentGrowthTxt = investmentGrowth.getText().toString();
        EditText annualValue = (EditText) findViewById(R.id.edit_text_annual_value);
        String annualValueTxt = annualValue.getText().toString();
        EditText maintenanceFee = (EditText) findViewById(R.id.edit_text_maintainence_fee);
        String maintenanceFeeTxt = maintenanceFee.getText().toString();
        EditText monthlyUtil = (EditText) findViewById(R.id.edit_text_monthly_utilities);
        String monthlyUtilTxt = monthlyUtil.getText().toString();
        EditText homeOwnerInsurance = (EditText) findViewById(R.id.edit_text_homeowner_insurance);
        String homeOwnerInsuranceTxt = homeOwnerInsurance.getText().toString();
        EditText secDeposit = (EditText) findViewById(R.id.edit_text_security_deposit);
        String secDepositTxt = secDeposit.getText().toString();
        EditText brokerFee = (EditText) findViewById(R.id.edit_text_broker_fee);
        String brokerFeeTxt = brokerFee.getText().toString();
        EditText rentalInsurance = (EditText) findViewById(R.id.edit_text_rental_insurance);
        String rentalInsuranceTxt = rentalInsurance.getText().toString();

        if (citizenShip.getSelectedItem().toString().equals("Select Citizenship"))
        {
            Toast.makeText(this, "Please select citizenship", Toast.LENGTH_SHORT).show();
            citizenShip.requestFocus();
        }
        else if (citizenShip.getSelectedItem().toString().equals("Select Number of Purchases"))
        {
            Toast.makeText(this, "Please select select number of purchases", Toast.LENGTH_SHORT).show();
            firstTime.requestFocus();
        }
        else if (isEmpty(duration))
        {
            Toast.makeText(this, "Please fill in duration of stay", Toast.LENGTH_SHORT).show();
            focusText(duration);
        }
        else if (isEmpty(price))
        {
            Toast.makeText(this, "Please fill in price of property", Toast.LENGTH_SHORT).show();
            focusText(price);
        }
        else if (isEmpty(downPayment))
        {
            Toast.makeText(this, "Please fill in downpayment", Toast.LENGTH_SHORT).show();
            focusText(downPayment);
        }
        else if (isEmpty(mortgageRate))
        {
            Toast.makeText(this, "Please fill in mortgage rate", Toast.LENGTH_SHORT).show();
            focusText(mortgageRate);
        }
        else if (isEmpty(mortgageDur))
        {
            Toast.makeText(this, "Please fill in mortgage duration", Toast.LENGTH_SHORT).show();
            focusText(mortgageDur);
        }
        else if (isEmpty(investmentGrowth))
        {
            Toast.makeText(this, "Please fill in investment growth", Toast.LENGTH_SHORT).show();
            focusText(investmentGrowth);
        }
        else if (isEmpty(annualValue))
        {
            Toast.makeText(this, "Please fill in annual value of house", Toast.LENGTH_SHORT).show();
            focusText(annualValue);
        }
        else if (isEmpty(maintenanceFee))
        {
            Toast.makeText(this, "Please fill in maintenance fee", Toast.LENGTH_SHORT).show();
            focusText(maintenanceFee);
        }
        else if (isEmpty(monthlyUtil))
        {
            Toast.makeText(this, "Please fill in monthly utilities", Toast.LENGTH_SHORT).show();
            focusText(monthlyUtil);
        }
        else if (isEmpty(homeOwnerInsurance))
        {
            Toast.makeText(this, "Please fill in homeowner's insurance", Toast.LENGTH_SHORT).show();
            focusText(homeOwnerInsurance);
        }
        else if (isEmpty(secDeposit))
        {
            Toast.makeText(this, "Please fill in security deposit", Toast.LENGTH_SHORT).show();
            focusText(secDeposit);
        }
        else if (isEmpty(brokerFee))
        {
            Toast.makeText(this, "Please fill in broker's fee", Toast.LENGTH_SHORT).show();
            focusText(brokerFee);
        }
        else if (isEmpty(rentalInsurance))
        {
            Toast.makeText(this, "Please fill in rental insurance", Toast.LENGTH_SHORT).show();
            focusText(rentalInsurance);
        }
        else
        {
            Intent intent = new Intent(this, Tabs.class);
            intent.putExtra("price", priceTxt);
            intent.putExtra("downPayment", downPaymentTxt);
            intent.putExtra("citizenship", citizenShipTxt);
            intent.putExtra("firstTime", firstTimeSelection);
            intent.putExtra("duration", durationTxt);
            intent.putExtra("mortgageRate", mortgageRateTxt);
            intent.putExtra("mortgageDur", mortgageDurTxt);
            intent.putExtra("investmentGrowth", investmentGrowthTxt);
            intent.putExtra("annualValue", annualValueTxt);
            intent.putExtra("maintenanceFee", maintenanceFeeTxt);
            intent.putExtra("monthlyUtil", monthlyUtilTxt);
            intent.putExtra("homeOwnerInsurance", homeOwnerInsuranceTxt);
            intent.putExtra("secDeposit", secDepositTxt);
            intent.putExtra("brokerFeeRate", brokerFeeTxt);
            intent.putExtra("rentalInsurance", rentalInsuranceTxt);

            startActivity(intent);
        }
    }

    /**
     * Checks whether the user input is empty.
     * @param etText
     * @return whether empty
     */

    private boolean isEmpty(EditText etText) {
        //Check whether EditText is empty
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    /**
     * Change focus of view to empty text.
     * @param etText
     */

    private void focusText(EditText etText)
    {
        //Focus on the EditText that is empty or contain invalid characters
        etText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Request data from external website.
     */

    private class getJsonData extends AsyncTask<Void, Void, String[]> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Request and read data from external website.
         * @param arg0
         * @return data
         */

        @Override
        protected String[] doInBackground(Void... arg0) {

            String[] strArr = new String[2];
            Http_Handler sh = new Http_Handler();
            // Making a request to url and getting response
            String url1 = "https://data.gov.sg//api/action/datastore_search?offset=1650&resource_id=8741b6b0-81fe-4f3e-a412-5d7dd669bfb9";
            String jsonStr1 = sh.makeServiceCall(url1);

            String url2 = "https://data.gov.sg/api/action/datastore_search?offset=100&resource_id=f83f5994-e7f8-4dce-b70d-8e7b3cc3b8fb";
            String jsonStr2 = sh.makeServiceCall(url2);

            strArr[0] = jsonStr1;
            strArr[1] = jsonStr2;

            return strArr;
        }

        /**
         * Isolate required data from json data.
         * @param result
         */

        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObj = new JSONObject(result[0]);
                JSONObject result1 = jsonObj.getJSONObject("result");
                JSONArray record = result1.getJSONArray("records");

                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<Inflation_Data>>() {
                }.getType();
                List<Inflation_Data> details = gson.fromJson(record.toString(), collectionType);

                for (int i = 0; i < details.size(); i++) {

                    if (details.get(i).getYear().equals("2016") && details.get(i).get_id().equals("1743")) {

                        inflationFromWeb = Float.valueOf(details.get(i).getValue());
                    }
                }
            } catch (Exception e) {
                Log.e("error", "exception");
            }

            try {
                JSONObject jsonObj = new JSONObject(result[1]);
                JSONObject result1 = jsonObj.getJSONObject("result");
                JSONArray record = result1.getJSONArray("records");

                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<Growth_Rate>>() {
                }.getType();
                List<Growth_Rate> details = gson.fromJson(record.toString(), collectionType);

                for (int i = 0; i < details.size(); i++) {
                    if (details.get(i).get_id().equals("147")) {
                        lastPriceIndexFromWeb = Float.valueOf(details.get(i).getPrice_index());
                    }
                    if (details.get(i).get_id().equals("150")) {
                        priceIndexFromWeb = Float.valueOf(details.get(i).getPrice_index());
                    }

                }
            } catch (Exception e) {
            }
        }

    }
}
