package cooldomainname.com.thebesttexteditorever;

import android.content.Context;
import android.util.AttributeSet;

public class BetterSpinner extends android.support.v7.widget.AppCompatSpinner {

    public boolean initialized = false;

    public BetterSpinner(Context context) {
        super(context);
    }

    public BetterSpinner(Context context, int mode) {
        super(context, mode);
    }

    public BetterSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BetterSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
