package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    //vivian
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!OpenCVLoader.initDebug()){
            Log.d("test","No OpenCV");
        }
        else{
            Log.d("test", "OpenCV is fine");
        }


        Log.d("viv", "00");
        final ImageView imageView = findViewById(R.id.imageView);
        Log.d("viv", "0");


        Button dbtn = findViewById(R.id.button);
        Log.d("viv", "1");



        dbtn.setOnClickListener(new View.OnClickListener() {

            Bitmap orig = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

            Point firstPoint = new Point(orig.getWidth()/4, orig.getHeight()/4);
            Point secondPoint = new Point(orig.getWidth()/4, orig.getHeight()-(orig.getHeight()/4));
            Point thirdPoint = new Point(orig.getWidth()-orig.getWidth()/4, orig.getHeight()-orig.getHeight()/4);
            Point fourthPoint = new Point(orig.getWidth()-orig.getWidth()/4, orig.getHeight()/4);

            Scalar lineColor = new Scalar(255,0,0,255);
            int lineWidth = 15;
            int count = 0;



            @Override
            public void onClick(View view) {
                Log.d("viv", "2");
                count++;

                orig = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Mat img = new Mat(orig.getWidth(), orig.getHeight(), CvType.CV_8UC4);
                Utils.bitmapToMat(orig, img);

                switch(count) {
                    case 1:
                        Imgproc.line(img,firstPoint, secondPoint, lineColor, lineWidth);
                        break;
                    case 2:
                        Imgproc.line(img,secondPoint, thirdPoint, lineColor, lineWidth);
                        break;
                    case 3:
                        Imgproc.line(img,thirdPoint, fourthPoint, lineColor, lineWidth);
                        break;
                    case 4:
                        Imgproc.line(img,fourthPoint, firstPoint, lineColor, lineWidth);
                        break;
                    default:
                        Log.d("viv", "n/a");
                }


                Bitmap bitmap = Bitmap.createBitmap(img.width(), img.height(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(img, bitmap);
                imageView.setImageBitmap(bitmap);


            }
        });
    }
}
