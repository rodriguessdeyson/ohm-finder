package com.rad.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.rad.services.resistor.ResistorService;
import com.rad.models.Resistor;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;
import com.rad.services.adapters.BandSelectAdapter;
import com.rad.services.BandService;
import com.rad.models.Band;
import com.rad.resistorcolorcode.databinding.FragmentFourBandsBinding;

import java.util.ArrayList;

public class FragmentFourBand extends Fragment
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
	 * The multiplier band color.
	 */
	private Band multiplierBand;
	
	/**
	 * The tolerance band color.
	 */
	private Band toleranceBand;
	
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
	 * The band service.
	 */
	private BandService bandService;
	
	/**
	 * The resistor service.
	 */
	private ResistorService resistorService;
	
	/**
	 * The binding for the fragment.
	 */
	private FragmentFourBandsBinding binding;
	
	/**
	 * The resistor previous value.
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
		binding = FragmentFourBandsBinding.inflate(inflater, container, false);
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
		}
		return binding.getRoot();
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		initializeWebView();
		initializeFirstBandDropdown();
		initializeSecondBandDropdown();
		initializeMultiplierBandDropdown();
		initializeToleranceBandDropdown();
		initializeListeners();
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putSerializable("firstBand", firstBand);
		outState.putSerializable("secondBand", secondBand);
		outState.putSerializable("multiplierBand", multiplierBand);
		outState.putSerializable("toleranceBand", toleranceBand);
		outState.putSerializable("previousValue", previousValue);
		
		outState.putSerializable("significantBandConfiguration", significantBandConfiguration);
		outState.putSerializable("multiplierBandConfiguration", multiplierBandConfiguration);
		outState.putSerializable("toleranceBandConfiguration", toleranceBandConfiguration);
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
		multiplierBand = (Band) savedInstanceState.getSerializable("multiplierBand");
		toleranceBand = (Band) savedInstanceState.getSerializable("toleranceBand");
		previousValue = (String) savedInstanceState.getSerializable("previousValue");
		
		significantBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("significantBandConfiguration");
		multiplierBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("multiplierBandConfiguration");
		toleranceBandConfiguration = (ArrayList<Band>) savedInstanceState.getSerializable("toleranceBandConfiguration");
		
		calculateResistorValue();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initializeWebView()
	{
		WebView webView = binding.WebView4BandsResistor;
		webView.getSettings();
		webView.setBackgroundColor(0);
		webView.getSettings().setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient()
		{
			public void onPageFinished(WebView view, String url)
			{
				if (firstBand != null)
					updateResistorBandColor((String)binding.DropdownFirstBand.getTag(), firstBand.getColor());
				if (secondBand != null)
					updateResistorBandColor((String)binding.DropdownSecondBand.getTag(), secondBand.getColor());
				if (multiplierBand != null)
					updateResistorBandColor((String)binding.DropdownMultiplierBand.getTag(), multiplierBand.getColor());
				if (toleranceBand != null)
					updateResistorBandColor((String)binding.DropdownToleranceBand.getTag(), toleranceBand.getColor());
			}
		});
		
		webView.loadUrl("file:///android_asset/four_bands_resistor.html");
	}
	
	private void initializeFirstBandDropdown()
	{
		BandSelectAdapter adapter = new BandSelectAdapter(getContext(), significantBandConfiguration);
		MaterialAutoCompleteTextView colorDropdown =
			(MaterialAutoCompleteTextView) binding.DropdownFirstBand;
		
		colorDropdown.setSaveEnabled(false);
		
		if (firstBand != null)
			colorDropdown.setText(firstBand.getName(), false);
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
		colorDropdown.setSaveEnabled(false);

		if (secondBand != null)
			colorDropdown.setText(secondBand.getName(), false);
		colorDropdown.setAdapter(adapter);
		
		colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
			secondBand = significantBandConfiguration.get(position);
			updateResistorBandColor((String)colorDropdown.getTag(), secondBand.getColor());
			calculateResistorValue();
		});
		
		colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
	}
	
	private void initializeMultiplierBandDropdown()
	{
		BandSelectAdapter adapter = new BandSelectAdapter(getContext(), multiplierBandConfiguration, "", " Ω");
		MaterialAutoCompleteTextView colorDropdown =
			(MaterialAutoCompleteTextView) binding.DropdownMultiplierBand;
		
		colorDropdown.setSaveEnabled(false);
		if (multiplierBand != null)
			colorDropdown.setText(multiplierBand.getName(), false);
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
		
		colorDropdown.setSaveEnabled(false);
		if (toleranceBand != null)
			colorDropdown.setText(toleranceBand.getName(), false);
		colorDropdown.setAdapter(adapter);
		
		colorDropdown.setOnItemClickListener((parent, view, position, id) -> {
			toleranceBand = toleranceBandConfiguration.get(position);
			updateResistorBandColor((String)colorDropdown.getTag(), toleranceBand.getColor());
			calculateResistorValue();
		});
		
		colorDropdown.setOnFocusChangeListener(OnFocusChangeListener);
	}
	
	private void initializeListeners()
	{
		binding.ButtonReset.setOnClickListener(v ->
		{
			if (firstBand != null && secondBand != null && multiplierBand != null && toleranceBand != null)
			{
				firstBand = null;
				secondBand = null;
				multiplierBand = null;
				toleranceBand = null;
				
				this.initializeWebView();
				binding.TextViewResult.setVisibility(View.GONE);
				
				binding.DropdownFirstBand.setText(null, false);
				binding.DropdownSecondBand.setText(null, false);
				binding.DropdownMultiplierBand.setText(null, false);
				binding.DropdownToleranceBand.setText(null, false);
				binding.CardView4BandResistor.requestFocus();
				
				this.initializeFirstBandDropdown();
				this.initializeSecondBandDropdown();
				this.initializeMultiplierBandDropdown();
				this.initializeToleranceBandDropdown();
			}
		});
	}
	
	private final View.OnFocusChangeListener OnFocusChangeListener = (v, hasFocus) -> {
	String bandName = (String) v.getTag();
	if (hasFocus) setResistorBandOnFocus(bandName);
	else removeResistorBandOnFocus(bandName);
};
	
	private void setResistorBandOnFocus(String bandName)
	{
		binding.WebView4BandsResistor
			.evaluateJavascript(String.format("setFocus('%s')", bandName), null);
	}
	
	private void removeResistorBandOnFocus(String bandName)
	{
		binding.WebView4BandsResistor
			.evaluateJavascript(String.format("removeFocus('%s')", bandName), null);
	}
	
	private void updateResistorBandColor(String bandName, int color)
	{
		String hexColor = String.format("#%06X", (0xFFFFFF & color));
		binding.WebView4BandsResistor
			.evaluateJavascript(String.format("updateBandColor('%s', '%s')", bandName, hexColor), null);
	}
	
	private void calculateResistorValue()
	{
		boolean canCalculate =
			firstBand != null &&
			secondBand != null &&
			multiplierBand != null &&
			toleranceBand != null;
		
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
			binding.WebView4BandsResistor.requestFocus();
			binding.NestedScrollView.post(() ->
				binding.NestedScrollView.smoothScrollTo(0, binding.CardView4BandResistor.getTop()));
			
			double resistorValue = bandService.getFourBandsResistorValue(
				firstBand.getValue(),
				secondBand.getValue(),
				multiplierBand.getValue());
			
			String msg = bandService.getFormatedResistorNotation(resistorValue, toleranceBand.getValue());
			
			if (msg.equalsIgnoreCase(this.previousValue))
				return;
			this.previousValue = msg;
			resultTextView.setText(msg);
			
			Resistor resistor = new Resistor(
				ResistorType.FOUR_BAND,
				firstBand.getColor(),
				secondBand.getColor(),
				multiplierBand.getColor(),
				toleranceBand.getColor(),
				resistorValue, toleranceBand.getValue());
			
			resistorService.create(resistor);
		}
	}
}