package com.rad.services;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.rad.services.resistor.ResistorService;
import com.rad.models.Resistor;
import com.rad.models.types.ResistorType;
import com.rad.resistorcolorcode.R;
import com.rad.services.adapters.ResistorHistoryListViewAdapter;
import com.rad.services.listeners.ResistorManagerListener;
import com.rad.services.observables.ResistorHistoryViewModel;

import java.util.ArrayList;

public class HistoryBottomSheetDialog extends BottomSheetDialogFragment implements ResistorManagerListener {
    //region Attributes

    private ResistorHistoryViewModel resistorHistoryViewModel;

    private ResistorType resistorType;
    private ResistorService resistorService;
    private ArrayList<Resistor> resistor = new ArrayList<>();
    private ResistorHistoryListViewAdapter resistorHistoryListViewAdapter;

    private boolean isLoading = false;
    private final int PAGE_SIZE = 10;
    private int totalItemCountThreshold = 0;

    //endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.resistorService = new ResistorService(this.requireContext());
        this.resistorType = ResistorType.values()[args.getInt("resistorType")];
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.layout_bottom_sheet_history, container, false);
        ListView resistorHistoryListView = v.findViewById(R.id.ListViewPreviousValues);
        CircularProgressIndicator progressIndicator = v.findViewById(R.id.CircularProgressIndicator);
        progressIndicator.setIndeterminate(true);
        progressIndicator.hide();
        
        setObservable(v);

        resistor = resistorService.getPaginated(resistorType,0, 10);
        resistorHistoryListViewAdapter = new ResistorHistoryListViewAdapter(this.requireContext(), resistor);
        resistorHistoryListViewAdapter.setResistorManagerListener(this);
        resistorHistoryListView.setAdapter(resistorHistoryListViewAdapter);
        resistorHistoryViewModel.getCurrentSize().setValue(resistor.size());

        resistorHistoryListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if (firstVisibleItem == 0) return;

                if (!isLoading
                        && totalItemCount >= totalItemCountThreshold // Ensure we load only for new items
                        && firstVisibleItem + visibleItemCount >= totalItemCount - 5 // Threshold to load more
                        && totalItemCount > 0) { // Ensure there are items in the list
                    isLoading = true; // Set loading flag
                    totalItemCountThreshold = totalItemCount + PAGE_SIZE; // Increase threshold
                    
                    progressIndicator.show();
                    new android.os.Handler(Looper.getMainLooper()).postDelayed(() ->
                        {
	                        loadPaginatedData(); // Fetch more data
                            progressIndicator.hide();
	                    },
                        1500);
                }
            }
        });

        return v;
    }

    @Override
    public void onDeleteClick(int resistorId) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this.requireContext());
        builder
                .setTitle(R.string.resistor_delete_dialog_title)
                .setMessage(R.string.resistor_delete_dialog_message)
                .setPositiveButton(R.string.resistor_delete_dialog_yes, (dialog, id) ->
                {
                    resistorService.delete(resistorId);

                    int retrieveNumber = resistor.size();
                    resistorHistoryListViewAdapter.clear();
                    resistor = resistorService.getPaginated(this.resistorType, 0, retrieveNumber);
                    resistorHistoryListViewAdapter.addAll(resistor);
                    resistorHistoryListViewAdapter.notifyDataSetChanged();
                    resistorHistoryViewModel.getCurrentSize().setValue(resistor.size());

                    Toast
                        .makeText(this.requireContext(), R.string.resistor_deleted, Toast.LENGTH_SHORT)
                        .show();
                })
                .setNegativeButton(R.string.resistor_delete_dialog_no, (dialog, id) ->
                    Toast
                        .makeText(this.requireContext(), R.string.resistor_not_deleted, Toast.LENGTH_SHORT)
                        .show());

        // Create the AlertDialog.
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onInfoClick(String dateTime)
    {
        Toast
            .makeText(this.requireContext(), getString(R.string.resistor_created_at, dateTime), Toast.LENGTH_LONG)
            .show();

    }

    private void setObservable(View v) {
        resistorHistoryViewModel = new ViewModelProvider(this).get(ResistorHistoryViewModel.class);

        // Create the observer which updates the UI.
        final Observer<Integer> countObserver = newCount ->
        {
            // Update the UI, in this case, a TextView.
            TextView itemsCount = v.findViewById(R.id.TextViewItemsCount);
            if (newCount == 0)
                itemsCount.setText(getString(R.string.resistor_history_empty));
            else
            {
                int totalItemCount = resistorService.getCount(this.resistorType);
                itemsCount.setText(getString(R.string.resistor_history_size, newCount, totalItemCount));
            }
        };
        resistorHistoryViewModel.getCurrentSize().observe(this, countObserver);
    }

    private void loadPaginatedData()
    {
        int lastId = resistor.get(resistor.size() - 1).getId();
        ArrayList<Resistor> moreData = resistorService.getPaginated(this.resistorType, lastId, PAGE_SIZE);
        if (!moreData.isEmpty()) {
            resistor.addAll(moreData);
            resistorHistoryListViewAdapter.notifyDataSetChanged();
            resistorHistoryViewModel.getCurrentSize().setValue(resistor.size());
        }
        this.isLoading = false;
    }
}
