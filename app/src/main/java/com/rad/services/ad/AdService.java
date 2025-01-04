package com.rad.services.ad;

import android.annotation.SuppressLint;
import android.content.Context;

import com.rad.database.ad.AdRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdService
{
	private final AdRepository adRepository;

	public AdService(Context context)
	{
		adRepository = new AdRepository(context);
	}
	
	/**
	 * Checks if the ad is to be shown.
	 * @return True or false.
	 */
	public boolean canShowAd(int maxTimeElapsed)
	{
		// Checks the last time an ad was shown.
		Date lastShowed = adRepository.getTimeLastAdShowed();
		if (lastShowed == null)
		{
			@SuppressLint("SimpleDateFormat")
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date date = new Date();
			adRepository.insert(dateFormat.format(date));
			return true;
		}
		
		Date date = new Date();
		return date.getTime() - lastShowed.getTime() <= maxTimeElapsed;
	}

	public void updateLastAdShown()
	{
		this.adRepository.update();
	}
}
