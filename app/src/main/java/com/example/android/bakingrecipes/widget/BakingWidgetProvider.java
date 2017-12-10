package com.example.android.bakingrecipes.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import com.example.android.bakingrecipes.R;

/**
 * Created by Noga on 12/5/2017.
 */

public class BakingWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent intent = new Intent(context, BakingWidgetRemoteViewsService.class);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_view);
        views.setTextViewText(R.id.recipe_name,BakingWidgetRemoteViewsService.name);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.recipe_name);

        for(int i = 0; i < appWidgetIds.length; i ++) {
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.putExtra("Random", Math.random() * 1000);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            views.setRemoteAdapter( R.id.widget_list, intent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds[i], R.id.widget_list);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        ComponentName thisWidget = new ComponentName(context.getApplicationContext(), BakingWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        onUpdate(context, appWidgetManager, appWidgetIds);
    }


}
