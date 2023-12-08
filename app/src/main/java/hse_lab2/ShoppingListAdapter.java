package hse_lab2;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ItemsHolder> {
    public ShoppingListAdapter(List<Pair<String, String>> items) {
        this.shoppingList = items;
    }
    private final List<Pair<String, String>> shoppingList;

    private Context context;

    public void addNewItem(String itemName, String number, int position) {
        shoppingList.add(position, new Pair<>(itemName, number));
        notifyItemInserted(position);
    }

    public void deleteItem(int position) {
        shoppingList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        this.context = parent.getContext();
        return new ItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        holder.getItem().setText(shoppingList.get(position).first);
        holder.getQuantity().setText(context.getString(R.string.count_text, shoppingList.get(position).second));
        holder.getDeleteButton().setOnClickListener(t -> deleteItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public static class ItemsHolder extends RecyclerView.ViewHolder {

        private final TextView item;

        private final TextView quantity;

        private final Button deleteButton;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.thing);
            quantity = itemView.findViewById(R.id.quantity);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        public TextView getItem() {
            return item;
        }

        public TextView getQuantity() {
            return quantity;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }
    }
}