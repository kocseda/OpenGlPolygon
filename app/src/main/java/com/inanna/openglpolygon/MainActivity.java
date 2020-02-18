package com.inanna.openglpolygon;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity {
    GLSurfaceView mView;

    @Override
    protected void onCreate ( Bundle icicle )
    {
        super.onCreate(icicle);
        createViewAndRender();
    }

    //create an opengl surface view and render through c++ class.
    protected void createViewAndRender() {
        pushToVertices();
        mView = new GLSurfaceView(getApplication());
        mView.setEGLContextClientVersion(2);
        mView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                PolygonJNI.onSurfaceCreated();
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                PolygonJNI.onSurfaceChanged();
            }

            //draw polygon with new color params every 10 sec.
            @Override
            public void onDrawFrame(GL10 gl) {
                float[] colorRgb = getRandomRgb();
                PolygonJNI.onDrawFrame(colorRgb[0], colorRgb[1], colorRgb[2]);
            }
        });
        setContentView(mView);
    }

    //push at least three vertices to create at least one triangle.
    protected void pushToVertices() {
        PolygonJNI.pushToVertices(-1.0f, 0.0f);
        PolygonJNI.pushToVertices(-1.0f , -1.0f);
        PolygonJNI.pushToVertices(1.0f , -1.0f);
        PolygonJNI.pushToVertices(1.0f , 0.0f);
        PolygonJNI.pushToVertices(1.0f , 1.0f);
    }

    //create random color within 0-1 range.
    protected float[] getRandomRgb() {
        Random rnd = new Random();
        float colorRed = rnd.nextFloat();
        float colorGreen = rnd.nextFloat();
        float colorBlue = rnd.nextFloat();
        float[] rgb = {colorRed, colorGreen, colorBlue};
        return  rgb;
    }
}
