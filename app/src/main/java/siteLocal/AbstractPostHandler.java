package siteLocal;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 5/24/2017.
 */

public abstract class AbstractPostHandler extends AsyncTask<Void, Void, IData> {

    protected Map<String,String> arguments;
    protected final String serverName = "http://mobileapp3s.890m.com/Public";
    protected abstract IData getModelInstance();
    protected abstract String getServerResources();
    protected abstract void onPostExecute(IData result);

    protected AbstractPostHandler()
    {
        arguments = new HashMap<>();
    }
    @Override
    protected IData doInBackground(Void... params)   {
        try {
            HttpUtility.sendPostRequest(serverName + getServerResources(),arguments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = null;
        try {
            data = HttpUtility.readSingleLineResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpUtility.disconnect();

        IData model = getModelInstance();
        JSONObject messageResponse = null;
        try {
             messageResponse = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Boolean isValid =false;
        try {
             isValid = (boolean)messageResponse.get("isValid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject userAsJson = new JSONObject();
        if(isValid) {
            try {
                userAsJson = (JSONObject)messageResponse.get("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        model.decode(userAsJson);

        return model;
    }
}
