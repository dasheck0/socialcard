package com.dasheck.socialcard;

import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dasheck.socialcard.fragments.SocialCardFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity
    implements SocialCardFragment.LayoutRevealListener {

  Pair<Integer, Integer> currentTouchPosition;

  Fragment currentFrament;
  Fragment previousFragment;

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

  @OnClick(R.id.button) public void onFirstClicked(View view) {
    SocialCardFragment fragment =
        SocialCardFragment.newInstance(currentTouchPosition.first, currentTouchPosition.second);
    addFragment(fragment, "Button");
  }

  @OnClick(R.id.button2) public void onSecondClicked(View view) {
    SocialCardFragment fragment =
        SocialCardFragment.newInstance(currentTouchPosition.first, currentTouchPosition.second);
    addFragment(fragment, "Button2");
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
  public void reavealAnimationEnd(Fragment fragment) {
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
