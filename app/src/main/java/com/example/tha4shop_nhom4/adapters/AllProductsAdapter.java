package com.example.tha4shop_nhom4.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tha4shop_nhom4.R;
import com.example.tha4shop_nhom4.models.AllProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ViewHolder> {
    private Context context;

    public AllProductsAdapter(Context context, List<AllProductsModel> list) {
        this.context = context;
        this.list = list;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    private List<AllProductsModel> list;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_products_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImage);
        holder.mCost.setText(String.valueOf(list.get(position).getPrice()) + " VNĐ");
        holder.mName.setText(list.get(position).getName());

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("ShowAll")
                        .document(list.get(position).getDocumentId())
                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    list.remove(list.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        TextView mCost;
        TextView mName;
        Button deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemImage = itemView.findViewById(R.id.item_image);
            mCost = itemView.findViewById(R.id.item_cost);
            mName = itemView.findViewById(R.id.item_name);
            deleteItem = itemView.findViewById(R.id.delete_product);
        }
    }
}
