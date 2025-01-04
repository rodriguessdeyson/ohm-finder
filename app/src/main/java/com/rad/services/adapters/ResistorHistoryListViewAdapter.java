package com.rad.services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rad.models.Resistor;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;
import com.rad.services.BandService;
import com.rad.services.CustomVectorDrawable;
import com.rad.utils.Parser;
import com.rad.services.listeners.ResistorManagerListener;

import java.io.Serializable;
import java.util.ArrayList;

public class ResistorHistoryListViewAdapter extends ArrayAdapter<Resistor> implements Serializable {
    private ResistorManagerListener resistorManagerListener;
    private final ArrayList<Resistor> resistor;
    private final BandService bandService;

    public ResistorHistoryListViewAdapter(Context context, ArrayList<Resistor> resistor)
    {
        super(context, android.R.layout.simple_list_item_1, resistor);
        this.resistor = resistor;
        this.bandService = new BandService(context);
    }

    public void setResistorManagerListener(ResistorManagerListener resistorManagerListener)
    {
        this.resistorManagerListener = resistorManagerListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View row;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_history_item_component, parent, false);
        }
        else
            row = convertView;

        Resistor resistor = this.resistor.get(position);
        setResistorValues(row, resistor);
        setResistorImage(row, resistor);
        setEvent(row, resistor);
        return row;
    }

    private void setResistorValues(View row, Resistor resistor)
    {
        // Find the views.
        TextView textViewResultValue = row.findViewById(R.id.TextViewResistorValue);
        TextView textViewToleranceValue = row.findViewById(R.id.TextViewToleranceValue);
        TextView textViewRangeValue = row.findViewById(R.id.TextViewRangeValue);

        String resistorValue = bandService.getFormatedResistorNotation(resistor.getValue());
        String toleranceValue = getContext().getString(R.string.resistor_tolerance_value, Parser.sanitizeDouble(resistor.getToleranceValue()));
        String rangeValue = bandService.getRangeValue(resistor.getValue(), resistor.getToleranceValue());

        textViewResultValue.setText(resistorValue);
        textViewToleranceValue.setText(toleranceValue);
        textViewRangeValue.setText(rangeValue);

        if (resistor.getType() == ResistorType.SIX_BAND) {
            TextView textViewPpmValue = row.findViewById(R.id.TextViewPpmValue);
            String ppmValue = getContext().getString(R.string.resistor_ppm_value, Parser.sanitizeDouble(resistor.getPpmValue()));
            textViewPpmValue.setText(ppmValue);
            textViewPpmValue.setVisibility(View.VISIBLE);
        }
    }

    private void setResistorImage(View row, Resistor resistor)
    {
        CustomVectorDrawable drawable = new CustomVectorDrawable(resistor);
        row.findViewById(R.id.ImageViewResistor).setBackground(drawable);
    }

    private void setEvent(View row, Resistor resistor)
    {
        Button buttonInfo = row.findViewById(R.id.ButtonInfo);
        Button buttonDelete = row.findViewById(R.id.ButtonDelete);

        buttonInfo.setOnClickListener(v -> {
            if (resistorManagerListener != null)
               resistorManagerListener.onInfoClick(resistor.getDateTime());
        });

        buttonDelete.setOnClickListener(v -> {
            if (resistorManagerListener != null)
                resistorManagerListener.onDeleteClick(resistor.getId());
        });
    }
}
