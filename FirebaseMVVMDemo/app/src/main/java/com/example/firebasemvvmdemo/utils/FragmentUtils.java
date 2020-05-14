package com.example.firebasemvvmdemo.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    public static void showFragment(AppCompatActivity context, int containerId, Fragment fragment, String tag, boolean addToBackStack){
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, tag);
        if (addToBackStack){
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }
}
