package com.example.testmedia;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements  OnClickListener, OnPreparedListener, OnCompletionListener {
	
	private MediaPlayer mMediaPlayer[];
	
	private MediaPlayer mediaPlayer = new MediaPlayer();
	
	private AudioManager mAudioManager;
	
	private SeekBar seekbar1;
	private SeekBar seekbar2;
	
	
	private Button button1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMediaPlayer = new MediaPlayer[2];
		
		mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		
		seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
		
		mMediaPlayer[0] = new MediaPlayer();
		mMediaPlayer[1] = new MediaPlayer();
		
		mMediaPlayer[0].create(this, R.raw.do_neba);
		mMediaPlayer[1].create(this, R.raw.ne_do_neba);
		
		AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.do_neba);
		try {
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		afd = getResources().openRawResourceFd(R.raw.ne_do_neba);
		try {
			mMediaPlayer[0].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mMediaPlayer[0].setOnPreparedListener(this);
		mMediaPlayer[0].prepareAsync();
		
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.prepareAsync();
		
		int i = 0;
		
		mMediaPlayer[0].setOnPreparedListener(this);
		
		mMediaPlayer[0].setLooping(true);
		mMediaPlayer[1].setLooping(true);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		
		seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener () {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(arg1, arg1);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		seekbar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				float set = (float) progress/100;
				Log.v("set", set+"");
				mMediaPlayer[0].setVolume(set,set);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
			case R.id.button1:
				if (mediaPlayer.isPlaying() && mMediaPlayer[0].isPlaying()) {
					mediaPlayer.pause();
					mMediaPlayer[0].pause();
				} else {
					mMediaPlayer[0].seekTo(0);
					mMediaPlayer[0].start();
					
					mediaPlayer.seekTo(0);
					mediaPlayer.start();
					
				}
					
				break;
		}
	}



	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.start();
	}



	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stud
	}
	
	protected void setVolume(int volume) {
        {
            if (volume == 1) {
                volume = 2;
            }
            try {
                float vol = ((float) volume / 100);
                mediaPlayer.setVolume(vol, vol);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
