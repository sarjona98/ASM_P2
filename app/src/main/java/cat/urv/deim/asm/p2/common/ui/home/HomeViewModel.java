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
            mText.setValue("Esta es la página por defecto");
        } else if (Locale.getDefault().getLanguage().equals("ca")) {
            mText.setValue("Aquesta és la pàgina per defecte");
        } else {
            mText.setValue("This is Default page");
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}