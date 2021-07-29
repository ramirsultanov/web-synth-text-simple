package searchiarium.ct.com.websynthsimpletext.models;

import lombok.Data;

import java.time.OffsetTime;

@Data
public class TimeModel {

    private OffsetTime offsetTime;

    public TimeModel() {
        offsetTime = OffsetTime.now();
    }

}
