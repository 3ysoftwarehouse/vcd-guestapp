package br.com.a3ysoftwarehouse.vcdguest.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IRecyclerViewOnClickListener;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ListPassengerAdapter extends RecyclerView.Adapter<ListPassengerAdapter.ViewHolder> {
    public static final int LINEAR = 0;
    public static final int GRID = 1;
    private static final String TAG = "ListCallAdapter";
    private static IRecyclerViewOnClickListener<Passenger> mListener;
    private static List<Passenger> mPassengerList;
    private static int mType = LINEAR;

    public ListPassengerAdapter(List<Passenger> passengerList,
                                IRecyclerViewOnClickListener<Passenger> listener) {
        mPassengerList = passengerList;
        mListener = listener;
    }

    public void setType(int type) {
        mType = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (mType) {
            case LINEAR:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_passengers_recyclerview_layout, parent, false);

                return new ViewHolder(view);

            case GRID:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_passengers_recyclerview_grid_layout, parent, false);

                return new ViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Passenger p = mPassengerList.get(position);

        switch (mType) {
            case LINEAR:
                holder.nameTv.setText(p.getPAX());

                Picasso.with(App.getContext())
                        .load(Utils.getPerfilPictureUrl(p.getCOD()))
                        .placeholder(new IconicsDrawable(App.getContext())
                                .icon(GoogleMaterial.Icon.gmd_person)
                                .color(App.getContext().getResources().getColor(R.color.colorIcon))
                                .sizeDp(32))
                        .error(new IconicsDrawable(App.getContext())
                                .icon(GoogleMaterial.Icon.gmd_person)
                                .color(App.getContext().getResources().getColor(R.color.colorIcon))
                                .sizeDp(32))
                        .into(holder.passengerProfileIv);

                // Se houver tag, exibe o icone.
                if (p.getTag() == null || p.getTag().isEmpty()) {
                    holder.tagIv.setVisibility(View.GONE);

                } else {
                    holder.tagIv.setVisibility(View.VISIBLE);
                    holder.tagIv.setImageBitmap(new IconicsDrawable(App.getContext())
                            .icon(GoogleMaterial.Icon.gmd_local_offer)
                            .color(App.getContext().getResources().getColor(R.color.colorAccent))
                            .sizeDp(24)
                            .toBitmap()
                    );
                }
                break;

            case GRID:
                String[] name = p.getPAX().split(" ");

                holder.nameTv.setText(name[0] + " " + name[name.length - 1]);

                Picasso.with(App.getContext())
                        .load(Utils.getPerfilPictureUrl(p.getCOD()))
                        .placeholder(new IconicsDrawable(App.getContext())
                                .icon(GoogleMaterial.Icon.gmd_person)
                                .color(App.getContext().getResources().getColor(R.color.colorIcon))
                                .sizeDp(32))
                        .error(new IconicsDrawable(App.getContext())
                                .icon(GoogleMaterial.Icon.gmd_person)
                                .color(App.getContext().getResources().getColor(R.color.colorIcon))
                                .sizeDp(32))
                        .into(holder.passengerProfileIv);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mPassengerList.size();
    }

    public void setData(List<Passenger> passengerList) {
        mPassengerList = passengerList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView passengerProfileIv;
        TextView nameTv;
        ImageView tagIv;

        ViewHolder(View v) {
            super(v);

            switch (mType) {
                case LINEAR:
                    passengerProfileIv =
                            (CircularImageView) v.findViewById(R.id.passenger_profile_iv);
                    nameTv = (TextView) v.findViewById(R.id.passenger_name_tv);
                    tagIv = (ImageView) v.findViewById(R.id.tag_iv);
                    break;

                case GRID:
                    passengerProfileIv =
                            (CircularImageView) v.findViewById(R.id.passenger_profile_iv);
                    nameTv = (TextView) v.findViewById(R.id.passenger_name_tv);
                    break;
            }

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(
                            v, getAdapterPosition(), mPassengerList.get(getAdapterPosition())
                    );
                }
            });
        }
    }
}
