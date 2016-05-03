package com.damam.wtestapp.listener;

import com.damam.wtestapp.io.ListResponse;

/**
 * Created by Damam on 03-May-16.
 */
public interface GetListListener {
    // Returns the result to UI.
    void onSuccess(ListResponse listResponse);

    // Return failure.
    void onFailure();
}
