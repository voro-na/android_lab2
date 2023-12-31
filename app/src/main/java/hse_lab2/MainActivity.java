package hse_lab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private final ShoppingListAdapter ShoppingList = new ShoppingListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.productList);
        recyclerView.setAdapter(ShoppingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    public void addNewCardItem(View view) {
        EditText itemInput = findViewById(R.id.itemInput);
        String itemName = itemInput.getText().toString();
        itemInput.setText("");

        if (itemName.isEmpty()) {
            showError();
            return;
        }

        EditText countInput = findViewById(R.id.quantityInput);
        String thingQuantity = countInput.getText().toString();

        if (thingQuantity.isEmpty()){
            thingQuantity = "1";
        }
        countInput.setText("");

        hideError();
        ShoppingList.addNewItem(itemName, thingQuantity , 0);
    }

    private void showError() {
        TextView errorTextView = findViewById(R.id.error_message);
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        TextView errorMessage = findViewById(R.id.error_message);
        errorMessage.setVisibility(View.INVISIBLE);
    }
}