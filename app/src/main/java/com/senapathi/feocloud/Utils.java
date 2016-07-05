package com.senapathi.feocloud;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by Senapathi on 05-07-2016.
 */
public class Utils {

    public static final String dummyImage = "https://s-media-cache-ak0.pinimg.com/236x/8d/e4/20/8de42050e671b93b1d6bad2f2764ba89.jpg";

    public static KProgressHUD kProgressHUD;
    public static void showdialog(Context context){
        if(kProgressHUD == null){
        KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Loading...")
        .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();}
    }

    public static void dismissdialog(Context context)
    {
        kProgressHUD.dismiss();
    }
}
