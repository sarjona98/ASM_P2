package cat.urv.deim.asm.p2.common.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import cat.urv.deim.asm.Constant;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        if (Locale.getDefault().getLanguage().equals(Constant.SPANISH)){
            mText.setValue(Constant.DEFAULT_MESSAGE_SPANISH);
        } else if (Locale.getDefault().getLanguage().equals(Constant.CATALAN)) {
            mText.setValue(Constant.DEFAULT_MESSAGE_CATALAN);
        } else {
            mText.setValue(Constant.DEFAULT_MESSAGE);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}