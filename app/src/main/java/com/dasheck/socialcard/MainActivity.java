package com.dasheck.socialcard;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import butterknife.Bind;
import com.dasheck.socialcard.fragments.SocialCardFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import com.dasheck.socialcard.utilities.Utilities;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements SocialCardFragment.LayoutRevealListener {

  Pair<Integer, Integer> currentTouchPosition;

  Fragment currentFrament;
  Fragment previousFragment;

  @Bind({ R.id.twitterButton, R.id.githubButton }) List<ImageView> socialButtons;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnTouch(R.id.clickContainer) public boolean onButtonTouched(MotionEvent motionEvent) {
    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
      currentTouchPosition = new Pair<>((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    return false;
  }

  @OnClick(R.id.githubButton) public void onGithubButtonClicked(View view) {
    SocialCardFragment fragment =
        SocialCardFragment.newInstance(
            Utilities.bundleFrom(currentTouchPosition.first, currentTouchPosition.second, "GITHUB.",
                "this is a sample text", R.drawable.background, Color.argb(255, 229, 76, 133)
            ));
    addFragment(fragment, "github");
    hightlightButton((ImageView) findViewById(R.id.githubButton), Color.argb(255, 229, 76, 133));
  }

  @OnClick(R.id.twitterButton) public void onTwitterButtonClicked(View view) {
    SocialCardFragment fragment =
        SocialCardFragment.newInstance(
            Utilities.bundleFrom(currentTouchPosition.first, currentTouchPosition.second, "TWITTER.",
                "this is a sample text but a little longer", R.drawable.background2, Color.argb(255, 26, 112, 249)
            ));
    addFragment(fragment, "twitter");
    hightlightButton((ImageView) findViewById(R.id.twitterButton), Color.argb(255, 26, 112, 249));
  }

  private void hightlightButton(ImageView button, int color) {
    for (ImageView socialButton : socialButtons) {
      socialButton.setColorFilter(Color.WHITE);
      socialButton.setEnabled(true);
    }

    button.setColorFilter(color);
    button.setEnabled(false);
  }

  private void addFragment(SocialCardFragment fragment, String tag) {
    if (currentFrament == null || !tag.equals(currentFrament.getTag())) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragmentContainer, fragment, tag)
          .commit();

      previousFragment = currentFrament;
      currentFrament = fragment;

      fragment.setRevealListener(this);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }

  @Override
  public void reavealAnimationEnd() {
    if (previousFragment != null) {
      getSupportFragmentManager().beginTransaction().remove(previousFragment).commit();
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
