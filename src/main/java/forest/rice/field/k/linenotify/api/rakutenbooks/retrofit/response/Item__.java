
package forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "author",
    "artistName",
    "publisherName",
    "label",
    "isbn",
    "jan",
    "hardware",
    "os",
    "itemCaption",
    "salesDate",
    "itemPrice",
    "listPrice",
    "discountRate",
    "discountPrice",
    "itemUrl",
    "affiliateUrl",
    "smallImageUrl",
    "mediumImageUrl",
    "largeImageUrl",
    "chirayomiUrl",
    "availability",
    "postageFlag",
    "limitedFlag",
    "reviewCount",
    "reviewAverage",
    "booksGenreId"
})
public class Item__ {

    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("artistName")
    private String artistName;
    @JsonProperty("publisherName")
    private String publisherName;
    @JsonProperty("label")
    private String label;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("jan")
    private String jan;
    @JsonProperty("hardware")
    private String hardware;
    @JsonProperty("os")
    private String os;
    @JsonProperty("itemCaption")
    private String itemCaption;
    @JsonProperty("salesDate")
    private String salesDate;
    @JsonProperty("itemPrice")
    private Integer itemPrice;
    @JsonProperty("listPrice")
    private Integer listPrice;
    @JsonProperty("discountRate")
    private Integer discountRate;
    @JsonProperty("discountPrice")
    private Integer discountPrice;
    @JsonProperty("itemUrl")
    private String itemUrl;
    @JsonProperty("affiliateUrl")
    private String affiliateUrl;
    @JsonProperty("smallImageUrl")
    private String smallImageUrl;
    @JsonProperty("mediumImageUrl")
    private String mediumImageUrl;
    @JsonProperty("largeImageUrl")
    private String largeImageUrl;
    @JsonProperty("chirayomiUrl")
    private String chirayomiUrl;
    @JsonProperty("availability")
    private String availability;
    @JsonProperty("postageFlag")
    private Integer postageFlag;
    @JsonProperty("limitedFlag")
    private Integer limitedFlag;
    @JsonProperty("reviewCount")
    private Integer reviewCount;
    @JsonProperty("reviewAverage")
    private String reviewAverage;
    @JsonProperty("booksGenreId")
    private String booksGenreId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("artistName")
    public String getArtistName() {
        return artistName;
    }

    @JsonProperty("artistName")
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @JsonProperty("publisherName")
    public String getPublisherName() {
        return publisherName;
    }

    @JsonProperty("publisherName")
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonProperty("jan")
    public String getJan() {
        return jan;
    }

    @JsonProperty("jan")
    public void setJan(String jan) {
        this.jan = jan;
    }

    @JsonProperty("hardware")
    public String getHardware() {
        return hardware;
    }

    @JsonProperty("hardware")
    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    @JsonProperty("os")
    public String getOs() {
        return os;
    }

    @JsonProperty("os")
    public void setOs(String os) {
        this.os = os;
    }

    @JsonProperty("itemCaption")
    public String getItemCaption() {
        return itemCaption;
    }

    @JsonProperty("itemCaption")
    public void setItemCaption(String itemCaption) {
        this.itemCaption = itemCaption;
    }

    @JsonProperty("salesDate")
    public String getSalesDate() {
        return salesDate;
    }

    @JsonProperty("salesDate")
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    @JsonProperty("itemPrice")
    public Integer getItemPrice() {
        return itemPrice;
    }

    @JsonProperty("itemPrice")
    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    @JsonProperty("listPrice")
    public Integer getListPrice() {
        return listPrice;
    }

    @JsonProperty("listPrice")
    public void setListPrice(Integer listPrice) {
        this.listPrice = listPrice;
    }

    @JsonProperty("discountRate")
    public Integer getDiscountRate() {
        return discountRate;
    }

    @JsonProperty("discountRate")
    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    @JsonProperty("discountPrice")
    public Integer getDiscountPrice() {
        return discountPrice;
    }

    @JsonProperty("discountPrice")
    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    @JsonProperty("itemUrl")
    public String getItemUrl() {
        return itemUrl;
    }

    @JsonProperty("itemUrl")
    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    @JsonProperty("affiliateUrl")
    public String getAffiliateUrl() {
        return affiliateUrl;
    }

    @JsonProperty("affiliateUrl")
    public void setAffiliateUrl(String affiliateUrl) {
        this.affiliateUrl = affiliateUrl;
    }

    @JsonProperty("smallImageUrl")
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    @JsonProperty("smallImageUrl")
    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    @JsonProperty("mediumImageUrl")
    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    @JsonProperty("mediumImageUrl")
    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    @JsonProperty("largeImageUrl")
    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    @JsonProperty("largeImageUrl")
    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    @JsonProperty("chirayomiUrl")
    public String getChirayomiUrl() {
        return chirayomiUrl;
    }

    @JsonProperty("chirayomiUrl")
    public void setChirayomiUrl(String chirayomiUrl) {
        this.chirayomiUrl = chirayomiUrl;
    }

    @JsonProperty("availability")
    public String getAvailability() {
        return availability;
    }

    @JsonProperty("availability")
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @JsonProperty("postageFlag")
    public Integer getPostageFlag() {
        return postageFlag;
    }

    @JsonProperty("postageFlag")
    public void setPostageFlag(Integer postageFlag) {
        this.postageFlag = postageFlag;
    }

    @JsonProperty("limitedFlag")
    public Integer getLimitedFlag() {
        return limitedFlag;
    }

    @JsonProperty("limitedFlag")
    public void setLimitedFlag(Integer limitedFlag) {
        this.limitedFlag = limitedFlag;
    }

    @JsonProperty("reviewCount")
    public Integer getReviewCount() {
        return reviewCount;
    }

    @JsonProperty("reviewCount")
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @JsonProperty("reviewAverage")
    public String getReviewAverage() {
        return reviewAverage;
    }

    @JsonProperty("reviewAverage")
    public void setReviewAverage(String reviewAverage) {
        this.reviewAverage = reviewAverage;
    }

    @JsonProperty("booksGenreId")
    public String getBooksGenreId() {
        return booksGenreId;
    }

    @JsonProperty("booksGenreId")
    public void setBooksGenreId(String booksGenreId) {
        this.booksGenreId = booksGenreId;
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
