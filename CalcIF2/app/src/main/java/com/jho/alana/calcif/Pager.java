package com.jho.alana.calcif;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.View.OnClickListener;

/**
 * Created by alana on 31/07/16.
 */
public class Pager extends PagerAdapter {

    TextView tvExpOrigin, tvFormatOrigin, tvExpPosfixa, tvFormatPosfixa;
    private Context context;
    private LayoutInflater layoutInflater;

    public Pager(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager, container, false);
        TextView tvExpOri    = (TextView) tvExpOrigin.findViewById(R.id.tvExpOrigin);
        TextView tvFormatOri = (TextView) tvFormatOrigin.findViewById(R.id.tvFormatOrigin);
        TextView tvExpPos    = (TextView) tvExpPosfixa.findViewById(R.id.tvExpPosfixa);
        TextView tvFormatPos = (TextView) tvFormatPosfixa.findViewById(R.id.tvFormatPosfixa);

        tvFormatOri.getText();
        tvFormatPos.getText();

        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }


}
