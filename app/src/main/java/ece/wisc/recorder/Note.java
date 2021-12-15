package ece.wisc.recorder;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer mp3_file;
    long time;
    public Note(Integer set_mp3, long set_time){
        mp3_file = set_mp3;
        time = set_time;
    }

    public Integer getMp3_file(){return mp3_file;}
}
