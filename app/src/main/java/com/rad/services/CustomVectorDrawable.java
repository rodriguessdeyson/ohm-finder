package com.rad.services;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import com.rad.models.Resistor;
import com.rad.utils.Parser;

public class CustomVectorDrawable extends Drawable
{
    /**
     * The type of the resistor.
     */
    private final Resistor resistor;

    /**
     * The paint used to draw the resistor.
     */
    private final Paint paint = new Paint();

    public CustomVectorDrawable(Resistor resistor) {
        paint.setAntiAlias(true);
        this.resistor = resistor;
    }

    @Override
    public void draw(@NonNull Canvas canvas)
    {
        int width = getBounds().width();
        int height = getBounds().height();

        // Scale factors to adapt viewport to drawable bounds
        float scaleX = width / 650.65f;
        float scaleY = height / 155.5f;
        canvas.scale(scaleX, scaleY);

        switch (this.resistor.getType())
        {
            case FOUR_BAND:
                fourBandResistor(canvas);
                break;
            case FIVE_BAND:
                fiveBandResistor(canvas);
                break;
            case SIX_BAND:
                sixBandResistor(canvas);
                break;
            case NONE:
                break;
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return 250;
    }

    @Override
    public int getIntrinsicHeight() {
        return 65;
    }

    private void fourBandResistor(Canvas canvas)
    {
        // Draw first gray line
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#7b7d86"));
        paint.setStrokeWidth(40.67f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(20.33f, 75.23f, 121.99f, 75.23f, paint);

        // Draw second gray line
        canvas.drawLine(630.32f, 75.23f, 528.65f, 75.23f, paint);

        // Draw the filled shape
        Path shapePath = new Path();
        shapePath.set(PathParser.createPathFromPathData("m121.99,132.17l-0,-111.83c-0,-12.2 8.13,-20.33 20.33,-20.33l50.83,0c4.07,0 6.1,0 8.13,2.03l42.7,20.33c2.03,2.03 6.1,2.03 8.13,2.03l142.33,0c4.07,0 6.1,0 8.13,-2.03l42.7,-20.33c2.03,-2.03 6.1,-2.03 8.13,-2.03l54.9,0c12.2,0 20.33,8.13 20.33,20.33l-0,111.83c-0,12.2 -8.13,20.33 -20.33,20.33l-50.83,0c-4.07,0 -6.1,0 -8.13,-2.03l-42.7,-20.33c-2.03,-2.03 -6.1,-2.03 -8.13,-2.03l-142.33,0c-4.07,0 -6.1,0 -8.13,2.03l-42.7,20.33c-6.1,2.03 -8.13,2.03 -12.2,2.03l-50.83,0c-12.2,0 -20.33,-8.13 -20.33,-20.33z"));
        shapePath.close();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#fab66e"));
        canvas.drawPath(shapePath, paint);

        // Draw first band
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getFirstBand())));
        paint.setStrokeWidth(30.67f);

        Path firstBandClipPath = new Path();
        firstBandClipPath.set(PathParser.createPathFromPathData("m121.99,132.17l-0,-111.83c-0,-12.2 8.13,-20.33 20.33,-20.33l50.83,0c4.07,0 6.1,0 8.13,2.03l42.7,20.33c2.03,2.03 6.1,2.03 8.13,2.03l142.33,0c4.07,0 6.1,0 8.13,-2.03l42.7,-20.33c2.03,-2.03 6.1,-2.03 8.13,-2.03l54.9,0c12.2,0 20.33,8.13 20.33,20.33l-0,111.83c-0,12.2 -8.13,20.33 -20.33,20.33l-50.83,0c-4.07,0 -6.1,0 -8.13,-2.03l-42.7,-20.33c-2.03,-2.03 -6.1,-2.03 -8.13,-2.03l-142.33,0c-4.07,0 -6.1,0 -8.13,2.03l-42.7,20.33c-6.1,2.03 -8.13,2.03 -12.2,2.03l-50.83,0c-12.2,0 -20.33,-8.13 -20.33,-20.33z"));
        Path firstBandPath = new Path();
        firstBandPath.set(PathParser.createPathFromPathData("M197.22,0L197.22,152.5"));

        canvas.clipPath(firstBandClipPath);
        canvas.drawPath(firstBandPath, paint);

        // Draw second band
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getSecondBand())));
        paint.setStrokeWidth(40.67f);

        Path secondBandClipPath = new Path();
        secondBandClipPath.set(PathParser.createPathFromPathData("m121.99,132.17l-0,-111.83c-0,-12.2 8.13,-20.33 20.33,-20.33l50.83,0c4.07,0 6.1,0 8.13,2.03l42.7,20.33c2.03,2.03 6.1,2.03 8.13,2.03l142.33,0c4.07,0 6.1,0 8.13,-2.03l42.7,-20.33c2.03,-2.03 6.1,-2.03 8.13,-2.03l54.9,0c12.2,0 20.33,8.13 20.33,20.33l-0,111.83c-0,12.2 -8.13,20.33 -20.33,20.33l-50.83,0c-4.07,0 -6.1,0 -8.13,-2.03l-42.7,-20.33c-2.03,-2.03 -6.1,-2.03 -8.13,-2.03l-142.33,0c-4.07,0 -6.1,0 -8.13,2.03l-42.7,20.33c-6.1,2.03 -8.13,2.03 -12.2,2.03l-50.83,0c-12.2,0 -20.33,-8.13 -20.33,-20.33z"));
        canvas.clipPath(secondBandClipPath);
        canvas.drawLine(274.49f, 0f, 274.49f, 152.5f, paint);

        // Draw multiplier band
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getMultiplierBand())));
        paint.setStrokeWidth(40.67f);
        canvas.drawLine(351.76f, 0f, 351.76f, 152.5f, paint);

        // Draw tolerance band
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getToleranceBand())));
        paint.setStrokeWidth(9f);
        canvas.drawLine(477.82f, 0f, 477.82f, 152.5f, paint);
    }

    private void fiveBandResistor(Canvas canvas)
    {
        // Draw first gray line
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#7b7d86"));
        paint.setStrokeWidth(40.67f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(20.33f, 75.23f, 121.99f, 75.23f, paint);

        // Draw second gray line
        canvas.drawLine(630.32f, 75.23f, 528.65f, 75.23f, paint);

        // Draw the filled shape
        Path shapePath = new Path();
        shapePath.set(PathParser.createPathFromPathData("M122,132.2V20.3C122,8.1 130.1,0 142.3,0h50.8c4.1,0 6.1,0 8.1,2L244,22.4c2,2 6.1,2 8.1,2h142.3c4.1,0 6.1,0 8.1,-2L445.3,2c2,-2 6.1,-2 8.1,-2h54.9c12.2,0 20.3,8.1 20.3,20.3v111.8c0,12.2 -8.1,20.3 -20.3,20.3h-50.8c-4.1,0 -6.1,0 -8.1,-2l-42.7,-20.3c-2,-2 -6.1,-2 -8.1,-2H256.2c-4.1,0 -6.1,0 -8.1,2l-42.7,20.3c-6.1,2 -8.1,2 -12.2,2h-50.8C130.1,152.5 122,144.4 122,132.2z"));
        shapePath.close();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#2F8CE7"));
        canvas.drawPath(shapePath, paint);

        // Draw first band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getFirstBand())));
        paint.setStrokeWidth(0);

        Path firstBandPath = new Path();
        firstBandPath.set(PathParser.createPathFromPathData("M193.2,152.5c4.1,0 6.1,0 12.2,-2l7.2,-3.4V7.4L201.3,2c-2,-2 -4.1,-2 -8.1,-2h-11.3v152.5H193.2z"));
        canvas.drawPath(firstBandPath, paint);

        // Draw second band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getSecondBand())));
        paint.setStrokeWidth(0);

        Path secondBandPath = new Path();
        secondBandPath.set(PathParser.createPathFromPathData("M269.7,24.4h-17.6c-2,0 -6.1,0 -8.1,-2l-4.9,-2.3v114.4l9,-4.3c2,-2 4.1,-2 8.1,-2h13.5V24.4z"));
        canvas.drawPath(secondBandPath, paint);

        // Draw third band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getThirdBand())));
        paint.setStrokeWidth(0);

        Path thirdBandPath = new Path();
        thirdBandPath.set(PathParser.createPathFromPathData("M296.2,24.4h30.7v103.7h-30.7z"));
        canvas.drawPath(thirdBandPath, paint);

        // Draw multiplier band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getMultiplierBand())));
        paint.setStrokeWidth(0);

        Path multiplierBandPath = new Path();
        multiplierBandPath.set(PathParser.createPathFromPathData("M353.4,24.4h30.7v103.7h-30.7z"));
        canvas.drawPath(multiplierBandPath, paint);

        // Draw tolerance band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getToleranceBand())));
        paint.setStrokeWidth(0);

        Path toleranceBandPath = new Path();
        toleranceBandPath.set(PathParser.createPathFromPathData("M478.09,0h9.87v152.38h-9.87z"));
        canvas.drawPath(toleranceBandPath, paint);
    }

    private void sixBandResistor(Canvas canvas)
    {
        // Draw first gray line
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#7b7d86"));
        paint.setStrokeWidth(40.67f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(20.33f, 75.23f, 121.99f, 75.23f, paint);

        // Draw second gray line
        canvas.drawLine(630.32f, 75.23f, 528.65f, 75.23f, paint);

        // Draw the filled shape
        Path shapePath = new Path();
        shapePath.set(PathParser.createPathFromPathData("M122,132.2V20.3C122,8.1 130.1,0 142.3,0h50.8c4.1,0 6.1,0 8.1,2L244,22.4c2,2 6.1,2 8.1,2h142.3c4.1,0 6.1,0 8.1,-2L445.3,2c2,-2 6.1,-2 8.1,-2h54.9c12.2,0 20.3,8.1 20.3,20.3v111.8c0,12.2 -8.1,20.3 -20.3,20.3h-50.8c-4.1,0 -6.1,0 -8.1,-2l-42.7,-20.3c-2,-2 -6.1,-2 -8.1,-2H256.2c-4.1,0 -6.1,0 -8.1,2l-42.7,20.3c-6.1,2 -8.1,2 -12.2,2h-50.8C130.1,152.5 122,144.4 122,132.2z"));
        shapePath.close();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#2F8CE7"));
        canvas.drawPath(shapePath, paint);

        // Draw first band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getFirstBand())));
        paint.setStrokeWidth(0);

        Path firstBandPath = new Path();
        firstBandPath.set(PathParser.createPathFromPathData("M193.2,152.5c4.1,0 6.1,0 12.2,-2l7.2,-3.4V7.4L201.3,2c-2,-2 -4.1,-2 -8.1,-2h-11.3v152.5H193.2z"));
        canvas.drawPath(firstBandPath, paint);

        // Draw second band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getSecondBand())));
        paint.setStrokeWidth(0);

        Path secondBandPath = new Path();
        secondBandPath.set(PathParser.createPathFromPathData("M269.7,24.4h-17.6c-2,0 -6.1,0 -8.1,-2l-4.9,-2.3v114.4l9,-4.3c2,-2 4.1,-2 8.1,-2h13.5V24.4z"));
        canvas.drawPath(secondBandPath, paint);

        // Draw third band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getThirdBand())));
        paint.setStrokeWidth(0);

        Path thirdBandPath = new Path();
        thirdBandPath.set(PathParser.createPathFromPathData("M296.2,24.4h30.7v103.7h-30.7z"));
        canvas.drawPath(thirdBandPath, paint);

        // Draw multiplier band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getMultiplierBand())));
        paint.setStrokeWidth(0);

        Path multiplierBandPath = new Path();
        multiplierBandPath.set(PathParser.createPathFromPathData("M353.4,24.4h30.7v103.7h-30.7z"));
        canvas.drawPath(multiplierBandPath, paint);

        // Draw tolerance band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getToleranceBand())));
        paint.setStrokeWidth(0);

        Path toleranceBandPath = new Path();
        toleranceBandPath.set(PathParser.createPathFromPathData("M461.82,0h16.27v152.38h-16.27z"));
        canvas.drawPath(toleranceBandPath, paint);

        // Draw ppm band
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(Parser.getHexColor(this.resistor.getPpmBand())));
        paint.setStrokeWidth(0);

        Path ppmBandPath = new Path();
        ppmBandPath.set(PathParser.createPathFromPathData("M486.23,0h16.27v152.38h-16.27z"));
        canvas.drawPath(ppmBandPath, paint);
    }
}
