package com.example.app3.views

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.app3.R
import com.example.app3.databinding.CustomDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

interface OnAlertClickAction {
    fun onConfirmAction()
    fun onResetAction()
    fun onCtaAction() = Unit
    fun onNavigateAction(funzione: String? = null) = Unit
    fun onLogoutAction() = Unit
    fun onNavigateUrl() = Unit
    fun onGestMessaggiBloccanti() = Unit
    fun onOkAction() = Unit
}
interface OnAlertClickActionWithInput {
    fun onCofirmActionWithInput(value: String)
    fun onDismissActionWithInput()
}
class CustomDialogBuilder(
    val context: Context
) {
    private var binding = CustomDialogBinding.inflate(LayoutInflater.from(context))
    var icon: Drawable? = null
    var iconContentDescription: CharSequence? = null
    var title: CharSequence? = null
    var description: CharSequence? = null
    var ctaDescription: CharSequence? = null
    var titleButtonLeft: CharSequence? = null
    var titleButtonRight: CharSequence? = null
    var gravity: Int? = Gravity.START
    var titleSingleButton: CharSequence? = null
    var onAlertClickAction: OnAlertClickAction? = null
    var onAlertClickActionWithInput: OnAlertClickActionWithInput? = null
    var centerButtonFullWidth: Boolean? = false
    var inputHint: String? = null
    var inputText: String? = null

    fun build(): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(context, R.style.CustomMessageDialog)
            .setCancelable(false)
            .setView(binding.root)
            .setBackgroundInsetTop(context.resources.getDimension(R.dimen.size0).toInt())
            .setBackgroundInsetBottom(context.resources.getDimension(R.dimen.size0).toInt())
    }
    fun setAction(onOnAlertClickAction: OnAlertClickAction?) = apply {
        this.onAlertClickAction = onOnAlertClickAction
    }

    fun setSingleButtonColor(@ColorRes colorResId: Int) = apply {
        with(binding) {
            singleButton.setTextColor(
                ContextCompat.getColor(
                    context,
                    colorResId
                )
            )
        }
    }
    fun setCenterButton(
        titleButtonRight: CharSequence,
        isConfirm: Boolean,
        isLoadNextMessage: Boolean = false
    ) = apply {
        with(binding) {
            centerButton.text = titleButtonRight
            centerButton.visibility = View.VISIBLE
            centerButton.setOnClickListener {
                when {
                    isConfirm -> {
                        onAlertClickAction?.onConfirmAction()
                    }
                    isLoadNextMessage -> {
                        onAlertClickAction?.onOkAction()
                    }
                    else -> {
                        onAlertClickAction?.onNavigateAction()
                    }
                }
            }
        }
    }
    fun centerButtons() = apply {
        this.centerButtonFullWidth = true
    }

    fun setButtonInCenterOfView(centeredButton: Boolean) = apply {
        this.centerButtonFullWidth = centeredButton
        if (centeredButton) {
            ConstraintSet().run {
                with(binding) {
                    clone(dialogLayout)
                    clear(leftButton.id, ConstraintSet.END)
                    clear(rightButton.id, ConstraintSet.START)
                    clear(leftButton.id, ConstraintSet.START)
                    clear(rightButton.id, ConstraintSet.END)
                    connect(
                        rightButton.id,
                        ConstraintSet.START,
                        leftButton.id,
                        ConstraintSet.END
                    )
                    setHorizontalChainStyle(leftButton.id, ConstraintSet.CHAIN_PACKED)
                    setHorizontalBias(leftButton.id, 0.5f)
                    applyTo(dialogLayout)
                }
            }
        }
    }
    fun setGravityText(gravity: Int) = apply {
        this.gravity = gravity
        with(binding) {
            dialogTitle.gravity = gravity
            dialogDescription.gravity = gravity
        }
    }

    fun moveIconUnderTitle() = apply {
        ConstraintSet().run {
            with(binding) {
                clone(dialogLayout)
                clear(dialogTopIcon.id, ConstraintSet.TOP)
                clear(dialogTitle.id, ConstraintSet.TOP)
                clear(dialogDescription.id, ConstraintSet.TOP)
                connect(
                    dialogTopIcon.id,
                    ConstraintSet.TOP,
                    dialogTitle.id,
                    ConstraintSet.BOTTOM
                )
                connect(
                    dialogTitle.id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    dialogDescription.id,
                    ConstraintSet.TOP,
                    dialogTopIcon.id,
                    ConstraintSet.BOTTOM
                )
                applyTo(dialogLayout)
            }
        }
    }
    fun setDescriptionColor(color: Int) = apply {
        with(binding) {
            dialogDescription.setTextColor(context.resources.getColor(color))
        }
    }
    fun setCtaDescriptionToRight() = apply {
        with(binding) {
            dialogCta.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            dialogCta.setTextColor(context.resources.getColor(R.color.primary))
        }
    }

    fun setButtonTextSize(sizeDp: Float) = apply {
        with(binding) {
            rightButton.textSize = sizeDp
            leftButton.textSize = sizeDp
        }
    }

    fun setHeaderIcon(icon: Drawable?) = apply {
        icon?.let {
            this.icon = icon
            with(binding) {
                dialogTopIcon.setImageDrawable(icon)
                dialogTopIcon.visibility = View.VISIBLE
                dialogTopIcon.setColorFilter(
                    context.resources.getColor(R.color.primary),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }
    fun addButtonRight(
        titleButtonRight: CharSequence,
        isConfirm: Boolean = true,
//        isLogoutRequest: Boolean = false,
//        isFirmaDigitale: Boolean = false,
//        isMessaggiBloccant: Boolean = false,
//        funzione: String? = null
    ) =
        apply {
            this.titleButtonRight = titleButtonRight
            with(binding) {
//                if (isFirmaDigitale) {
//                    rightButtonSmall.text = titleButtonRight
//                    rightButton.visibility = View.GONE
//                    singleButton.visibility = View.GONE
//                    rightButtonSmall.visibility = View.VISIBLE
//                    rightButtonSmall.setOnClickListener {
//                        onAlertClickAction?.onNavigateUrl()
//                    }
//                } else {
                    rightButton.text = titleButtonRight
                    singleButton.visibility = View.GONE
                    rightButton.visibility = View.VISIBLE
                    rightButton.setOnClickListener {

                        when {
                            isConfirm -> {
                                onAlertClickActionWithInput?.onCofirmActionWithInput(dialogInputEdit.text.toString())
                            }
//                            isLogoutRequest -> {
//                                onAlertClickAction?.onLogoutAction()
//                            }
//                            isMessaggiBloccant -> {
//                                onAlertClickAction?.onGestMessaggiBloccanti()
//                            }
                            else -> {
                                onAlertClickAction?.onResetAction()
                            }
                        }
                    }
//                }
            }
        }
    fun addButtonLeft(
        titleButtonLeft: CharSequence,
        isLogoutRequest: Boolean = false,
        isNavigate: Boolean = false,
        isFirmaDigitale: Boolean = false
    ) =
        apply {
            this.titleButtonLeft = titleButtonLeft
            with(binding) {
                if (isFirmaDigitale) {
                    leftButtonOutlinedSmall.text = titleButtonLeft
                    leftButton.visibility = View.GONE
                    singleButton.visibility = View.GONE
                    leftButtonOutlinedSmall.visibility = View.VISIBLE
                    leftButtonOutlinedSmall.setOnClickListener {
                        onAlertClickAction?.onResetAction()
                    }
                } else {
                    leftButton.text = titleButtonLeft
                    singleButton.visibility = View.GONE
                    leftButton.visibility = View.VISIBLE
                    leftButton.setOnClickListener {
                        when {
                            isLogoutRequest -> {
                                onAlertClickAction?.onLogoutAction()
                            }
                            isNavigate -> {
                                onAlertClickAction?.onNavigateAction()
                            }
                            else -> {
                                onAlertClickActionWithInput?.onDismissActionWithInput()
                            }
                        }
                    }
                }
            }
        }

    fun addSingleButton(
        title: CharSequence,
        isLogoutRequest: Boolean = false,
        hideFileLay: Boolean = true,
        isRemindMeLater: Boolean = false
    ) =
        apply {
            this.titleSingleButton = title
            with(binding) {
                if (hideFileLay) attachedFileLay.visibility = View.GONE
                singleButton.visibility = View.VISIBLE
                singleButton.text = title
                leftButton.visibility = View.GONE
                rightButton.visibility = View.GONE
                singleButton.setOnClickListener {
                    if (isLogoutRequest) {
                        onAlertClickAction?.onLogoutAction()
                    } else if (isRemindMeLater) {
                        onAlertClickAction?.onResetAction()
                    } else {
                        onAlertClickAction?.onConfirmAction()
                    }
                }
            }
        }
    fun ctaDescription(ctaDescription: CharSequence) = apply {
        this.ctaDescription = ctaDescription
        with(binding) {
            dialogCta.text = ctaDescription
            dialogCta.visibility = View.VISIBLE
            dialogCta.setOnClickListener {
                onAlertClickAction?.onCtaAction()
            }
        }
    }
    fun description(description: CharSequence) = apply {
        this.description = description
        with(binding) {
            dialogDescription.text = description
            dialogDescription.movementMethod = LinkMovementMethod.getInstance();
            dialogDescription.visibility = View.VISIBLE
        }
    }
    fun title(title: CharSequence) = apply {
        this.title = title
        with(binding) {
            dialogTitle.text = title
            dialogTitle.isVisible = title.isNotEmpty()
        }
    }

    fun icon(
        icon: Drawable?,
        color: Int? = null,
        iconWidth: Int? = null,
        iconHeight: Int? = null,
        adjustViewBounds: Boolean = false
    ) = apply {
        icon?.let {
            this.icon = icon
            with(binding) {
                dialogTopIcon.setImageDrawable(icon)
                dialogTopIcon.visibility = View.VISIBLE
                if (adjustViewBounds) dialogTopIcon.adjustViewBounds = adjustViewBounds

                color?.let {
                    dialogTopIcon.setColorFilter(
                        context.resources.getColor(color),
                        PorterDuff.Mode.SRC_ATOP
                    )
                }

                iconWidth?.let {
                    dialogTopIcon.layoutParams.width =
                        context.resources.getDimensionPixelSize(iconWidth)
                }

                iconHeight?.let {
                    dialogTopIcon.layoutParams.height =
                        context.resources.getDimensionPixelSize(iconHeight)
                }

                dialogTopIcon.setOnClickListener {
                    onAlertClickAction?.onCtaAction()
                }
            }
        }
    }

    fun iconContentDescription(contentDescription: CharSequence?) = apply {
        this.iconContentDescription = contentDescription
        with(binding) {
            contentDescription?.let {
                dialogTopIcon.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                dialogTopIcon.contentDescription = contentDescription
            }
        }
    }

    fun setActionWithInput(onAlertClickActionWithInput: OnAlertClickActionWithInput?) =
        apply {
            this.onAlertClickActionWithInput = onAlertClickActionWithInput
        }

    fun setInputHint(hint: String?) = apply {
        hint?.let {
            this.inputHint = hint
            with(binding) {
                dialogInput.hint = inputHint
                dialogInput.visibility = View.VISIBLE
            }
        }
    }

    fun setInputText(inputText: String?) = apply {
        inputText?.let {
            this.inputText = inputText
            with(binding) {
                dialogInputEdit.setText(inputText)
                dialogInputEdit.visibility = View.VISIBLE
            }
        }
    }
}