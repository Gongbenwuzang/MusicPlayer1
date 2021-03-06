package com.example.yuanyuan.musicplayer;


import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class Findmusic {
    public List<Music> getmusics(ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        //解析所有歌曲的信息，默认顺序
        List<Music> musics = new ArrayList<Music>();
        for (int i = 0; i < cursor.getCount(); i++) {  //遍历cursor中的所有行
            Music music = new Music();     //新建一个歌曲对象,将从cursor里读出的信息存放进去,直到取完cursor里面的内容为止.
            cursor.moveToNext();
            String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));	//文件路径
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片
            long album_id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)); //唱片图片ID
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
            if (isMusic != 0 && duration/(3000 * 60) >= 1) {		//只把3分钟以上的音乐添加到集合当中
                music.setTitle(title);
                music.setArtist(artist);
                music.setDuration(duration);
                music.setUrl(url);
                //  music.setAlbum_id(album_id);
                musics.add(music);
            }
        }
        cursor.close();
        return musics;
    }


}
