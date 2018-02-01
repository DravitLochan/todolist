package king.com.to_do_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DravitLochan on 06-01-2018.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{

    Context context;
    ArrayList<ToDoListItem> items;

    public ListItemAdapter(Context context, ArrayList<ToDoListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(items.get(position).getTitle());
        // holder.sample_text.setText(items.get(position).getSample_text());
        holder.date.setText(items.get(position).getDate().substring(0, 10) + items.get(position).getDate().substring(29, 34));
        int len = items.get(position).getSample_text().length();
        len = len > 10 ? 10 : len;
        if(len == 0) {
            holder.sample_text.setText("");
        } else {
            holder.sample_text.setText(items.get(position).getSample_text().substring(0, len-1));
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewToDo.class);
                intent.putExtra("itemNumber", items.get(position).getId() + "");
                context.startActivity(intent);
            }
        });
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ToDoListItem toDoListItem = ToDoListItem.findById(ToDoListItem.class, items.get(position).getId());
                toDoListItem.delete();
                items.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date, sample_text;
        CardView item;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            sample_text = itemView.findViewById(R.id.sample_text);
            item = itemView.findViewById(R.id.item);
        }
    }
}
