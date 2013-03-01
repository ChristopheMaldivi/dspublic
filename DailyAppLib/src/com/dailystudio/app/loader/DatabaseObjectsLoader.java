package com.dailystudio.app.loader;

import java.util.List;

import com.dailystudio.dataobject.DatabaseObject;
import com.dailystudio.dataobject.database.DatabaseConnectivity;
import com.dailystudio.dataobject.query.Query;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

public abstract class DatabaseObjectsLoader<T extends DatabaseObject> 
	extends AbsAsyncDataLoader<List<T>> {

    final ForceLoadContentObserver mObserver = new ForceLoadContentObserver();

	public DatabaseObjectsLoader(Context context) {
		super(context);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadInBackground() {
		if (mObserver != null) {
			getContext().getContentResolver().unregisterContentObserver(mObserver);
		}
		
		final Class<? extends DatabaseObject> objectClass = getObjectClass();
		if (objectClass == null) {
			return null;
		}
		
		final DatabaseConnectivity connectivity = 
			getDatabaseConnectivity(objectClass);
		if (connectivity == null) {
			return null;
		}
				
		final Query query = getQuery(objectClass);
		if (query == null) {
			return null;
		}
		
		final Class<? extends DatabaseObject> projectionClass =
			getProjectionClass();
	
		List<DatabaseObject> data = null;
		if (projectionClass == null) {
			data = connectivity.query(query);
		} else {
			data = connectivity.query(query, projectionClass);
		}
		
		
		Uri uri = connectivity.getDatabaseObserverUri();
		if (uri != null) {
			ContentResolver cr = getContext().getContentResolver();
			
			cr.registerContentObserver(uri, true, mObserver);
		}
		
		return (List<T>)data;
	}
	
	protected DatabaseConnectivity getDatabaseConnectivity(
			Class<? extends DatabaseObject> objectClass) {
		return new DatabaseConnectivity(getContext(), objectClass);
	}
	
	protected Query getQuery(Class<? extends DatabaseObject> klass) {
		return new Query(klass);
	}
	
	protected Class<? extends DatabaseObject> getProjectionClass() {
		return null;
	}

	abstract protected Class<? extends DatabaseObject> getObjectClass();
	
}
