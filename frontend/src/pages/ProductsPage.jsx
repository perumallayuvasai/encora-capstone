import ProductCard from "../components/ProductCard";
import ProductFilters from "../components/ProductFilters";
import { useState } from "react";

export default function ProductsPage() {
    // Temporary fake data (replace with backend later)
    const [products] = useState([
        {
            id: 1,
            name: "Classic Beige Sweater",
            description: "Soft-touch knitwear with slim modern fit.",
            price: 49.99,
            size: "M",
            qty: 20,
            category: "women",
            img: "https://images.unsplash.com/photo-1544441893-675973e31985?w=800",
        },
        {
            id: 2,
            name: "Men’s Olive Jacket",
            description: "Durable outdoor jacket with minimalist aesthetic.",
            price: 89.99,
            size: "L",
            qty: 12,
            category: "men",
            img: "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=800",
        },
        {
            id: 3,
            name: "Leather Mini Bag",
            description: "Premium leather accessory for everyday elegance.",
            price: 59.99,
            size: "-",
            qty: 30,
            category: "accessories",
            img: "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=800",
        },
        {
            id: 4,
            name: "Men’s White Sneakers",
            description: "Breathable rubber sole with clean urban design.",
            price: 79.99,
            size: "42",
            qty: 10,
            category: "men",
            img: "https://images.unsplash.com/photo-1528701800489-20be0f6a9949?w=800",
        },
    ]);

    return (
        <div className="bg-base-200 min-h-screen py-10 px-6 lg:px-20 animate-fadeIn">
            {/* Title */}
            <h1 className="text-4xl font-bold mb-6  text-center tracking-wide">
                Explore Our Collection
            </h1>

            {/* Filters */}
            <ProductFilters />

            {/* Product Grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-10 mt-10">
                {products.map((p) => (
                    <ProductCard key={p.id} product={p} />
                ))}
            </div>
        </div>
    );
}
