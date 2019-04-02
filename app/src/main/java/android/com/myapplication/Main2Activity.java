package android.com.myapplication;

import android.com.myapplication.video.PermissionChecker;
import android.com.myapplication.video.RecordSettings;
import android.com.myapplication.video.ToastUtils;
import android.com.myapplication.video.VideoRecordActivity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {

    Button button_prev ;
    Button button_next ;

    private Spinner mPreviewSizeRatioSpinner;
    private Spinner mPreviewSizeLevelSpinner;
    private Spinner mEncodingModeLevelSpinner;
    private Spinner mEncodingSizeLevelSpinner;
    private Spinner mEncodingBitrateLevelSpinner;
    private Spinner mAudioChannelNumSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button_prev = findViewById(R.id.button_prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev();
            }
        });
        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        mPreviewSizeRatioSpinner = (Spinner) findViewById(R.id.PreviewSizeRatioSpinner);
        mPreviewSizeLevelSpinner = (Spinner) findViewById(R.id.PreviewSizeLevelSpinner);
        mEncodingModeLevelSpinner = (Spinner) findViewById(R.id.EncodingModeLevelSpinner);
        mEncodingSizeLevelSpinner = (Spinner) findViewById(R.id.EncodingSizeLevelSpinner);
        mEncodingBitrateLevelSpinner = (Spinner) findViewById(R.id.EncodingBitrateLevelSpinner);
        mAudioChannelNumSpinner = (Spinner) findViewById(R.id.AudioChannelNumSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_RATIO_TIPS_ARRAY);
        mPreviewSizeRatioSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_LEVEL_TIPS_ARRAY);
        mPreviewSizeLevelSpinner.setAdapter(adapter);
        mPreviewSizeLevelSpinner.setSelection(3);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_MODE_LEVEL_TIPS_ARRAY);
        mEncodingModeLevelSpinner.setAdapter(adapter);
        mEncodingModeLevelSpinner.setSelection(0);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_SIZE_LEVEL_TIPS_ARRAY);
        mEncodingSizeLevelSpinner.setAdapter(adapter);
        mEncodingSizeLevelSpinner.setSelection(7);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_BITRATE_LEVEL_TIPS_ARRAY);
        mEncodingBitrateLevelSpinner.setAdapter(adapter);
        mEncodingBitrateLevelSpinner.setSelection(2);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.AUDIO_CHANNEL_NUM_TIPS_ARRAY);
        mAudioChannelNumSpinner.setAdapter(adapter);
        mAudioChannelNumSpinner.setSelection(0);
    }

    public void prev(){
        if (isPermissionOK()) {
            Intent intent = new Intent(Main2Activity.this, VideoRecordActivity.class);
            intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO, mPreviewSizeRatioSpinner.getSelectedItemPosition());
            intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL, mPreviewSizeLevelSpinner.getSelectedItemPosition());
            intent.putExtra(VideoRecordActivity.ENCODING_MODE, mEncodingModeLevelSpinner.getSelectedItemPosition());
            intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL, mEncodingSizeLevelSpinner.getSelectedItemPosition());
            intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL, mEncodingBitrateLevelSpinner.getSelectedItemPosition());
            intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM, mAudioChannelNumSpinner.getSelectedItemPosition());
            startActivity(intent);
        }
    }
    public void next(){

    }

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            ToastUtils.s(this, "Some permissions is not approved !!!");
        }
        return isPermissionOK;
    }
}
