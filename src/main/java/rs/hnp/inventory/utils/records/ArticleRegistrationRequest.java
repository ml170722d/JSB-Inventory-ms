package rs.hnp.inventory.utils.records;

public record ArticleRegistrationRequest(
                String externalId,
                String name,
                String unit,
                Long amount,
                String description,
                Long buying_price,
                Long selling_price) {
}
