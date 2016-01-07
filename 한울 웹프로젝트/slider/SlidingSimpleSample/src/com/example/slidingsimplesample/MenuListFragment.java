package com.example.slidingsimplesample;
 
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuListFragment extends ListFragment {
 
     
    public MenuListFragment(){
    }
 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, null);
    }
 
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SampleAdapter adapter = new SampleAdapter(getActivity());
        adapter.add(new SampleItem("메인화면", R.drawable.ic_action_phone));
        adapter.add(new SampleItem("모아보기", R.drawable.ic_action_view_as_grid));
        adapter.add(new SampleItem("사회과학", R.drawable.ic_action_storage));
        adapter.add(new SampleItem("문학소설", R.drawable.ic_action_storage));
        adapter.add(new SampleItem("판타지",R.drawable.ic_action_storage));
        setListAdapter(adapter);
        /*<item>사회과학</item>
        <item>문학소설</item>
        <item>판타지</item>*/
    }
 
    private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}
	}

 
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
         
        switch (position) {
        case 0:
            ((BaseActivity)getActivity()).fragmentReplace(0);
            break;
        case 1:
            ((BaseActivity)getActivity()).fragmentReplace(1);
            break;
 
        case 2:
            ((BaseActivity)getActivity()).fragmentReplace(2);
            break;
        case 3:
            ((BaseActivity)getActivity()).fragmentReplace(3);
            break;
        case 4:
            ((BaseActivity)getActivity()).fragmentReplace(4);
            break;
        }
        super.onListItemClick(l, v, position, id);
    }
}