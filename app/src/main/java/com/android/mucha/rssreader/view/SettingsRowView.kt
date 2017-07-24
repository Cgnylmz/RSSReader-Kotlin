package com.android.mucha.rssreader.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.android.mucha.rssreader.R
import kotlinx.android.synthetic.main.view_settings_row.view.*

/**
 * The list-alike view to be used in Setting screens.
 *
 * @author Patrik Mucha
 */
class SettingsRowView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.settingsRowViewStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr,
            R.style.View_SettingsRowView)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        orientation = VERTICAL
        View.inflate(context, R.layout.view_settings_row, this)

        initFromAttributes(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun initFromAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.SettingsRowView, defStyleAttr, defStyleRes)

        val titleResId = styledAttrs.getResourceId(R.styleable.SettingsRowView_rowTitle, -1)
        if (titleResId != -1) {
            setTitle(titleResId)
        } else {
            setTitle(styledAttrs.getString(R.styleable.SettingsRowView_rowTitle))
        }

        val subtitleResId = styledAttrs.getResourceId(R.styleable.SettingsRowView_rowSubtitle, -1)
        if (subtitleResId != -1) {
            setSubtitle(subtitleResId)
        } else {
            setSubtitle(styledAttrs.getString(R.styleable.SettingsRowView_rowSubtitle))
        }

        styledAttrs.recycle()
    }

    /**
     * Sets the text for the title.
     *
     * @param text The text resource Id.
     */
    fun setTitle(text: Int) {
        view_settings_title.setText(text)
    }

    /**
     * Sets the text for the title.
     *
     * @param text The text.
     */
    fun setTitle(text: CharSequence?) {
        view_settings_title.text = text
    }

    /**
     * Sets the text for the subtitle.
     *
     * @param text The text resource Id.
     */
    fun setSubtitle(text: Int) {
        view_settings_subtitle.setText(text)
    }

    /**
     * Sets the text for the subtitle.
     *
     * @param text The text.
     */
    fun setSubtitle(text: CharSequence?) {
        view_settings_subtitle.text = text
    }
}