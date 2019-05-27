package com.xbrain.loja.listener;

import org.springframework.stereotype.Component;

import com.xbrain.loja.domain.Product;
import com.xbrain.loja.repositories.ProductRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;


@Component
public class ProductMessageListener {

    private ProductRepository productRepository;

    private static final Logger log = LogManager.getLogger(ProductMessageListener.class);

    public ProductMessageListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link com.xbrain.loja.XbrainApplication} for more details
     * @param message
     */
    public void receiveMessage(Map<String, String> message) {
        log.info("Received <" + message + ">");
        Integer id = Integer.valueOf(message.get("id"));
        Product product = productRepository.findById(id).orElse(null);
        // product.setMessageReceived(true);
        // product.setMessageCount(product.getMessageCount() + 1);

        productRepository.save(product);
        log.info("Message processed...");
    }
}
