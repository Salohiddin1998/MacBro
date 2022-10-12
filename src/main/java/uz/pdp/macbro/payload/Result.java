package uz.pdp.macbro.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String massage;

    private boolean success;

    private Object object;

    public Result(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }

    public Result(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }

    public Result(boolean success) {
        this.success = success;
    }
}
