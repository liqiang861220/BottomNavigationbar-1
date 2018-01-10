package com.monster.monstersport;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.monster.monstersport.fragment.LoadingFragment;
import com.monster.monstersport.fragment.TestFragment;
import com.monster.monstersport.fragment.ZwFragment;
import com.monster.monstersport.view.SViewPager;

import java.util.ArrayList;
import java.util.List;

import me.sugarkawhi.bottomnavigationbar.BottomNavigationBar;
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity;


public class MainActivity extends AppCompatActivity {

    private SViewPager mSViewPager;

    private List<BottomNavigationEntity> mEntities;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSViewPager = findViewById(R.id.viewPager);
        mSViewPager.setCanScroll(false);
        mSViewPager.setOffscreenPageLimit(4);
        final BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottomNavigationBar);

        mEntities = new ArrayList<>();
        mFragments = new ArrayList<>();
        mFragments.add(ZwFragment.newInstance());
        mEntities.add(new BottomNavigationEntity(
                R.drawable.hy_mian_icon_sc,
                R.drawable.hy_mian_icon_sc_down));
        mEntities.add(new BottomNavigationEntity(
                R.drawable.hy_mian_icon_sj,
                R.drawable.hy_mian_icon_sj_down));
        mEntities.add(new BottomNavigationEntity(
                R.drawable.hy_mian_icon_tj,
                R.drawable.hy_mian_icon_tj_down));
        mEntities.add(new BottomNavigationEntity(
                R.drawable.hy_mian_icon_wd,
                R.drawable.hy_mian_icon_wd_down));
        bottomNavigationBar.setEntities(mEntities);
        bottomNavigationBar.setBnbItemSelectListener(new BottomNavigationBar.IBnbItemSelectListener() {

            @Override
            public void onBnbItemSelect(int position) {
                mSViewPager.setCurrentItem(position);
            }
        });

        bottomNavigationBar.setBnbItemDoubleClickListener(new BottomNavigationBar.IBnbItemDoubleClickListener() {
            @Override
            public void onBnbItemDoubleClick(int position) {
                Toast.makeText(MainActivity.this, "onBnbItemDoubleClick " + position, Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigationBar.setCurrentPosition(0);

        mSViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
