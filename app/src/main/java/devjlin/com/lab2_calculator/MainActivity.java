package devjlin.com.lab2_calculator;

import android.net.UrlQuerySanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LoanCalculator lc = new LoanCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onClick_Calculate (View view) {
        TextView textMonthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        TextView textTotalPayment = (TextView) findViewById(R.id.totalPayment);
        TextView textTotalInterest = (TextView) findViewById(R.id.totalInterest);

        Double loanAmount;
        int years;
        Double interestRate;

        try {
            loanAmount = Double.parseDouble(((EditText)findViewById(R.id.loanAmount)).getText().toString());
            years = Integer.parseInt(((EditText)findViewById(R.id.years)).getText().toString());
            interestRate = Double.parseDouble(((EditText)findViewById(R.id.interestRate)).getText().toString());

            lc.setLoanAmount(loanAmount);
            lc.setNumberOfYears(years);
            lc.setYearlyInterestRate(interestRate);

            if (lc.getNumberOfYears()<1 || lc.getNumberOfYears()>25) {
                throw new IndexOutOfBoundsException();
            }

            if (lc.getYearlyInterestRate() >100) {
                throw new IndexOutOfBoundsException();
            }

            String monthlyPayment = lc.getMonthlyPayment()+"";
            String totalPayment = lc.getTotalCostOfLoan()+"";
            String totalInterest = lc.getTotalInterest()+"";

            textMonthlyPayment.setText(monthlyPayment);
            textTotalPayment.setText(totalPayment);
            textTotalInterest.setText(totalInterest);

        } catch (IllegalArgumentException e) {
            Toast.makeText(MainActivity.this, "Do not have any blank fields!", Toast.LENGTH_SHORT).show();
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(MainActivity.this, "number of years must be between 1 to 25, interest rate must be below 100.", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClick_Clear (View view) {
        TextView monthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        TextView totalPayment = (TextView) findViewById(R.id.totalPayment);
        TextView totalInterest = (TextView) findViewById(R.id.totalInterest);

        ((EditText) findViewById(R.id.loanAmount)).setText("");
        ((EditText) findViewById(R.id.years)).setText("");
        ((EditText) findViewById(R.id.interestRate)).setText("");

        monthlyPayment.setText("");
        totalPayment.setText("");
        totalInterest.setText("");
    }
}
