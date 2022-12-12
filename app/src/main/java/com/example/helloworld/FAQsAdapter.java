package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.ViewHolder> {
    // Adapter
    ArrayList<FAQsClass> arrayList;
    Context context;

    //arrow
    ImageView arrowIcon;

    // PreferredReferences
    ThemeSettings settings;

    public FAQsAdapter(ArrayList<FAQsClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FAQsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faqs_item_design,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.faqsTitle.setText(arrayList.get(position).title);
        holder.faqsDesc.setText(arrayList.get(position).description);

        holder.faqsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(position).isVisible){

                    holder.faqsDesc.setVisibility(View.GONE);
                    holder.descLine.setVisibility(View.GONE);
                    holder.titleLine.setVisibility(View.VISIBLE);
                    arrayList.get(position).isVisible = false;

                }else{
                    holder.faqsDesc.setVisibility(View.VISIBLE);
                    holder.descLine.setVisibility(View.VISIBLE);
                    holder.titleLine.setVisibility(View.GONE);
                    arrayList.get(position).isVisible = true;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView faqsTitle, faqsDesc;
        RelativeLayout titleLine, descLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            faqsTitle = itemView.findViewById(R.id.faqsTitle);
            faqsDesc = itemView.findViewById(R.id.faqsDesc);
            titleLine = itemView.findViewById(R.id.faqsTitleLine);
            descLine = itemView.findViewById(R.id.faqsDescLine);
        }
    }
}
