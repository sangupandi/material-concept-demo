/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.maksing.materialconceptdemo.presentation.presenter;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter {

    public static int DIALOG_OK = 0;
    public static int DIALOG_CANCEL = 1;

    /**
    * Method that control the lifecycle of the view. It should be called in the view's
    * (Activity or Fragment) onResume() method.
    */
    void resume();

    /**
    * Method that control the lifecycle of the view. It should be called in the view's
    * (Activity or Fragment) onPause() method.
    */
    void pause();
}
