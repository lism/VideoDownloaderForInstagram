package com.zxmark.videodownloader.main;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.imobapp.videodownloaderforinstagram.R;
import com.zxmark.videodownloader.util.Globals;
import com.zxmark.videodownloader.util.LogUtil;

/**
 * Created by fanlitao on 17/6/15.
 */

public class VideoPlayActivity extends Activity {




    private VideoView mVideoView;


    private Uri mVideoPath;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);  //去掉 title
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.video_play);

        mVideoView = (VideoView) findViewById(R.id.videoView);

        //  4.  videoview 的设置

        mVideoPath  = getIntent().getData();

        //  4.1  获取MediaController对象，控制媒体播放，这里应该是获取 android.widget.MediaController 的对象
        android.widget.MediaController mediaController = new android.widget.MediaController(this);
        //  4.2  绑定到 Video View
        mVideoView.setMediaController(mediaController);
        //  4.3  设置 URI，播放源
//        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/hehe2.mp4");
//        videoView.setVideoURI(uri);
        LogUtil.e("play","mVideoPath=" + mVideoPath);
        mVideoView.setVideoURI(mVideoPath);
        //  4.4  开始播放
        mVideoView.start();
        //  4.5  获取焦点
        mVideoView.requestFocus();

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });


        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.setVideoURI(mVideoPath);
                mVideoView.start();
            }
        });
    }
}
