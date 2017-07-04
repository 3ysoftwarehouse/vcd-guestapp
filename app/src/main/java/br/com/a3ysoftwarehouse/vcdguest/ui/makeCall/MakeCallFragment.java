package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.adapter.ListPassengerAdapter;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseFragment;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class MakeCallFragment extends BaseFragment<IMakeCallPresenter> implements IMakeCallView {
    // Constants
    private static final String TAG = "MakeCallFragment";
    private static final int SHOW_TIME = 500;

    // Views
    @BindView(R.id.passengers_not_present_rc) RecyclerView mPassengersNotPresentRc;
    @BindView(R.id.make_call_fab) FloatingActionButton mMakeCallFab;

    // Adapters
    private ListPassengerAdapter mPassengersPresentAdapter;
    private ListPassengerAdapter mPassengersNotPresentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_call, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new MakeCallPresenter(this));

        // Set initial icon
        mMakeCallFab.setImageBitmap(new IconicsDrawable(getActivity())
                .icon(GoogleMaterial.Icon.gmd_tap_and_play)
                .color(Color.WHITE)
                .sizeDp(24)
                .toBitmap()
        );

        // mPassengersNotPresentRc
        LinearLayoutManager passengersNotPresentLm = new GridLayoutManager(getActivity(), 3);
        mPassengersNotPresentRc.setLayoutManager(passengersNotPresentLm);

        mPassengersNotPresentAdapter =
                new ListPassengerAdapter(new ArrayList<Passenger>(), getPresenter());
        mPassengersNotPresentAdapter.setType(ListPassengerAdapter.GRID);

        mPassengersNotPresentRc.setAdapter(mPassengersNotPresentAdapter);

        return view;
    }

    @Override
    public void setPassengersNotPresentsRcData(List<Passenger> passengerList) {
        mPassengersNotPresentAdapter.setData(passengerList);
        mPassengersNotPresentAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMakeCallBtPlayIcon() {
        mMakeCallFab.setImageBitmap(new IconicsDrawable(getActivity())
                .icon(GoogleMaterial.Icon.gmd_tap_and_play)
                .color(Color.WHITE)
                .sizeDp(24)
                .toBitmap()
        );
    }

    @Override
    public void setMakeCallSaveIcon() {
        mMakeCallFab.setImageBitmap(new IconicsDrawable(getActivity())
                .icon(GoogleMaterial.Icon.gmd_save)
                .color(Color.WHITE)
                .sizeDp(24)
                .toBitmap()
        );
    }

    @Override
    public void showReleasePassengerDialog(Passenger passenger) {
        new MaterialDialog.Builder(getActivity())
                .title(getString(R.string.release_passenger_dialog_tittle) + " " + passenger.getPAX())
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getPresenter().onDialogPositiveBtClick();
                    }
                })
                .negativeText(R.string.disagree)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getPresenter().onDialogNegativeBtClick();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void showPassengerDialog(Passenger passenger) {
        View view = View.inflate(getActivity(), R.layout.passenger_dialog_layout, null);

        ImageView profilePicture = (ImageView) view.findViewById(R.id.passenger_profile_iv);
        TextView name = (TextView) view.findViewById(R.id.passenger_name_tv);

        Picasso.with(getActivity())
                .load(Utils.getPerfilPictureUrl(passenger.getCOD()))
                .into(profilePicture);

        name.setText(passenger.getPAX());

        final MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .customView(view, false)
                .show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, SHOW_TIME);
    }

    @OnClick(R.id.make_call_fab)
    public void onMakeCallBtClick() {
        getPresenter().onMakeCallBtClick();
    }
}
