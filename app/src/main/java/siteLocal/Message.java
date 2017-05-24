package siteLocal;
import org.json.JSONObject;

class Message implements IData
{
    public String message;
    public boolean haveError;
    public IData data;
    public String token;
    
    
    @Override
    public void decode(JSONObject json)
    {
        try{
        init((JSONObject)json.get("data"),
            (String)json.get("message"),
            (boolean)json.get("haveError"),
            (String)json.get("token"));
    } catch (org.json.JSONException e) {
}
    }
    
    
    public Message()
    {
        
    }
    
    private void init(JSONObject data, String message,boolean haveError,String token)
    {
        this.message = message;
        this.haveError = haveError;
        if(data != null)
        {
            this.data.decode(data);
        }
        this.token = token;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", haveError=" + haveError + ", data=" + data + ", token=" + token + '}';
    }


    
}