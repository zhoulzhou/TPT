package com.example.tire.test;

import android.test.ActivityInstrumentationTestCase2;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestMainActivityTest extends ActivityInstrumentationTestCase2<TestMainActivity> {

    private TestMainActivity testMainActivity;

    public TestMainActivityTest() {
        super(TestMainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        testMainActivity = getActivity();
    }

    @Test
    public void testcheckValue() {
        ArrayList<String> arrayList = new ArrayList<>(80);
        arrayList.add("panorama_getpanoramaonlinestate");
        arrayList.add("setting_gethomelighttime");
        arrayList.add("setting_getleavehomelighttime");
        arrayList.add("setting_getdriverseatback");
        arrayList.add("setting_getsteerback");
        arrayList.add("setting_getautolock");
        arrayList.add("setting_getremotecontrolupwindow");
        arrayList.add("setting_getlockupwindow");
        arrayList.add("setting_getremotecontroldownwindow");
        arrayList.add("setting_getlpswitchupwindow");
//        arrayList.add("setting_getlpswitchdownwindow");
//        arrayList.add("bodywork_getautotype");
//        arrayList.add("pm2p5_getpm2p5onlinestate");
//        arrayList.add("setting_getrearmirrorflip");
//        arrayList.add("setting_getbasepadautorise");
//        arrayList.add("setting_getdrivingrecorder");
//        arrayList.add("gearbox_getgearboxtype");
//        arrayList.add("setting_getelechandbrakestate");
//        arrayList.add("setting_getrearaconlinestate");
//        arrayList.add("ac_getactemperaturecontrolMode");
//        arrayList.add("setting_getunlocksettingonlinestate");
//        arrayList.add("bodywork_getmoonroofconfig");
//        arrayList.add("setting_feature_interior_atmosphere_lamp");
//        arrayList.add("engine_feature_engine_voice_simulator");
//        arrayList.add("setting_feature_seat_heating_and_ventilating");
//        arrayList.add("setting_feature_overspeed_locking");
//        arrayList.add("adas_feature_pcw");
//        arrayList.add("setting_getbackdoorelectricmodeonlinestate");
//        arrayList.add("setting_feature_rearview_mirror_follow_up");
//        arrayList.add("adas_feature_aeb");
//        arrayList.add("adas_feature_bsd");
//        arrayList.add("adas_feature_sla");
//        arrayList.add("adas_feature_lks_mode");
//        arrayList.add("adas_feature_lks_sensitivity");
//        arrayList.add("adas_feature_ldsw");
//        arrayList.add("adas_feature_hma");
//        arrayList.add("Setting_feature_front_windscreen_wiper_overhaul");
//        arrayList.add("Setting_feature_rear_windscreen_wiper_overhaul");
//        arrayList.add("setting_feature_power_steering");
//        arrayList.add("auto_study_item_40");
//        arrayList.add("setting_feature_engine_oil_detection");
//        arrayList.add("setting_feature_four_wheel_drive");
//        arrayList.add("adas_feature_adas_avh");
//        arrayList.add("setting_getsocconfig");
//        arrayList.add("audio_getdiraclivestate");
//        arrayList.add("instrument_getunit");
//        arrayList.add("setting_getacautoair");
//        arrayList.add("adas_feature_adas_ldw");
//        arrayList.add("ac_getrearacmaxwindlevel");
//        arrayList.add("ac_feature_ac_remote_ctl");
//        arrayList.add("engine_feature_engine_voice_source");
//        arrayList.add("settings_feature_inside_light");
//        arrayList.add("settings_feature_driver_seat_heating");
//        arrayList.add("settings_feature_driver_seat_ventilating");
//        arrayList.add("settings_feature_passenger_seat_heating");
//        arrayList.add("settings_feature_passenger_seat_ventilating");
//        arrayList.add("settings_feature_rear_left_seat_heating");
        arrayList.add("Settings.feature_rear_left_seat_ventilating");
        arrayList.add("Settings.FEATURE_rear_right_seat_heating");
        arrayList.add("settings.feature_REAR_right_seat_ventilating");
        arrayList.add("charging.feature_charge_by_appointment");
        arrayList.add("systemproperties_sys_probe_number");
        arrayList.add("instrument_pressure_unit");
        arrayList.add("instrument_oninstrumentscreentypechanged_unit");
        arrayList.add("instrument_oninstrumentscreentypechanged");
        arrayList.add("audio_getLoudspeakernum");
        arrayList.add("statistic_getevdrivingmileageconfig");
        arrayList.add("Settings_Level1_feature_driver_seat_heating_level1");
        arrayList.add("settings_level1_feature_passenger_seat_heating_leve1");
        arrayList.add("ac_getactype");

        for(int i=0; i<arrayList.size();i++){
            String source = arrayList.get(i);
            String target = source;
            testMainActivity.checkValue(source,target);
        }

    }
}