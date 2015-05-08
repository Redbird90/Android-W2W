package com.example.jkt.wall2wall0;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 4/21/2015.
 */
public class SpawnTimer {
    private final ArrayList<String> enemyArray = new ArrayList();
    public final ArrayList<SpawnEvent> spawnEventArray = new ArrayList();


    public void createEnemyArray() {

              enemyArray.add("(00),(00),(01,160x),(00),(00),(00),(00),(00),002s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),004s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(03,320x),(00),006s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),008s");
              enemyArray.add("(00),(03,120x),(00),(00),(00),(00),(00),(00),010s");
              enemyArray.add("(00),(00),(02,160x),(00),(00),(00),(00),(00),012s");
              enemyArray.add("(00),(00),(00),(00),(00),(04,280x),(00),(00),014s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),016s");
              enemyArray.add("(00),(05,120x),(00),(00),(00),(00),(00),(00),018s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),020s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(04,360x),022s");
              enemyArray.add("(06,085x),(00),(00),(00),(00),(00),(00),(00),024s");
              enemyArray.add("(00),(07,120x),(00),(00),(00),(00),(00),(00),026s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(08,360x),028s");
              enemyArray.add("(00),(00),(00),(00),(00),(09,280x),(00),(00),030s");
              enemyArray.add("(00),(00),(10,160x),(00),(00),(00),(00),(00),032s");
              enemyArray.add("(00),(06,120x),(00),(00),(00),(00),(00),(00),034s");
              enemyArray.add("(00),(00),(00),(07,200x),(00),(00),(00),(00),036s");
              enemyArray.add("(00),(00),(00),(00),(00),(08,280x),(00),(00),038s");
              enemyArray.add("(09,085x),(00),(00),(00),(00),(00),(00),(00),040s");
              enemyArray.add("(00),(00),(09,160x),(00),(00),(00),(00),(00),042s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),044s");
              enemyArray.add("(00),(02,120x),(00),(00),(00),(00),(00),(00),046s");
              enemyArray.add("(00),(00),(00),(00),(00),(02,280x),(00),(00),048s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),050s");
              enemyArray.add("(00),(01,120x),(00),(00),(00),(00),(00),(02,360x),052s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),054s");
              enemyArray.add("(03,085x),(00),(00),(00),(00),(00),(00),(00),056s");
              enemyArray.add("(00),(02,120x),(00),(00),(00),(00),(00),(00),058s");
              enemyArray.add("(00),(00),(00),(00),(03,240x),(00),(02,320x),(00),060s");
              enemyArray.add("(00),(00),(04,160x),(00),(00),(03,280x),(00),(00),062s");
              enemyArray.add("(03,085x),(00),(00),(00),(00),(00),(01,320x),(00),064s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(02,320x),(00),066s");
              enemyArray.add("(00),(00),(00),(00),(04,240x),(00),(03,320x),(00),068s");
              enemyArray.add("(00),(00),(02,160x),(00),(00),(05,280x),(00),(00),070s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),072s");
              enemyArray.add("(01,085x),(00),(04,160x),(00),(00),(00),(00),(00),074s");
              enemyArray.add("(00),(00),(00),(00),(00),(05,280x),(00),(02,360x),076s");
              enemyArray.add("(00),(03,120x),(00),(03,200x),(00),(03,280x),(00),(00),078s");
              enemyArray.add("(00),(00),(04,160x),(00),(04,240x),(00),(00),(00),080s");
              enemyArray.add("(05,085x),(00),(00),(00),(00),(00),(00),(05,360x),082s");
              enemyArray.add("(00),(01,120x),(00),(00),(00),(00),(01,320x),(00),084s");
              enemyArray.add("(00),(00),(00),(04,200x),(00),(02,280x),(00),(00),086s");
              enemyArray.add("(00),(00),(02,160x),(00),(04,240x),(00),(00),(00),088s");
              enemyArray.add("(00),(03,120x),(00),(00),(00),(00),(03,320x),(00),090s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),092s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),094s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),096s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),098s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),100s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),102s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),104s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),106s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),108s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),110s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),112s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),114s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),116s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),118s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),120s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),122s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),124s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),126s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),128s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),130s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),132s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),134s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),136s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),138s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),140s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),142s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),144s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),146s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),148s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),150s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),152s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),154s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),156s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),158s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),160s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),162s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),164s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),166s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),168s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),170s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),172s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),174s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),176s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),178s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),180s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),182s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),184s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),186s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),188s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),190s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),192s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),194s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),196s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),198s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),200s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),202s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),204s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),206s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),208s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),210s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),212s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),214s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),216s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),218s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),220s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),222s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),224s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),226s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),228s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),230s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),232s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),234s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),236s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),238s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),240s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),242s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),244s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),246s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),248s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),250s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),252s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),254s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),256s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),258s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),260s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),262s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),264s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),266s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),268s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),270s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),272s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),274s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),276s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),278s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),280s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),282s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),284s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),286s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),288s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),290s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),292s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),294s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),296s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),298s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),300s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),302s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),304s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),306s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),308s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),310s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),312s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),314s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),316s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),318s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),320s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),322s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),324s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),326s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),328s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),330s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),332s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),334s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),336s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),338s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),340s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),342s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),344s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),346s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),348s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),350s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),352s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),354s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),356s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),358s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),360s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),362s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),364s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),366s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),368s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),370s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),372s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),374s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),376s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),378s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),380s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),382s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),384s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),386s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),388s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),390s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),392s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),394s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),396s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),398s");
              enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),400s");

    }


    public ArrayList<SpawnEvent> parseEnemyArray() {
        int enemy_type;
        int enemy_location;
        int time_value;
        int s_location;

        for (int i=0; i < enemyArray.size(); i++) {
            int starting_index = 0;
            int possible_enemies = enemyArray.get(i).length() -
                    enemyArray.get(i).replace("(", "").length();
            ArrayList<String> time_enemy_line_info = new ArrayList();
            int end_index = enemyArray.get(i).length();
            //Log.i("SpawnTimer1", String.valueOf(possible_enemies));

            s_location = enemyArray.get(i).indexOf("s");
            time_value = Integer.parseInt
                    (enemyArray.get(i).substring(s_location-3, s_location));

            for (int x=0; x < possible_enemies; x++) {
                int parenth_start = enemyArray.get(i).substring
                        (starting_index, end_index).indexOf("(")+starting_index;
                int parenth_end = enemyArray.get(i).substring
                        (starting_index, end_index).indexOf(")")+starting_index;
                String current_enemy_info = enemyArray.get(i).substring
                        (parenth_start + 1, parenth_end);

                time_enemy_line_info.add(current_enemy_info);
                starting_index = parenth_end + 1;

                //Log.i("SpawnTimer2", String.valueOf(parenth_start));
                //Log.i(String.valueOf(starting_index), current_enemy_info);
            }

            for (int y=0; y < time_enemy_line_info.size(); y++) {

                if (time_enemy_line_info.get(y).length()>3) {

                    //Log.i("SpawnTimer3", "Starting addition");
                    enemy_type = Integer.parseInt
                            (time_enemy_line_info.get(y).substring(0,2));
                    enemy_location = Integer.parseInt
                            (time_enemy_line_info.get(y).substring(3,6));

                    //Log.i("EnemyATTR", (String.valueOf(enemy_type)+String.valueOf(enemy_location)+String.valueOf(time_value)));

                    SpawnEvent enemy_to_create =
                            new SpawnEvent(enemy_type, enemy_location, time_value);

                    spawnEventArray.add(enemy_to_create);
                    //Log.i("SpawnTimer4", "Enemy ADDED"+String.valueOf(time_value));
                }
            }
        }
        Log.i("SpawnTimer", "Parsing complete, returning...");
        return spawnEventArray;
    }

            //       enemyArray.add("00),(03,120x),(00),(03,200x),(00),(03,280x),(00),(00),078s");


/*    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {

            // Writes a file called .enemydata to the SD Card
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".enemydata");));



            // Line by line ("\n" creates a new line), write the value of each variable to the file.

            out.write(Boolean.toString(soundEnabled));
            out.write("\n");;

            // Uses a for loop to save 5 numbers to the save file. INCORRECT NOTE
            out.write(Integer.toString(highScore));
            out.write("\n");;

            // This section handles errors in file management!

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }*/

}
