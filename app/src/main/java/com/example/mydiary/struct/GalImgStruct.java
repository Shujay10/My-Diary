package com.example.mydiary.struct;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class GalImgStruct {

    Bitmap image;

    public GalImgStruct() {
    }

    public GalImgStruct(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
