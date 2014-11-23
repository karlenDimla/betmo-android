package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoListItem {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("qty")
    private int qty;
    @SerializedName("uom")
    private String uom;

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
