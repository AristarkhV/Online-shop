package db;

import model.Order;
import model.Product;
import model.Role;
import model.User;

import java.util.ArrayList;

public class Storage {
    public static final ArrayList<User> users = new ArrayList();
    public static final ArrayList<Product> products = new ArrayList();
    public static final ArrayList<Order> orders = new ArrayList();
    public static final ArrayList<Role> roles = new ArrayList();
}
