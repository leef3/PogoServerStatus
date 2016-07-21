package pogostatus.studios.redleef.pogoserverstatus;

import java.util.ArrayList;

/**
 * Created by Fred Lee on 7/21/2016.
 */
public class CountryList {

    public static ArrayList<Country> countryList;
    Country toAdd;

    public CountryList()
    {
        if(countryList != null)
        {
            countryList.clear();
        }
        else
        {
            countryList = new ArrayList<Country>();
        }

        //Add all countries
        toAdd = new Country("Australia", R.drawable.aus);
        countryList.add(toAdd);
        toAdd = new Country("France", R.drawable.france);
        countryList.add(toAdd);
        toAdd = new Country("Germany", R.drawable.germany);
        countryList.add(toAdd);
        toAdd = new Country("Netherlands", R.drawable.netherlands);
        countryList.add(toAdd);
        toAdd = new Country("New Zealand", R.drawable.aus);
        countryList.add(toAdd);
        toAdd = new Country("Other", R.drawable.eu);
        countryList.add(toAdd);
        toAdd = new Country("United Kingdom", R.drawable.uk);
        countryList.add(toAdd);
        toAdd = new Country("United States", R.drawable.us);
        countryList.add(toAdd);
        toAdd = new Country("Italy", R.drawable.italy);
        countryList.add(toAdd);
        toAdd = new Country("Austria", R.drawable.austria);
        countryList.add(toAdd);
        toAdd = new Country("Belgium", R.drawable.belgium);
        countryList.add(toAdd);
        toAdd = new Country("Bulgaria", R.drawable.bulgaria);
        countryList.add(toAdd);
        toAdd = new Country("Canada", R.drawable.canada);
        countryList.add(toAdd);
        toAdd = new Country("Croatia", R.drawable.croatia);
        countryList.add(toAdd);
        toAdd = new Country("Czech", R.drawable.czech);
        countryList.add(toAdd);
        toAdd = new Country("Denmark", R.drawable.denmark);
        countryList.add(toAdd);
        toAdd = new Country("Estonia", R.drawable.estonia);
        countryList.add(toAdd);
        toAdd = new Country("Finland", R.drawable.finland);
        countryList.add(toAdd);
        toAdd = new Country("Greece", R.drawable.greece);
        countryList.add(toAdd);
        toAdd = new Country("Hungary", R.drawable.hungary);
        countryList.add(toAdd);
        toAdd = new Country("Iceland", R.drawable.iceland);
        countryList.add(toAdd);
        toAdd = new Country("Ireland", R.drawable.ireland);
        countryList.add(toAdd);
        toAdd = new Country("Luxembourg", R.drawable.luxembourg);
        countryList.add(toAdd);
        toAdd = new Country("Norway", R.drawable.norway);
        countryList.add(toAdd);
        toAdd = new Country("Poland", R.drawable.poland);
        countryList.add(toAdd);
        toAdd = new Country("Portugal", R.drawable.portugal);
        countryList.add(toAdd);
        toAdd = new Country("Romania", R.drawable.romania);
        countryList.add(toAdd);
        toAdd = new Country("Slovakia", R.drawable.slovakia);
        countryList.add(toAdd);
        toAdd = new Country("Slovenia", R.drawable.slovenia);
        countryList.add(toAdd);
        toAdd = new Country("Spain", R.drawable.spain);
        countryList.add(toAdd);
        toAdd = new Country("Sweden", R.drawable.sweden);
        countryList.add(toAdd);
        toAdd = new Country("Argentina", R.drawable.argentina);
        countryList.add(toAdd);
        toAdd = new Country("Brazil", R.drawable.brazil);
        countryList.add(toAdd);
        toAdd = new Country("Chile", R.drawable.chile);
        countryList.add(toAdd);
        toAdd = new Country("Hong Kong", R.drawable.hongkong);
        countryList.add(toAdd);
        toAdd = new Country("India", R.drawable.india);
        countryList.add(toAdd);
        toAdd = new Country("Japan", R.drawable.japan);
        countryList.add(toAdd);
        toAdd = new Country("Indonesia", R.drawable.indonesia);
        countryList.add(toAdd);
        toAdd = new Country("Philippines", R.drawable.phil);
        countryList.add(toAdd);
        toAdd = new Country("Russia", R.drawable.russia);
        countryList.add(toAdd);
        toAdd = new Country("Singapore", R.drawable.singapore);
        countryList.add(toAdd);
        toAdd = new Country("Taiwan", R.drawable.taiwan);
        countryList.add(toAdd);
        toAdd = new Country("Thailand", R.drawable.thailand);
        countryList.add(toAdd);
        toAdd = new Country("Turkey", R.drawable.turkey);
        countryList.add(toAdd);
    }

    public Country FindCountry(String toFind)
    {
        for(int x = 0; x < countryList.size(); x++)
        {
            if(toFind.contains(countryList.get(x).name))
            {
                return countryList.get(x);
            }
        }
        //Returns other
        return null;
    }

}
