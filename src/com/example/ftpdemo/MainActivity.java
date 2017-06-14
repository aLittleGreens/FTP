package com.example.ftpdemo;

import java.io.IOException;

import com.example.ftpdemo.ContinueFTP.DownloadStatus;
import com.example.ftpdemo.ContinueFTP.UploadStatus;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

@SuppressLint("SdCardPath")
public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onUpLoad(View view) {

		new Thread(new Runnable() {

			private ContinueFTP continueFTP;

			@Override
			public void run() {
				try {
					continueFTP = new ContinueFTP();
					boolean connect = continueFTP.connect("172.16.6.134",
							Integer.parseInt("21"), "admin", "admin");
					if(connect){
						UploadStatus upload = continueFTP.upload(
								"/sdcard/download/JNI.7z", "/fff/JNI.7z");
						System.out.println("upload" + upload);
					}
					continueFTP.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	public void download(View view) {

		new Thread(new Runnable() {
			private ContinueFTP continueFTP;

			@Override
			public void run() {
				try {
					continueFTP = new ContinueFTP();
					boolean connect = continueFTP.connect("172.16.6.134",
							Integer.parseInt("21"), "admin", "admin");
					if(connect){
						DownloadStatus download = continueFTP.download(
								"/fff/JNI.7z", "/sdcard/download/jni2.7z", "p");
						System.out.println("download:" + download);
					}
					continueFTP.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}
}
