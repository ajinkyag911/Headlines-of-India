package adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ajinkya.headlinesofindia.Article_;
import com.example.ajinkya.headlinesofindia.MainActivity;
import com.example.ajinkya.headlinesofindia.R;
import com.squareup.picasso.Picasso;


public class CustomAdapter extends ArrayAdapter<Article_>{

    public TextView titleText;

    public CustomAdapter (Context context, Article_[] articles)
    {
        super(context, R.layout.articlelistlayout, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        Article_ article = getItem(position);
        final View customView;
            if (article.getUrlToImage() != null) {
                customView = myInflater.inflate(R.layout.articlelistlayout, parent, false);
                titleText = (TextView) customView.findViewById(R.id.listArticleTitle);
                ImageView articleImage = (ImageView) customView.findViewById(R.id.listArticleImage);
                Picasso.get().load(article.getUrlToImage()).into(articleImage);
                String titleItem = article.getTitle();
                titleText.setText(titleItem);
            } else {
                customView = myInflater.inflate(R.layout.articlelistlayoutwithoutimage, parent, false);
                titleText = (TextView) customView.findViewById(R.id.listArticleTitleWithoutImage);
                String titleItem = article.getTitle();
                titleText.setText(titleItem);
            }

        return customView;
    }

}
