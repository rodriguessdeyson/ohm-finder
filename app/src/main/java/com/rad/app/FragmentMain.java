package com.rad.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;
import com.rad.resistorcolorcode.databinding.FragmentMainBinding;

public class FragmentMain extends Fragment {

    /**
     * FragmentMainBinding reference.
     */
    private FragmentMainBinding binding;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeWebView(binding.IncludeFourBand.WebView4BandsResistor, "file:///android_asset/four_bands_resistor.html");
        initializeWebView(binding.IncludeFiveBand.WebView5BandsResistor, "file:///android_asset/five_bands_resistor.html");
        initializeWebView(binding.IncludeSixBand.WebView6BandsResistor, "file:///android_asset/six_bands_resistor.html");

        binding.IncludeFourBand.ButtonFourBands.setOnClickListener(v ->
                                                                       navigateToResistorPage(ResistorType.FOUR_BAND)
        );

        binding.IncludeFiveBand.ButtonFiveBands.setOnClickListener(v ->
                                                                       navigateToResistorPage(ResistorType.FIVE_BAND)
        );

        binding.IncludeSixBand.ButtonSixBands.setOnClickListener(v ->
                                                                     navigateToResistorPage(ResistorType.SIX_BAND)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initializeWebView(WebView webView, String url)
    {
        webView.loadUrl(url);
        webView.getSettings();
        webView.setBackgroundColor(0);
    }
    
    private void navigateToResistorPage(ResistorType type)
    {
        switch (type)
        {
	        case FOUR_BAND -> NavHostFragment.findNavController(FragmentMain.this)
	            .navigate(R.id.action_FirstFragment_to_SecondFragment);
	        case FIVE_BAND -> NavHostFragment.findNavController(FragmentMain.this)
	            .navigate(R.id.action_FirstFragment_to_ThirdFragment);
	        case SIX_BAND -> NavHostFragment.findNavController(FragmentMain.this)
	            .navigate(R.id.action_FirstFragment_to_FourthFragment);
        }
    }
}