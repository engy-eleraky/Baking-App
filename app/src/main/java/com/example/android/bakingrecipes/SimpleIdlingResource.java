package com.example.android.bakingrecipes;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

/**
 * Created by Noga on 12/7/2017.
 */

public class SimpleIdlingResource implements IdlingResource {
    private boolean isIdle = false  ;
    @Nullable
    private volatile ResourceCallback mCallback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback ;

    }
    public void setIdleState(boolean isIdleNow) {
        isIdle =isIdleNow ;
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();

        }
    }
}
