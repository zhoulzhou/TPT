package com.example.tire.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tire.R;
import com.example.tire.common.LogUtils;

public class SortMainActivity extends AppCompatActivity {
    TextView mText;
    int[] mArray = {4,1,7,6,9,2,8,0,3,5};
    int[] mArrayA = {3,6,10,21,45,51,65,71,73,81,89,95,99};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_layout);

        mText = (TextView) findViewById(R.id.time_c);
        mText.setText("sort");

//        quitSort(mArray,0,9);
//        bubbleSort(mArray);
        binarySearch(mArrayA, 81);
    }

    private void quitSort(int[] array, int left, int right){
        if(left >= right){
            LogUtils.d("quitSort over");
            return ;
        }

        int index = partSort(array, left, right);
        quitSort(array, left, index-1);
        quitSort(array, index+1, right);
    }

    /**
     * 1、选取一个关键字(key)作为枢轴，一般取整组记录的第一个数/最后一个，这里采用选取序列最后一个数为枢轴。
     * 2、设置两个变量left = 0;right = N - 1;
     * 3、从left一直向后走，直到找到一个大于key的值，right从后至前，直至找到一个小于key的值，然后交换这两个数。
     * 4、重复第三步，一直往后找，直到left和right相遇，这时将key放置left的位置即可。
     *
     */
    private int partSort(int[] array, int left, int right){
        int keyIndex = right;//最好选用（第一个 最后一个  中间的） 三个中的中间值
        int key = array[keyIndex];
        while(left < right){
            while(left < right && array[left] <= key){
                ++left;
            }

            while(left < right && array[right] >= key){
                --right;
            }

            swap(array, left, right);
            showArray(array);
        }

        swap(array, left, keyIndex);
        showArray(array);
        return left;
    }

    private void swap(int[] arrary, int left, int right){
        int tmp = arrary[left];
        arrary[left] = arrary[right];
        arrary[right] = tmp;
    }

    private void showArray(int[] array){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<array.length; i++){
            sb.append(array[i]);
            sb.append("  ");
        }
        LogUtils.d("array= " + sb.toString());
    }

    private void bubbleSort(int[] arry){
        boolean isSwaped;
        for(int i=0; i<arry.length-1; i++){
            isSwaped = false;
            for(int j=0; j<arry.length-1-i; j++){
                if(arry[j] > arry[j+1]){
                    swap(arry,j, j+1);
                    isSwaped = true;
                }
                showArray(arry);
            }
            if(!isSwaped){
                break;
            }
        }
    }

    private int binarySearch(int[] array, int key){
        int low, high, mid;
        low = 0;
        high = array.length-1;
        while(low < high){
            mid = (low + high)/2;
            LogUtils.d("mid= " + mid + " value= " + array[mid]);
            if(array[mid] == key){
                LogUtils.d("find mid= " + mid);
                return mid;
            }

            if(array[mid] > key){
                high = mid - 1;
            }

            if(array[mid] < key){
                low = mid + 1;
            }
        }
        return -1;
    }
}
