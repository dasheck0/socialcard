package com.dasheck.socialcard.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.dasheck.socialcard.models.Color;
import com.dasheck.socialcard.models.SocialData;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dasheck on 12/6/15.
 */
public class Utilities {

  public static final String BUNDLE_KEY_CX = "cxKey";
  public static final String BUNDLE_KEY_CY = "cyKey";
  public static final String BUNDLE_KEY_CAPTION = "captionKey";
  public static final String BUNDLE_KEY_TEXT = "textKey";
  public static final String BUNDLE_KEY_RESOURCE_ID = "resourceIdKey";
  public static final String BUNDLE_KEY_BACKGROUND_COLOR = "backgroundColorKey";
  public static final String BUNDLE_KEY_URL = "urlKey";

  public static Bundle bundleFrom(int cx, int cy, SocialData socialData) {
    Bundle bundle = new Bundle();

    bundle.putInt(BUNDLE_KEY_CX, cx);
    bundle.putInt(BUNDLE_KEY_CY, cy);
    bundle.putString(BUNDLE_KEY_CAPTION, socialData.getCaption());
    bundle.putString(BUNDLE_KEY_TEXT, socialData.getBody());
    bundle.putString(BUNDLE_KEY_RESOURCE_ID, socialData.getResourceFilename());
    bundle.putInt(BUNDLE_KEY_BACKGROUND_COLOR, toAndroidColor(socialData.getColor()));
    bundle.putString(BUNDLE_KEY_URL, socialData.getUrl());

    return bundle;
  }

  public static int toAndroidColor(Color color) {
    return android.graphics.Color.argb(color.getA(), color.getR(), color.getG(), color.getB());
  }

  public static Color toColor(int color) {
    int r = android.graphics.Color.red(color);
    int g = android.graphics.Color.green(color);
    int b = android.graphics.Color.blue(color);
    int a = android.graphics.Color.alpha(color);

    return new Color(a, r, g, b);
  }

  public static Bitmap getBitmapFromAsset(Context context, String filePath) {
    AssetManager assetManager = context.getAssets();

    InputStream istr;
    Bitmap bitmap = null;
    try {
      istr = assetManager.open(filePath);
      bitmap = BitmapFactory.decodeStream(istr);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return bitmap;
  }
}
