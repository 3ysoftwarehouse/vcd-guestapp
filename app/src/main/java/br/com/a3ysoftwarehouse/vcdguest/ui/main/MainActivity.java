package br.com.a3ysoftwarehouse.vcdguest.ui.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.observer.NfcTagIdObserver;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseNfcActivity;
import br.com.a3ysoftwarehouse.vcdguest.ui.listCall.ListCallFragment;
import br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger.ListPassengersFragment;
import br.com.a3ysoftwarehouse.vcdguest.ui.makeCall.MakeCallFragment;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseNfcActivity<IMainPresenter> implements IMainView, Drawer.OnDrawerItemClickListener {

    // Constants
    private static final String TAG = "MainActivity";
    private static final int LIST_PASSENGERS_ITEM = 0;
    private static final int MAKE_CALL_ITEM = 1;
    private static final int LIST_CALL_ITEM = 2;

    // Views
    @BindView(R.id.main_tb) Toolbar mMainTb;
    private Drawer mDrawer;

    // TagObserver
    private NfcTagIdObserver mNfcTagIdObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(
                getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setPresenter(new MainPresenter(this));

        // TagObserver
        mNfcTagIdObserver = NfcTagIdObserver.getInstance();

        // Set this fragment as default.
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new ListPassengersFragment());
        fragmentTransaction.commit();

        mMainTb.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mMainTb);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mMainTb)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(getResources()
                                        .getString(R.string.list_passengers))
                                .withIdentifier(LIST_PASSENGERS_ITEM),

                        new PrimaryDrawerItem()
                                .withName(getResources()
                                        .getString(R.string.make_call))
                                .withIdentifier(MAKE_CALL_ITEM),

                        new PrimaryDrawerItem()
                                .withName(getResources()
                                        .getString(R.string.list_call))
                                .withIdentifier(LIST_CALL_ITEM)
                )
                .withOnDrawerItemClickListener(this)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch ((int) drawerItem.getIdentifier()) {
            case LIST_PASSENGERS_ITEM:
                fragmentTransaction.replace(R.id.fragment_container, new ListPassengersFragment());
                break;

            case MAKE_CALL_ITEM:
                fragmentTransaction.replace(R.id.fragment_container, new MakeCallFragment());
                break;

            case LIST_CALL_ITEM:
                fragmentTransaction.replace(R.id.fragment_container, new ListCallFragment());
                break;
        }

        fragmentTransaction.commit();

        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mNfcTagIdObserver.notifyListeners(
                Utils.ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
    }
}
