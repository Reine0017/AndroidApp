/**
 * Activity that holds rent tab and buy tab fragment
 */

package com.example.soh.cz2006testapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.text.DecimalFormat;

public class Tabs extends AppCompatActivity {

    DecimalFormat decimalFormatter = new DecimalFormat("###,###,###,###,###");

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static String  name, citizenShip, firstTime, duration, price, mortgageRate, mortgageDur, investmentGro,
            downPayment, annualValue, maintFee, monthlyUtil, homeOwnerInsur, secDeposit,
            brokerFeeRate, rentalInsur;

    public static double proceedsDbl, stampDbl, addstampDbl, mortgagetaxDbl, initialValueDbl, annualValueDbl,
            monInterest, monMortgage, sellingPriceDbl, mortgageLoan, remainingLoan,
            propertyTaxDbl, durationDbl, priceDbl;

    public static double downPaymentPctDbl, downPaymentDbl, mortgageRateDbl, mortgageDurDbl, investmentGroDbl,
            monthlyUtilDbl, insuranceDbl, maintDbl, sellerStampDbl, recurringCost,
            netPriceIndex, netProfitDbl, brokerFeeRateDbl,
            rentalInsurDbl, rentDbl, initialOppCostDbl, RecOppCostDbl;

    public static double leaseDutyDbl, brokerFeeDbl, secDepositDbl, rentalInitialDbl, rentalRecurring, rentalOppCost,
            rentalProceeds, leaseDutyRateDbl, oppCostDbl;

    /**
     * Creates the view using the activity_tabs xml.
     * @param savedInstanceState
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        //new GetJsonData().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUserInputs();
        Calculators c = new Calculate();
        c.calculate();

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

   /* private class GetJsonData extends AsyncTask<Void, Void, String[]> {

        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Tabs.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String[] doInBackground(Void... arg0) {

            String[] strArr = new String[2];
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url1 = "https://data.gov.sg//api/action/datastore_search?offset=1650&resource_id=8741b6b0-81fe-4f3e-a412-5d7dd669bfb9";
            String jsonStr1 = sh.makeServiceCall(url1);

            String url2 = "https://data.gov.sg/api/action/datastore_search?offset=100&resource_id=f83f5994-e7f8-4dce-b70d-8e7b3cc3b8fb";
            String jsonStr2 = sh.makeServiceCall(url2);

            strArr[0] = jsonStr1;
            strArr[1] = jsonStr2;

            return strArr;
        }

        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObj = new JSONObject(result[0]);
                JSONObject result1 = jsonObj.getJSONObject("result");
                JSONArray record = result1.getJSONArray("records");

                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<inflationData>>() {
                }.getType();
                List<inflationData> details = gson.fromJson(record.toString(), collectionType);

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
                Type collectionType = new TypeToken<List<GrowthRate>>() {
                }.getType();
                List<GrowthRate> details = gson.fromJson(record.toString(), collectionType);

                for (int i = 0; i < details.size(); i++) {
                    if (details.get(i).get_id().equals("147")) {
                        lastPriceIndexFromWeb = Float.valueOf(details.get(i).getPrice_index());
                    }
                    if (details.get(i).get_id().equals("150")) {
                        priceIndexFromWeb = Float.valueOf(details.get(i).getPrice_index());
                        calculate();
                    }

                }
            } catch (Exception e) {
                Log.e(TAG, "exception in parsing data 2");
            }
        }

    } */

    /**
     * Gets user input from calculator form.
     */

    public void getUserInputs()
    {
        citizenShip = getIntent().getStringExtra("citizenship");
        firstTime = getIntent().getStringExtra("firstTime");
        duration = getIntent().getStringExtra("duration");
        price = getIntent().getStringExtra("price");
        downPayment = getIntent().getStringExtra("downPayment");
        mortgageRate = getIntent().getStringExtra("mortgageRate");
        mortgageDur = getIntent().getStringExtra("mortgageDur");
        investmentGro = getIntent().getStringExtra("investmentGrowth");
        annualValue = getIntent().getStringExtra("annualValue");
        maintFee = getIntent().getStringExtra("maintenanceFee");
        monthlyUtil = getIntent().getStringExtra("monthlyUtil");
        homeOwnerInsur = getIntent().getStringExtra("homeOwnerInsurance");
        secDeposit = getIntent().getStringExtra("secDeposit");
        brokerFeeRate = getIntent().getStringExtra("brokerFeeRate");
        rentalInsur = getIntent().getStringExtra("rentalInsurance");

        brokerFeeRateDbl = Double.parseDouble(brokerFeeRate);

        priceDbl = Double.parseDouble(price);
        downPaymentPctDbl = (Double.parseDouble(downPayment) / 100);
        mortgageRateDbl = Double.parseDouble(mortgageRate);
        investmentGroDbl = Double.parseDouble(investmentGro);
        durationDbl = Double.parseDouble(duration);
        mortgageDurDbl = Double.parseDouble(mortgageDur);
        monthlyUtilDbl = Double.parseDouble(monthlyUtil);
        annualValueDbl = Double.parseDouble(annualValue);

        insuranceDbl = Double.parseDouble(homeOwnerInsur);
        maintDbl = Double.parseDouble(maintFee);
        rentalInsurDbl = Double.parseDouble(rentalInsur);
        secDepositDbl = Double.parseDouble(secDeposit);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        /**
         * Constructor for adapter.
         * @param fm
         */

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Displays the view depending on the position.
         * @param position
         * @return view of tab fragment
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Buy_Tab buyTab = new Buy_Tab();
                    return buyTab;
                case 1:
                    Rent_Tab rentTab = new Rent_Tab();
                    return rentTab;
                default:
                    return null;
            }
        }

        /**
         * Gets number of fragment.
         * @return number of fragments
         */

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        /**
         * Gets title of fragment.
         * @param position
         * @return title of fragment.
         */

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Buy Cost Breakdown";
                case 1:
                    return "Rent Cost Breakdown";
            }
            return null;
        }
    }

    /**
     * When up button is clicked, return to previous open activity.
     * @param item
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

