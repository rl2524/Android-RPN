package edu.brcc.maxfieldj.rpncalc;

import edu.brcc.maxfieldj.rpncalcmodelcontroller.RPNCalcMath;

/**
 * this class has the logic handling all of the calculator keys. no GUI code
 * moves logic specific to GUI away from the actual GUI code
 * 2-27-2019
 * @author John Maxfield
 * @author Ramiro Rojas
 * @author Bunguio Robles
 */
public class RPNCalcGUIHelper {
    private StringBuilder display;               // the string that is returned for the user to see
    private boolean       isNumberEntryFinished; // current num is completely entered
    private RPNCalcMath   calc;                  // MVC controller object

    /**
     * no-arg constructor. initialize display attributes
     */
    public RPNCalcGUIHelper() {
        calc = new RPNCalcMath();
        setDisplay("");
        isNumberEntryFinished = true;
    }

    /**
     * set/reset the string to be displayed
     * @param init the string to be displayed
     */
    private void setDisplay(String init) {
        init = (init == null) ? "" : init;
        display = new StringBuilder(init);
    }

   /**
    * does all the work and added try/catch blocks to prevent any errors from occurring and kicking
    * the user from the app.
    * @param key String key
    * @return the key and its function
    */
    public String addKey(String key) {
        double num;   // the double version of the current number

        switch (key) {
            case "+":
                try {
                    finishCurrentNum();
                    setDisplay("" + calc.add());
                    break;
                }catch (NumberFormatException e){
                    break;
                }


                case "-":
                try {
                    finishCurrentNum();
                    setDisplay("" + calc.subtract());
                    break;
                } catch (NumberFormatException e){
                    break;
                }


            case "*":
                try {
                    finishCurrentNum();
                    setDisplay("" + calc.multiply());
                    break;
                }catch (NumberFormatException e){
                    break;
                }


            case "/":
                try {
                    finishCurrentNum();
                    setDisplay("" + calc.divide());
                    break;
                }catch (NumberFormatException e){
                    break;
                }


            case "^":     // "enter" key
                try {     // e.g. "-" is invalid
                    num = Double.parseDouble(display.toString());
                    calc.enterNumber(num);
                    setDisplay("" + num);
                    isNumberEntryFinished = true;
                }
                catch (Exception e) {
                          // if it isn't a number, ignore the '^'
                }
                break;


            case "+/-":    // change sign
                if (isNumberEntryFinished) {
                    setDisplay("-");
                }
                else {
                    if (display.toString().length() > 0 && display.toString().charAt(0) == '-') {
                        display.deleteCharAt(0);
                    }
                    else {
                        display.insert(0, '-');
                    }
                }
                isNumberEntryFinished = false;
                break;


            case "<":
                try {

                if (!isNumberEntryFinished) {
                   display.deleteCharAt(display.length() - 1);
                }
                    break;
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        break;
                    }


            case "pi":
                if (!isNumberEntryFinished) {
                    try {
                        num = Double.parseDouble(display.toString());
                           // if a number was in display, enter it
                        calc.enterNumber(num);
                    }
                    catch (Exception e) {
                           // if it wasn't a number, ignore current display
                    }
                }
                setDisplay("" + Math.PI);
                calc.enterNumber(Math.PI);
                isNumberEntryFinished = true;
                break;


            case ".":      // only one . in a number!
                if (isNumberEntryFinished) {
                    setDisplay(".");
                    isNumberEntryFinished = false;
                    break;
                }
                if (display.toString().contains(".")) {
                    break;
                }
                           // else, fall through and add to display string

            default:       // the . (from case above) or any digit key
                if (isNumberEntryFinished) {
                    setDisplay("");
                }
                isNumberEntryFinished = false;
                display.append(key);
                break;
        }

        return display.toString();
    }

    /**
     * some code common to all the operator keys in addKey
     */
    private void finishCurrentNum() {
        double num;        // the double version of the current number

        if (!isNumberEntryFinished) {
            num = Double.parseDouble(display.toString());
            calc.enterNumber(num);
        }
        isNumberEntryFinished = true;
    }
}
