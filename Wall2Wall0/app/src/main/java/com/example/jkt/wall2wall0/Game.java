// NOT BEING USED, THE GAME CLASS HAS BEEN MOVED TO MAIN ACTIVITY

package com.example.jkt.wall2wall0;

import android.content.Context;

public interface Game {

    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();

}
