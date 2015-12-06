package com.dasheck.socialcard.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dasheck.socialcard.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class SocialCardFragment extends Fragment implements SupportAnimator.AnimatorListener {

  private static final String BUNDLE_CX_KEY = "cxKey";
  private static final String BUNDLE_CY_KEY = "cyKey";

  private boolean revealed = false;
  private int cx;
  private int cy;

  @Bind(R.id.container) View container;
  @Bind(R.id.first) View first;
  @Bind(R.id.second) View second;

  private LayoutRevealListener revealListener;

  public void setRevealListener(LayoutRevealListener revealListener) {
    this.revealListener = revealListener;
  }

  public static SocialCardFragment newInstance(int x, int y) {
    SocialCardFragment fragment = new SocialCardFragment();

    Bundle args = new Bundle();
    args.putInt(BUNDLE_CX_KEY, x);
    args.putInt(BUNDLE_CY_KEY, y);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_social_card, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Bundle args = getArguments();
    cx = args.getInt(BUNDLE_CX_KEY);
    cy = args.getInt(BUNDLE_CY_KEY);

    first.setBackgroundColor(randomColor());
    second.setBackgroundColor(randomColor());

    first.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override
          public void onGlobalLayout() {
            reveal();
          }
        });
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }

  @Override
  public void onAnimationStart() {

  }

  @Override
  public void onAnimationEnd() {
    if (revealListener != null) {
      revealListener.reavealAnimationEnd(this);
    }
  }

  @Override
  public void onAnimationCancel() {

  }

  @Override
  public void onAnimationRepeat() {

  }

  public interface LayoutRevealListener {

    void reavealAnimationEnd(Fragment fragment);
  }

  void reveal() {
    if (!revealed) {
      revealed = true;

      View view = container;

      int dx = Math.max(cx, view.getWidth() - cx);
      int dy = Math.max(cy, view.getHeight() - cy);
      float finalRadius = (float) Math.hypot(dx, dy);

      SupportAnimator animator =
          ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
      animator.setInterpolator(new AccelerateDecelerateInterpolator());
      animator.setDuration(500);
      animator.start();

      animator.addListener(this);
    }
  }

  static int randomColor() {
    Random random = new Random();
    return Color.HSVToColor(new float[] {
        random.nextInt(360),
        0.70f,
        0.70f
    });
  }
}
