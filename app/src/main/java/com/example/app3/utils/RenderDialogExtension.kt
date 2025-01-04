package com.example.app3.utils

import android.view.Gravity
import androidx.fragment.app.FragmentActivity
import com.example.app3.views.CustomDialogBuilder
import com.example.app3.views.OnAlertClickAction
import com.example.app3.views.OnAlertClickActionWithInput
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun FragmentActivity.createDialogMessageWithSingleButton(
    title: CharSequence,
    description: CharSequence,
    titleButton: CharSequence,
    onCDSAlertClickAction: OnAlertClickAction? = null
): MaterialAlertDialogBuilder = CustomDialogBuilder(this)
    .title(title)
    .description(description)
    .addSingleButton(titleButton)
    .setGravityText(Gravity.CENTER)
    .setAction(onCDSAlertClickAction)
    .build()

fun FragmentActivity.createDialogInputWithTwoButtons(
    title: String,
    titleButtonLeft: String,
    titleButtonRight: String,
    hint: String,
    onAlertClickActionWithInput: OnAlertClickActionWithInput? = null
): MaterialAlertDialogBuilder = CustomDialogBuilder(this)
    .title(title)
    .addButtonLeft(titleButtonLeft)
    .addButtonRight(titleButtonRight)
    .setActionWithInput(onAlertClickActionWithInput)
    .setInputHint(hint)
    .setGravityText(Gravity.CENTER)
    .build()