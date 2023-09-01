package com.hyunhii.dinnerForU.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Menu {

    @Id @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems = new ArrayList<>();

    private LocalDate date;
    private int stockQuantity;
    private int price;

    public Menu (LocalDate date, int stockQuantity, int price) {
        this.date = date;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        menuItem.addMenu(this);
    }


    public static Menu createMenu(LocalDate date, int cnt, int price , MenuItem... menuItems) {

        Menu menu = new Menu(date, cnt, price);

        for (MenuItem item : menuItems) {
            menu.addMenuItem(item);
        }

        return menu;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0) {
            throw new RuntimeException("need more stock");
        }

        this.stockQuantity = restStock;
    }
}
