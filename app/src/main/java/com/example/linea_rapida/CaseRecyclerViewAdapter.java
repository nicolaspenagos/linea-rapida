package com.example.linea_rapida;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.linea_rapida.model.CaseTicket;
import com.example.linea_rapida.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.linea_rapida.databinding.FragmentTabCaseBinding;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CaseRecyclerViewAdapter extends RecyclerView.Adapter<CaseRecyclerViewAdapter.ViewHolder> {

    private final List<CaseTicket> mValues;
    private  int position;
    private ViewGroup parent;

    public CaseRecyclerViewAdapter(List<CaseTicket> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.parent = parent;

        return new ViewHolder(FragmentTabCaseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        this.position=position;
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getUsername()+", Case: ");
        holder.mContentView.setText(mValues.get(position).getNumber()+"");
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mIdView;
        public final TextView mContentView;
        public CaseTicket mItem;

        public ViewHolder(FragmentTabCaseBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            final View frag_case=v.inflate(v.getContext(),R.layout.fragment_case_ticket,null);
            //value changes
            TextView textViewDiagnosticValue = frag_case.findViewById(R.id.textViewDiagnosticValue);
            TextView textViewDateValue = frag_case.findViewById(R.id.textViewDateValue);
            TextView textViewUserIdValue = frag_case.findViewById(R.id.textViewUserIdValue);
            TextView textViewUsernameValue = frag_case.findViewById(R.id.textViewUsernameValue);
            TextView textViewNumberVal = frag_case.findViewById(R.id.textViewNumberVal);
            TextView textViewStatusValue = frag_case.findViewById(R.id.textViewStatusValue);
            TextView textViewSymptomsValue = frag_case.findViewById(R.id.textViewSymptomValue);
            //static val
            TextView textView15 = frag_case.findViewById(R.id.textView15);
            TextView textView13 = frag_case.findViewById(R.id.textView13);
            TextView textView11 = frag_case.findViewById(R.id.textView11);
            TextView textView6 = frag_case.findViewById(R.id.textView6);
            TextView textView8 = frag_case.findViewById(R.id.textView8);
            TextView textView17 = frag_case.findViewById(R.id.textView17);
            TextView textView19 = frag_case.findViewById(R.id.textView19);
            TextView textView22 = frag_case.findViewById(R.id.textView22);

            Button locationBtn = frag_case.findViewById(R.id.locationBtn);
            Button changeStatusBtn = frag_case.findViewById(R.id.changeStatusBtn);

            textViewNumberVal.setText(mValues.get(position).getNumber()+"");
            textViewDateValue.setText(mValues.get(position).getDate()+"");
            textViewUsernameValue.setText(mValues.get(position).getUsername());
            textViewUserIdValue.setText(mValues.get(position).getUserid());
            String[] strings = mValues.get(position).getBody().split(";");
            textViewSymptomsValue.setText(strings[0]);
            textViewDiagnosticValue.setText(strings[1]);

            locationBtn.setOnClickListener((view)->{

            });

            changeStatusBtn.setOnClickListener(view->{

            });

            builder.setView(frag_case);
            Dialog dialog = builder.create();
            dialog.show();
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}