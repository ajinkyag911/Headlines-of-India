package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ajinkya.headlinesofindia.Article;
import com.example.ajinkya.headlinesofindia.ArticleAPI;
import com.example.ajinkya.headlinesofindia.ArticleActivity;
import com.example.ajinkya.headlinesofindia.Article_;
import adapters.CustomAdapter;
import com.example.ajinkya.headlinesofindia.R;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technology_tab_fragment extends Fragment {
    public static final String TAG = "Technology Tab";
    private ListView mArticlesListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.technology_tab_fragment,container,false);
        mArticlesListView = (ListView) view.findViewById(R.id.technologyArticles);
        getData();
        return view;
    }

    private void getData()
    {
        Call<Article> article = ArticleAPI.getService().getTechnologyArticles();
        article.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                final Article list = response.body();

                Article_[] articles = new Article_[list.getArticles().size()];
                for (int i=0 ; i<list.getArticles().size(); i++)
                {
                    articles[i] = list.getArticles().get(i);
                }

                ListAdapter myAdapter = null;
                try {
                    myAdapter = new CustomAdapter(getActivity(),articles);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                mArticlesListView.setAdapter(myAdapter);

                mArticlesListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                                intent.putExtra("MainActivity", (Serializable) list.getArticles().get(position));
                                startActivity(intent);
                            }
                        }

                );

            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(getActivity(),"Network Unavailable",Toast.LENGTH_LONG).show();

            }
        });
    }
}
