package pogostatus.studios.redleef.pokemongoserverstatus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        viewHolder.mLargeText.setText(itemData.get(position).Region);
        viewHolder.mSmallText.setText("");
        //Set color Categories
        switch(itemData.get(position).Status)
        {
            //0 is Online No Prob
            case 0:
                viewHolder.mSmallText.setBackgroundResource(R.drawable.button_wire_filled_green);
                viewHolder.mSmallText.setText("ONLINE");
                break;
            //1 is Offline is Prob
            case 1:
                viewHolder.mSmallText.setBackgroundResource(R.drawable.button_wire_filled_orange);
                viewHolder.mSmallText.setText("OFFLINE");
                break;
            //2 is Unreachable or unknown
            case 2:
                viewHolder.mSmallText.setBackgroundResource(R.drawable.button_wire_filled_grey);
                viewHolder.mSmallText.setText("UNKNOWN");
        }
        viewHolder.mImage.setText("");


    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mLargeText;
        public TextView mSmallText;
        public TextView mImage;
        public View itemLayoutView;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);
            this.itemLayoutView = itemLayoutView;
            mLargeText = (TextView)itemLayoutView.findViewById(R.id.cardTextLarge);
            mSmallText = (TextView)itemLayoutView.findViewById(R.id.cardTextSmall);
            mImage = (TextView)itemLayoutView.findViewById(R.id.small_card_image);
        }
    }

    @Override
    public int getItemCount()
    {
        return itemData.size();
    }
}