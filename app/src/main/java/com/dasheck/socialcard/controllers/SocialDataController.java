package com.dasheck.socialcard.controllers;

import android.content.Context;
import com.dasheck.socialcard.models.SocialData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by dasheck on 12/6/15.
 */
public class SocialDataController {

  public static final String GITHUB = "github";
  public static final String TWITTER = "twitter";

  private Context context;
  private Map<String, SocialData> socialData;

  public SocialDataController(Context context) {
    this.context = context;
  }

  public void loadSocialData() {
    Gson gson = new Gson();
    String json = loadFileContentFromAsset("socialdata.json");
    Type type = new TypeToken<Map<String, SocialData>>(){}.getType();

    socialData = gson.fromJson(json, type);
  }

  public SocialData getSocialDataFor(String socialDataId) {
    return socialData.get(socialDataId);
  }

  private String loadFileContentFromAsset(String filename) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(
          new InputStreamReader(context.getAssets().open(filename), "UTF-8"));

      StringBuilder builder = new StringBuilder();

      String mLine;
      while ((mLine = reader.readLine()) != null) {
        builder.append(mLine);
      }

      return builder.toString();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return "";
  }
}
