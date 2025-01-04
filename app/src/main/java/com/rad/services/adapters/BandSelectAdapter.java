package com.rad.services.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rad.models.Band;
import com.rad.resistorcolorcode.R;
import com.rad.utils.Parser;

import java.util.ArrayList;
import java.util.Locale;

public class BandSelectAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private String prefix;
    private String suffix;
    private final ArrayList<Band> bandColorCodes;

    public BandSelectAdapter(Context context, ArrayList<Band> bandColorCodes)
    {
        super(context, R.layout.layout_dropdown_item_component, bandColorCodes.stream().map(Band::getName).toArray(String[]::new));
        this.context = context;
        this.bandColorCodes = bandColorCodes;
    }

    public BandSelectAdapter(Context context, ArrayList<Band> bandColorCodes, String prefix, String suffix)
    {
        super(context, R.layout.layout_dropdown_item_component, bandColorCodes.stream().map(Band::getName).toArray(String[]::new));
        this.context = context;
        this.bandColorCodes = bandColorCodes;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_dropdown_item_component, parent, false);

        // Set the band color name.
        setColorName(position, convertView);

        // Set the band value.
        setBandValue(position, convertView);

        // Create a circular drawable programmatically
        setColorIndicator(position, convertView);

        return convertView;
    }

    private void setColorName(int position, View convertView)
    {
        TextView textViewColorName = convertView.findViewById(R.id.TextViewColorName);
        String colorName = bandColorCodes.get(position).getName();
        textViewColorName.setText(colorName);
    }

    private void setBandValue(int position, @NonNull View convertView)
    {
        TextView textViewBandValue = convertView.findViewById(R.id.TextViewBandValue);
        String colorValue = Parser.sanitizeDouble(bandColorCodes.get(position).getValue());

        if (prefix != null && suffix != null)
        {
            Locale locale = Locale.getDefault();
            String bandValue = String.format(locale, "%s%s%s", prefix, colorValue, suffix);
            textViewBandValue.setText(bandValue);
        }
        else
            textViewBandValue.setText(String.valueOf(colorValue));
    }

    private void setColorIndicator(int position, @NonNull View convertView)
    {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(bandColorCodes.get(position).getColor());

        // Apply the drawable to the View
        View viewColorCircle = convertView.findViewById(R.id.ViewColorCircle);
        viewColorCircle.setBackground(drawable);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
