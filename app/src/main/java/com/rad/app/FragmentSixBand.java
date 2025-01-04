package com.rad.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.rad.services.resistor.ResistorService;
import com.rad.models.Resistor;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;
import com.rad.resistorcolorcode.databinding.FragmentSixBandsBinding;
import com.rad.services.adapters.BandSelectAdapter;
import com.rad.services.BandService;
import com.rad.models.Band;

import java.util.ArrayList;

public class FragmentSixBand extends Fragment
{
    /**
     * The first band color.
     */
    private Band firstBand;

    /**
     * The second band color.
     */
    private Band secondBand;

    /**
     * The third band color.
     */
    private Band thirdBand;

    /**
     * The multiplier band color.
     */
    private Band multiplierBand;

    /**
     * The tolerance band color.
     */
    private Band toleranceBand;

    /**
     * The ppm temperature coefficient band color.
     */
    private Band ppmBand;

    /**
     * The first and second band colors.
     */
    private ArrayList<Band> significantBandConfiguration;

    /**
     * The multiplier band colors.
     */
    private ArrayList<Band> multiplierBandConfiguration;

    /**
     * The tolerance band colors.
     */
    private ArrayList<Band> toleranceBandConfiguration;

    /**
     * The ppm band colors.
     */
    private ArrayList<Band> ppmBandConfiguration;

    /**
     * The band service.
     */
    private BandService bandService;

    /**
     * The resistor services.
     */
    private ResistorService resistorService;
    
    /**
     * The binding for the fragment.
     */
    private FragmentSixBandsBinding binding;

    /**
     * The resistor history clone.
     */
    private String previousValue = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        binding = FragmentSixBandsBinding.inflate(inflater, container, false);
        bandService = new BandService(getContext());
        resistorService = new ResistorService(getContext());
        
        if (savedInstanceState != null)
        {
            retrieveSavedInstance(savedInstanceState);
        }
        else
        {
            significantBandConfiguration = bandService.getSignificantBandsCodes();
            multiplierBandConfiguration = bandService.getMultiplierBandColorCodes();
            toleranceBandConfiguration = bandService.getToleranceBandColorCodes();
            ppmBandConfiguration = bandService.getPPMBandColorCodes();
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initializeWebView();
        initializeFirstBandDropdown();
        initializeSecondBandDropdown();
        initializeThirdBandDropdown();
        initializeMultiplierBandDropdown();
        initializeToleranceBandDropdown();
        initializePPMBandDropdown();
        initializeListeners();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("firstBand", firstBand);
        outState.putSerializable("secondBand", secondBand);
        outState.putSerializable("thirdBand", thirdBand);
        outState.putSerializable("multiplierBand", multiplierBand);
        outState.putSerializable("toleranceBand", toleranceBand);
        outState.putSerializable("ppmBand", ppmBand);
        outState.putSerializable("previousValue", previousValue);
        
        outState.putSerializable("significantBandConfiguration", significantBandConfiguration);
        outState.putSerializable("multiplierBandConfiguration", multiplierBandConfiguration);
        outState.putSerializable("toleranceBandConfiguration", toleranceBandConfiguration);
        outState.putSerializable("ppmBandConfiguration", ppmBandConfiguration);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    private void retrieveSavedInstance(Bundle savedInstanceState)
    {
        firstBand = (Band) savedInstanceState.getSerializable("firstBand");
        secondBand = (Band) savedInstanceState.getSerializable("secondBand");
        thirdBand = (Band) savedInstanceState.getSerializable("thirdBand");
        multiplierBand = (Band) savedInstanceState.getSerializable("multiplierBand");
        toleranceBand = (Band) savedInstanceState.getSerializable("toleranceBand");
        ppmBand = (Band) savedInstanceState.getSerializable("ppmBand");
        previousValue = (String) savedInstanceState.getSerializable("previousValue");
        
        significantBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("significantBandConfiguration");
        multiplierBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("multiplierBandConfiguration");
        toleranceBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("toleranceBandConfiguration");
        ppmBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("ppmBandConfiguration");
        
        calculateResistorValue();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initializeWebView()
    {
        WebView webView = binding.WebView6BandsResistor;
        webView.getSettings();
        webView.setBackgroundColor(0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/six_bands_resistor.html");
    }

    private void initializeFirstBandDropdown()
    {
        BandSelectAdapter adapter = new BandSelectAdapter(getContext(), significantBandConfiguration);
        MaterialAutoCompleteTextView colorDropdown =
                (MaterialAutoCompleteTextView) binding.DropdownFirstBand;
        colorDropdown.setAdapter(adapter);

        colorDropdown.setOnItemClickListener((parent, view, position, id) ->
        {
            firstBand = significantBandConfiguration.get(position);
            updateResistorBandColor((String)colorDropdown.getTag(), firstBand.getColor());
            calculateResistorValue();
        });

        colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
    }

    private void initializeSecondBandDropdown()
    {
        BandSelectAdapter adapter = new BandSelectAdapter(getContext(), significantBandConfiguration);
        MaterialAutoCompleteTextView colorDropdown =
                (MaterialAutoCompleteTextView) binding.DropdownSecondBand;
        colorDropdown.setAdapter(adapter);

        colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
            secondBand = significantBandConfiguration.get(position);
            updateResistorBandColor((String)colorDropdown.getTag(), secondBand.getColor());
            calculateResistorValue();
        });

        colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
    }

    private void initializeThirdBandDropdown()
    {
        BandSelectAdapter adapter = new BandSelectAdapter(getContext(), significantBandConfiguration);
        MaterialAutoCompleteTextView colorDropdown =
                (MaterialAutoCompleteTextView) binding.DropdownThirdBand;
        colorDropdown.setAdapter(adapter);

        colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
            thirdBand = significantBandConfiguration.get(position);
            updateResistorBandColor((String)colorDropdown.getTag(), thirdBand.getColor());
            calculateResistorValue();
        });

        colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
    }

    private void initializeMultiplierBandDropdown()
    {
        BandSelectAdapter adapter = new BandSelectAdapter(getContext(), multiplierBandConfiguration, "", " Ω");
        MaterialAutoCompleteTextView colorDropdown =
                (MaterialAutoCompleteTextView) binding.DropdownMultiplierBand;
        colorDropdown.setAdapter(adapter);

        colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
            multiplierBand = multiplierBandConfiguration.get(position);
            updateResistorBandColor((String)colorDropdown.getTag(), multiplierBand.getColor());
            calculateResistorValue();
        });

        colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
    }

    private void initializeToleranceBandDropdown()
    {
        BandSelectAdapter adapter = new BandSelectAdapter(getContext(), toleranceBandConfiguration, "± ", "%");
        MaterialAutoCompleteTextView colorDropdown =
                (MaterialAutoCompleteTextView) binding.DropdownToleranceBand;
        colorDropdown.setAdapter(adapter);

        colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
            toleranceBand = toleranceBandConfiguration.get(position);
            updateResistorBandColor((String)colorDropdown.getTag(), toleranceBand.getColor());
            calculateResistorValue();
        });

        colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
    }

    private void initializePPMBandDropdown()
    {
        BandSelectAdapter adapter = new BandSelectAdapter(getContext(), ppmBandConfiguration, "", " ppm");
        MaterialAutoCompleteTextView colorDropdown =
                (MaterialAutoCompleteTextView) binding.DropdownPPMToleranceBand;
        colorDropdown.setAdapter(adapter);

        colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
            ppmBand = ppmBandConfiguration.get(position);
            updateResistorBandColor((String)colorDropdown.getTag(), ppmBand.getColor());
            calculateResistorValue();
        });

        colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
    }

    private void initializeListeners()
    {
        binding.ButtonReset.setOnClickListener(v ->
        {
            if (firstBand != null && secondBand != null && thirdBand != null &&
                    multiplierBand != null && toleranceBand != null && ppmBand != null)
            {
                firstBand = null;
                secondBand = null;
                thirdBand = null;
                multiplierBand = null;
                toleranceBand = null;
                ppmBand = null;
                
                this.initializeWebView();
                binding.TextViewResult.setVisibility(View.GONE);
                
                binding.DropdownFirstBand.setText(null, false);
                binding.DropdownSecondBand.setText(null, false);
                binding.DropdownThirdBand.setText(null, false);
                binding.DropdownMultiplierBand.setText(null, false);
                binding.DropdownToleranceBand.setText(null, false);
                binding.DropdownPPMToleranceBand.setText(null, false);
                binding.CardView6BandResistor.requestFocus();
                
                this.initializeFirstBandDropdown();
                this.initializeSecondBandDropdown();
                this.initializeThirdBandDropdown();
                this.initializeMultiplierBandDropdown();
                this.initializeToleranceBandDropdown();
                this.initializePPMBandDropdown();
            }
        });
    }

    private final View.OnFocusChangeListener OnFocusChangeListener = (v, hasFocus) -> {
        String bandName = (String) v.getTag();
        if (hasFocus)
            setResistorBandOnFocus(bandName);
        else
            removeResistorBandOnFocus(bandName);
    };

    private void setResistorBandOnFocus(String bandName)
    {
        binding.WebView6BandsResistor
            .evaluateJavascript(String.format("setFocus('%s')", bandName), null);
    }

    private void removeResistorBandOnFocus(String bandName)
    {
        binding.WebView6BandsResistor
            .evaluateJavascript(String.format("removeFocus('%s')", bandName), null);
    }

    private void updateResistorBandColor(String bandName, int color)
    {
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        binding.WebView6BandsResistor
            .evaluateJavascript(String.format("updateBandColor('%s', '%s')", bandName, hexColor), null);
    }

    private void calculateResistorValue()
    {
        boolean canCalculate =
            firstBand != null &&
            secondBand != null &&
            thirdBand != null &&
            multiplierBand != null &&
            toleranceBand != null &&
            ppmBand != null;

        if (canCalculate)
        {
            // Load animation
            Animation slideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);

            // Make the TextView visible and start the animation
            TextView resultTextView = binding.TextViewResult;
            if (resultTextView.getVisibility() != View.VISIBLE)
            {
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.startAnimation(slideDown);
            }
            binding.WebView6BandsResistor.requestFocus();
            binding.NestedScrollView.post(() ->
                    binding.NestedScrollView.smoothScrollTo(0, binding.CardView6BandResistor.getTop()));

            double resistorValue = bandService.getFiveBandsResistorValue(
                    firstBand.getValue(),
                    secondBand.getValue(),
                    thirdBand.getValue(),
                    multiplierBand.getValue());
            
            String msg = bandService.getFormatedResistorNotation(resistorValue, toleranceBand.getValue());
            
            if (msg.equalsIgnoreCase(this.previousValue))
                return;
            this.previousValue = msg;
            resultTextView.setText(msg);

            Resistor resistor = new Resistor(
                    ResistorType.SIX_BAND,
                    firstBand.getColor(),
                    secondBand.getColor(),
                    thirdBand.getColor(),
                    multiplierBand.getColor(),
                    toleranceBand.getColor(),
                    ppmBand.getColor(),
                    resistorValue,
                    toleranceBand.getValue(),
                    ppmBand.getValue());
            
            resistorService.create(resistor);
        }
    }
}