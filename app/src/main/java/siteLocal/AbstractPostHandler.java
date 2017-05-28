package siteLocal;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 5/24/2017.
 */

public abstract class AbstractPostHandler extends AsyncTask<Void, Void, AbstractData> {

    private static final String TAG_AbstractPostHandler = "AbstractPostHandler";
    protected Map<String,String> arguments;
    private final String serverName = "http://mobileapp3s.890m.com/Public";
    protected abstract AbstractData getModelInstance();
    protected abstract String getServerResources();
    protected abstract void onPostExecute(AbstractData result);

    protected AbstractPostHandler()
    {
        arguments = new HashMap<>();
    }
    @Override
    protected AbstractData doInBackground(Void... params)   {

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

        AbstractData model = getModelInstance();
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

        Log.d(TAG_AbstractPostHandler," in abstractPostHandler userAsjson: "+userAsJson );
        model.decode(userAsJson);

        Log.d(TAG_AbstractPostHandler," in abstractPostHandler model: "+ model.toString());

        return model;
    }
}
