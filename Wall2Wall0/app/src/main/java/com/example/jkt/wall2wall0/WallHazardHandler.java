package com.example.jkt.wall2wall0;

import android.util.Log;

/**
 * Created by JDK on 5/6/2015.
 */
public class WallHazardHandler {
    
    public final int[] leftWallLowHazardArray = {3, 15, 19};
    public final int[] leftWallHighHazardArray = {8, 12, 30};
    public final int[] rightWallLowHazardArray = {6, 9, 26};
    public final int[] rightWallHighHazardArray = {10, 20, 38};
    private int leftLowIndex = 0;
    private int leftHighIndex = 0;
    private int rightLowIndex = 0;
    private int rightHighIndex = 0;

    
    public boolean checkForLeftLowHazard(int wall_num) {
        try {
            if (wall_num == leftWallLowHazardArray[this.leftLowIndex]) {
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

    public boolean checkForLeftHighHazard(int wall_num) {
        try {
            if (wall_num == leftWallHighHazardArray[this.leftHighIndex]) {
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

    public boolean checkForRightLowHazard(int wall_num) {
        try {
            if (wall_num == rightWallLowHazardArray[this.rightLowIndex]) {
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

    public boolean checkForRightHighHazard(int wall_num) {
        try {
            if (wall_num == rightWallHighHazardArray[this.rightHighIndex]) {
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
