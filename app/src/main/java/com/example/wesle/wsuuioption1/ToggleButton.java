package com.example.wesle.wsuuioption1;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Toast;

/**
 * this class is made so we have a 3 state button, and so that we can add attributes to buttons. The default button
 * does not work with the setBackgrounFfilter on JellyBean
 */
public class ToggleButton extends Button {

    // Keeps track of the current state, 0, 1, or 2
    private int _state;
    public static boolean started = false;

    public boolean getStarted(){
        return started;
    }

    public int getState()
    {
        return _state;
    }

    // Get the attributes created in attrs.xml
    private static final int[] STATE_ONE_SET =
            {
                    R.attr.state_one
            };

    private static final int[] STATE_TWO_SET =
            {
                    R.attr.state_two
            };

    /*private static final int[] STATE_THREE_SET =
            {
                    R.attr.state_three
            };*/

    // Generate the drawable needed for the current state
    @Override
    protected int[] onCreateDrawableState(int extraSpace)
    {
        // Add the number of states you have
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 3);

        if(_state == 0)
        {
            mergeDrawableStates(drawableState, STATE_ONE_SET);
        }
        else if(_state == 1)
        {
            mergeDrawableStates(drawableState, STATE_TWO_SET);
        }
        /*else if(_state == 2)
        {
            mergeDrawableStates(drawableState, STATE_THREE_SET);
        }*/

        return drawableState;
    }

    // Constructors
    public ToggleButton(Context context)
    {
        super(context);

        // Set the default state and text
        _state = 0;
        //this.setText("1");
    }

    public ToggleButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Set the default state and text
        _state = 0;
        //this.setText("1");
    }

    public ToggleButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        // Set the default state and text
        _state = 0;
        //this.setText("1");
    }

    @Override
    public boolean performClick()
    {
        // Move to the next state
        if(started)
            nextState();
        else{
            showShortToast("Start NOT before clicking errors");
        }

        return super.performClick();
    }

    // Increases state, or loops to 0
    public void nextState()
    {
        _state++;

        // Loop around if at the last state
        if(_state > 1) //if code is changed to work with three states the the _state > 1 to > 2
        {
            _state = 0;
        }

        setButtonText();
        showShortToast("ToggleState: " + _state);
    }

    // Set the text displayed on the button
    private void setButtonText()
    {
        switch(_state)
        {
            case 0:
                this.setText("1");
                this.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0xA9A9A9));
                break;
            case 1:
                this.setText("2");
                this.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x981e32));
                break;
            /*case 2: this.setText("3");
                break;*/
            default: this.setText("N/A"); // Should never happen, but just in case
                break;
        }
    }

    // A method just to make using Toasts easier
    private void showShortToast(String s)
    {
        Toast.makeText(this.getContext(), s, Toast.LENGTH_SHORT).show();
    }



}
