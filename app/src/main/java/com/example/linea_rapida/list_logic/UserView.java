package com.example.linea_rapida.list_logic;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linea_rapida.HomeFragmentAdmin;
import com.example.linea_rapida.MainActivity;
import com.example.linea_rapida.R;

public class UserView extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    private ConstraintLayout root;
    private TextView textView_name;
    private ImageView image_available;
    private ImageView button_options;

    public UserView(ConstraintLayout root) {
        super(root);

        this.root = root;
        textView_name = root.findViewById(R.id.textView_name);
        image_available = root.findViewById(R.id.image_available);
        button_options = root.findViewById(R.id.button_options);
        HomeFragmentAdmin fragment = HomeFragmentAdmin.newInstance();

        button_options.setLongClickable(false);
        button_options.setOnCreateContextMenuListener(this);

    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getTextView_name() {
        return textView_name;
    }

    public void setTextView_name(TextView textView_name) {
        this.textView_name = textView_name;
    }

    public ImageView getImage_available() {
        return image_available;
    }

    public void setImage_available(ImageView image_available) {
        this.image_available = image_available;
    }

    public ImageView getButton_options() {
        return button_options;
    }

    public void setButton_options(ImageButton button_options) {
        this.button_options = button_options;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle(textView_name.getText());
        menu.add(0, v.getId(), 0, "Editar");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Eliminar").setIcon(R.drawable.ic_baseline_delete_24);

    }

}
