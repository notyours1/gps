package yuseok.gps.senderapp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {

	double latitude;
	double longitude;

	TextView mStatus;
	LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		init();
		super.onCreate(savedInstanceState);
	}

	void init() {
		setContentView(R.layout.main);

		mStatus = (TextView) findViewById(R.id.status);
		findViewById(R.id.requsetBtn).setOnClickListener(
				new Button.OnClickListener() {

					@Override
					public void onClick(View v) {

						Toast.makeText(Main.this, "지도", Toast.LENGTH_SHORT)
								.show();

					}
				});

		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

	}

	protected void onResume() {

		if (locationManager.getAllProviders().contains(
				LocationManager.NETWORK_PROVIDER))
			locationManager
					.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
							5000, 0, locationListener);

		if (locationManager.getAllProviders().contains(
				LocationManager.GPS_PROVIDER))
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 500, 0, locationListener);

		super.onResume();

	};

	protected void onPause() {
		locationManager.removeUpdates(locationListener);
		super.onPause();

	};

	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			makeUseOfNewLocation(location);

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};

	void makeUseOfNewLocation(Location loc) {
		latitude = loc.getLatitude();
		longitude = loc.getLongitude();

		mStatus.append(latitude + ", " + longitude + "\n");

	}

}
