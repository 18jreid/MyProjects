package com.example.contacts;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.contacts.R;

public class ImageSelector extends FrameLayout {
    String imageUri = "";
    AppCompatImageView imageView;

    public interface ImageSelectorClickListener {
        public void onClick();
    }
    public ImageSelector(Context context, ImageSelectorClickListener listener) {
        this(context, listener, "");
    }

    public ImageSelector(Context context, ImageSelectorClickListener listener, String imageUri) {
        super(context);
        this.imageUri = imageUri;
        imageView = new AppCompatImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        imageView.setLayoutParams(params);

        setImageUri(imageUri);

        setOnClickListener((View) -> {
            listener.onClick();
        });

        addView(imageView);
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
        if (imageUri.equals("")) {
            imageView.setImageResource(R.drawable.ic_baseline_add_a_photo_240);
        } else {
            imageView.setImageURI(Uri.parse(imageUri));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }
    }

    public String getImageUri() {
        return imageUri;
    }
}
