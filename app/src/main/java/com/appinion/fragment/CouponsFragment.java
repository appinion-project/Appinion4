package com.appinion.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appinion.app.MainActivity;
import com.appinion.app.R;
import com.appinion.app.UpdateProfileActivity;
import com.appinion.utils.MaterialUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

public class CouponsFragment extends BaseFragment {
    View view;
    View view1;
    private GridView mCouponsGrid;
    CustomAdapter mAdapter;
    private Dialog mCouponDetailDialog;
    private TextView mCouponCodeTv;
    private Button mCloseBtn;
    private ImageView mBarCodeIv;

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;
    private String couponCode = "1236352372537";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coupons, container, false);
        //view1= inflater.inflate(R.layout.listitemscoupons, container, false);
        try {
            initComponenets();
            prepareView();
           // setActionListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    private void initComponenets() {
        mCouponsGrid = (GridView) view.findViewById(R.id.frag_coupons_gridview);
        mCouponDetailDialog = new Dialog(getActivity());
        mCouponDetailDialog.setContentView(R.layout.dialog_coupondetail);
        mCloseBtn = (Button) mCouponDetailDialog.findViewById(R.id.dialog_coupon_btn_close);
        mBarCodeIv = (ImageView) mCouponDetailDialog.findViewById(R.id.dialog_coupon_iv_barcode);
        mCouponCodeTv = (TextView) mCouponDetailDialog.findViewById(R.id.dialog_coupon_iv_barcodenumber);
        mAdapter = new CustomAdapter(getActivity(), R.layout.listitemscoupons);
        mCouponsGrid.setAdapter(mAdapter);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCouponDetailDialog.dismiss();
            }
        });




    }

    private void prepareView() {
    }

    private void setActionListener() {
        mCouponsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    mCouponCodeTv.setText(couponCode);
                    mBarCodeIv.setImageBitmap(encodeAsBitmap(couponCode, BarcodeFormat.CODE_128, 600, 300));
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                mCouponDetailDialog.show();
            }
        });

//        mCloseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCouponDetailDialog.dismiss();
//            }
//        });

    }


    public class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return 31;
            //use save and send
        }

        /**
         * Appinion webservice goes here.
         * We will need a coupon webservice for this GridView. It should have a unique coupon id, title, full image url and thumb url.
         * <p/>
         * Using the Holder pattern reduces the overload on a scrollable view as it reduces the cell recreation by recycling the cells which go out of the view.
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.listitemscoupons, parent, false);
                holder.mTextTv = (TextView) convertView.findViewById(R.id.listitemscoupons_tv_title);
                holder.buttonUse=(Button)convertView.findViewById(R.id.button_Use);
                holder.imgCoupon=(ImageView)convertView.findViewById(R.id.imageViewCoupon);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.mTextTv.setText("Coupon " + position);

            holder.buttonUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast toast=Toast.makeText(getContext(),"Hello",Toast.LENGTH_LONG);
                    //toast.show();
                    try {
                        mCouponCodeTv.setText(couponCode);
                        mBarCodeIv.setImageBitmap(encodeAsBitmap(couponCode, BarcodeFormat.CODE_128, 600, 300));
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    mCouponDetailDialog.show();
                }
            });

            //holder.imgCoupon.setImageResource(R.mipmap.name);

            return convertView;
        }

        public class Holder {
            TextView mTextTv;
            Button buttonUse;
            ImageView imgCoupon;
        }
    }

    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}
