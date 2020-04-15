package cat.urv.deim.asm.p2.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Tutorial1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView;
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial1, container, false);

        return rootView;
    }
}
    