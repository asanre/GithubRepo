package com.example.asanre.githubrepo.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asanre.githubrepo.R;
import com.example.asanre.githubrepo.domain.model.IRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private final Context context;
    private List<IRepository> repositories;
    private final AdapterOnClickListener listener;

    public RepoListAdapter(Context context, AdapterOnClickListener listener) {

        this.context = context;
        this.listener = listener;
        repositories = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.repo_list_item, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final IRepository currentRepo = repositories.get(position);

        holder.tvRepoName.setText(currentRepo.getName());
        holder.tvRepoOwner.setText(currentRepo.getOwnerLogin());
        holder.tvRepoDescription.setText(currentRepo.getDescription());

        final int color = ContextCompat.getColor(context,
                currentRepo.hasFork() ? R.color.white : R.color.light_green);
        holder.rlContainer.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {

        return repositories == null ? 0 : repositories.size();
    }

    public void addRepositories(List<IRepository> repoList) {

        this.repositories.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface AdapterOnClickListener {

        void onLongClick(IRepository repository);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_container)
        RelativeLayout rlContainer;
        @BindView(R.id.tv_name)
        TextView tvRepoName;
        @BindView(R.id.tv_owner)
        TextView tvRepoOwner;
        @BindView(R.id.tv_description)
        TextView tvRepoDescription;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnLongClick(R.id.rl_container)
        boolean onLongClick() {

            listener.onLongClick(repositories.get(getAdapterPosition()));
            return true;
        }
    }
}
