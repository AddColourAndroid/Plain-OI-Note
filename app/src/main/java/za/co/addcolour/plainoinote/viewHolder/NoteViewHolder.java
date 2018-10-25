package za.co.addcolour.plainoinote.viewHolder;

import android.support.v7.widget.RecyclerView;

import za.co.addcolour.plainoinote.databinding.RowItemNoteBinding;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public RowItemNoteBinding mBinding;

    public NoteViewHolder(RowItemNoteBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}