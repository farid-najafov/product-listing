package productlisting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import productlisting.model.ProductResponseModel;
import productlisting.model.db.ProductCategory;
import productlisting.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs("target/snippets")
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getProducts() throws Exception {
        List<ProductResponseModel> list = new ArrayList<>();
        list.add(new ProductResponseModel("1", "a", "a", 1.0, 2,
                new ProductCategory(2L, "a"), new ArrayList<>(), new ArrayList<>(), "1"));
        given(productService.getProducts()).willReturn(list);
        mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk())
                        .andDo(document("home"));
    }
}