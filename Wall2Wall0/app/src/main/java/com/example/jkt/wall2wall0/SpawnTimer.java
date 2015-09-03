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

// SpawnTimer class is used to read data from the excel file "Enemy_Spawning_Sheet_2.xlsx" and
// parses that data into individual SpawnEvents which are added to spawnEventArray, which is
// read by GameScreen class to handle all enemy spawns
public class SpawnTimer {
    private final ArrayList<String> enemyArray = new ArrayList<String>();
    public final ArrayList<SpawnEvent> spawnEventArray = new ArrayList<SpawnEvent>();


    // createEnemyArray method is used to add raw string values from excel to ArrayList enemyArray
    public void createEnemyArray() {
/*

        enemyArray.add("(00),(00),(01,160x),(00),(00),(00),(00),(00),002.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(01,280x),(00),(00),004.0s");
        enemyArray.add("(00),(01,120x),(00),(00),(00),(00),(00),(00),006.0s");
        enemyArray.add("(00),(00),(01,160x),(00),(00),(00),(00),(00),008.0s");
        enemyArray.add("(00),(01,120x),(00),(00),(00),(00),(00),(00),010.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(03,320x),(00),012.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(01,280x),(00),(00),014.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(03,320x),(00),016.0s");
        enemyArray.add("(00),(01,120x),(00),(03,200x),(00),(00),(00),(00),018.0s");
        enemyArray.add("(00),(00),(00),(00),(01,240x),(00),(00),(00),020.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(03,360x),022.0s");
        enemyArray.add("(00),(00),(00),(00),(03,240x),(00),(00),(00),024.0s");
        enemyArray.add("(00),(01,120x),(00),(00),(00),(00),(00),(00),026.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(03,320x),(00),028.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(01,280x),(00),(00),030.0s");
        enemyArray.add("(00),(00),(03,160x),(00),(00),(00),(00),(00),032.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),034.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),036.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(04,280x),(00),(00),038.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),040.0s");
        enemyArray.add("(00),(00),(03,160x),(00),(00),(00),(00),(00),042.0s");
        enemyArray.add("(00),(00),(00),(00),(01,240x),(00),(00),(00),044.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),046.0s");
        enemyArray.add("(00),(00),(00),(01,200x),(00),(00),(00),(00),048.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),050.0s");
        enemyArray.add("(00),(03,120x),(00),(00),(00),(00),(00),(00),052.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),054.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(04,320x),(00),056.0s");
        enemyArray.add("(03,085x),(00),(00),(00),(00),(00),(00),(00),058.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),060.0s");
        enemyArray.add("(00),(00),(04,160x),(00),(00),(03,280x),(00),(00),062.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(01,320x),(00),064.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),066.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),068.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(05,320x),(00),070.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),072.0s");
        enemyArray.add("(01,085x),(00),(00),(00),(00),(00),(00),(00),074.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(05,360x),076.0s");
        enemyArray.add("(00),(03,120x),(00),(00),(00),(03,280x),(00),(00),078.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),080.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),082.0s");
        enemyArray.add("(00),(00),(04,160x),(00),(04,240x),(00),(00),(00),084.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),086.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),088.0s");
        enemyArray.add("(05,085x),(00),(00),(00),(00),(00),(00),(05,360x),090.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),092.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),094.0s");
        enemyArray.add("(00),(01,120x),(00),(00),(00),(00),(01,320x),(00),096.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),098.0s");
        enemyArray.add("(00),(00),(00),(04,200x),(00),(00),(00),(00),100.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),102.0s");
        enemyArray.add("(00),(00),(00),(00),(04,240x),(00),(00),(00),104.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),106.0s");
        enemyArray.add("(00),(04,120x),(00),(00),(00),(00),(00),(00),108.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),110.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(05,360x),112.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),114.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),116.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),118.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),120.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),122.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),124.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),126.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),128.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),130.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),132.0s");
        enemyArray.add("(00),(00),(06,160x),(00),(00),(00),(00),(00),134.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),136.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(06,280x),(00),(00),138.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),140.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(06,360x),142.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),144.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),146.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(07,360x),148.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),150.0s");
        enemyArray.add("(00),(00),(07,160x),(00),(06,240x),(00),(00),(00),152.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),154.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),156.0s");
        enemyArray.add("(00),(00),(06,160x),(00),(07,240x),(00),(00),(00),158.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),160.0s");
        enemyArray.add("(00),(00),(00),(08,200x),(00),(00),(00),(00),162.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),164.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),166.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(08,320x),(00),168.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),170.0s");
        enemyArray.add("(08,085x),(00),(00),(00),(00),(00),(00),(00),172.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),174.0s");
        enemyArray.add("(00),(00),(06,160x),(00),(00),(00),(00),(00),176.0s");
        enemyArray.add("(00),(00),(00),(00),(07,240x),(00),(00),(00),178.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),180.0s");
        enemyArray.add("(00),(08,120x),(00),(00),(00),(00),(00),(00),182.0s");
        enemyArray.add("(00),(00),(00),(07,200x),(00),(00),(00),(00),184.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),186.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),188.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(09,280x),(00),(00),190.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),192.0s");
        enemyArray.add("(00),(06,120x),(00),(00),(00),(00),(00),(00),194.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),196.0s");
        enemyArray.add("(00),(00),(00),(00),(09,240x),(00),(00),(00),198.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),200.0s");
        enemyArray.add("(00),(00),(07,160x),(07,200x),(07,240x),(00),(00),(00),202.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),204.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),206.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),208.0s");
        enemyArray.add("(00),(00),(00),(08,200x),(07,240x),(08,280x),(00),(00),210.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),212.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),214.0s");
        enemyArray.add("(09,085x),(00),(00),(00),(00),(00),(00),(00),216.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(08,360x),218.0s");
        enemyArray.add("(09,085x),(00),(00),(00),(00),(00),(00),(00),220.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),222.0s");
        enemyArray.add("(00),(00),(06,160x),(00),(00),(00),(00),(00),224.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),226.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),228.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(06,280x),(00),(00),230.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),232.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(07,320x),(00),234.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),236.0s");
        enemyArray.add("(00),(08,120x),(00),(00),(00),(00),(00),(00),238.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(09,280x),(00),(00),240.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),242.0s");
        enemyArray.add("(00),(00),(09,160x),(00),(00),(00),(00),(00),244.0s");
        enemyArray.add("(00),(00),(00),(00),(08,240x),(00),(00),(00),246.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(09,320x),(00),248.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),250.0s");
        enemyArray.add("(06,085x),(00),(00),(00),(00),(00),(00),(00),252.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),254.0s");
        enemyArray.add("(00),(00),(10,160x),(00),(00),(00),(00),(00),256.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),258.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(10,280x),(00),(00),260.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),262.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),264.0s");
        enemyArray.add("(00),(00),(00),(10,200x),(00),(00),(00),(00),266.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),268.0s");
        enemyArray.add("(00),(06,120x),(00),(00),(00),(00),(00),(00),270.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(06,320x),(00),272.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),274.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),276.0s");
        enemyArray.add("(00),(00),(00),(07,200x),(00),(00),(00),(00),278.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),280.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(10,280x),(00),(00),282.0s");
        enemyArray.add("(00),(08,120x),(07,160x),(00),(00),(00),(00),(00),284.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),286.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),288.0s");
        enemyArray.add("(00),(00),(00),(09,200x),(00),(00),(00),(00),290.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),292.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(09,280x),(00),(00),294.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),296.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(09,360x),298.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),300.0s");
        enemyArray.add("(00),(00),(06,160x),(00),(00),(00),(00),(00),302.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),304.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(07,280x),(00),(00),306.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),308.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(08,320x),(00),310.0s");
        enemyArray.add("(00),(10,120x),(00),(00),(00),(00),(00),(00),312.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),314.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),316.0s");
        enemyArray.add("(00),(00),(00),(00),(07,240x),(00),(00),(00),318.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),320.0s");
        enemyArray.add("(00),(00),(10,160x),(00),(00),(00),(00),(00),322.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(06,320x),(00),324.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),326.0s");
        enemyArray.add("(00),(00),(00),(08,200x),(00),(00),(00),(00),328.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(07,280x),(00),(00),330.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),332.0s");
        enemyArray.add("(09,085x),(00),(00),(00),(00),(00),(00),(00),334.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),336.0s");
        enemyArray.add("(00),(00),(08,160x),(00),(00),(00),(00),(00),338.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(09,360x),340.0s");
        enemyArray.add("(00),(00),(00),(00),(09,240x),(00),(00),(00),342.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),344.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(08,360x),346.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),348.0s");
        enemyArray.add("(07,085x),(00),(00),(00),(00),(00),(00),(00),350.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),352.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(07,360x),354.0s");
        enemyArray.add("(00),(00),(00),(00),(06,240x),(00),(00),(00),356.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),358.0s");
        enemyArray.add("(07,085x),(00),(00),(00),(00),(00),(00),(00),360.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),362.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),364.0s");
        enemyArray.add("(06,085x),(00),(00),(00),(00),(00),(00),(00),366.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),368.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(06,360x),370.0s");
        enemyArray.add("(00),(10,120x),(00),(00),(00),(00),(00),(00),372.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(10,320x),(00),374.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),376.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),378.0s");
        enemyArray.add("(00),(00),(00),(10,200x),(00),(00),(00),(00),380.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),382.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(07,320x),(00),384.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),386.0s");
        enemyArray.add("(00),(00),(00),(08,200x),(00),(00),(00),(00),388.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),390.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),392.0s");
        enemyArray.add("(09,085x),(00),(00),(00),(00),(00),(00),(09,360x),394.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),396.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),398.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),400.0s");
*/

        
        
        // IF (TESTING == TRUE) {
        enemyArray.add("(00),(00),(00),(00),(00),(10,200x),(00),(00),002.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),004.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),006.0s");
        enemyArray.add("(00),(00),(08,160x),(00),(00),(00),(00),(00),008.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),010.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),012.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),014.0s");
        enemyArray.add("(00),(00),(00),(00),(06,160x),(00),(00),(00),016.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(07,320x),(00),018.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),020.0s");
        enemyArray.add("(00),(00),(09,160x),(00),(00),(00),(00),(00),022.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),024.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(08,280x),(00),(00),026.0s");
        enemyArray.add("(00),(09,280x),(00),(00),(00),(00),(00),(00),028.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(10,200x),(00),(00),030.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),032.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),034.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),036.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),038.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),040.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),042.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),044.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),046.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),048.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),050.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),052.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),054.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),056.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),058.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),060.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),062.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),064.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),066.0s");
        enemyArray.add("(00),(00),(10,160x),(00),(00),(10,280x),(00),(00),068.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),070.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),072.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),074.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),076.0s");
        enemyArray.add("(00),(00),(10,160x),(00),(00),(10,280x),(00),(00),078.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),080.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),082.0s");
        enemyArray.add("(00),(00),(10,160x),(00),(00),(10,280x),(00),(00),084.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),086.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),088.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),090.0s");
        enemyArray.add("(00),(00),(10,160x),(00),(00),(10,280x),(00),(00),092.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),094.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),096.0s");
        enemyArray.add("(00),(00),(00),(00),(00),(00),(00),(00),098.0s");
    //}

    }

    // parseEnemyArray method is used to read the raw strings in enemyArray and convert them to
    // SpawnEvent Objects which can be read by the GameScreen class
    public ArrayList<SpawnEvent> parseEnemyArray() {
        int enemy_type;
        int enemy_location;
        float time_value;
        int s_location;

        for (int i=0; i < enemyArray.size(); i++) {
            int starting_index = 0;
            int possible_enemies = enemyArray.get(i).length() -
                    enemyArray.get(i).replace("(", "").length();
            ArrayList<String> time_enemy_line_info = new ArrayList<String>();
            int end_index = enemyArray.get(i).length();
            //Log.i("SpawnTimer1", String.valueOf(possible_enemies));

            s_location = enemyArray.get(i).indexOf("s");
            time_value = Float.parseFloat(
                    (enemyArray.get(i).substring(s_location - 5, s_location)));

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
