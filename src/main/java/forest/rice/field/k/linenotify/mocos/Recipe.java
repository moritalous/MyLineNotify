package forest.rice.field.k.linenotify.mocos;

public class Recipe {

	private String url;
	private String name;
	private String imgSrc;
	private String time;

	public Recipe(String url, String name, String imgSrc, String time) {
		super();
		this.url = url;
		this.name = name;
		this.imgSrc = imgSrc;
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
