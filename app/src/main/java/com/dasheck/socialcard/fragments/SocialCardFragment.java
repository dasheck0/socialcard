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

import android.widget.ImageView;
import android.widget.TextView;
import com.dasheck.socialcard.R;

import com.dasheck.socialcard.utilities.Utilities;
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
  private String caption;
  private String body;
  private int resourceId;
  private int backgroundColor;

  @Bind(R.id.container) View container;
  @Bind(R.id.captionTextView) TextView captionTextView;
  @Bind(R.id.bodyTextView) TextView bodyTextView;
  @Bind(R.id.imageView) ImageView imageView;

  private LayoutRevealListener revealListener;

  public void setRevealListener(LayoutRevealListener revealListener) {
    this.revealListener = revealListener;
  }

  public static SocialCardFragment newInstance(Bundle bundle) {
    SocialCardFragment fragment = new SocialCardFragment();
    fragment.setArguments(bundle);

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
    unpackBundle();

    captionTextView.setText(caption);
    bodyTextView.setText(body);
    imageView.setImageResource(resourceId);

    container.setBackgroundColor(backgroundColor);
    container.getViewTreeObserver()
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
      revealListener.reavealAnimationEnd();
    }
  }

  @Override
  public void onAnimationCancel() {

  }

  @Override
  public void onAnimationRepeat() {

  }

  public interface LayoutRevealListener {

    void reavealAnimationEnd();
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

  private void unpackBundle() {
    Bundle args = getArguments();

    cx = args.getInt(Utilities.BUNDLE_KEY_CX);
    cy = args.getInt(Utilities.BUNDLE_KEY_CY);
    caption = args.getString(Utilities.BUNDLE_KEY_CAPTION);
    body = args.getString(Utilities.BUNDLE_KEY_TEXT);
    resourceId = args.getInt(Utilities.BUNDLE_KEY_RESOURCE_ID);
    backgroundColor = args.getInt(Utilities.BUNDLE_KEY_BACKGROUND_COLOR);
  }
}
