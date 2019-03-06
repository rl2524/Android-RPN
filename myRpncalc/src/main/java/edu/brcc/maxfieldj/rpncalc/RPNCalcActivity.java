package edu.brcc.maxfieldj.rpncalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.brcc.maxfieldj.rpncalcmodelcontroller.RPNCalcMath;

import java.util.ArrayList;
import java.util.List;

/**
 * An RPN calculator that does calculations and displays it on the TextView.
 * 02-27-2019
 * @author John Maxfield
 * @author Ramiro Rojas
 * @author Bunguiu Norales
 *
 */

public class RPNCalcActivity extends AppCompatActivity {
   private              TextView     mDisplay;             // The display for the RPNcalculator
   private              List<Button> buttons;              // An Arraylist of buttons.
   private static final int []       IDS_BUTTONS = {       // an array of the numbers and operators

       R.id.zero_button, R.id.one_button, R.id.two_button,R.id.three_button,R.id.four_button,
       R.id.five_button, R.id.six_button, R.id.seven_button, R.id.eight_button, R.id.nine_button,
       R.id.plus_button, R.id.minus_button, R.id.multy_button, R.id.divi_button, R.id.back_button,
       R.id.negative_button, R.id.enter_button, R.id.decimal_button, R.id.pi_button
   };


   @Override
   /**
    * Displays the button and when clicked it either displays it on TextView or does a operation
    * command.
    */
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState); // calls the super class
      setContentView(R.layout.activity_rpncalc);
      final RPNCalcGUIHelper helper = new RPNCalcGUIHelper(); // object from the RPNCalculator


     mDisplay = (TextView) findViewById(R.id.display_screen); // initialize mdisplay
     buttons = new ArrayList<Button>();                       // the arraylist of buttons

      /**
       * An enhance loop for ids and buttons where it sets it and displays it. Used some code from
       * stackoverflow https://stackoverflow.com/questions/15642104/array-of-buttons-in-android
       * but modified it to make it work for this for-enhance loop.
       */
     for(final int assignIdsToButtons : IDS_BUTTONS){
        final Button button = (Button)findViewById(assignIdsToButtons);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              mDisplay.setText(helper.addKey(button.getText().toString()));
           }
        });
        if(savedInstanceState != null) {
           savedInstanceState.getIntArray(IDS_BUTTONS.toString());
           mDisplay.setText((button.getText().toString()));
        }
     }

   }

   @Override
   protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);

      outState.putIntArray("number", IDS_BUTTONS);
   }

//   @Override
//   protected void onRestoreInstanceState(Bundle savedInstanceState) {
//      super.onRestoreInstanceState(savedInstanceState);
//      savedInstanceState.getIntArray( IDS_BUTTONS.toString());
//
//   }
}
