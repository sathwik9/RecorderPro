package ece.wisc.recorder;

import android.media.MediaPlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Serializable {
    private ArrayList<Note> song;
    private long start_time;
    private String title;


    public Record(){
        song = new ArrayList<>();
        start_time = System.currentTimeMillis();
        title = "";
    }

    public void add_note(Integer mp3_file){
         song.add(new Note(mp3_file, System.currentTimeMillis()));
    }

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public String getTitle(){
        return title;
    }

    public String toString(){
        return "Title: " + title + "Song: " + song;
    }

    public ArrayList<Note> getSong(){return song;}

    public long getStartTime(){return start_time;}

}
