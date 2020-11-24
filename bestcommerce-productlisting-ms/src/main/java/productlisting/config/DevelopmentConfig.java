package productlisting.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import productlisting.model.db.DeliveryOption;
import productlisting.model.db.PaymentOption;
import productlisting.model.db.Product;
import productlisting.model.db.ProductCategory;
import productlisting.repo.DeliveryOptionRepo;
import productlisting.repo.PaymentOptionRepo;
import productlisting.repo.ProductCategoryRepo;
import productlisting.repo.ProductRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Configuration
@Profile("!prod")
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner loader(ProductCategoryRepo productCategoryRepo,
                                    PaymentOptionRepo paymentOptionRepo,
                                    DeliveryOptionRepo deliveryOptionRepo,
                                    ProductRepo productRepo) {
        return args -> {
            // persisted product category
            ProductCategory electronics = new ProductCategory(1L, "Electronics");
            ProductCategory fashion = new ProductCategory(2L, "Fashion");
            ProductCategory food = new ProductCategory(3L, "Food");

            productCategoryRepo.save(electronics);
            productCategoryRepo.save(fashion);
            productCategoryRepo.save(food);

            // persisted payment options
            PaymentOption direct = new PaymentOption(1L, "direct");
            PaymentOption installments = new PaymentOption(2L, "installments");

            paymentOptionRepo.save(direct);
            paymentOptionRepo.save(installments);

            // persisted delivery options
            DeliveryOption self_pick_up = new DeliveryOption(1L, "self_pick_up");
            DeliveryOption home_delivery = new DeliveryOption(2L, "home_delivery");

            deliveryOptionRepo.save(self_pick_up);
            deliveryOptionRepo.save(home_delivery);

            // persisted sample products
            Product router = new Product();
            router.setProdId(UUID.randomUUID().toString());
            router.setName("Router");
            router.setDescription("Wifi router/wireless router");
            router.setUnitPrice(119.99);
            router.setInventory(55);
            router.setCategory(productCategoryRepo.findByName("Electronics"));
            router.setPaymentOptions(Arrays.asList(paymentOptionRepo.findByOption("direct"), paymentOptionRepo.findByOption("installments")));
            router.setDeliveryOptions(Arrays.asList(deliveryOptionRepo.findByOption("self_pick_up"), deliveryOptionRepo.findByOption("home_delivery")));
            router.setMerchantId("1");
            productRepo.save(router);

            Product headset = new Product();
            headset.setProdId(UUID.randomUUID().toString());
            headset.setName("Headset");
            headset.setDescription("VR Gaming Headset");
            headset.setUnitPrice(79.70);
            headset.setInventory(120);
            headset.setCategory(productCategoryRepo.findByName("Electronics"));
            headset.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            headset.setDeliveryOptions(Arrays.asList(deliveryOptionRepo.findByOption("self_pick_up"), deliveryOptionRepo.findByOption("home_delivery")));
            headset.setMerchantId("1");
            productRepo.save(headset);

            Product keyboard = new Product();
            keyboard.setProdId(UUID.randomUUID().toString());
            keyboard.setName("Keyboard");
            keyboard.setDescription("Cordless keyboard");
            keyboard.setUnitPrice(24.90);
            keyboard.setInventory(45);
            keyboard.setCategory(productCategoryRepo.findByName("Electronics"));
            keyboard.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            keyboard.setDeliveryOptions(Collections.singletonList(deliveryOptionRepo.findByOption("self_pick_up")));
            keyboard.setMerchantId("1");
            productRepo.save(keyboard);

            Product watch = new Product();
            watch.setProdId(UUID.randomUUID().toString());
            watch.setName("Watch");
            watch.setDescription("Men's wrist watch");
            watch.setUnitPrice(79.00);
            watch.setInventory(12);
            watch.setCategory(productCategoryRepo.findByName("Fashion"));
            watch.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            watch.setDeliveryOptions(Collections.singletonList(deliveryOptionRepo.findByOption("self_pick_up")));
            watch.setMerchantId("2");
            productRepo.save(watch);

            Product coat = new Product();
            coat.setProdId(UUID.randomUUID().toString());
            coat.setName("Coat");
            coat.setDescription("Women's trench coat");
            coat.setUnitPrice(359.00);
            coat.setInventory(4);
            coat.setCategory(productCategoryRepo.findByName("Fashion"));
            coat.setPaymentOptions(Arrays.asList(paymentOptionRepo.findByOption("direct"), paymentOptionRepo.findByOption("installments")));
            coat.setDeliveryOptions(Arrays.asList(deliveryOptionRepo.findByOption("self_pick_up"), deliveryOptionRepo.findByOption("home_delivery")));
            coat.setMerchantId("2");
            productRepo.save(coat);

            Product sneaker = new Product();
            sneaker.setProdId(UUID.randomUUID().toString());
            sneaker.setName("Sneaker");
            sneaker.setDescription("Casual footwear");
            sneaker.setUnitPrice(45.90);
            sneaker.setInventory(25);
            sneaker.setCategory(productCategoryRepo.findByName("Fashion"));
            sneaker.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            sneaker.setDeliveryOptions(Collections.singletonList(deliveryOptionRepo.findByOption("self_pick_up")));
            sneaker.setMerchantId("2");
            productRepo.save(sneaker);

            Product chocolate = new Product();
            chocolate.setProdId(UUID.randomUUID().toString());
            chocolate.setName("Chocolate");
            chocolate.setDescription("Belgian dark chocolate");
            chocolate.setUnitPrice(5.90);
            chocolate.setInventory(110);
            chocolate.setCategory(productCategoryRepo.findByName("Food"));
            chocolate.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            chocolate.setDeliveryOptions(Collections.singletonList(deliveryOptionRepo.findByOption("self_pick_up")));
            chocolate.setMerchantId("3");
            productRepo.save(chocolate);

            Product cake = new Product();
            cake.setProdId(UUID.randomUUID().toString());
            cake.setName("Cake");
            cake.setDescription("Red Velvet cake");
            cake.setUnitPrice(24.5);
            cake.setInventory(3);
            cake.setCategory(productCategoryRepo.findByName("Food"));
            cake.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            cake.setDeliveryOptions(Arrays.asList(deliveryOptionRepo.findByOption("self_pick_up"), deliveryOptionRepo.findByOption("home_delivery")));
            cake.setMerchantId("3");
            productRepo.save(cake);

            Product croissant = new Product();
            croissant.setProdId(UUID.randomUUID().toString());
            croissant.setName("Croissant");
            croissant.setDescription("French butter croissant");
            croissant.setUnitPrice(2.40);
            croissant.setInventory(72);
            croissant.setCategory(productCategoryRepo.findByName("Food"));
            croissant.setPaymentOptions(Collections.singletonList(paymentOptionRepo.findByOption("direct")));
            croissant.setDeliveryOptions(Collections.singletonList(deliveryOptionRepo.findByOption("self_pick_up")));
            croissant.setMerchantId("3");
            productRepo.save(croissant);
        };
    }
}
