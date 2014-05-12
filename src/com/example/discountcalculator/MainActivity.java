//Robert Payne
//Homework 2
package com.example.discountcalculator;

import java.text.DecimalFormat;
import com.example.discountcalculator.R;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnCheckedChangeListener, OnSeekBarChangeListener, OnClickListener,TextWatcher {
	EditText listPrice;										//List price field
	Button exitButton;										//Exit button on the UI
	SeekBar customDiscount;									//Seekbar used to enter custom discount
	RadioGroup discountButtons;								//Radio group used to enter discount
	TextView customProgress;								//Text view used to show custom discount
	TextView discount;										//Text view used to show discount in dollars/cents
	TextView purchasePrice;									//Text view used to show the final price after discount
	String newPrice = new String();							//string used to store the new price before it is output to the UI
	String discountInDollars = new String();				//string used to store the discount before it is output to the UI
	int radioButton =1;										//keeps track of the current radio button; default is 1 for 10%
	DecimalFormat moneyFormat = new DecimalFormat("0.00");	//format used to represent money

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listPrice = (EditText) findViewById(R.id.editText1);
		exitButton = (Button) findViewById(R.id.button1);
		customDiscount = (SeekBar) findViewById(R.id.seekBar1);
		discountButtons = (RadioGroup) findViewById(R.id.radioGroup1);
		customProgress = (TextView) findViewById(R.id.textView8);
		discount = (TextView) findViewById(R.id.textView5);
		purchasePrice = (TextView) findViewById(R.id.textView7);
		
		//Setting up the listeners for button, radio group, edit text, and seekbar
		discountButtons.setOnCheckedChangeListener(this);	
		customDiscount.setOnSeekBarChangeListener(this);
		exitButton.setOnClickListener(this);
		listPrice.addTextChangedListener(this); 
        
        
	}//end of method

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}//end of method

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		customProgress.setText(customDiscount.getProgress() + "%");
		updateDiscount();
	}//end of method

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}//end of method

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}//end of method

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.radio0:
			radioButton = 1;
			break;
		case R.id.radio1:
			radioButton = 2;
			break;
		case R.id.radio2:
			radioButton = 3;
			break;
		case R.id.radio3:
			radioButton = 4;
			break;
		default:
			break;
		}
		
		updateDiscount();
	}//end of method

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();		//end the program when the button is pressed
	}//end of method
	
	private void updateDiscount() {
		//checks to see which button was checked
		if(listPrice.getText().toString().length() == 0)
			;
	    else if(radioButton == 4)
		{
			discountInDollars = Double.valueOf(listPrice.getText().toString())*(Double.valueOf(customDiscount.getProgress())/ 100) + "";
			newPrice = Double.valueOf(listPrice.getText().toString()) - Double.valueOf(discountInDollars) + "";
			updatePriceAndDiscount(newPrice, discountInDollars);
		}
		else if (radioButton == 1){
			discountInDollars = Double.valueOf(listPrice.getText().toString())*(.10) + "";
			newPrice = Double.valueOf(listPrice.getText().toString()) - Double.valueOf(discountInDollars) + "";
			updatePriceAndDiscount(newPrice, discountInDollars);
		}
		else if (radioButton == 2){
			discountInDollars = Double.valueOf(listPrice.getText().toString())*(.25) + "";
			newPrice = Double.valueOf(listPrice.getText().toString()) - Double.valueOf(discountInDollars) + "";
			updatePriceAndDiscount(newPrice, discountInDollars);
		}
		else if (radioButton == 3) {
			discountInDollars = Double.valueOf(listPrice.getText().toString())*(.5) + "";
			newPrice = Double.valueOf(listPrice.getText().toString()) - Double.valueOf(discountInDollars) + "";	
			updatePriceAndDiscount(newPrice, discountInDollars);
		}
		
	}//end of method
	
	private void updatePriceAndDiscount(String newPrice, String newDiscount){
		discount.setText("$"+moneyFormat.format(Double.valueOf(newDiscount)));
		purchasePrice.setText("$"+moneyFormat.format(Double.valueOf(newPrice)));
	}//end of method

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}//end of method

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
	}//end of method

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		if (listPrice.getText().toString().length() == 0) {
            listPrice.setError("Enter List Price");
            updatePriceAndDiscount("0.00", "0.00");
		}
		else {
			updateDiscount();
		}
	}//end of method
}

