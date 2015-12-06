package com.dasheck.socialcard.models;

import com.google.gson.annotations.Expose;

/**
 * Created by dasheck on 12/6/15.
 */
public class Color {

  @Expose private int a;
  @Expose private int r;
  @Expose private int g;
  @Expose private int b;

  public Color(int a, int r, int g, int b) {
    this.a = a;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public Color() {
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }
}
