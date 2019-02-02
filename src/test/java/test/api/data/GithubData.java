package test.api.data;

public class GithubData {

	private String user;
	private String count;
	private String name;
	private String language;
	private String keyword;
	private String pages;
	private String langResultsCount;	
	private String sort;
	private String order;
	
    public GithubData(
    		String user, 
    		String count, 
    		String name, 
    		String language, 
    		String keyword, 
    		String pages,
    		String langResultsCount,
    		String sort, 
    		String order) {
        this.user = user;
        this.count = count;
        this.name = name;
        this.language = language;
        this.keyword = keyword;
        this.pages = pages;
        this.langResultsCount = langResultsCount;
        this.sort = sort;
        this.order = order;
    }
 	
	public String getUser() {
		return user;
	}

	public String getCount() {
		return count;
	}

	public String getName() {
		return name;
	}
	
	public String getLanguage() {
		return language;
	}	
	
	public String getKeyword() {
		return keyword;
	}
	
	public String getPages() {
		return pages;
	}
	
	public String getLangResultsCount() {
		return langResultsCount;
	}	
	
	public String getSort() {
		return sort;
	}
	
	public String getOrder() {
		return order;
	}	
	
	
	
	
	
}