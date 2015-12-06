package com.dasheck.socialcard.models;

import com.google.gson.annotations.Expose;

/**
 * Created by dasheck on 12/6/15.
 */
public class SocialData {

  @Expose private String caption;
  @Expose private String body;
  @Expose private int resourceId;
  @Expose private Color color;
  @Expose private String url;

  public SocialData(String caption, String body, int resourceId,
      Color color, String url) {
    this.caption = caption;
    this.body = body;
    this.resourceId = resourceId;
    this.color = color;
    this.url = url;
  }

  public SocialData() {
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public int getResourceId() {
    return resourceId;
  }

  public void setResourceId(int resourceId) {
    this.resourceId = resourceId;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
