package pogostatus.studios.redleef.pogoserverstatus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fred on 3/29/2016.
 */
public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder> {

    private ArrayList<StatusItem> itemData;

    public ListRecyclerAdapter(ArrayList<StatusItem> itemData)
    {
        this.itemData = itemData;
    }

    @Override
    public ListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_item, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position)
    {
        viewHolder.mLargeText.setText(itemData.get(position).mCountry.name);
        viewHolder.mSmallText.setText("");
        viewHolder.mSmallTextTwo.setText("");

        //Set color Categories
        switch(itemData.get(position).Status)
        {
            //0 is Online No Prob
            case 0:
                viewHolder.mSmallText.setBackgroundResource(R.drawable.button_wire_filled_green);
                viewHolder.mSmallText.setText("ONLINE");
                viewHolder.mSmallTextTwo.setVisibility(View.INVISIBLE);
                break;
            //1 is Offline is Prob
            case 1:
                viewHolder.mSmallText.setBackgroundResource(R.drawable.button_wire_filled_orange);
                viewHolder.mSmallText.setText("OFFLINE");

                if(itemData.get(position).Time.length() > 0)
                {
                    viewHolder.mSmallTextTwo.setVisibility(View.VISIBLE);
                    viewHolder.mSmallTextTwo.setBackgroundResource(R.drawable.button_wire_filled_grey);
                    viewHolder.mSmallTextTwo.setText(itemData.get(position).Time);
                }
                break;
            //2 is Unreachable or unknown
            case 2:
                viewHolder.mSmallTextTwo.setVisibility(View.INVISIBLE);
                viewHolder.mSmallText.setBackgroundResource(R.drawable.button_wire_filled_grey);
                viewHolder.mSmallText.setText("UNKNOWN");
        }
        viewHolder.mImage.setImageResource(itemData.get(position).mCountry.image);

        /*
        if(itemData.get(position).Region.contains("Australia"))
        {

        }else if(itemData.get(position).Region.contains("France"))
        {
            viewHolder.mImage.setImageResource(R.drawable.france);
        }else if(itemData.get(position).Region.contains("Germany"))
        {
            viewHolder.mImage.setImageResource(R.drawable.germany);
        }else if(itemData.get(position).Region.contains("Netherlands"))
        {
            viewHolder.mImage.setImageResource(R.drawable.netherlands);
        }else if(itemData.get(position).Region.contains("Zealand"))
        {
            viewHolder.mImage.setImageResource(R.drawable.aus);
        }else if(itemData.get(position).Region.contains("Other"))
        {
            viewHolder.mImage.setImageResource(R.drawable.eu);
        }else if(itemData.get(position).Region.contains("Kingdom"))
        {
            viewHolder.mImage.setImageResource(R.drawable.uk);
        }else if(itemData.get(position).Region.contains("States"))
        {
            viewHolder.mImage.setImageResource(R.drawable.us);
        }
        else
        {
            viewHolder.mImage.setImageResource(R.drawable.eu);
        }
        */


    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mLargeText;
        public TextView mSmallText;
        public TextView mSmallTextTwo;
        public ImageView mImage;
        public View itemLayoutView;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);
            this.itemLayoutView = itemLayoutView;
            mLargeText = (TextView)itemLayoutView.findViewById(R.id.cardTextLarge);
            mSmallText = (TextView)itemLayoutView.findViewById(R.id.cardTextSmall);
            mImage = (ImageView)itemLayoutView.findViewById(R.id.small_card_image);
            mSmallTextTwo = (TextView)itemLayoutView.findViewById(R.id.cardTextSmallTwo);
        }
    }

    @Override
    public int getItemCount()
    {
        return itemData.size();
    }
}