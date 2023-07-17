package com.pius.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data // 롬복에서 getter, setter, equals(), hashCode(), toString() method를 자동으로 생성해 데이터클래스를 만든다.
@NoArgsConstructor // 롬복에 parameter가 없는 생성자를 만들도록 지시해 argument가 필요하지 않음
@AllArgsConstructor // 롬복에 모든 필드를 parameter로 사용하는 생성자를 만들도록 지시하고, 모든 맴버 변수에 argument를 제공
/* JsonIgnoreProperties(ignoreUnknown = true)
* JSON 응답 필드 중에서 클래스에 상응하는 멤버 변수가 없는 경우, JSON 응답 필드를 무시하도록 지시
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {
    @Id // annotation이 달린 멤버 변수가 데이터베이스 항목/레코드의 고유 식별자를 가지도록 지정
    private String id;
    private String callsign, squawk, reg, flightno, route, type, category;
    private int altitude, heading, speed;
    @JsonProperty("vert_rate")
    private int vertRate;
    @JsonProperty("selected_altitude")
    private int selectedAltitude;
    private double lat, lon, barometer;
    @JsonProperty("polar_distance")
    private double polarDistance;
    @JsonProperty("polar_bearing")
    private double polarBearing;
    @JsonProperty("is_adsb")
    private boolean isADSB;
    @JsonProperty("is_on_ground")
    private boolean isOnGround;
    @JsonProperty("last_seen_time")
    private Instant lastSeenTime;
    @JsonProperty("pos_update_time")
    private Instant posUpdateTime;
    @JsonProperty("bds40_seen_time")
    private Instant bds40SeenTime;

    // 세 Instant 멤버 변수는 Instant::parse method를 호출해 JSON 값을 parse하고
    // String에서 복잡한 데이터 타입으로 변환해야 한다.
    public String getLastSeenTime() {
        return lastSeenTime.toString();
    }

    public void setLastSeenTime(String lastSeenTime){
        if (null != lastSeenTime){
            this.lastSeenTime = Instant.parse(lastSeenTime);
        } else {
            // 해당 값이 없다면(null) 다른 로직을 수행해 parse()에 null 값을 전달하지 않고 setter를 통해
            // 해당 멤버 변수에 의미 있는 대체 값을 할당한다.
            this.lastSeenTime = Instant.ofEpochSecond(0);
        }
    }

    public String getPosUpdateTime() {
        return posUpdateTime.toString();
    }

    public void setPosUpdateTime(String posUpdateTime){
        if (null != posUpdateTime){
            this.posUpdateTime = Instant.parse(posUpdateTime);
        } else {
            this.posUpdateTime = Instant.ofEpochSecond(0);
        }
    }

    public String getBds40SeenTime() {
        return bds40SeenTime.toString();
    }

    public void setBds40SeenTime(String bds40SeenTime){
        if (null != bds40SeenTime){
            this.bds40SeenTime = Instant.parse(bds40SeenTime);
        } else {
            this.bds40SeenTime = Instant.ofEpochSecond(0);
        }
    }
}
