/*
 * Copyright (C)  guolin, PermissionX Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.permissionx.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.permissionx.callback.RequestCallback;

/**
 * Dialog fragment for permission rationale.
 */
public class RationaleDialogFragment extends DialogFragment {

    private String message;
    private String positiveText;
    private String negativeText;
    private RequestCallback callback;

    public static RationaleDialogFragment newInstance(String message, String positiveText, String negativeText) {
        RationaleDialogFragment fragment = new RationaleDialogFragment();
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("positiveText", positiveText);
        args.putString("negativeText", negativeText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            message = arguments.getString("message");
            positiveText = arguments.getString("positiveText");
            negativeText = arguments.getString("negativeText");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = requireContext();
        return new DefaultDialog(context, message, positiveText, negativeText, callback);
    }

    public void setRequestCallback(RequestCallback callback) {
        this.callback = callback;
    }

}
}