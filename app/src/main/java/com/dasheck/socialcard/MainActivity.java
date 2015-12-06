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
import com.dasheck.socialcard.controllers.SocialDataController;
import com.dasheck.socialcard.fragments.SocialCardFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import com.dasheck.socialcard.models.SocialData;
import com.dasheck.socialcard.utilities.Utilities;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements SocialCardFragment.LayoutRevealListener {

  private SocialDataController socialDataController;

  Pair<Integer, Integer> currentTouchPosition;

  Fragment currentFrament;
  Fragment previousFragment;

  @Bind({R.id.twitterButton, R.id.githubButton}) List<ImageView> socialButtons;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    socialDataController = new SocialDataController(this);
    socialDataController.loadSocialData();
  }

  @OnTouch(R.id.clickContainer) public boolean onButtonTouched(MotionEvent motionEvent) {
    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
      currentTouchPosition = new Pair<>((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    return false;
  }

  @OnClick(R.id.githubButton) public void onGithubButtonClicked(View view) {
    prepareFragment(SocialDataController.GITHUB, R.id.githubButton);
  }

  @OnClick(R.id.twitterButton) public void onTwitterButtonClicked(View view) {
    prepareFragment(SocialDataController.TWITTER, R.id.twitterButton);
  }

  private void prepareFragment(String socialDataId, int buttonId) {
    SocialData socialData = socialDataController.getSocialDataFor(socialDataId);
    int color = Utilities.toAndroidColor(socialData.getColor());

    SocialCardFragment fragment =
        SocialCardFragment.newInstance(
            Utilities.bundleFrom(currentTouchPosition.first, currentTouchPosition.second, socialData));
    addFragment(fragment, socialDataId);
    hightlightButton((ImageView) findViewById(buttonId), color);
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
