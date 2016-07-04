package com.senapathi.feocloud;

import retrofit.RestAdapter;

/**
 * Created by Senapathi on 04-07-2016.
 */
public class SoundCloud {

    public static final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
    public static final SCService SERVICE = restAdapter.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }
}
