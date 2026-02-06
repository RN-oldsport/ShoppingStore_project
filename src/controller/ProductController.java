package controller;

import common.OperationResult;
import model.Product;
import services.ProductServices;
import view.*;

import javax.swing.*;
import java.util.List;

public class ProductController {

    private final MainFrame mainFrame;
    private final ProductServices productServices;
    private final ProductManagementPanel adminPanel;

    public ProductController(MainFrame mainFrame, ProductServices productServices) {
        this.mainFrame = mainFrame;
        this.productServices = productServices;
        this.adminPanel = mainFrame.getProductManagementPanel();

        // وصل کردن دکمه New Product
        connectNewProductButton();

        // بارگذاری اولیه محصولات
        loadProductsToPanel();
    }

    // ==========================
    // Load Products into Panel
    // ==========================
    private void loadProductsToPanel() {
        List<Product> products = productServices.getProducts();
        adminPanel.refreshProducts(products);

        // بعد از refresh دکمه‌های Modify و Delete هر کارت را وصل می‌کنیم
        connectCardButtons();
    }

    // ==========================
    // Connect Buttons of Product Cards
    // ==========================
    private void connectCardButtons() {
        List<ProductCardPanel> cards = adminPanel.getCards();

        for (ProductCardPanel card : cards) {

            // --------------------------
            // Delete Button
            // --------------------------
            card.getBtnDelete().addActionListener(e -> {
                int id = card.getProduct().getId();

                int confirm = JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to delete product with id " + id + " ?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    OperationResult<Void> result = productServices.deleteProduct(id);
                    JOptionPane.showMessageDialog(mainFrame, result.getMessage());
                    loadProductsToPanel();
                }
            });

            // --------------------------
            // Modify Button
            // --------------------------
            card.getBtnModify().addActionListener(e -> {
                Product product = card.getProduct();
                ModifyProductDialog dialog = new ModifyProductDialog(mainFrame, product);

                // وصل کردن دکمه‌های Update به سرویس
                connectModifyDialogButtons(dialog);

                dialog.setVisible(true);

                // بعد از بستن دیالوگ پنل ریفرش می‌شود
                loadProductsToPanel();
            });
        }
    }

    // ==========================
    // Connect ModifyProductDialog Buttons
    // ==========================
    private void connectModifyDialogButtons(ModifyProductDialog dialog) {
        Product product = dialog.getProduct();
        int id = product.getId();

        dialog.getBtnUpdateName().addActionListener(e -> {
            OperationResult<Void> result = productServices.modifyProductName(id, dialog.getTxtName().getText());
            JOptionPane.showMessageDialog(dialog, result.getMessage());
        });

        dialog.getBtnUpdateCategory().addActionListener(e -> {
            OperationResult<Void> result = productServices.modifyProductCategory(id, dialog.getTxtCategory().getText());
            JOptionPane.showMessageDialog(dialog, result.getMessage());
        });

        dialog.getBtnUpdatePrice().addActionListener(e -> {
            try {
                int price = Integer.parseInt(dialog.getTxtPrice().getText());
                OperationResult<Void> result = productServices.modifyProductPrice(id, price);
                JOptionPane.showMessageDialog(dialog, result.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid price value!");
            }
        });

        dialog.getBtnUpdateStock().addActionListener(e -> {
            try {
                int stock = Integer.parseInt(dialog.getTxtStock().getText());
                OperationResult<Void> result = productServices.modifyProductStockQuantity(id, stock);
                JOptionPane.showMessageDialog(dialog, result.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid stock value!");
            }
        });

        dialog.getBtnUpdateDescription().addActionListener(e -> {
            OperationResult<Void> result = productServices.modifyProductDescription(id, dialog.getTxtDescription().getText());
            JOptionPane.showMessageDialog(dialog, result.getMessage());
        });

        dialog.getBtnUpdateImage().addActionListener(e -> {
            OperationResult<Void> result = productServices.modifyProductImage(id, dialog.getTxtImage().getText());
            JOptionPane.showMessageDialog(dialog, result.getMessage());
        });

        dialog.getBtnClose().addActionListener(e -> dialog.dispose());
    }

    // ==========================
    // Connect New Product Button in Panel
    // ==========================
    private void connectNewProductButton() {
        JButton btnNewProduct = adminPanel.getBtnNewProduct(); // getter باید در پنل وجود داشته باشد
        btnNewProduct.addActionListener(e -> {

            NewProductDialog dialog = new NewProductDialog(mainFrame);

            dialog.getBtnAdd().addActionListener(ev -> {
                try {
                    String name = dialog.getTxtName().getText();
                    String category = dialog.getTxtCategory().getText();
                    int price = Integer.parseInt(dialog.getTxtPrice().getText());
                    int stock = Integer.parseInt(dialog.getTxtStock().getText());
                    String desc = dialog.getTxtDescription().getText();
                    String image = dialog.getTxtImage().getText();

                    OperationResult<Void> result = productServices.newProduct(
                            name, category, price, stock, desc, image
                    );

                    JOptionPane.showMessageDialog(dialog, result.getMessage());

                    dialog.dispose();
                    loadProductsToPanel();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Price or Stock value is invalid!");
                }
            });

            dialog.setVisible(true);
        });
    }
}
