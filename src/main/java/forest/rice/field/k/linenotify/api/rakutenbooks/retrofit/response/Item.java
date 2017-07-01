
package forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "page",
    "first",
    "last",
    "hits",
    "carrier",
    "pageCount",
    "Items",
    "GenreInformation"
})
public class Item {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("first")
    private Integer first;
    @JsonProperty("last")
    private Integer last;
    @JsonProperty("hits")
    private Integer hits;
    @JsonProperty("carrier")
    private Integer carrier;
    @JsonProperty("pageCount")
    private Integer pageCount;
    @JsonProperty("Items")
    private List<Item_> items = null;
    @JsonProperty("GenreInformation")
    private List<Object> genreInformation = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("first")
    public Integer getFirst() {
        return first;
    }

    @JsonProperty("first")
    public void setFirst(Integer first) {
        this.first = first;
    }

    @JsonProperty("last")
    public Integer getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setLast(Integer last) {
        this.last = last;
    }

    @JsonProperty("hits")
    public Integer getHits() {
        return hits;
    }

    @JsonProperty("hits")
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @JsonProperty("carrier")
    public Integer getCarrier() {
        return carrier;
    }

    @JsonProperty("carrier")
    public void setCarrier(Integer carrier) {
        this.carrier = carrier;
    }

    @JsonProperty("pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("pageCount")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("Items")
    public List<Item_> getItems() {
        return items;
    }

    @JsonProperty("Items")
    public void setItems(List<Item_> items) {
        this.items = items;
    }

    @JsonProperty("GenreInformation")
    public List<Object> getGenreInformation() {
        return genreInformation;
    }

    @JsonProperty("GenreInformation")
    public void setGenreInformation(List<Object> genreInformation) {
        this.genreInformation = genreInformation;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
