package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        jdbcTemplate.update("DELETE from supplier", namedParameters);
        jdbcTemplate.update("DELETE from product", namedParameters);
    }

    // Saves a product in the database
    public void save(Product product) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("productnumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());

        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)", namedParameters);

        // Save supplier
        if (product.getSupplier() != null) {
            Map<String, Object> supplierParams = new HashMap<>();
            supplierParams.put("name", product.getSupplier().getName());
            supplierParams.put("phone", product.getSupplier().getPhone());
            supplierParams.put("productnumber", product.getProductNumber());
            jdbcTemplate.update("INSERT INTO supplier VALUES ( :name, :phone, :productnumber)", supplierParams);
        }
    }

    // Return the product with this productNumber
    public Product findByProductNumber(int productNumber) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("productNumber", productNumber);

        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> {
                    int pNum = rs.getInt("productNumber");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Supplier supplier = getSupplierForProduct(pNum);
                    return new Product(pNum, name, price, supplier);
                });

        return product;
    }

    // Return the list with all products
    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> {
                    int pNum = rs.getInt("productNumber");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Supplier supplier = getSupplierForProduct(pNum);
                    return new Product(pNum, name, price, supplier);
                });
    }

    // Return the list with products with this name
    public List<Product> findByProductName(String name) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name", name);
        List<Product> products = jdbcTemplate.query(
                "SELECT * FROM product WHERE name = :name",
                namedParameters,
                (rs, rowNum) -> {
                    int pNum = rs.getInt("productNumber");
                    String pname = rs.getString("name");
                    double price = rs.getDouble("price");
                    Supplier supplier = getSupplierForProduct(pNum);
                    return new Product(pNum, pname, price, supplier);
                });
        return products;
    }

    // Remove the product with the given productNumber
    public boolean removeProduct(int productNumber) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", productNumber);
        int rowsAffected = jdbcTemplate.update("DELETE FROM product WHERE productNumber = :productNumber",
                namedParameters);
        return rowsAffected > 0;
    }

    private Supplier getSupplierForProduct(int productNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("productnumber", productNumber);
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM supplier WHERE productnumber = :productnumber",
                    params,
                    (rs, rowNum) -> new Supplier(rs.getString("name"), rs.getString("phone")));
        } catch (Exception e) {
            return null;
        }
    }
}
