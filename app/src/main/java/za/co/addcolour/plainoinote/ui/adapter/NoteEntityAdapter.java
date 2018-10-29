package za.co.addcolour.plainoinote.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import za.co.addcolour.plainoinote.R;
import za.co.addcolour.plainoinote.databinding.RowItemNoteBinding;
import za.co.addcolour.plainoinote.model.NoteEntity;
import za.co.addcolour.plainoinote.ui.clickCallback.NoteEntityClickCallback;
import za.co.addcolour.plainoinote.viewHolder.NoteViewHolder;

public class NoteEntityAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<? extends NoteEntity> mNoteEntityList;

    @Nullable
    private final NoteEntityClickCallback mNoteEntituClickCallback;

    public NoteEntityAdapter(@Nullable NoteEntityClickCallback noteEntityClickCallback) {
        mNoteEntituClickCallback = noteEntityClickCallback;
    }

    public void setNoteEntityList(final List<? extends NoteEntity> noteEntityList) {
        if (mNoteEntityList == null) {
            mNoteEntityList = noteEntityList;
            notifyItemRangeInserted(0, noteEntityList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mNoteEntityList.size();
                }

                @Override
                public int getNewListSize() {
                    return noteEntityList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mNoteEntityList.get(oldItemPosition).getId() == noteEntityList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NoteEntity newCategory = noteEntityList.get(newItemPosition);
                    NoteEntity oldCategory = mNoteEntityList.get(oldItemPosition);
                    return Objects.equals(newCategory.getId(), oldCategory.getId())
                            && newCategory.getDate() == oldCategory.getDate()
                            && newCategory.getText().equals(oldCategory.getText());
                }
            });
            mNoteEntityList = noteEntityList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemNoteBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.row_item_note,
                        parent, false);
        binding.setCallback(mNoteEntituClickCallback);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.mBinding.setData(mNoteEntityList.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNoteEntityList == null ? 0 : mNoteEntityList.size();
    }
}