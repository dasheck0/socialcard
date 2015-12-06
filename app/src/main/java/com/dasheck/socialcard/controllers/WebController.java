package com.dasheck.socialcard.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by dasheck on 12/6/15.
 */
public class WebController {

  private Context context;

  public WebController(Context context) {
    this.context = context;
  }

  public void openWebsite(String url) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    context.startActivity(intent);
  }
}
