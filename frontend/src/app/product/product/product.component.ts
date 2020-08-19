import { ProductService } from './../product.service';
import { ProductModel } from '../product.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: ProductModel;
  isNewProduct = true;
  constructor(private service: ProductService) { }

  ngOnInit(): void {
    console.log(this.product);
    if (this.isNewProduct) {
      this.product = new ProductModel();
    }
  }

  saveProduct(): void {
    console.log(this.product);
  }

  newProduct(): void {
    this.product = new ProductModel();
  }

  removeProduct(): void {

  }
}
