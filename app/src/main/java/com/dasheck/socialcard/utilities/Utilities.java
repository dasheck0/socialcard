package com.dasheck.socialcard.utilities;

import android.media.CamcorderProfile;
import android.os.Bundle;
import java.security.cert.CertificateNotYetValidException;

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

  public static Bundle bundleFrom(int cx, int cy, String caption, String body, int resourceId, int backgroundColor) {
    Bundle bundle = new Bundle();

    bundle.putInt(BUNDLE_KEY_CX, cx);
    bundle.putInt(BUNDLE_KEY_CY, cy);
    bundle.putString(BUNDLE_KEY_CAPTION, caption);
    bundle.putString(BUNDLE_KEY_TEXT, body);
    bundle.putInt(BUNDLE_KEY_RESOURCE_ID, resourceId);
    bundle.putInt(BUNDLE_KEY_BACKGROUND_COLOR, backgroundColor);

    return bundle;
  }

}
