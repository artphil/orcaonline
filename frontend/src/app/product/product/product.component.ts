import { ProductService } from './../product.service';
import { ProductModel } from '../product.model';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: ProductModel;
  segments = [
    { label: 'Segmento1', value: 1 },
    { label: 'Segmento2', value: 2 }
  ];
  isNewProduct = true;

  constructor(private service: ProductService) { }

  ngOnInit(): void {
    console.log(this.product);
    if (this.isNewProduct) {
      this.product = new ProductModel();
    }
  }

  saveProduct(form: NgForm): void {
    console.log(this.product);
    form.reset();
  }

  newProduct(form: NgForm): void {
    this.product = new ProductModel();
    form.reset();
  }

  removeProduct(form: NgForm): void {
    console.log(form);
    // form.reset();
  }
}
