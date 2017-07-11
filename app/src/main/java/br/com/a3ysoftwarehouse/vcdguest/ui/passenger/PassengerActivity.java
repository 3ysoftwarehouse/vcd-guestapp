package br.com.a3ysoftwarehouse.vcdguest.ui.passenger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.DataManager;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Tag;
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
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private static final int GET_PERMISSIONS_REQUEST_CODE = 100;

    // Views
    @BindView(R.id.passenger_tag_tv) TextView mTagTv;
    @BindView(R.id.passenger_cod_tv) TextView mCodTv;
    @BindView(R.id.passenger_pax_tv) TextView mPaxTv;
    @BindView(R.id.passenger_quarto_tv) TextView mQuartoTv;
    @BindView(R.id.passenger_voo_tv) TextView mVooTv;
    @BindView(R.id.passenger_loc_tv) TextView mLocTv;
    @BindView(R.id.passenger_etkt_tv) TextView mEtktTv;
    @BindView(R.id.passenger_cia_tv) TextView mCiaTv;
    @BindView(R.id.passenger_grupo_tv) TextView mGrupoTv;
    @BindView(R.id.passenger_emissor_tv) TextView mEmissorTv;
    @BindView(R.id.passenger_agencia_tv) TextView mAgenciaTv;
    @BindView(R.id.passenger_escola_tv) TextView mEscolaTv;
    @BindView(R.id.passenger_sexo_tv) TextView mSexoTv;
    @BindView(R.id.passenger_datanascimento_tv) TextView mDataNascimentoTv;
    @BindView(R.id.passenger_cellpax_tv) TextView mCellPaxTv;
    @BindView(R.id.passenger_emailpax_tv) TextView mEmailPaxTv;
    @BindView(R.id.passenger_resppax_tv) TextView mRespPaxTv;
    @BindView(R.id.passenger_foneresp_tv) TextView mFoneRespTv;
    @BindView(R.id.passenger_emailresp_tv) TextView mEmailRespTv;
    @BindView(R.id.passenger_rommates1_tv) TextView mRommates1Tv;
    @BindView(R.id.passenger_rommates2_tv) TextView mRommates2Tv;
    @BindView(R.id.passenger_rommates3_tv) TextView mRommates3Tv;
    @BindView(R.id.passenger_seguro_viagem_tv) TextView mSeguroViagemTv;
    @BindView(R.id.passenger_opcionais_tv) TextView mOpcionaisTv;
    @BindView(R.id.passenger_opcional1_tv) TextView mOpcional1Tv;
    @BindView(R.id.passenger_opcional2_tv) TextView mOpcional2Tv;
    @BindView(R.id.passenger_opcional3_tv) TextView mOpcional3Tv;
    @BindView(R.id.passenger_opcional4_tv) TextView mOpcional4Tv;
    @BindView(R.id.passenger_opcional5_tv) TextView mOpcional5Tv;
    @BindView(R.id.passenter_opcional6_tv) TextView mOpcional6Tv;
    @BindView(R.id.passenger_opcional7_tv) TextView mOpcional7Tv;
    @BindView(R.id.passenger_medical_record_bt) Button mMedicalRecordBt;
    @BindView(R.id.register_bracelet_fab) FloatingActionButton mRegisterBraceletFab;
    @BindView(R.id.passenger_tb) Toolbar mPassengerTb;
    @BindView(R.id.profile_img) ImageView mProfileImg;

    // Passenger
    private Passenger mPassenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        ButterKnife.bind(this);

        // Toolbar
        mPassengerTb.setTitle(mPassenger.getPAX());
        mPassengerTb.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(mPassengerTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showPassengerData(mPassenger);
    }

    @Override
    protected IPassengerPresenter initPresenter() {
        String paxCod = getIntent().getStringExtra(Constants.Keys.PASSENGER);
        mPassenger = DataManager.getInstance().getPassenger(paxCod);

        return new PassengerPresenter(this, mPassenger);
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

        // Tag text
        String tagTxt = "";

        for (Tag t : passenger.getTagList()) {
            tagTxt += t.getTag() + "\n";
        }

        // Data
        mTagTv.setText(tagTxt);
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
        mSeguroViagemTv.setText(passenger.getREFEICOES());
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

    @Override
    public void openPdfIntent(File file) {
        Uri path = FileProvider.getUriForFile(
                this, this.getApplicationContext().getPackageName() + ".my.package.name.provider",
                file);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent chooser = Intent.createChooser(intent, "Abrir arquivo");

        startActivity(chooser);
    }

    @Override
    public void checkPermission() {
        int write =
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (write != PackageManager.PERMISSION_GRANTED
                || read != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, GET_PERMISSIONS_REQUEST_CODE);

        } else {
            getPresenter().permissionsGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case GET_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    getPresenter().permissionsGranted();

                } else {

                    getPresenter().permissionsNotGranted();
                }
                break;
        }
    }

    @OnClick(R.id.register_bracelet_fab)
    void onRegisterBraceletBtClick() {
        getPresenter().onRegisterBraceletBtClick();
    }

    @OnClick(R.id.passenger_medical_record_bt)
    void onMedicalRecordBtClick() {
        getPresenter().onMedicalRecordBtClick();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        getPresenter().onTagRead(
                Utils.ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
