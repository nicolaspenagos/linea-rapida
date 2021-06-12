package com.example.linea_rapida;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


    public CaseRecyclerViewAdapter(List<CaseTicket> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTabCaseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        this.position=position;
        holder.mItem = mValues.get(position);
        switch (holder.mItem.getStatus()) {
            case CaseTicket.STATUS_STARTED:
                holder.mImageViewStatus.setImageResource(R.drawable.green_dot);
                holder.mContentView.setText("Estado: Iniciado");
                break;
            case CaseTicket.STATUS_IN_PROGRESS:
                holder.mImageViewStatus.setImageResource(R.drawable.orange_dot);
                holder.mContentView.setText("Estado: En progreso");
                break;
            case CaseTicket.STATUS_FINISHED:
                holder.mImageViewStatus.setImageResource(R.drawable.red_dor);
                holder.mContentView.setText("Estado: Finalizado");
                break;
        }
        holder.mIdView.setText("Caso: "+holder.mItem.getNumber());
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mImageViewStatus;
        public final TextView mIdView;
        public final TextView mContentView;
        public CaseTicket mItem;

        public ViewHolder(FragmentTabCaseBinding binding) {
            super(binding.getRoot());
            mImageViewStatus = binding.imageViewStatus;
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            View view = binding.getRoot().getRootView();

            view.setOnClickListener((v)->{

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final View frag_case = v.inflate(v.getContext(), R.layout.fragment_case_ticket, null);
                //linearlayouts
                LinearLayout linearLayoutVertical = frag_case.findViewById(R.id.verticalLinearLayout);
                LinearLayout linearLayoutCase = frag_case.findViewById(R.id.linearLayoutCase);
                LinearLayout linearLayoutDate = frag_case.findViewById(R.id.linearLayoutDate);
                LinearLayout linearLayoutPacientName = frag_case.findViewById(R.id.linearLayoutPacientName);
                LinearLayout linearLayoutPacientAge = frag_case.findViewById(R.id.linearLayoutPacientAge);
                LinearLayout linearLayoutUsername = frag_case.findViewById(R.id.linearLayoutUsername);
                LinearLayout linearLayoutUserid = frag_case.findViewById(R.id.linearLayoutUserId);
                LinearLayout linearLayoutStatus = frag_case.findViewById(R.id.linearLayoutStatus);
                LinearLayout linearLayoutLocation = frag_case.findViewById(R.id.linearLayoutLocation);
                LinearLayout linearLayoutSymptoms = frag_case.findViewById(R.id.linearLayoutSymptoms);
                LinearLayout linearLayoutDiagnostics = frag_case.findViewById(R.id.linearLayoutDiagnostics);

                //value changes
                TextView textViewDiagnosticValue = frag_case.findViewById(R.id.textViewDiagnosticValue);
                TextView textViewDateValue = frag_case.findViewById(R.id.textViewDateValue);
                TextView textViewUserIdValue = frag_case.findViewById(R.id.textViewUserIdValue);
                TextView textViewUsernameValue = frag_case.findViewById(R.id.textViewUsernameValue);
                TextView textViewNumberVal = frag_case.findViewById(R.id.textViewNumberVal);
                TextView textViewStatusValue = frag_case.findViewById(R.id.textViewStatusValue);
                TextView textViewSymptomsValue = frag_case.findViewById(R.id.textViewSymptomValue);
                TextView textViewPacientName = frag_case.findViewById(R.id.textViewPacientName);
                TextView textViewPacientAge = frag_case.findViewById(R.id.textViewPacientAge);
                //static val
                TextView textView15 = frag_case.findViewById(R.id.textView15);
                TextView textView13 = frag_case.findViewById(R.id.textView13);
                TextView textView11 = frag_case.findViewById(R.id.textView11);
                TextView textView6 = frag_case.findViewById(R.id.textView6);
                TextView textView8 = frag_case.findViewById(R.id.textView8);
                TextView textView17 = frag_case.findViewById(R.id.textView17);
                TextView textView19 = frag_case.findViewById(R.id.textView19);
                TextView textView22 = frag_case.findViewById(R.id.textView22);
                TextView textView3 = frag_case.findViewById(R.id.textView3);
                TextView textView9 = frag_case.findViewById(R.id.textView9);
                TextView textView10 = frag_case.findViewById(R.id.textView10);

                Button dismissBtn = frag_case.findViewById(R.id.dismissBtn);
                Button locationBtn = frag_case.findViewById(R.id.locationBtn);
                Button changeStatusBtn = frag_case.findViewById(R.id.changeStatusBtn);

                textViewNumberVal.setText(mItem.getNumber()+"");
                textViewDateValue.setText(mItem.getDate()+"");
                textViewUsernameValue.setText(mItem.getUsername());
                textViewUserIdValue.setText(mItem.getUserid());
                textViewPacientName.setText(mItem.getPacientName());
                textViewPacientAge.setText(mItem.getPacientAge()+"");


                switch (mItem.getStatus()) {
                    case CaseTicket.STATUS_STARTED:
                        textViewStatusValue.setText("Iniciado");
                        break;
                    case CaseTicket.STATUS_IN_PROGRESS:
                        textViewStatusValue.setText("En proceso");
                        break;
                    case CaseTicket.STATUS_FINISHED:
                        textViewStatusValue.setText("Terminado");
                        break;
                }

                String[] strings = mItem.getBody().split(";");
                textViewSymptomsValue.setText(strings[0]);
                textViewDiagnosticValue.setText(strings[1]);

                builder.setView(frag_case);

                Dialog dialog = builder.create();

                dismissBtn.setOnClickListener(view1->{
                    dialog.dismiss();
                });

                locationBtn.setOnClickListener((view1)->{

                });

                changeStatusBtn.setOnClickListener((view2)->{
                    dialog.dismiss();
                });

                dialog.show();
            });
        }




        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}