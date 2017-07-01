package br.com.a3ysoftwarehouse.vcdguest.ui.passenger;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseNfcActivity;
import br.com.a3ysoftwarehouse.vcdguest.util.Constants;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PassengerActivity extends BaseNfcActivity<IPassengerPresenter>
        implements IPassengerView {
    // Constants
    private static final String TAG = "PassengerActivity";

    // Views
    @BindView(R.id.passenter_tag_tv) TextView mTagTv;
    @BindView(R.id.passenter_cod_tv) TextView mCodTv;
    //    @BindView(R.id.passenter_data_tv) TextView mDataTv;
    @BindView(R.id.passenter_pax_tv) TextView mPaxTv;
    @BindView(R.id.passenter_quarto_tv) TextView mQuartoTv;
    @BindView(R.id.passenter_voo_tv) TextView mVooTv;
    @BindView(R.id.passenter_loc_tv) TextView mLocTv;
    @BindView(R.id.passenter_etkt_tv) TextView mEtktTv;
    @BindView(R.id.passenter_cia_tv) TextView mCiaTv;
    @BindView(R.id.passenter_grupo_tv) TextView mGrupoTv;
    @BindView(R.id.passenter_emissor_tv) TextView mEmissorTv;
    @BindView(R.id.passenter_agencia_tv) TextView mAgenciaTv;
    @BindView(R.id.passenter_escola_tv) TextView mEscolaTv;
    @BindView(R.id.passenter_sexo_tv) TextView mSexoTv;
    @BindView(R.id.passenter_datanascimento_tv) TextView mDataNascimentoTv;
    @BindView(R.id.passenter_cellpax_tv) TextView mCellPaxTv;
    @BindView(R.id.passenter_emailpax_tv) TextView mEmailPaxTv;
    @BindView(R.id.passenter_resppax_tv) TextView mRespPaxTv;
    @BindView(R.id.passenter_foneresp_tv) TextView mFoneRespTv;
    @BindView(R.id.passenter_emailresp_tv) TextView mEmailRespTv;
    //    @BindView(R.id.passenter_rommates_tv) TextView mRommatesTv;
    @BindView(R.id.passenter_rommates1_tv) TextView mRommates1Tv;
    @BindView(R.id.passenter_rommates2_tv) TextView mRommates2Tv;
    @BindView(R.id.passenter_rommates3_tv) TextView mRommates3Tv;
    //    @BindView(R.id.passenter_codr1_tv) TextView mCodr1Tv;
//    @BindView(R.id.passenter_codr2_tv) TextView mCodr2Tv;
//    @BindView(R.id.passenter_codr3_tv) TextView mCodr3Tv;
    @BindView(R.id.passenter_refeicoes_tv) TextView mRefeicoesTv;
    @BindView(R.id.passenter_opcionais_tv) TextView mOpcionaisTv;
    @BindView(R.id.passenter_opcional1_tv) TextView mOpcional1Tv;
    @BindView(R.id.passenter_opcional2_tv) TextView mOpcional2Tv;
    @BindView(R.id.passenter_opcional3_tv) TextView mOpcional3Tv;
    @BindView(R.id.passenter_opcional4_tv) TextView mOpcional4Tv;
    @BindView(R.id.passenter_opcional5_tv) TextView mOpcional5Tv;
    @BindView(R.id.passenter_opcional6_tv) TextView mOpcional6Tv;
    @BindView(R.id.passenter_opcional7_tv) TextView mOpcional7Tv;
    @BindView(R.id.register_bracelet_fab) FloatingActionButton mRegisterBraceletFab;
    @BindView(R.id.passenger_tb) Toolbar mPassengerTb;
    @BindView(R.id.profile_img) ImageView mProfileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        ButterKnife.bind(this);

        // Get passenger.
        Passenger passenger = getIntent().getParcelableExtra(Constants.Keys.PASSENGER);
        setPresenter(new PassengerPresenter(this, passenger));

        // Toolbar
        mPassengerTb.setTitle(passenger.getPAX());
        mPassengerTb.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(mPassengerTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showPassengerData(passenger);
    }

    @Override
    public void showPassengerData(Passenger passenger) {
        // Set Perfil Picture.
        Picasso.with(this)
                .load(Utils.getPerfilPictureUrl(passenger.getCOD()))
                .placeholder(new IconicsDrawable(App.getContext())
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(App.getContext().getResources().getColor(R.color.colorIcon))
                        .sizeDp(24))
                .error(new IconicsDrawable(App.getContext())
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(App.getContext().getResources().getColor(R.color.colorIcon))
                        .sizeDp(24))
                .into(mProfileImg);

        // Data
        mTagTv.setText((passenger.getTag() != null) ? passenger.getTag() : "");
        mCodTv.setText(passenger.getCOD());
        mPaxTv.setText(passenger.getPAX());
        mQuartoTv.setText(passenger.getQUARTO());
        mVooTv.setText(passenger.getVOO());
        mLocTv.setText(passenger.getLOC());
        mEtktTv.setText(passenger.getETKT());
        mCiaTv.setText(passenger.getCIA());
        mGrupoTv.setText(passenger.getGRUPO());
        mEmissorTv.setText(passenger.getEMISSOR());
        mAgenciaTv.setText(passenger.getAGENCIA());
        mEscolaTv.setText(passenger.getESCOLA());
        mSexoTv.setText(passenger.getSEXO());
        mDataNascimentoTv.setText(passenger.getDATANASCIMENTO());
        mCellPaxTv.setText(passenger.getCELPAX());
        mEmailPaxTv.setText(passenger.getEMAILPAX());
        mRespPaxTv.setText(passenger.getRESPPAX());
        mFoneRespTv.setText(passenger.getFONERESP());
        mEmailRespTv.setText(passenger.getEMAILRESP());
        mRommates1Tv.setText(passenger.getROMMATE1());
        mRommates2Tv.setText(passenger.getROMMATE2());
        mRommates3Tv.setText(passenger.getROMMATE3());
        mRefeicoesTv.setText(passenger.getREFEICOES());
        mOpcionaisTv.setText(passenger.getOPCIONAIS());
        mOpcional1Tv.setText(passenger.getOPCIONAL1());
        mOpcional2Tv.setText(passenger.getOPCIONAL2());
        mOpcional3Tv.setText(passenger.getOPCIONAL3());
        mOpcional4Tv.setText(passenger.getOPCIONAL4());
        mOpcional5Tv.setText(passenger.getOPCIONAL5());
        mOpcional6Tv.setText(passenger.getOPCIONAL6());
        mOpcional7Tv.setText(passenger.getOPCIONAL7());

        mRegisterBraceletFab.setImageBitmap(
                new IconicsDrawable(this)
                        .icon(GoogleMaterial.Icon.gmd_local_offer)
                        .color(Color.WHITE)
                        .sizeDp(24)
                        .toBitmap()
        );
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @OnClick(R.id.register_bracelet_fab)
    void onRegisterBraceletBtClick() {
        getPresenter().onRegisterBraceletBtClick();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        getPresenter().onTagRead(
                Utils.ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
    }
}
