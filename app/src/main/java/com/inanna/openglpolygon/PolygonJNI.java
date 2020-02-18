package com.inanna.openglpolygon;
import android.content.res.AssetManager;

public class PolygonJNI {
    static {
        System.loadLibrary("polygon");
    }

    public static native void onSurfaceCreated();
    public static native void onSurfaceChanged();
    public static native void onDrawFrame(float red, float green, float blue);
    public static native void pushToVertices(float x, float y);
}
