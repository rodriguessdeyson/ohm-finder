package com.rad.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;

import java.io.Serializable;

public class HelpBottomSheetDialog extends BottomSheetDialogFragment implements Serializable
{
    //region Attributes

    public ResistorType resistorType;

    //endregion

    //region Constructor

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.resistorType = ResistorType.values()[args.getInt("resistorType")];
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        if (this.resistorType != null)
        {
			return switch (this.resistorType)
            {
				case FOUR_BAND -> fourBandHelpView(inflater, container);
				case FIVE_BAND -> fiveBandHelpView(inflater, container);
				case SIX_BAND -> sixBandHelpView(inflater, container);
				default -> helpView(inflater, container);
			};
        }
        return helpView(inflater, container);
    }

    //endregion

    //region Methods

    private View fourBandHelpView(LayoutInflater inflater, @Nullable ViewGroup container)
    {
        View v = inflater.inflate(R.layout.layout_bottom_sheet_resistor_help, container, false);
        
        AdView adView = v.findViewById(R.id.banner_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        TextView title = v.findViewById(R.id.TextViewHelpTitle);
        
        title.setText(this.requireContext().getString(R.string.four_band_resistor_model));

        ImageView help_image = v.findViewById(R.id.ImageViewHelpImage);
        help_image.setImageResource(R.drawable.drawable_four_band_resistor);

        TextView help_introduction = v.findViewById(R.id.TextViewHelpIntroduction);
        help_introduction.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_four_band_resistor_introduction), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_example = v.findViewById(R.id.TextViewHelpExample);
        help_example.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_four_band_resistor_example), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_color_table = v.findViewById(R.id.TextViewHelpColorTable);
        help_color_table.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_color_table), HtmlCompat.FROM_HTML_MODE_LEGACY));
        
        return v;
    }

    private View fiveBandHelpView(LayoutInflater inflater, @Nullable ViewGroup container)
    {
        View v = inflater.inflate(R.layout.layout_bottom_sheet_resistor_help, container, false);
        
        AdView adView = v.findViewById(R.id.banner_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        TextView title = v.findViewById(R.id.TextViewHelpTitle);
        title.setText(this.requireContext().getResources().getString(R.string.five_band_resistor_model));

        ImageView help_image = v.findViewById(R.id.ImageViewHelpImage);
        help_image.setImageResource(R.drawable.drawable_five_band_resistor);

        TextView help_introduction = v.findViewById(R.id.TextViewHelpIntroduction);
        help_introduction.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_five_band_resistor_introduction), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_example = v.findViewById(R.id.TextViewHelpExample);
        help_example.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_five_band_resistor_example), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_color_table = v.findViewById(R.id.TextViewHelpColorTable);
        help_color_table.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_color_table), HtmlCompat.FROM_HTML_MODE_LEGACY));
        return v;
    }

    private View sixBandHelpView(LayoutInflater inflater, @Nullable ViewGroup container)
    {
        View v = inflater.inflate(R.layout.layout_bottom_sheet_resistor_help, container, false);
        
        AdView adView = v.findViewById(R.id.banner_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        TextView title = v.findViewById(R.id.TextViewHelpTitle);
        title.setText(this.requireContext().getResources().getString(R.string.six_band_resistor_model));

        ImageView help_image = v.findViewById(R.id.ImageViewHelpImage);
        help_image.setImageResource(R.drawable.drawable_six_band_resistor);

        TextView help_introduction = v.findViewById(R.id.TextViewHelpIntroduction);
        help_introduction.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_six_band_resistor_introduction), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_example = v.findViewById(R.id.TextViewHelpExample);
        help_example.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_six_band_resistor_example), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_color_table = v.findViewById(R.id.TextViewHelpColorTable);
        help_color_table.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_color_table), HtmlCompat.FROM_HTML_MODE_LEGACY));
        return v;
    }


    private View helpView(LayoutInflater inflater, @Nullable ViewGroup container)
    {
        View v = inflater.inflate(R.layout.layout_bottom_sheet_help, container, false);

        AdView adView = v.findViewById(R.id.banner_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        TextView title = v.findViewById(R.id.TextViewHelpTitle);
        title.setText(this.requireContext().getResources().getString(R.string.help_title));

        TextView help_introduction = v.findViewById(R.id.TextViewHelpIntroduction);
        help_introduction.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_introduction), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_example = v.findViewById(R.id.TextViewHelpExample);
        help_example.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_example), HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView help_color_table = v.findViewById(R.id.TextViewHelpColorTable);
        help_color_table.setText(HtmlCompat.fromHtml(this.requireContext().getResources().getString(R.string.help_color_table), HtmlCompat.FROM_HTML_MODE_LEGACY));

        return v;
    }

    //endregion
}

