package com.rad.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.BuildConfig;
import com.rad.resistorcolorcode.R;
import com.rad.resistorcolorcode.databinding.FragmentMainBinding;
import com.rad.services.ad.AdService;

public class FragmentMain extends Fragment {

    private static final String adMobAppId = BuildConfig.MAIN_ADMOB_ID;
    private static final String TAG = "FragmentMainActivity";

    /**
     * InterstitialAd reference.
     */
    private InterstitialAd mainInterstitialAd;
    
    /**
     * FragmentMainBinding reference.
     */
    private FragmentMainBinding binding;
    
    /**
     * AdRepository reference.
     */
    private AdService adService;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        adService = new AdService(getContext());
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(), adMobAppId, adRequest,
            new InterstitialAdLoadCallback()
            {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mainInterstitialAd = interstitialAd;
                    Log.i(TAG, "onAdLoaded");
                }
                
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.d(TAG, loadAdError.toString());
                    mainInterstitialAd = null;
                }
            });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        
        // Reload AdRequest.
        reloadAdRequest();
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
            showAdIfAvailable(ResistorType.FOUR_BAND)
        );

        binding.IncludeFiveBand.ButtonFiveBands.setOnClickListener(v ->
            showAdIfAvailable(ResistorType.FIVE_BAND)
        );

        binding.IncludeSixBand.ButtonSixBands.setOnClickListener(v ->
            showAdIfAvailable(ResistorType.SIX_BAND)
        );

        AdView adViewFourFive = binding.bannerAdView1;
        AdRequest adRequestFourFive = new AdRequest.Builder().build();
        adViewFourFive.loadAd(adRequestFourFive);

        AdView adViewFiveSix = binding.bannerAdView2;
        AdRequest adRequestFiveSix = new AdRequest.Builder().build();
        adViewFiveSix.loadAd(adRequestFiveSix);
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
    
    private void showAdIfAvailable(ResistorType type)
    {
        try {
            if (mainInterstitialAd == null || adService.canShowAd(600000))
            {
                navigateToResistorPage(type);
                return;
            }
            
            mainInterstitialAd.show(requireActivity());
            mainInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback()
            {
                @Override
                public void onAdDismissedFullScreenContent() {
                    adService.updateLastAdShown();
                    mainInterstitialAd = null;
                    navigateToResistorPage(type);
                }
                
                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    mainInterstitialAd = null;
                    navigateToResistorPage(type);
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        
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

    private void reloadAdRequest()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(), adMobAppId, adRequest,
            new InterstitialAdLoadCallback()
            {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mainInterstitialAd = interstitialAd;
                    Log.i(TAG, "onAdLoaded");
                }
                
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.d(TAG, loadAdError.toString());
                    mainInterstitialAd = null;
                }
            });
    }
}