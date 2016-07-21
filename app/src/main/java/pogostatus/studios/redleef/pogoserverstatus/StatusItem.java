package pogostatus.studios.redleef.pogoserverstatus;

/**
 * Created by Fred Lee on 7/8/2016.
 */
public class StatusItem {
    public String Time;
    public int Status;
    public Country mCountry;

    public StatusItem(Country mCountry, int mStatus, String mTime)
    {
        this.mCountry = mCountry;
        this.Status = mStatus;
        this.Time = mTime;

    }
}
