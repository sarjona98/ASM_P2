package cat.urv.deim.asm.p2.common.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        if (Locale.getDefault().getLanguage().equals("es")){
            mText.setValue("Esta es la página de noticias");
        } else if (Locale.getDefault().getLanguage().equals("ca")) {
            mText.setValue("Aquesta és la pàgina de notícies");
        } else {
            mText.setValue("This is News page");
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}