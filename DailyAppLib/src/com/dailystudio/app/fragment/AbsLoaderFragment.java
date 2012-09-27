package com.dailystudio.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;

public abstract class AbsLoaderFragment<T> extends Fragment implements LoaderCallbacks<T> {
    
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		bindIntent(getActivity().getIntent());
		
		getLoaderManager().initLoader(getLoaderId(), createLoaderArguments(), this);
	}
	
	public void onNewIntent(Intent intent) {
		bindIntent(intent);
		
		restartLoader();
	}

	protected void bindIntent(Intent intent) {
	}
	
	public void restartLoader() {
		getLoaderManager().restartLoader(getLoaderId(), createLoaderArguments(), this);
	}

	abstract protected int getLoaderId();
	abstract protected Bundle createLoaderArguments();

}