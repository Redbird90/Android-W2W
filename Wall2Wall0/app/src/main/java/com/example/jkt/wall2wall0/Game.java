// NOT BEING USED, THE GAME CLASS HAS BEEN MOVED TO MAIN ACTIVITY

package com.example.jkt.wall2wall0;

import android.content.Context;

public interface Game {
	
	public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
    
    public Context getContext();
    
    public int getScreenWidth();
    
    public int getScreenHeight();
    
    public int getScreenOrientation();
    
}