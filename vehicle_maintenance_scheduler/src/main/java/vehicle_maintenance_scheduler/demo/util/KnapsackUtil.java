package vehicle_maintenance_scheduler.demo.util;

import vehicle_maintenance_scheduler.demo.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class KnapsackUtil {

    public static List<Vehicle> optimize(
            List<Vehicle> vehicles,
            int capacity
    ) {

        int n = vehicles.size();

        int[][] dp =
                new int[n + 1][capacity + 1];

        for(int i=1;i<=n;i++){

            Vehicle v = vehicles.get(i - 1);

            for(int w=0;w<=capacity;w++){

                if(v.getDuration() <= w){

                    dp[i][w] = Math.max(

                            v.getImpact()
                                    + dp[i - 1]
                                    [w-v.getDuration()],

                            dp[i - 1][w]
                    );
                }
                else{
                    dp[i][w] =
                            dp[i - 1][w];
                }
            }
        }

        List<Vehicle> selected =
                new ArrayList<>();

        int w = capacity;

        for(int i=n;i>0;i--){

            if(dp[i][w]
                    != dp[i-1][w]){

                Vehicle v =
                        vehicles.get(i-1);

                selected.add(v);

                w -= v.getDuration();
            }
        }

        return selected;
    }
}