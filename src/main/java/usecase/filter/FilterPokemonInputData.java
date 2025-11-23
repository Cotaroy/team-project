package usecase.filter;

public class FilterPokemonInputData {
    private final String filterCategory;
    private final String filterValue;
    public FilterPokemonInputData(String filterCategory, String filterValue) {
        this.filterCategory = filterCategory;
        this.filterValue = filterValue;
    }
    public String getFilterCategory() {
        return filterCategory;
    }
    public String getFilterValue() {
        return filterValue;
    }

}
