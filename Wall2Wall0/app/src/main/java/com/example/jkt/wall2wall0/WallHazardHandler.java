package com.example.jkt.wall2wall0;

import android.util.Log;

/**
 * Created by JDK on 5/6/2015.
 */

// WallHazardHandler class plans the placement of hazards on the left and right walls based on four
// arrays, and the number of walls blitted in the current game
// CHANGE TO REFLECT TIME PASSED
public class WallHazardHandler {
    
    public final int[] leftWallLowHazardArray = {3, 16, 48, 121, 172, 248, 300};//, 15, 19};
    public final int[] leftWallHighHazardArray = {8, 34, 62, 123, 131, 182, 276, 318};//, 12, 30};
    public final int[] rightWallLowHazardArray = {22, 41, 79, 82, 129, 200, 215, 242};//, 9, 26};
    public final int[] rightWallHighHazardArray = {29, 54, 116, 124, 127, 133, 156, 163, 215, 300};//, 20, 38};
    private int leftLowIndex = 0;
    private int leftHighIndex = 0;
    private int rightLowIndex = 0;
    private int rightHighIndex = 0;

    
    public boolean checkForLeftLowHazard(long current_time) {
        try {
            if (current_time >= (leftWallLowHazardArray[this.leftLowIndex] * 1000)) {
                this.leftLowIndex += 1;
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i("WallHazardHandler1", String.valueOf(e));
            return false;
        }
    }

    public boolean checkForLeftHighHazard(long current_time) {
        try {
            if (current_time >= (leftWallHighHazardArray[this.leftHighIndex] * 1000)) {
                this.leftHighIndex += 1;
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i("WallHazardHandler2", String.valueOf(e));
            return false;
        }
    }

    public boolean checkForRightLowHazard(long current_time) {
        try {
            if (current_time >= (rightWallLowHazardArray[this.rightLowIndex] * 1000)) {
                this.rightLowIndex += 1;
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i("WallHazardHandler3", String.valueOf(e));
            return false;
        }
    }

    public boolean checkForRightHighHazard(long current_time) {
        try {
            if (current_time >= (rightWallHighHazardArray[this.rightHighIndex] * 1000)) {
                this.rightHighIndex += 1;
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i("WallHazardHandler4", String.valueOf(e));
            return false;
        }
    }
    
    
}
