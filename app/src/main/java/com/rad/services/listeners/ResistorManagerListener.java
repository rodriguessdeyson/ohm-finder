package com.rad.services.listeners;

public interface ResistorManagerListener
{
    /**
     * Trigger when delete is clicked.
     * @param resistorId Identifier of element to delete.
     */
    void onDeleteClick(int resistorId);

    /**
     * Trigger when info is clicked.
     */
    void onInfoClick(String dateTIme);
}